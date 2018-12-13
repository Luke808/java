package com.accenture.masterdata.starter.controller;

import com.accenture.masterdata.core.entity.ClientServiceLevel;
import com.accenture.masterdata.service.ClientServiceLevelService;
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
@RequestMapping("/task/clientServiceLevel")
public class ClientServiceLevelController {

    @Autowired
    ClientServiceLevelService clientServiceLevelService;

    @PostMapping("/save")
    public int clientServiceLevelSave(@RequestBody ClientServiceLevel clientServiceLevel) {
        return clientServiceLevelService.save(clientServiceLevel);
    }

    @PostMapping("/batch-save")
    public int clientServiceLevelBatchSave(@RequestBody List<ClientServiceLevel> clientServiceLevels) {
        return clientServiceLevelService.save(clientServiceLevels);
    }

    @DeleteMapping("/delete")
    public int clientServiceLevelDelete(@RequestParam("id") String id) {
	    return clientServiceLevelService.delete(id);
    }

    @DeleteMapping("/batch-delete")
    public int clientServiceLevelBatchDelete(@RequestParam("ids") String ids) {
        return clientServiceLevelService.batchDelete("\"" + String.join("\",\"", ids.split(",")) + "\"");
    }

    @PutMapping("/update")
    public int clientServiceLevelUpdate(@RequestBody ClientServiceLevel clientServiceLevel) {
	    return clientServiceLevelService.update(clientServiceLevel);
    }

    @GetMapping("/find")
    public ClientServiceLevel clientServiceLevelFind(@RequestParam("id") String id) {
        return clientServiceLevelService.findById(id);
    }

    @GetMapping("/list/{page-no}/{page-size}")
    public PageInfo<List<ClientServiceLevel>> clientServiceLevelList(@PathVariable(value="page-no") int
    pageNumber,
    @PathVariable(value="page-size") int pageSize) {
        List<ClientServiceLevel> list = clientServiceLevelService.list(pageNumber, pageSize);
        return new PageInfo(list);
    }

    @PostMapping("/find-by/{page-no}/{page-size}")
    public PageInfo<List<ClientServiceLevel>> clientServiceLevelFindBy(@RequestBody ClientServiceLevel
    clientServiceLevel, @PathVariable("page-no") int pageNumber, @PathVariable("page-size") int pageSize) {
        List<ClientServiceLevel> list = clientServiceLevelService.findBy(clientServiceLevel, pageNumber, pageSize);
        return new PageInfo(list);
    }

    @GetMapping("/find-one")
    public ClientServiceLevel clientServiceLevelFindOne(@RequestParam("fieldName") String fieldName,
    @RequestParam("value") String
    value) {
        return clientServiceLevelService.findBy(fieldName, value);
    }
}
