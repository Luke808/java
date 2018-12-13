package com.accenture.masterdata.starter.controller;

import com.accenture.masterdata.core.entity.Process;
import com.accenture.masterdata.service.ProcessService;
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
@RequestMapping("/task/process")
public class ProcessController {

    @Autowired
    ProcessService processService;

    @PostMapping("/save")
    public int processSave(@RequestBody Process process) {
        return processService.save(process);
    }

    @PostMapping("/batch-save")
    public int processBatchSave(@RequestBody List<Process> processs) {
        return processService.save(processs);
    }

    @DeleteMapping("/delete")
    public int processDelete(@RequestParam("id") String id) {
	    return processService.delete(id);
    }

    @DeleteMapping("/batch-delete")
    public int processBatchDelete(@RequestParam("ids") String ids) {
        return processService.batchDelete("\"" + String.join("\",\"", ids.split(",")) + "\"");
    }

    @PutMapping("/update")
    public int processUpdate(@RequestBody Process process) {
	    return processService.update(process);
    }

    @GetMapping("/find")
    public Process processFind(@RequestParam("id") String id) {
        return processService.findById(id);
    }

    @GetMapping("/list/{page-no}/{page-size}")
    public PageInfo<List<Process>> processList(@PathVariable(value="page-no") int
    pageNumber,
    @PathVariable(value="page-size") int pageSize) {
        List<Process> list = processService.list(pageNumber, pageSize);
        return new PageInfo(list);
    }

    @PostMapping("/find-by/{page-no}/{page-size}")
    public PageInfo<List<Process>> processFindBy(@RequestBody Process
    process, @PathVariable("page-no") int pageNumber, @PathVariable("page-size") int pageSize) {
        List<Process> list = processService.findBy(process, pageNumber, pageSize);
        return new PageInfo(list);
    }

    @GetMapping("/find-one")
    public Process processFindOne(@RequestParam("fieldName") String fieldName,
    @RequestParam("value") String
    value) {
        return processService.findBy(fieldName, value);
    }
}
