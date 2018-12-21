package com.accenture.masterdata.starter.controller;

import com.accenture.masterdata.core.entity.CompanyServiceLevel;
import com.accenture.masterdata.service.CompanyServiceLevelService;
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
@RequestMapping("/masterdata/companyServiceLevel")
public class CompanyServiceLevelController {

    @Autowired
    CompanyServiceLevelService companyServiceLevelService;

    @PostMapping("/save")
    @Permission(values= {Permissions.MASTERDATA_COMPANY_SERVICE_LEVEL_MANAGE})
    public int companyServiceLevelSave(@RequestBody CompanyServiceLevel companyServiceLevel) {
        return companyServiceLevelService.save(companyServiceLevel);
    }

    @PostMapping("/batch-save")
    @Permission(values= {Permissions.MASTERDATA_COMPANY_SERVICE_LEVEL_MANAGE})
    public int companyServiceLevelBatchSave(@RequestBody List<CompanyServiceLevel> companyServiceLevels) {
        return companyServiceLevelService.save(companyServiceLevels);
    }

    @DeleteMapping("/delete")
    @Permission(values= {Permissions.MASTERDATA_COMPANY_SERVICE_LEVEL_MANAGE})
    public int companyServiceLevelDelete(@RequestParam("id") String id) {
	    return companyServiceLevelService.delete(id);
    }

    @DeleteMapping("/batch-delete")
    @Permission(values= {Permissions.MASTERDATA_COMPANY_SERVICE_LEVEL_MANAGE})
    public int companyServiceLevelBatchDelete(@RequestParam("ids") String ids) {
        return companyServiceLevelService.batchDelete("\"" + String.join("\",\"", ids.split(",")) + "\"");
    }

    @PutMapping("/update")
    @Permission(values= {Permissions.MASTERDATA_COMPANY_SERVICE_LEVEL_MANAGE})
    public int companyServiceLevelUpdate(@RequestBody CompanyServiceLevel companyServiceLevel) {
	    return companyServiceLevelService.update(companyServiceLevel);
    }

    @GetMapping("/find")
    @Permission(values= {Permissions.MASTERDATA_COMPANY_SERVICE_LEVEL_VIEW})
    public CompanyServiceLevel companyServiceLevelFind(@RequestParam("id") String id) {
        return companyServiceLevelService.findById(id);
    }

    @GetMapping("/list-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_COMPANY_SERVICE_LEVEL_VIEW})
    public PageInfo<List<CompanyServiceLevel>> companyServiceLevelListPaged(@PathVariable(value="page-no") int
    pageNumber,
                                                                            @PathVariable(value="page-size") int pageSize) {
        List<CompanyServiceLevel> list = companyServiceLevelService.list(pageNumber, pageSize);
        return new PageInfo(list);
    }

    @GetMapping("/list")
    @Permission(values= {Permissions.MASTERDATA_COMPANY_SERVICE_LEVEL_VIEW})
    public List<CompanyServiceLevel> companyServiceLevelList() {
        return companyServiceLevelService.list();
    }

    @PostMapping("/find-by-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_COMPANY_SERVICE_LEVEL_VIEW})
    public PageInfo<List<CompanyServiceLevel>> companyServiceLevelFindByPaged(@RequestBody CompanyServiceLevel
    companyServiceLevel, @PathVariable("page-no") int pageNumber, @PathVariable("page-size") int pageSize) {
        List<CompanyServiceLevel> list = companyServiceLevelService.findBy(companyServiceLevel, pageNumber, pageSize);
        return new PageInfo(list);
    }

    @PostMapping("/find-by")
    @Permission(values= {Permissions.MASTERDATA_COMPANY_SERVICE_LEVEL_VIEW})
    public List<CompanyServiceLevel> companyServiceLevelFindByPaged(@RequestBody CompanyServiceLevel
    companyServiceLevel) {
        return companyServiceLevelService.findBy(companyServiceLevel);
    }

    @GetMapping("/find-one")
    @Permission(values= {Permissions.MASTERDATA_COMPANY_SERVICE_LEVEL_VIEW})
    public CompanyServiceLevel companyServiceLevelFindOne(@RequestParam("fieldName") String fieldName,
                                                          @RequestParam("value") String
    value) {
        return companyServiceLevelService.findBy(fieldName, value);
    }

    @PostMapping("/find-by/{columns}")
    @Permission(values= {Permissions.MASTERDATA_COMPANY_SERVICE_LEVEL_VIEW})
    public List<CompanyServiceLevel> companyServiceLevelFindByColumnsPaged(@RequestBody CompanyServiceLevel companyServiceLevel,
                                                                           @PathVariable("columns") String columns) {
        return companyServiceLevelService.findByColumns(companyServiceLevel, columns);
    }
}
