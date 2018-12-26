package com.accenture.masterdata.starter.controller;

import com.accenture.masterdata.core.entity.Process;
import com.accenture.masterdata.service.ProcessService;
import com.accenture.masterdata.starter.Permissions;
import com.accenture.smsf.authority.permission.loader.annotation.Permission;
import com.accenture.smsf.framework.starter.web.core.annotation.RestController;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author s.c.gao
 */
@RestController
@RequestMapping("/masterdata/process")
public class ProcessController {

    @Autowired
    ProcessService processService;

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
    public PageInfo<Process> processListPaged(@PathVariable(value="page-no") int
    pageNumber,
                                                    @PathVariable(value="page-size") int pageSize) {
        List<Process> list = processService.list(pageNumber, pageSize);
        return new PageInfo<>(list);
    }

    @GetMapping("/list")
    @Permission(values= {Permissions.MASTERDATA_PROCESS_VIEW})
    public List<Process> processList() {
        return processService.list();
    }

    @PostMapping("/find-by-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_PROCESS_VIEW})
    public PageInfo<Process> processFindByPaged(@RequestBody Process
    process, @PathVariable("page-no") int pageNumber, @PathVariable("page-size") int pageSize) {
        List<Process> list = processService.findBy(process, pageNumber, pageSize);
        return new PageInfo<>(list);
    }

    @PostMapping("/find-by")
    @Permission(values= {Permissions.MASTERDATA_PROCESS_VIEW})
    public List<Process> processFindBy(@RequestBody Process
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
