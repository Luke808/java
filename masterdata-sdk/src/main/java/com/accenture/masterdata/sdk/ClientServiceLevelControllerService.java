package com.accenture.masterdata.sdk;

import com.accenture.masterdata.core.entity.ClientServiceLevel;
import com.accenture.smsf.framework.starter.web.sdk.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "smsf-masterdata-service-uat", configuration = {FeignConfig.class})
@RequestMapping("/masterdata/clientServiceLevel")
public interface ClientServiceLevelControllerService {
    @GetMapping("/find")
    ClientServiceLevel clientServiceLevelFind(@RequestParam("id") String id);

    @PostMapping("/find-by")
    List<ClientServiceLevel> clientServiceLevelFindBy(@RequestBody ClientServiceLevel clientServiceLevel);
}
