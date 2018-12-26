package com.accenture.masterdata.sdk;

import com.accenture.masterdata.core.entity.Step;
import com.accenture.smsf.framework.starter.web.sdk.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "smsf-masterdata-service", configuration = {FeignConfig.class})
@RequestMapping("/masterdata/step")
public interface StepControllerService {
    @GetMapping("/find")
    Step stepFind(@RequestParam("id") String id);

    @PostMapping("/find-by")
    List<Step> stepFindBy(@RequestBody Step step);
}
