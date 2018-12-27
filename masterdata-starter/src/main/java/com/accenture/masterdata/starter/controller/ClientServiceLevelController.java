package com.accenture.masterdata.starter.controller;

import com.accenture.masterdata.core.entity.ClientServiceLevel;
import com.accenture.masterdata.core.entity.Process;
import com.accenture.masterdata.dto.ClientServiceLevelDto;
import com.accenture.masterdata.service.ClientServiceLevelService;
import com.accenture.masterdata.service.ProcessService;
import com.accenture.masterdata.starter.Permissions;
import com.accenture.smsf.authority.permission.loader.annotation.Permission;
import com.accenture.smsf.framework.starter.web.core.annotation.RestController;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author s.c.gao
 */
@RestController
@RequestMapping("/masterdata/clientServiceLevel")
public class ClientServiceLevelController {

    @Autowired
    ClientServiceLevelService clientServiceLevelService;

    @Autowired
    ProcessService processService;

    @PostMapping("/save")
    @Permission(values= {Permissions.MASTERDATA_CLIENT_SERVICE_LEVEL_MANAGE})
    public int clientServiceLevelSave(@RequestBody ClientServiceLevel clientServiceLevel) {
        return clientServiceLevelService.save(clientServiceLevel);
    }

    @PostMapping("/batch-save")
    @Permission(values= {Permissions.MASTERDATA_CLIENT_SERVICE_LEVEL_MANAGE})
    public int clientServiceLevelBatchSave(@RequestBody List<ClientServiceLevel> clientServiceLevels) {
        return clientServiceLevelService.save(clientServiceLevels);
    }

    @DeleteMapping("/delete")
    @Permission(values= {Permissions.MASTERDATA_CLIENT_SERVICE_LEVEL_MANAGE})
    public int clientServiceLevelDelete(@RequestParam("id") String id) {
	    return clientServiceLevelService.delete(id);
    }

    @DeleteMapping("/batch-delete")
    @Permission(values= {Permissions.MASTERDATA_CLIENT_SERVICE_LEVEL_MANAGE})
    public int clientServiceLevelBatchDelete(@RequestParam("ids") String ids) {
        return clientServiceLevelService.batchDelete("\"" + String.join("\",\"", ids.split(",")) + "\"");
    }

    @PutMapping("/update")
    @Permission(values= {Permissions.MASTERDATA_CLIENT_SERVICE_LEVEL_MANAGE})
    public int clientServiceLevelUpdate(@RequestBody ClientServiceLevel clientServiceLevel) {
	    return clientServiceLevelService.update(clientServiceLevel);
    }

    @GetMapping("/find")
    @Permission(values= {Permissions.MASTERDATA_CLIENT_SERVICE_LEVEL_VIEW})
    public ClientServiceLevel clientServiceLevelFind(@RequestParam("id") String id) {
        return clientServiceLevelService.findById(id);
    }

    @GetMapping("/list-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_CLIENT_SERVICE_LEVEL_VIEW})
    public PageInfo<ClientServiceLevel> clientServiceLevelListPaged(@PathVariable(value="page-no") int
    pageNumber,
                                                                          @PathVariable(value="page-size") int pageSize) {
        List<ClientServiceLevel> list = clientServiceLevelService.list(pageNumber, pageSize);
        return new PageInfo<>(list);
    }

    @GetMapping("/list")
    @Permission(values= {Permissions.MASTERDATA_CLIENT_SERVICE_LEVEL_VIEW})
    public List<ClientServiceLevel> clientServiceLevelList() {
        return clientServiceLevelService.list();
    }

    @PostMapping("/find-by-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_CLIENT_SERVICE_LEVEL_VIEW})
    public PageInfo<ClientServiceLevel> clientServiceLevelFindByPaged(@RequestBody ClientServiceLevel
    clientServiceLevel, @PathVariable("page-no") int pageNumber, @PathVariable("page-size") int pageSize) {
        List<ClientServiceLevel> list = clientServiceLevelService.findBy(clientServiceLevel, pageNumber, pageSize);
        Page<ClientServiceLevelDto> pagedClientServiceLevel = new Page<>();
        BeanUtils.copyProperties(list, pagedClientServiceLevel);
        Map<String, String> idNameMapping = processService.getIdNameMapping();
        list.forEach(entity->{
            ClientServiceLevelDto dto = new ClientServiceLevelDto();
            BeanUtils.copyProperties(entity, dto);
            dto.setProcessName(idNameMapping.get(entity.getProcessId()));
            pagedClientServiceLevel.add(dto);
        });
        return new PageInfo<>(pagedClientServiceLevel);
    }

    @PostMapping("/find-by")
    @Permission(values= {Permissions.MASTERDATA_CLIENT_SERVICE_LEVEL_VIEW})
    public List<ClientServiceLevel> clientServiceLevelFindBy(@RequestBody ClientServiceLevel
    clientServiceLevel) {
        return clientServiceLevelService.findBy(clientServiceLevel);
    }

    @GetMapping("/find-one")
    @Permission(values= {Permissions.MASTERDATA_CLIENT_SERVICE_LEVEL_VIEW})
    public ClientServiceLevel clientServiceLevelFindOne(@RequestParam("fieldName") String fieldName,
                                                        @RequestParam("value") String
    value) {
        return clientServiceLevelService.findBy(fieldName, value);
    }

    @PostMapping("/find-by/{columns}")
    @Permission(values= {Permissions.MASTERDATA_CLIENT_SERVICE_LEVEL_VIEW})
    public List<ClientServiceLevel> clientServiceLevelFindByColumnsPaged(@RequestBody ClientServiceLevel clientServiceLevel,
                                                                         @PathVariable("columns") String columns) {
        return clientServiceLevelService.findByColumns(clientServiceLevel, columns);
    }
}
