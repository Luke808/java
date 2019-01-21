package com.accenture.masterdata.service;

import com.ac.smsf.codegen.core.service.MapperService;
import com.accenture.masterdata.core.entity.CompanyServiceLevel;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

/**
 *
 * @author s.c.gao
 */
public interface CompanyServiceLevelService extends MapperService<CompanyServiceLevel> {
    Map<String, String> getIdNameMapping();
}
