package com.accenture.masterdata.starter.controller;

import com.accenture.masterdata.core.entity.CompanyServiceLevel;
import com.accenture.masterdata.service.CompanyServiceLevelService;
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
@RequestMapping("/masterdata/companyServiceLevel")
public class CompanyServiceLevelController {

    @Autowired
    CompanyServiceLevelService companyServiceLevelService;

    @PostMapping("/save")
    public int companyServiceLevelSave(@RequestBody CompanyServiceLevel companyServiceLevel) {
        return companyServiceLevelService.save(companyServiceLevel);
    }

    @PostMapping("/batch-save")
    public int companyServiceLevelBatchSave(@RequestBody List<CompanyServiceLevel> companyServiceLevels) {
        return companyServiceLevelService.save(companyServiceLevels);
    }

    @DeleteMapping("/delete")
    public int companyServiceLevelDelete(@RequestParam("id") String id) {
	    return companyServiceLevelService.delete(id);
    }

    @DeleteMapping("/batch-delete")
    public int companyServiceLevelBatchDelete(@RequestParam("ids") String ids) {
        return companyServiceLevelService.batchDelete("\"" + String.join("\",\"", ids.split(",")) + "\"");
    }

    @PutMapping("/update")
    public int companyServiceLevelUpdate(@RequestBody CompanyServiceLevel companyServiceLevel) {
	    return companyServiceLevelService.update(companyServiceLevel);
    }

    @GetMapping("/find")
    public CompanyServiceLevel companyServiceLevelFind(@RequestParam("id") String id) {
        return companyServiceLevelService.findById(id);
    }

    @GetMapping("/list-paged/{page-no}/{page-size}")
    public PageInfo<List<CompanyServiceLevel>> companyServiceLevelListPaged(@PathVariable(value="page-no") int
    pageNumber,
    @PathVariable(value="page-size") int pageSize) {
        List<CompanyServiceLevel> list = companyServiceLevelService.list(pageNumber, pageSize);
        return new PageInfo(list);
    }

    @GetMapping("/list")
    public List<CompanyServiceLevel> companyServiceLevelList() {
        return companyServiceLevelService.list();
    }

    @PostMapping("/find-by-paged/{page-no}/{page-size}")
    public PageInfo<List<CompanyServiceLevel>> companyServiceLevelFindByPaged(@RequestBody CompanyServiceLevel
    companyServiceLevel, @PathVariable("page-no") int pageNumber, @PathVariable("page-size") int pageSize) {
        List<CompanyServiceLevel> list = companyServiceLevelService.findBy(companyServiceLevel, pageNumber, pageSize);
        return new PageInfo(list);
    }

    @PostMapping("/find-by")
    public List<CompanyServiceLevel> companyServiceLevelFindByPaged(@RequestBody CompanyServiceLevel
    companyServiceLevel) {
        return companyServiceLevelService.findBy(companyServiceLevel);
    }

    @GetMapping("/find-one")
    public CompanyServiceLevel companyServiceLevelFindOne(@RequestParam("fieldName") String fieldName,
    @RequestParam("value") String
    value) {
        return companyServiceLevelService.findBy(fieldName, value);
    }
}
