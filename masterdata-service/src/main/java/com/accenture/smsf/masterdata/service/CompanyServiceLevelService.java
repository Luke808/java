package com.accenture.smsf.masterdata.service;

import com.ac.smsf.codegen.core.service.MapperService;
import com.accenture.smsf.masterdata.core.entity.CompanyServiceLevel;

import java.util.Map;

/**
 *
 * @author s.c.gao
 */
public interface CompanyServiceLevelService extends MapperService<CompanyServiceLevel> {
    Map<String, String> getIdNameMapping();
}
