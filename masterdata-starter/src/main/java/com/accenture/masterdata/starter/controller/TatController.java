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
    public Tat tatFind(@RequestParam("id") String id) {
        return tatService.findById(id);
    }

    @GetMapping("/list-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_TAT_VIEW})
    public PageInfo<Tat> tatListPaged(@PathVariable(value="page-no") int
    pageNumber,
                                            @PathVariable(value="page-size") int pageSize) {
        List<Tat> list = tatService.list(pageNumber, pageSize);
        return new PageInfo<>(list);
    }

    @GetMapping("/list")
    @Permission(values= {Permissions.MASTERDATA_TAT_VIEW})
    public List<Tat> tatList() {
        return tatService.list();
    }

    @PostMapping("/find-by-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_TAT_VIEW})
    public PageInfo<TatDto> tatFindByPaged(@RequestBody Tat
    tat, @PathVariable("page-no") int pageNumber, @PathVariable("page-size") int pageSize) {
        List<Tat> list = tatService.findBy(tat, pageNumber, pageSize);
        Page<TatDto> pagedTat = new Page<>();
        BeanUtils.copyProperties(list, pagedTat);
        Map<String, String> idNameMapping = processService.getIdNameMapping();
        list.forEach(entity->{
            TatDto dto = new TatDto();
            BeanUtils.copyProperties(entity, dto);
            dto.setProcessName(idNameMapping.get(entity.getProcessId()));
            pagedTat.add(dto);
        });
        return new PageInfo<>(pagedTat);
    }

    @PostMapping("/find-by")
    @Permission(values= {Permissions.MASTERDATA_TAT_VIEW})
    public List<Tat> tatFindBy(@RequestBody Tat
    tat) {
        return tatService.findBy(tat);
    }

    @GetMapping("/find-one")
    @Permission(values= {Permissions.MASTERDATA_TAT_VIEW})
    public Tat tatFindOne(@RequestParam("fieldName") String fieldName,
                          @RequestParam("value") String
    value) {
        return tatService.findBy(fieldName, value);
    }

    @PostMapping("/find-by/{columns}")
    @Permission(values= {Permissions.MASTERDATA_TAT_VIEW})
    public List<Tat> tatFindByColumnsPaged(@RequestBody Tat tat,
                                           @PathVariable("columns") String columns) {
        return tatService.findByColumns(tat, columns);
    }
}
