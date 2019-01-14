package com.accenture.masterdata.starter.controller;

import com.accenture.masterdata.core.entity.NcType;
import com.accenture.masterdata.dto.NcTypeDto;
import com.accenture.masterdata.service.NcTypeService;
import com.accenture.masterdata.service.ProcessService;
import com.accenture.masterdata.starter.Permissions;
import com.accenture.smsf.authority.permission.loader.annotation.Permission;
import com.accenture.smsf.framework.starter.web.core.annotation.RestController;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 *
 * @author s.c.gao
 */
@RestController
@RequestMapping("/masterdata/ncType")
public class NcTypeController {

    @Autowired
    NcTypeService ncTypeService;
    @Autowired
    ProcessService processService;

    @PostMapping("/save")
    @Permission(values= {Permissions.MASTERDATA_NC_TYPE_MANAGE})
    public int ncTypeSave(@RequestBody NcType ncType) {

        return ncTypeService.save(ncType);
    }

    @PostMapping("/batch-save")
    @Permission(values= {Permissions.MASTERDATA_NC_TYPE_MANAGE})
    public int ncTypeBatchSave(@RequestBody List<NcType> ncTypes) {
        return ncTypeService.save(ncTypes);
    }

    @DeleteMapping("/delete")
    @Permission(values= {Permissions.MASTERDATA_NC_TYPE_MANAGE})
    public int ncTypeDelete(@RequestParam("id") String id) {
	    return ncTypeService.delete(id);
    }

    @DeleteMapping("/batch-delete")
    @Permission(values= {Permissions.MASTERDATA_NC_TYPE_MANAGE})
    public int ncTypeBatchDelete(@RequestParam("ids") String ids) {
        return ncTypeService.batchDelete("\"" + String.join("\",\"", ids.split(",")) + "\"");
    }

    @PutMapping("/update")
    @Permission(values= {Permissions.MASTERDATA_NC_TYPE_MANAGE})
    public int ncTypeUpdate(@RequestBody NcType ncType) {
	    return ncTypeService.update(ncType);
    }

    @GetMapping("/find")
    @Permission(values= {Permissions.MASTERDATA_NC_TYPE_VIEW})
    public NcTypeDto ncTypeFind(@RequestParam("id") String id) {
        return transformDto(ncTypeService.findById(id));
    }

    @GetMapping("/list-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_NC_TYPE_VIEW})
    public PageInfo<NcTypeDto> ncTypeListPaged(@PathVariable(value="page-no") int
    pageNumber,
                                            @PathVariable(value="page-size") int pageSize) {
        List<NcType> list = ncTypeService.list(pageNumber, pageSize);
        Page<NcTypeDto> pagedNcType = transformList(list);
        return new PageInfo<>(pagedNcType);
    }

    @GetMapping("/list")
    @Permission(values= {Permissions.MASTERDATA_NC_TYPE_VIEW})
    public List<NcTypeDto> ncTypeList() {
        List<NcType> list = ncTypeService.list();
        return transformList(list);
    }

    @PostMapping("/find-by-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_NC_TYPE_VIEW})
    public PageInfo<NcTypeDto> ncTypeFindByPaged(@RequestBody NcType
    ncType, @PathVariable("page-no") int pageNumber, @PathVariable("page-size") int pageSize) {
        List<NcType> list = ncTypeService.findBy(ncType, pageNumber, pageSize);
        Page<NcTypeDto> page = transformList(list);
        Map<String, String> idNameMapping = processService.getIdNameMapping();
        page.forEach(dto -> dto.setProcessName(idNameMapping.get(dto.getProcessId())));
        return new PageInfo<>(page);
    }

    @PostMapping("/find-by")
    @Permission(values= {Permissions.MASTERDATA_NC_TYPE_VIEW})
    public List<NcTypeDto> ncTypeFindBy(@RequestBody NcTypeDto
    ncTypeDto) {
        NcType ncType = new NcType();
        BeanUtils.copyProperties(ncTypeDto, ncType);
        List<NcType> list = ncTypeService.findBy(ncType);
        Page<NcTypeDto> page =transformList(list);
        Map<String, String> idNameMapping = processService.getIdNameMapping();
        page.forEach(dto -> dto.setProcessName(idNameMapping.get(dto.getProcessId())));
        return page;

    }

    @GetMapping("/find-one")
    @Permission(values= {Permissions.MASTERDATA_NC_TYPE_VIEW})
    public NcTypeDto ncTypeFindOne(@RequestParam("fieldName") String fieldName,
                                @RequestParam("value") String
    value) {
        return transformDto(ncTypeService.findBy(fieldName, value));
    }

    @PostMapping("/find-by/{columns}")
    @Permission(values= {Permissions.MASTERDATA_NC_TYPE_VIEW})
    public List<NcTypeDto> ncTypeFindByColumnsPaged(@RequestBody NcTypeDto ncTypeDto,
                                                    @PathVariable("columns") String columns) {
        NcType ncType = new NcType();
        BeanUtils.copyProperties(ncTypeDto, ncType);
        return transformList(ncTypeService.findByColumns(ncType, columns));
    }

    private Page<NcTypeDto> transformList(List<NcType> list){
        Page<NcTypeDto> pagedProcess = new Page<>();
        BeanUtils.copyProperties(list, pagedProcess);
        list.forEach(entity->{
            NcTypeDto dto = new NcTypeDto();
            BeanUtils.copyProperties(entity, dto);
            pagedProcess.add(dto);
        });
        return pagedProcess;
    }
    private NcTypeDto transformDto(NcType entity) {
        if (entity == null) {
            return null;
        }
        NcTypeDto dto = new NcTypeDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
