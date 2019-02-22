package com.accenture.smsf.masterdata.starter.controller;

import com.accenture.smsf.masterdata.core.entity.CutoffTime;
import com.accenture.smsf.masterdata.dto.CutoffTimeDto;
import com.accenture.smsf.masterdata.service.CutoffTimeService;
import com.accenture.smsf.masterdata.service.ProcessService;
import com.accenture.smsf.masterdata.starter.Permissions;
import com.accenture.smsf.authority.permission.loader.annotation.Permission;
import com.accenture.smsf.framework.starter.web.core.annotation.RestController;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 *
 * @author s.c.gao
 */
@RestController
@RequestMapping("/masterdata/cutoffTime")
public class CutoffTimeController {

    @Autowired
    CutoffTimeService cutoffTimeService;

    @Autowired
    ProcessService processService;

    @PostMapping("/save")
    @Permission(values= {Permissions.MASTERDATA_CUTOFF_TIME_MANAGE})
    public int cutoffTimeSave(@RequestBody CutoffTime cutoffTime) {
        return cutoffTimeService.save(cutoffTime);
    }

    @PostMapping("/batch-save")
    @Permission(values= {Permissions.MASTERDATA_CUTOFF_TIME_MANAGE})
    public int cutoffTimeBatchSave(@RequestBody List<CutoffTime> cutoffTimes) {
        return cutoffTimeService.save(cutoffTimes);
    }

    @DeleteMapping("/delete")
    @Permission(values= {Permissions.MASTERDATA_CUTOFF_TIME_MANAGE})
    public int cutoffTimeDelete(@RequestParam("id") String id) {
	    return cutoffTimeService.delete(id);
    }

    @DeleteMapping("/batch-delete")
    @Permission(values= {Permissions.MASTERDATA_CUTOFF_TIME_MANAGE})
    public int cutoffTimeBatchDelete(@RequestParam("ids") String ids) {
        return cutoffTimeService.batchDelete("\"" + String.join("\",\"", ids.split(",")) + "\"");
    }

    @PutMapping("/update")
    @Permission(values= {Permissions.MASTERDATA_CUTOFF_TIME_MANAGE})
    public int cutoffTimeUpdate(@RequestBody CutoffTime cutoffTime) {
	    return cutoffTimeService.update(cutoffTime);
    }

    @GetMapping("/find")
    @Permission(values= {Permissions.MASTERDATA_CUTOFF_TIME_VIEW})
    public CutoffTimeDto cutoffTimeFind(@RequestParam("id") String id) {
        return transformDto(cutoffTimeService.findById(id));
    }

    @GetMapping("/list-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_CUTOFF_TIME_VIEW})
    public PageInfo<CutoffTimeDto> cutoffTimeListPaged(@PathVariable(value="page-no") int
    pageNumber,
                                                          @PathVariable(value="page-size") int pageSize) {
        List<CutoffTime> list = cutoffTimeService.listByTenantId(pageNumber, pageSize);
        Page<CutoffTimeDto> page = transformList(list);
        return new PageInfo<>(page);
    }

    @GetMapping("/list")
    @Permission(values= {Permissions.MASTERDATA_CUTOFF_TIME_VIEW})
    public List<CutoffTimeDto> cutoffTimeList() {
        List<CutoffTime> list = cutoffTimeService.listByTenantId();
        Page<CutoffTimeDto> page = transformList(list);
        return page;
    }

    @PostMapping("/find-by-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_CUTOFF_TIME_VIEW})
    public PageInfo<CutoffTimeDto> cutoffTimeFindByPaged(@RequestBody CutoffTime
    cutoffTime, @PathVariable("page-no") int pageNumber, @PathVariable("page-size") int pageSize) {
        List<CutoffTime> list = cutoffTimeService.findBy(cutoffTime, pageNumber, pageSize);
        Page<CutoffTimeDto> pagedcutoffTime = transformList(list);
        Map<String , String> idNameMapping = processService.getIdNameMapping();
        pagedcutoffTime.forEach(dto -> dto.setProcessName(idNameMapping.get(dto.getProcessId())));
        return new PageInfo<>(pagedcutoffTime);
    }

    @PostMapping("/find-by")
    @Permission(values= {Permissions.MASTERDATA_CUTOFF_TIME_VIEW})
    public List<CutoffTimeDto> cutoffTimeFindBy(@RequestBody CutoffTime
    cutoffTime) {
        List<CutoffTime> list = cutoffTimeService.findBy(cutoffTime);
        Page<CutoffTimeDto> page = transformList(list);
        return page;
    }

    @GetMapping("/find-one")
    @Permission(values= {Permissions.MASTERDATA_CUTOFF_TIME_VIEW})
    public CutoffTimeDto cutoffTimeFindOne(@RequestParam("fieldName") String fieldName,
                                        @RequestParam("value") String
    value) {
        return transformDto(cutoffTimeService.findBy(fieldName, value));
    }

    @PostMapping("/find-by/{columns}")
    @Permission(values= {Permissions.MASTERDATA_CUTOFF_TIME_VIEW})
    public List<CutoffTime> cutoffTimeFindByColumnsPaged(@RequestBody CutoffTime cutoffTime,
                                                         @PathVariable("columns") String columns) {
        return cutoffTimeService.findByColumns(cutoffTime, columns);
    }
    private Page<CutoffTimeDto> transformList(List<CutoffTime> list){
        Page<CutoffTimeDto> pagedCutoffTime = new Page<>();
        BeanUtils.copyProperties(list, pagedCutoffTime);
        list.forEach(entity->{
            CutoffTimeDto dto = new CutoffTimeDto();
            BeanUtils.copyProperties(entity, dto);
            pagedCutoffTime.add(dto);
        });
        return pagedCutoffTime;
    }
    private CutoffTimeDto transformDto(CutoffTime entity) {
        if (entity == null) {
            return null;
        }
        CutoffTimeDto dto = new CutoffTimeDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
