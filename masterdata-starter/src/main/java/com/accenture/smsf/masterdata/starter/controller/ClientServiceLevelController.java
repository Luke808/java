package com.accenture.smsf.masterdata.starter.controller;

import com.accenture.smsf.masterdata.core.entity.ClientServiceLevel;
import com.accenture.smsf.masterdata.dto.ClientServiceLevelDto;
import com.accenture.smsf.masterdata.service.ClientServiceLevelService;
import com.accenture.smsf.masterdata.service.ProcessService;
import com.accenture.smsf.masterdata.starter.Permissions;
import com.accenture.smsf.authority.permission.loader.annotation.Permission;
import com.accenture.smsf.framework.starter.web.core.annotation.RestController;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        List<ClientServiceLevel> list = clientServiceLevelService.listByTenantId(pageNumber, pageSize);
        Page<ClientServiceLevelDto> page = transformList(list);
        Map<String, String> idNameMapping = processService.getIdNameMapping();
        page.forEach(dto -> dto.setProcessName(idNameMapping.get(dto.getProcessId())));
        return new PageInfo<>(page);
    }

    @GetMapping("/list")
    @Permission(values= {Permissions.MASTERDATA_CLIENT_SERVICE_LEVEL_VIEW})
    public List<ClientServiceLevelDto> clientServiceLevelList() {
        List<ClientServiceLevel> list = clientServiceLevelService.listByTenantId();
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

    @GetMapping("/id-name-map")
    @Permission(values= {Permissions.MASTERDATA_PROCESS_VIEW})
    public Map<String, String> clientServiceLevelIdNameMapping(){
        return clientServiceLevelService.getIdNameMapping();
    }

    @GetMapping("/id-layered-name-map")
    @Permission(values= {Permissions.MASTERDATA_PROCESS_VIEW})
    public Map<String, String> clientServiceLevelIdLayeredNameMapping(){
        return clientServiceLevelService.getIdLayeredNameMapping();
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
