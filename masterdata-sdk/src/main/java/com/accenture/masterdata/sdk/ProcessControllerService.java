package com.accenture.masterdata.sdk;

import com.accenture.masterdata.core.entity.Process;
import com.accenture.smsf.framework.starter.web.sdk.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "smsf-masterdata-service-uat", configuration = {FeignConfig.class})
@RequestMapping("/masterdata/process")
public interface ProcessControllerService {
    @GetMapping("/find")
    Process processFind(@RequestParam("id") String id);

    @PostMapping("/find-by")
    List<Process> processFindBy(@RequestBody Process process);
}
