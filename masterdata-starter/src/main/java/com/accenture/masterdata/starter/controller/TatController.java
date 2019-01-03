package com.accenture.masterdata.starter.controller;

import com.accenture.masterdata.core.entity.Tat;
import com.accenture.masterdata.dto.TatDto;
import com.accenture.masterdata.service.ProcessService;
import com.accenture.masterdata.service.TatService;
import com.accenture.masterdata.starter.Permissions;
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
@RequestMapping("/masterdata/tat")
public class TatController {

    @Autowired
    TatService tatService;
    @Autowired
    ProcessService processService;

    @PostMapping("/save")
    @Permission(values= {Permissions.MASTERDATA_TAT_MANAGE})
    public int tatSave(@RequestBody Tat tat) {
        return tatService.save(tat);
    }

    @PostMapping("/batch-save")
    @Permission(values= {Permissions.MASTERDATA_TAT_MANAGE})
    public int tatBatchSave(@RequestBody List<Tat> tats) {
        return tatService.save(tats);
    }

    @DeleteMapping("/delete")
    @Permission(values= {Permissions.MASTERDATA_TAT_MANAGE})
    public int tatDelete(@RequestParam("id") String id) {
	    return tatService.delete(id);
    }

    @DeleteMapping("/batch-delete")
    @Permission(values= {Permissions.MASTERDATA_TAT_MANAGE})
    public int tatBatchDelete(@RequestParam("ids") String ids) {
        return tatService.batchDelete("\"" + String.join("\",\"", ids.split(",")) + "\"");
    }

    @PutMapping("/update")
    @Permission(values= {Permissions.MASTERDATA_TAT_MANAGE})
    public int tatUpdate(@RequestBody Tat tat) {
	    return tatService.update(tat);
    }

    @GetMapping("/find")
    @Permission(values= {Permissions.MASTERDATA_TAT_VIEW})
    public TatDto tatFind(@RequestParam("id") String id) {
        return transformDto(tatService.findById(id));
    }

    @GetMapping("/list")
    @Permission(values= {Permissions.MASTERDATA_TAT_VIEW})
    public List<TatDto> tatList() {
        List<Tat> list = tatService.list();
        Page<TatDto> page = transformList(list);
        Map<String, String> idNameMapping = processService.getIdNameMapping();
        page.forEach(dto -> dto.setProcessName(idNameMapping.get(dto.getProcessId())));
        return page;
    }

    @PostMapping("/find-by-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_TAT_VIEW})
    public PageInfo<TatDto> tatFindByPaged(@RequestBody Tat
    tat, @PathVariable("page-no") int pageNumber, @PathVariable("page-size") int pageSize) {
        List<Tat> list = tatService.findBy(tat, pageNumber, pageSize);
        Page<TatDto> page = transformList(list);
        Map<String, String> idNameMapping = processService.getIdNameMapping();
        page.forEach(dto -> dto.setProcessName(idNameMapping.get(dto.getProcessId())));
        return new PageInfo<>(page);
    }

    @PostMapping("/find-by")
    @Permission(values= {Permissions.MASTERDATA_TAT_VIEW})
    public List<TatDto> tatFindBy(@RequestBody Tat
    tat) {
        List<Tat> list = tatService.findBy(tat);
        Page<TatDto> page = transformList(list);
        Map<String, String> idNameMapping = processService.getIdNameMapping();
        page.forEach(dto -> dto.setProcessName(idNameMapping.get(dto.getProcessId())));
        return page;
    }

    @GetMapping("/find-one")
    @Permission(values= {Permissions.MASTERDATA_TAT_VIEW})
    public TatDto tatFindOne(@RequestParam("fieldName") String fieldName,
                          @RequestParam("value") String
    value) {
        return transformDto(tatService.findById(fieldName));
    }

    @PostMapping("/find-by/{columns}")
    @Permission(values= {Permissions.MASTERDATA_TAT_VIEW})
    public List<TatDto> tatFindByColumnsPaged(@RequestBody Tat tat,
                                           @PathVariable("columns") String columns) {
        List<Tat> list = tatService.findByColumns(tat,columns);
        Page<TatDto> page = transformList(list);
        Map<String, String> idNameMapping = processService.getIdNameMapping();
        page.forEach(dto -> dto.setProcessName(idNameMapping.get(dto.getProcessId())));
        return page;
    }
    private Page<TatDto> transformList(List<Tat> list){
        Page<TatDto> pagedClientServiceLevel = new Page<>();
        BeanUtils.copyProperties(list, pagedClientServiceLevel);
        list.forEach(entity->{
            TatDto dto = new TatDto();
            BeanUtils.copyProperties(entity, dto);
            pagedClientServiceLevel.add(dto);
        });
        return pagedClientServiceLevel;
    }
    private TatDto transformDto(Tat entity) {
        TatDto dto = new TatDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
