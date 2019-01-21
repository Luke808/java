package com.accenture.masterdata.service;

import com.ac.smsf.codegen.core.service.MapperService;
import com.accenture.masterdata.core.entity.ClientServiceLevel;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

/**
 *
 * @author s.c.gao
 */
public interface ClientServiceLevelService extends MapperService<ClientServiceLevel> {
    Map<String, String> getIdNameMapping();
}
