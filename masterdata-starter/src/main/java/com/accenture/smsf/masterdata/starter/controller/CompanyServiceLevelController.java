package com.accenture.smsf.masterdata.starter.controller;

import com.accenture.smsf.masterdata.core.entity.CompanyServiceLevel;
import com.accenture.smsf.masterdata.dto.CompanyServiceLevelDto;
import com.accenture.smsf.masterdata.service.CompanyServiceLevelService;
import com.accenture.smsf.masterdata.starter.Permissions;
import com.accenture.smsf.authority.permission.loader.annotation.Permission;
import com.accenture.smsf.framework.starter.web.core.annotation.RestController;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
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
    public CompanyServiceLevelDto companyServiceLevelFind(@RequestParam("id") String id) {
        return transformDto(companyServiceLevelService.findById(id));
    }

    @GetMapping("/list-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_COMPANY_SERVICE_LEVEL_VIEW})
    public PageInfo<CompanyServiceLevelDto> companyServiceLevelListPaged(@PathVariable(value="page-no") int
    pageNumber,
                                                                            @PathVariable(value="page-size") int pageSize) {
        List<CompanyServiceLevel> list = companyServiceLevelService.list(pageNumber, pageSize);
        Page<CompanyServiceLevelDto> pagedCompany = transformList(list);
        return new PageInfo<>(pagedCompany);
    }

    @GetMapping("/list")
    @Permission(values= {Permissions.MASTERDATA_COMPANY_SERVICE_LEVEL_VIEW})
    public List<CompanyServiceLevelDto> companyServiceLevelList() {
        List<CompanyServiceLevel> list = companyServiceLevelService.list();
        Page<CompanyServiceLevelDto> pagedCompany = transformList(list);
        return pagedCompany;
    }

    @PostMapping("/find-by-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_COMPANY_SERVICE_LEVEL_VIEW})
    public PageInfo<CompanyServiceLevelDto> companyServiceLevelFindByPaged(@RequestBody CompanyServiceLevel
    companyServiceLevel, @PathVariable("page-no") int pageNumber, @PathVariable("page-size") int pageSize) {
        List<CompanyServiceLevel> list = companyServiceLevelService.findBy(companyServiceLevel, pageNumber, pageSize);
        Page<CompanyServiceLevelDto> pagedProcess = transformList(list);
        return new PageInfo<>(pagedProcess);

    }

    @PostMapping("/find-by")
    @Permission(values= {Permissions.MASTERDATA_COMPANY_SERVICE_LEVEL_VIEW})
    public List<CompanyServiceLevelDto> companyServiceLevelFindBy(@RequestBody CompanyServiceLevelDto
    companyServiceLevelDto) {
        CompanyServiceLevel companyServiceLevel = new CompanyServiceLevel();
        BeanUtils.copyProperties(companyServiceLevelDto, companyServiceLevel);
        List<CompanyServiceLevel> list = companyServiceLevelService.findBy(companyServiceLevel);
        Page<CompanyServiceLevelDto> pagedProcess = transformList(list);
        return pagedProcess;

    }

    @GetMapping("/find-one")
    @Permission(values= {Permissions.MASTERDATA_COMPANY_SERVICE_LEVEL_VIEW})
    public CompanyServiceLevelDto companyServiceLevelFindOne(@RequestParam("fieldName") String fieldName,
                                                          @RequestParam("value") String
    value) {
        return transformDto(companyServiceLevelService.findBy(fieldName, value));
    }

    @PostMapping("/find-by/{columns}")
    @Permission(values= {Permissions.MASTERDATA_COMPANY_SERVICE_LEVEL_VIEW})
    public List<CompanyServiceLevelDto> companyServiceLevelFindByColumnsPaged(@RequestBody CompanyServiceLevel companyServiceLevel,
                                                                           @PathVariable("columns") String columns) {
        List<CompanyServiceLevel> list = companyServiceLevelService.findByColumns(companyServiceLevel , columns);
        Page<CompanyServiceLevelDto> pagedProcess = transformList(list);
        return pagedProcess;
    }
    private Page<CompanyServiceLevelDto> transformList(List<CompanyServiceLevel> list){
        Page<CompanyServiceLevelDto> pagedCompanyServiceLevel = new Page<>();
        BeanUtils.copyProperties(list, pagedCompanyServiceLevel);
        list.forEach(entity->{
            CompanyServiceLevelDto dto = new CompanyServiceLevelDto();
            BeanUtils.copyProperties(entity, dto);
            pagedCompanyServiceLevel.add(dto);
        });
        return pagedCompanyServiceLevel;
    }
    private CompanyServiceLevelDto transformDto(CompanyServiceLevel entity) {
        if (entity == null) {
            return null;
        }
        CompanyServiceLevelDto dto = new CompanyServiceLevelDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
