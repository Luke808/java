package com.accenture.masterdata.starter.controller;

import com.accenture.masterdata.core.entity.Step;
import com.accenture.masterdata.service.StepService;
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
@RequestMapping("/task/step")
public class StepController {

    @Autowired
    StepService stepService;

    @PostMapping("/save")
    public int stepSave(@RequestBody Step step) {
        return stepService.save(step);
    }

    @PostMapping("/batch-save")
    public int stepBatchSave(@RequestBody List<Step> steps) {
        return stepService.save(steps);
    }

    @DeleteMapping("/delete")
    public int stepDelete(@RequestParam("id") String id) {
	    return stepService.delete(id);
    }

    @DeleteMapping("/batch-delete")
    public int stepBatchDelete(@RequestParam("ids") String ids) {
        return stepService.batchDelete("\"" + String.join("\",\"", ids.split(",")) + "\"");
    }

    @PutMapping("/update")
    public int stepUpdate(@RequestBody Step step) {
	    return stepService.update(step);
    }

    @GetMapping("/find")
    public Step stepFind(@RequestParam("id") String id) {
        return stepService.findById(id);
    }

    @GetMapping("/list/{page-no}/{page-size}")
    public PageInfo<List<Step>> stepList(@PathVariable(value="page-no") int
    pageNumber,
    @PathVariable(value="page-size") int pageSize) {
        List<Step> list = stepService.list(pageNumber, pageSize);
        return new PageInfo(list);
    }

    @PostMapping("/find-by/{page-no}/{page-size}")
    public PageInfo<List<Step>> stepFindBy(@RequestBody Step
    step, @PathVariable("page-no") int pageNumber, @PathVariable("page-size") int pageSize) {
        List<Step> list = stepService.findBy(step, pageNumber, pageSize);
        return new PageInfo(list);
    }

    @GetMapping("/find-one")
    public Step stepFindOne(@RequestParam("fieldName") String fieldName,
    @RequestParam("value") String
    value) {
        return stepService.findBy(fieldName, value);
    }
}
