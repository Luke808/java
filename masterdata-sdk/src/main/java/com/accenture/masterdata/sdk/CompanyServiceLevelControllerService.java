package com.accenture.masterdata.sdk;

import com.accenture.masterdata.core.entity.CompanyServiceLevel;
import com.accenture.smsf.framework.starter.web.sdk.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "smsf-masterdata-service", configuration = {FeignConfig.class})
@RequestMapping("/masterdata/companyServiceLevel")
public interface CompanyServiceLevelControllerService {
    @GetMapping("/find")
    CompanyServiceLevel companyServiceLevelFind(@RequestParam("id") String id);

    @PostMapping("/find-by")
    List<CompanyServiceLevel> companyServiceLevelFindBy(@RequestBody CompanyServiceLevel companyServiceLevel);
}
