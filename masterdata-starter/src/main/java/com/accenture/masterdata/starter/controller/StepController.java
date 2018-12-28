package com.accenture.masterdata.starter.controller;

import com.accenture.masterdata.core.entity.Step;
import com.accenture.masterdata.dto.StepDto;
import com.accenture.masterdata.service.ProcessService;
import com.accenture.masterdata.service.StepService;
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
@RequestMapping("/masterdata/step")
public class StepController {

    @Autowired
    StepService stepService;

    @Autowired
    ProcessService processService;

    @PostMapping("/save")
    @Permission(values= {Permissions.MASTERDATA_STEP_MANAGE})
    public int stepSave(@RequestBody Step step) {
        return stepService.save(step);
    }

    @PostMapping("/batch-save")
    @Permission(values= {Permissions.MASTERDATA_STEP_MANAGE})
    public int stepBatchSave(@RequestBody List<Step> steps) {
        return stepService.save(steps);
    }

    @DeleteMapping("/delete")
    @Permission(values= {Permissions.MASTERDATA_STEP_MANAGE})
    public int stepDelete(@RequestParam("id") String id) {
	    return stepService.delete(id);
    }

    @DeleteMapping("/batch-delete")
    @Permission(values= {Permissions.MASTERDATA_STEP_MANAGE})
    public int stepBatchDelete(@RequestParam("ids") String ids) {
        return stepService.batchDelete("\"" + String.join("\",\"", ids.split(",")) + "\"");
    }

    @PutMapping("/update")
    @Permission(values= {Permissions.MASTERDATA_STEP_MANAGE})
    public int stepUpdate(@RequestBody Step step) {
	    return stepService.update(step);
    }

    @GetMapping("/find")
    @Permission(values= {Permissions.MASTERDATA_STEP_VIEW})
    public Step stepFind(@RequestParam("id") String id) {
        return stepService.findById(id);
    }

    @GetMapping("/list-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_STEP_VIEW})
    public PageInfo<Step> stepListPaged(@PathVariable(value="page-no") int
    pageNumber,
                                              @PathVariable(value="page-size") int pageSize) {
        List<Step> list = stepService.list(pageNumber, pageSize);
        return new PageInfo<>(list);
    }

    @GetMapping("/list")
    @Permission(values= {Permissions.MASTERDATA_STEP_VIEW})
    public List<Step> stepList() {
        return stepService.list();
    }

    @PostMapping("/find-by-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_SPT_VIEW})
    public PageInfo<StepDto> stepFindByPaged(@RequestBody Step
                                                     step, @PathVariable("page-no") int pageNumber, @PathVariable("page-size") int pageSize) {
        List<Step> list = stepService.findBy(step, pageNumber, pageSize);
        Page<StepDto> pagedStep = new Page<>();
        BeanUtils.copyProperties(list,pagedStep);
        Map<String, String> idNameMapping = processService.getIdNameMapping();
        list.forEach(entity->{
            StepDto dto = new StepDto();
            BeanUtils.copyProperties(entity,dto);
            dto.setProcessName(idNameMapping.get(entity.getProcessId()));
            pagedStep.add(dto);
        });
        return new PageInfo<>(pagedStep);
    }

    @PostMapping("/find-by")
    @Permission(values= {Permissions.MASTERDATA_STEP_VIEW})
    public List<Step> stepFindBy(@RequestBody Step
    step) {
        return stepService.findBy(step);
    }

    @GetMapping("/find-one")
    @Permission(values= {Permissions.MASTERDATA_STEP_VIEW})
    public Step stepFindOne(@RequestParam("fieldName") String fieldName,
                            @RequestParam("value") String
    value) {
        return stepService.findBy(fieldName, value);
    }

    @PostMapping("/find-by/{columns}")
    @Permission(values= {Permissions.MASTERDATA_STEP_VIEW})
    public List<Step> stepFindByColumnsPaged(@RequestBody Step step,
                                             @PathVariable("columns") String columns) {
        return stepService.findByColumns(step, columns);
    }
}
