package com.accenture.masterdata.starter.controller;

import com.accenture.masterdata.core.entity.CutoffTime;
import com.accenture.masterdata.service.CutoffTimeService;
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
@RequestMapping("/masterdata/cutoffTime")
public class CutoffTimeController {

    @Autowired
    CutoffTimeService cutoffTimeService;

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
    public CutoffTime cutoffTimeFind(@RequestParam("id") String id) {
        return cutoffTimeService.findById(id);
    }

    @GetMapping("/list-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_CUTOFF_TIME_VIEW})
    public PageInfo<List<CutoffTime>> cutoffTimeListPaged(@PathVariable(value="page-no") int
    pageNumber,
                                                          @PathVariable(value="page-size") int pageSize) {
        List<CutoffTime> list = cutoffTimeService.list(pageNumber, pageSize);
        return new PageInfo(list);
    }

    @GetMapping("/list")
    @Permission(values= {Permissions.MASTERDATA_CUTOFF_TIME_VIEW})
    public List<CutoffTime> cutoffTimeList() {
        return cutoffTimeService.list();
    }

    @PostMapping("/find-by-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_CUTOFF_TIME_VIEW})
    public PageInfo<List<CutoffTime>> cutoffTimeFindByPaged(@RequestBody CutoffTime
    cutoffTime, @PathVariable("page-no") int pageNumber, @PathVariable("page-size") int pageSize) {
        List<CutoffTime> list = cutoffTimeService.findBy(cutoffTime, pageNumber, pageSize);
        return new PageInfo(list);
    }

    @PostMapping("/find-by")
    @Permission(values= {Permissions.MASTERDATA_CUTOFF_TIME_VIEW})
    public List<CutoffTime> cutoffTimeFindByPaged(@RequestBody CutoffTime
    cutoffTime) {
        return cutoffTimeService.findBy(cutoffTime);
    }

    @GetMapping("/find-one")
    @Permission(values= {Permissions.MASTERDATA_CUTOFF_TIME_VIEW})
    public CutoffTime cutoffTimeFindOne(@RequestParam("fieldName") String fieldName,
                                        @RequestParam("value") String
    value) {
        return cutoffTimeService.findBy(fieldName, value);
    }

    @PostMapping("/find-by/{columns}")
    @Permission(values= {Permissions.MASTERDATA_CUTOFF_TIME_VIEW})
    public List<CutoffTime> cutoffTimeFindByColumnsPaged(@RequestBody CutoffTime cutoffTime,
                                                         @PathVariable("columns") String columns) {
        return cutoffTimeService.findByColumns(cutoffTime, columns);
    }
}
