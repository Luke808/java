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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;

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
    public ClientServiceLevelDto clientServiceLevelFind(@RequestParam("id") String id) {
        return transformDto(clientServiceLevelService.findById(id));
    }

    @GetMapping("/list-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_CLIENT_SERVICE_LEVEL_VIEW})
    public PageInfo<ClientServiceLevelDto> clientServiceLevelListPaged(@PathVariable(value="page-no") int
    pageNumber,
                                                                          @PathVariable(value="page-size") int pageSize) {
        List<ClientServiceLevel> list = clientServiceLevelService.list(pageNumber, pageSize);
        Page<ClientServiceLevelDto> page = transformList(list);
        Map<String, String> idNameMapping = processService.getIdNameMapping();
        page.forEach(dto -> dto.setProcessName(idNameMapping.get(dto.getProcessId())));
        return new PageInfo<>(page);
    }

    @GetMapping("/list")
    @Permission(values= {Permissions.MASTERDATA_CLIENT_SERVICE_LEVEL_VIEW})
    public List<ClientServiceLevelDto> clientServiceLevelList() {
        List<ClientServiceLevel> list = clientServiceLevelService.list();
        Page<ClientServiceLevelDto> page = transformList(list);
        Map<String, String> idNameMapping = processService.getIdNameMapping();
        page.forEach(dto -> dto.setProcessName(idNameMapping.get(dto.getProcessId())));
        return page;
    }

    @PostMapping("/find-by-paged/{page-no}/{page-size}")
    @Permission(values= {Permissions.MASTERDATA_CLIENT_SERVICE_LEVEL_VIEW})
    public PageInfo<ClientServiceLevelDto> clientServiceLevelFindByPaged(@RequestBody ClientServiceLevel
    clientServiceLevel, @PathVariable("page-no") int pageNumber, @PathVariable("page-size") int pageSize) {
        List<ClientServiceLevel> list = clientServiceLevelService.findBy(clientServiceLevel, pageNumber, pageSize);
        Page<ClientServiceLevelDto> pagedClientServiceLevel = transformList(list);
        Map<String, String> idNameMapping = processService.getIdNameMapping();
        pagedClientServiceLevel.forEach(dto -> dto.setProcessName(idNameMapping.get(dto.getProcessId())));
        return new PageInfo<>(pagedClientServiceLevel);
    }

    @PostMapping("/find-by")
    @Permission(values= {Permissions.MASTERDATA_CLIENT_SERVICE_LEVEL_VIEW})
    public List<ClientServiceLevelDto> clientServiceLevelFindBy(@RequestBody ClientServiceLevelDto
    clientServiceLevelDto) {
        ClientServiceLevel clientServiceLevel = new ClientServiceLevel();
        BeanUtils.copyProperties(clientServiceLevelDto, clientServiceLevel);
        List<ClientServiceLevel> list = clientServiceLevelService.findBy(clientServiceLevel);
        Page<ClientServiceLevelDto> page =transformList(list);
        Map<String, String> idNameMapping = processService.getIdNameMapping();
        page.forEach(dto -> dto.setProcessName(idNameMapping.get(dto.getProcessId())));
        return page;
    }

    @GetMapping("/find-one")
    @Permission(values= {Permissions.MASTERDATA_CLIENT_SERVICE_LEVEL_VIEW})
    public ClientServiceLevelDto clientServiceLevelFindOne(@RequestParam("fieldName") String fieldName,
                                                        @RequestParam("value") String
    value) {
        return transformDto(clientServiceLevelService.findBy(fieldName, value));
    }

    @PostMapping("/find-by/{columns}")
    @Permission(values= {Permissions.MASTERDATA_CLIENT_SERVICE_LEVEL_VIEW})
    public List<ClientServiceLevelDto> clientServiceLevelFindByColumnsPaged(@RequestBody ClientServiceLevel clientServiceLevel,
                                                                         @PathVariable("columns") String columns) {
        List<ClientServiceLevel> list = clientServiceLevelService.findByColumns(clientServiceLevel,columns);
        Page<ClientServiceLevelDto> page = transformList(list);
        Map<String, String> idNameMapping = processService.getIdNameMapping();
        page.forEach(dto -> dto.setProcessName(idNameMapping.get(dto.getProcessId())));
        return page;
    }
    
    @GetMapping({"/filter-by"})
    @Permission(values= {Permissions.MASTERDATA_CLIENT_SERVICE_LEVEL_VIEW})
    public Map<String, List<ClientServiceLevelDto>> clientServiceLevelFilterByName(@RequestParam(value = "keyWord", required = false) String keyWord) {
        List<ClientServiceLevelDto> list = transformList(clientServiceLevelService.list());
        List<ClientServiceLevelDto> filteredList = list;
        if (!StringUtils.isEmpty(keyWord)) {
            String lowerKeyWord = keyWord.toLowerCase();
            filteredList = list.stream().filter(dto -> fatherMatches(list, dto, lowerKeyWord) || childrenMatches(list, dto, lowerKeyWord))
                    .collect(Collectors.toList());
        }
        return filteredList.stream().collect(Collectors.groupingBy(dto -> "L" + ((dto.getId().length() / 2) - 1)));
    }

    private boolean fatherMatches(List<ClientServiceLevelDto> list, ClientServiceLevelDto dto, String keyWord) {
        if (dto.getName().toLowerCase().contains(keyWord)) {
            return true;
        }
        ClientServiceLevelDto curr = dto;
        ClientServiceLevelDto parent;
        while((parent = findFather(list, curr)) != null) {
            curr = parent;
            if (parent.getName().toLowerCase().contains(keyWord)) {
                return true;
            }
        }
        return false;
    }

    private ClientServiceLevelDto findFather(List<ClientServiceLevelDto> list, ClientServiceLevelDto dto) {
        return list.stream().filter(each -> Objects.equals(each.getId(), dto.getParentId())).findAny().orElse(null);
    }

    private boolean childrenMatches(List<ClientServiceLevelDto> list, ClientServiceLevelDto dto, String keyWord) {
        List<ClientServiceLevelDto> children = list.stream().filter(each -> Objects.equals(each.getParentId(), dto.getId()))
                .collect(Collectors.toList());
        if (children.stream().anyMatch(each -> each.getName().toLowerCase().contains(keyWord))) {
            return true;
        }
        for (ClientServiceLevelDto each: children) {
            if (childrenMatches(list, each, keyWord)) {
                return true;
            }
        }
        return false;
    }
    
    private Page<ClientServiceLevelDto> transformList(List<ClientServiceLevel> list){
        Page<ClientServiceLevelDto> pagedClientServiceLevel = new Page<>();
        BeanUtils.copyProperties(list, pagedClientServiceLevel);
        list.forEach(entity->{
            ClientServiceLevelDto dto = new ClientServiceLevelDto();
            BeanUtils.copyProperties(entity, dto);
            pagedClientServiceLevel.add(dto);
        });
        return pagedClientServiceLevel;
    }
    private ClientServiceLevelDto transformDto(ClientServiceLevel entity) {
        if (entity == null) {
            return null;
        }
        ClientServiceLevelDto dto = new ClientServiceLevelDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
