package com.accenture.masterdata.sdk;

import com.accenture.masterdata.dto.StepDto;
import com.accenture.smsf.framework.starter.web.sdk.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "smsf-masterdata-service-uat", configuration = {FeignConfig.class})
@RequestMapping("/masterdata/step")
public interface StepControllerService {
    @GetMapping("/find")
    StepDto stepFind(@RequestParam("id") String id);

    @PostMapping("/find-by")
    List<StepDto> stepFindBy(@RequestBody StepDto stepDto);
}
