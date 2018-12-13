package com.accenture.masterdata.starter.controller;

import com.accenture.masterdata.core.entity.Spt;
import com.accenture.masterdata.service.SptService;
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
@RequestMapping("/task/spt")
public class SptController {

    @Autowired
    SptService sptService;

    @PostMapping("/save")
    public int sptSave(@RequestBody Spt spt) {
        return sptService.save(spt);
    }

    @PostMapping("/batch-save")
    public int sptBatchSave(@RequestBody List<Spt> spts) {
        return sptService.save(spts);
    }

    @DeleteMapping("/delete")
    public int sptDelete(@RequestParam("id") String id) {
	    return sptService.delete(id);
    }

    @DeleteMapping("/batch-delete")
    public int sptBatchDelete(@RequestParam("ids") String ids) {
        return sptService.batchDelete("\"" + String.join("\",\"", ids.split(",")) + "\"");
    }

    @PutMapping("/update")
    public int sptUpdate(@RequestBody Spt spt) {
	    return sptService.update(spt);
    }

    @GetMapping("/find")
    public Spt sptFind(@RequestParam("id") String id) {
        return sptService.findById(id);
    }

    @GetMapping("/list/{page-no}/{page-size}")
    public PageInfo<List<Spt>> sptList(@PathVariable(value="page-no") int
    pageNumber,
    @PathVariable(value="page-size") int pageSize) {
        List<Spt> list = sptService.list(pageNumber, pageSize);
        return new PageInfo(list);
    }

    @PostMapping("/find-by/{page-no}/{page-size}")
    public PageInfo<List<Spt>> sptFindBy(@RequestBody Spt
    spt, @PathVariable("page-no") int pageNumber, @PathVariable("page-size") int pageSize) {
        List<Spt> list = sptService.findBy(spt, pageNumber, pageSize);
        return new PageInfo(list);
    }

    @GetMapping("/find-one")
    public Spt sptFindOne(@RequestParam("fieldName") String fieldName,
    @RequestParam("value") String
    value) {
        return sptService.findBy(fieldName, value);
    }
}
