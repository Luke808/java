package com.accenture.masterdata.sdk;

import com.accenture.masterdata.dto.ProcessDto;
import com.accenture.smsf.framework.starter.web.sdk.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "smsf-masterdata-service-uat", configuration = {FeignConfig.class})
@RequestMapping("/masterdata/process")
public interface ProcessControllerService {
    @GetMapping("/find")
    ProcessDto processFind(@RequestParam("id") String id);

    @PostMapping("/find-by")
    List<ProcessDto> processFindBy(@RequestBody ProcessDto processDto);

    @GetMapping("/list")
    List<ProcessDto> processList();

    @GetMapping("/id-name-map")
    Map<String, String> processIdNameMapping();
}
