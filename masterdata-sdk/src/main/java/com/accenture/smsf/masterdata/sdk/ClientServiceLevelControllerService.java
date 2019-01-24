package com.accenture.smsf.masterdata.sdk;

import com.accenture.smsf.masterdata.dto.ClientServiceLevelDto;
import com.accenture.smsf.framework.starter.web.sdk.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "smsf-masterdata-service", configuration = {FeignConfig.class})
@RequestMapping("/masterdata/clientServiceLevel")
public interface ClientServiceLevelControllerService {
    @GetMapping("/find")
    ClientServiceLevelDto clientServiceLevelFind(@RequestParam("id") String id);

    @PostMapping("/find-by")
    List<ClientServiceLevelDto> clientServiceLevelFindBy(@RequestBody ClientServiceLevelDto clientServiceLevelDto);

    @GetMapping("/list")
    List<ClientServiceLevelDto> clientServiceLevelList();

    @GetMapping("/id-name-map")
    Map<String, String> clientServiceLevelIdNameMapping();

    @GetMapping("/id-layered-name-map")
    Map<String, String> clientServiceLevelIdLayeredNameMapping();
}