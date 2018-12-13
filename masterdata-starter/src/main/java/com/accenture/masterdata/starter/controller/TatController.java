package com.accenture.masterdata.starter.controller;

import com.accenture.masterdata.core.entity.Tat;
import com.accenture.masterdata.service.TatService;
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
@RequestMapping("/task/tat")
public class TatController {

    @Autowired
    TatService tatService;

    @PostMapping("/save")
    public int tatSave(@RequestBody Tat tat) {
        return tatService.save(tat);
    }

    @PostMapping("/batch-save")
    public int tatBatchSave(@RequestBody List<Tat> tats) {
        return tatService.save(tats);
    }

    @DeleteMapping("/delete")
    public int tatDelete(@RequestParam("id") String id) {
	    return tatService.delete(id);
    }

    @DeleteMapping("/batch-delete")
    public int tatBatchDelete(@RequestParam("ids") String ids) {
        return tatService.batchDelete("\"" + String.join("\",\"", ids.split(",")) + "\"");
    }

    @PutMapping("/update")
    public int tatUpdate(@RequestBody Tat tat) {
	    return tatService.update(tat);
    }

    @GetMapping("/find")
    public Tat tatFind(@RequestParam("id") String id) {
        return tatService.findById(id);
    }

    @GetMapping("/list/{page-no}/{page-size}")
    public PageInfo<List<Tat>> tatList(@PathVariable(value="page-no") int
    pageNumber,
    @PathVariable(value="page-size") int pageSize) {
        List<Tat> list = tatService.list(pageNumber, pageSize);
        return new PageInfo(list);
    }

    @PostMapping("/find-by/{page-no}/{page-size}")
    public PageInfo<List<Tat>> tatFindBy(@RequestBody Tat
    tat, @PathVariable("page-no") int pageNumber, @PathVariable("page-size") int pageSize) {
        List<Tat> list = tatService.findBy(tat, pageNumber, pageSize);
        return new PageInfo(list);
    }

    @GetMapping("/find-one")
    public Tat tatFindOne(@RequestParam("fieldName") String fieldName,
    @RequestParam("value") String
    value) {
        return tatService.findBy(fieldName, value);
    }
}
