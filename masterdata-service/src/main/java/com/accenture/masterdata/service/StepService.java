package com.accenture.masterdata.service;

import com.ac.smsf.codegen.core.service.MapperService;
import com.accenture.masterdata.core.entity.Step;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

/**
 *
 * @author s.c.gao
 */
public interface StepService extends MapperService<Step> {

    @Cacheable
    Map<String, String> getIdNameMapping();
}
