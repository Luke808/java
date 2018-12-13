package com.accenture.masterdata.starter.controller;

import com.accenture.masterdata.core.entity.CutoffTime;
import com.accenture.masterdata.service.CutoffTimeService;
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
@RequestMapping("/task/cutoffTime")
public class CutoffTimeController {

    @Autowired
    CutoffTimeService cutoffTimeService;

    @PostMapping("/save")
    public int cutoffTimeSave(@RequestBody CutoffTime cutoffTime) {
        return cutoffTimeService.save(cutoffTime);
    }

    @PostMapping("/batch-save")
    public int cutoffTimeBatchSave(@RequestBody List<CutoffTime> cutoffTimes) {
        return cutoffTimeService.save(cutoffTimes);
    }

    @DeleteMapping("/delete")
    public int cutoffTimeDelete(@RequestParam("id") String id) {
	    return cutoffTimeService.delete(id);
    }

    @DeleteMapping("/batch-delete")
    public int cutoffTimeBatchDelete(@RequestParam("ids") String ids) {
        return cutoffTimeService.batchDelete("\"" + String.join("\",\"", ids.split(",")) + "\"");
    }

    @PutMapping("/update")
    public int cutoffTimeUpdate(@RequestBody CutoffTime cutoffTime) {
	    return cutoffTimeService.update(cutoffTime);
    }

    @GetMapping("/find")
    public CutoffTime cutoffTimeFind(@RequestParam("id") String id) {
        return cutoffTimeService.findById(id);
    }

    @GetMapping("/list/{page-no}/{page-size}")
    public PageInfo<List<CutoffTime>> cutoffTimeList(@PathVariable(value="page-no") int
    pageNumber,
    @PathVariable(value="page-size") int pageSize) {
        List<CutoffTime> list = cutoffTimeService.list(pageNumber, pageSize);
        return new PageInfo(list);
    }

    @PostMapping("/find-by/{page-no}/{page-size}")
    public PageInfo<List<CutoffTime>> cutoffTimeFindBy(@RequestBody CutoffTime
    cutoffTime, @PathVariable("page-no") int pageNumber, @PathVariable("page-size") int pageSize) {
        List<CutoffTime> list = cutoffTimeService.findBy(cutoffTime, pageNumber, pageSize);
        return new PageInfo(list);
    }

    @GetMapping("/find-one")
    public CutoffTime cutoffTimeFindOne(@RequestParam("fieldName") String fieldName,
    @RequestParam("value") String
    value) {
        return cutoffTimeService.findBy(fieldName, value);
    }
}
