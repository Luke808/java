package com.accenture.smsf.masterdata.service;

import com.ac.smsf.codegen.core.service.MapperService;
import com.accenture.smsf.masterdata.core.entity.Process;

import java.util.Map;

/**
 *
 * @author s.c.gao
 */
public interface ProcessService extends MapperService<Process> {

    Map<String, String> getIdNameMapping();
}
