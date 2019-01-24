package com.accenture.smsf.masterdata.sdk;

import com.accenture.smsf.masterdata.dto.CompanyServiceLevelDto;
import com.accenture.smsf.framework.starter.web.sdk.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "smsf-masterdata-service", configuration = {FeignConfig.class})
@RequestMapping("/masterdata/companyServiceLevel")
public interface CompanyServiceLevelControllerService {
    @GetMapping("/find")
    CompanyServiceLevelDto companyServiceLevelFind(@RequestParam("id") String id);

    @PostMapping("/find-by")
    List<CompanyServiceLevelDto> companyServiceLevelFindBy(@RequestBody CompanyServiceLevelDto companyServiceLevelDto);
}
