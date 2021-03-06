package com.accenture.smsf.masterdata.service;

import com.ac.smsf.codegen.core.service.MapperService;
import com.accenture.smsf.masterdata.core.entity.Step;

import java.util.Map;

/**
 *
 * @author s.c.gao
 */
public interface StepService extends MapperService<Step> {
    Map<String, String> getIdNameMapping();
}
