package com.accenture.smsf.masterdata.starter.controller;

import com.accenture.smsf.masterdata.core.entity.Spt;
import com.accenture.smsf.masterdata.dto.SptDto;
import com.accenture.smsf.masterdata.service.ProcessService;
import com.accenture.smsf.masterdata.service.SptService;
import com.accenture.smsf.masterdata.service.StepService;
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
@RequestMapping("/masterdata/spt")
public class SptController {

    @Autowired
    SptService sptService;

    @Autowired
    StepService stepService;

    @Autowired
    ProcessService processService;

    @PostMapping("/save")
    @Permission(values= {Permissions.MASTERDATA_SPT_MANAGE})
    public int sptSave(@RequestBody Spt spt) {
        return sptService.save(spt);
    }

    @PostMapping("/batch-save")
    @Permission(values= {Permissions.MASTERDATA_SPT_MANAGE})
    public int sptBatchSave(@RequestBody List<Spt> spts) {
        return sptService.save(spts);
    }

    @DeleteMapping("/delete")
    @Permission(values= {Permissions.MASTERDATA_SPT_MANAGE})
    public int sptDelete(@RequestParam("id") String id) {
	    return sptService.delete(id);
    }

    @DeleteMapping("/batch-delete")
    @Permission(values= {Permissions.MASTERDATA_SPT_MANAGE})
    public int sptBatchDelete(@RequestParam("ids") String ids) {
        return sptService.batchDelete("\"" + String.join("\",\"", ids.split(",")) + "\"");
    }

    @PutMapping("/update")
    @Permission(values= {Permissions.MASTERDATA_SPT_MANAGE})
    public int sptUpdate(@RequestBody Spt spt) {
	    return sptService.update(spt);
    }

    @GetMapping("/find")
    @Permission(values= {Permissions.MASTERDATA_SPT_VIEW})
    public SptDto sptFind(@RequestParam("id") String id) {
        return transformDto(sptService.findById(id));
    }

    @GetMapping("/list-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_SPT_VIEW})
    public PageInfo<SptDto> sptListPaged(@PathVariable(value="page-no") int
    pageNumber,
                                            @PathVariable(value="page-size") int pageSize) {
        List<Spt> list = sptService.listByTenantId(pageNumber, pageSize);
        Page<SptDto> page = transformList(list);
        Map<String, String> idNameMapping = processService.getIdNameMapping();
        page.forEach(dto -> dto.setProcessName(idNameMapping.get(dto.getProcessId())));
        return new PageInfo<>(page);
    }

    @GetMapping("/list")
    @Permission(values= {Permissions.MASTERDATA_SPT_VIEW})
    public List<SptDto> sptList() {
        List<Spt> list = sptService.listByTenantId();
        Page<SptDto> page = transformList(list);
        Map<String, String> idNameMapping = processService.getIdNameMapping();
        page.forEach(dto -> dto.setProcessName(idNameMapping.get(dto.getProcessId())));
        return page;
    }

    @PostMapping("/find-by-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_SPT_VIEW})
    public PageInfo<SptDto> sptFindByPaged(@RequestBody Spt
    spt, @PathVariable("page-no") int pageNumber, @PathVariable("page-size") int pageSize) {
        List<Spt> list = sptService.findBy(spt, pageNumber, pageSize);
        Page<SptDto> pagedSpt = new Page<>();
        BeanUtils.copyProperties(list,pagedSpt);
        Map<String,String> processNameMapping = processService.getIdNameMapping();
        Map<String,String> stepNameMapping = stepService.getIdNameMapping();

        list.forEach(entity->{
            SptDto dto = new SptDto();
            BeanUtils.copyProperties(entity,dto);
            dto.setProcessName(processNameMapping.get(entity.getProcessId()));
            dto.setStepName(stepNameMapping.get(entity.getStepId()));
            pagedSpt.add(dto);
        });
        return new PageInfo<>(pagedSpt);
    }

    @PostMapping("/find-by")
    @Permission(values= {Permissions.MASTERDATA_SPT_VIEW})
    public List<SptDto> sptFindBy(@RequestBody Spt
    spt) {
        List<Spt> list = sptService.findBy(spt);
        Page<SptDto> page = transformList(list);
        Map<String, String> idNameMapping = processService.getIdNameMapping();
        page.forEach(dto -> dto.setProcessName(idNameMapping.get(dto.getProcessId())));
        return page;
    }

    @GetMapping("/find-one")
    @Permission(values= {Permissions.MASTERDATA_SPT_VIEW})
    public SptDto sptFindOne(@RequestParam("fieldName") String fieldName,
                          @RequestParam("value") String
    value) {
        return transformDto(sptService.findBy(fieldName, value));
    }

    @PostMapping("/find-by/{columns}")
    @Permission(values= {Permissions.MASTERDATA_SPT_VIEW})
    public List<SptDto> sptFindByColumnsPaged(@RequestBody Spt spt,
                                           @PathVariable("columns") String columns) {
        List<Spt> list = sptService.findByColumns(spt,columns);
        Page<SptDto> page = transformList(list);
        Map<String, String> idNameMapping = processService.getIdNameMapping();
        page.forEach(dto -> dto.setProcessName(idNameMapping.get(dto.getProcessId())));
        return page;
    }
    private Page<SptDto> transformList(List<Spt> list){
        Page<SptDto> pagedClientServiceLevel = new Page<>();
        BeanUtils.copyProperties(list, pagedClientServiceLevel);
        list.forEach(entity->{
            SptDto dto = new SptDto();
            BeanUtils.copyProperties(entity, dto);
            pagedClientServiceLevel.add(dto);
        });
        return pagedClientServiceLevel;
    }
    private SptDto transformDto(Spt entity) {
        if (entity == null) {
            return null;
        }
        SptDto dto = new SptDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}