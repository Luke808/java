package com.accenture.smsf.masterdata.sdk;

import com.accenture.smsf.masterdata.dto.NcTypeDto;
import com.accenture.smsf.framework.starter.web.sdk.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "smsf-masterdata-service-uat", configuration = {FeignConfig.class})
@RequestMapping("/masterdata/ncType")
public interface NcTypeControllerService {

    @GetMapping("/find")
    NcTypeDto ncTypeFind(@RequestParam("id") String id);

    @GetMapping("/list")
    List<NcTypeDto> ncTypeList();

    @PostMapping("/find-by")
    List<NcTypeDto> ncTypeFindBy(@RequestBody NcTypeDto ncTypeDto);
}
