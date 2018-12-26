package com.accenture.masterdata.service.impl;

import com.ac.smsf.codegen.core.service.impl.AbstractMapperServiceImpl;
import com.accenture.masterdata.core.entity.Process;
import com.accenture.masterdata.core.entity.Step;
import com.accenture.masterdata.service.StepService;
import com.accenture.smsf.framework.boot.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author s.c.gao
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class StepServiceImpl extends AbstractMapperServiceImpl<Step> implements StepService {

    @Override
    @Cacheable(cacheNames = "step")
    public Map<String, String> getIdNameMapping() {
        return this.list().parallelStream().collect(Collectors.toMap(Step::getId, Step::getName));

    }
}
