package com.accenture.masterdata.starter.controller;

import com.accenture.masterdata.core.entity.Process;
import com.accenture.masterdata.dto.ProcessDto;
import com.accenture.masterdata.service.ProcessService;
import com.accenture.masterdata.service.CompanyServiceLevelService;
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
@RequestMapping("/masterdata/process")
public class ProcessController {

    @Autowired
    ProcessService processService;

    @Autowired
    CompanyServiceLevelService companyServiceLevelService;

    @PostMapping("/save")
    @Permission(values= {Permissions.MASTERDATA_PROCESS_MANAGE})
    public int processSave(@RequestBody Process process) {
        return processService.save(process);
    }

    @PostMapping("/batch-save")
    @Permission(values= {Permissions.MASTERDATA_PROCESS_MANAGE})
    public int processBatchSave(@RequestBody List<Process> processs) {
        return processService.save(processs);
    }

    @DeleteMapping("/delete")
    @Permission(values= {Permissions.MASTERDATA_PROCESS_MANAGE})
    public int processDelete(@RequestParam("id") String id) {
	    return processService.delete(id);
    }

    @DeleteMapping("/batch-delete")
    @Permission(values= {Permissions.MASTERDATA_PROCESS_MANAGE})
    public int processBatchDelete(@RequestParam("ids") String ids) {
        return processService.batchDelete("\"" + String.join("\",\"", ids.split(",")) + "\"");
    }

    @PutMapping("/update")
    @Permission(values= {Permissions.MASTERDATA_PROCESS_MANAGE})
    public int processUpdate(@RequestBody Process process) {
	    return processService.update(process);
    }

    @GetMapping("/find")
    @Permission(values= {Permissions.MASTERDATA_PROCESS_VIEW})
    public Process processFind(@RequestParam("id") String id) {
        return processService.findById(id);
    }

    @GetMapping("/list-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_PROCESS_VIEW})
    public PageInfo<List<Process>> processListPaged(@PathVariable(value="page-no") int
    pageNumber,
                                                    @PathVariable(value="page-size") int pageSize) {
        List<Process> list = processService.list(pageNumber, pageSize);
        return new PageInfo(list);
    }

    @GetMapping("/list")
    @Permission(values= {Permissions.MASTERDATA_PROCESS_VIEW})
    public List<Process> processList() {
        return processService.list();
    }

    @PostMapping("/find-by-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_PROCESS_VIEW})
    public PageInfo<List<Process>> processFindByPaged(@RequestBody Process
    process, @PathVariable("page-no") int pageNumber, @PathVariable("page-size") int pageSize) {
        List<Process> list = processService.findBy(process, pageNumber, pageSize);
        Page<ProcessDto> pagedProcess = new Page<>();
        BeanUtils.copyProperties(list , pagedProcess);
        Map<String , String> idNameMapping = companyServiceLevelService.getIdNameMapping();
        list.forEach(entity->{
            ProcessDto dto = new ProcessDto();
            BeanUtils.copyProperties(entity, dto);
            dto.setCompanyservicelevelName(idNameMapping.get(entity.getCompanyServiceLevelId()));
            pagedProcess.add(dto);
        });
        return new PageInfo(pagedProcess);
    }

    @PostMapping("/find-by")
    @Permission(values= {Permissions.MASTERDATA_PROCESS_VIEW})
    public List<Process> processFindByPaged(@RequestBody Process
    process) {
        return processService.findBy(process);
    }

    @GetMapping("/find-one")
    @Permission(values= {Permissions.MASTERDATA_PROCESS_VIEW})
    public Process processFindOne(@RequestParam("fieldName") String fieldName,
                                  @RequestParam("value") String
    value) {
        return processService.findBy(fieldName, value);
    }

    @PostMapping("/find-by/{columns}")
    @Permission(values= {Permissions.MASTERDATA_PROCESS_VIEW})
    public List<Process> processFindByColumnsPaged(@RequestBody Process process,
                                                   @PathVariable("columns") String columns) {
        return processService.findByColumns(process, columns);
    }
}
