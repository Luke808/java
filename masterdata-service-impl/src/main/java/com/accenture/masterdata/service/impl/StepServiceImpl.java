package com.accenture.masterdata.service.impl;

import com.ac.smsf.codegen.core.service.impl.AbstractMapperServiceImpl;
import com.accenture.masterdata.core.entity.Process;
import com.accenture.masterdata.core.entity.Step;
import com.accenture.masterdata.service.StepService;
import com.accenture.smsf.framework.boot.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Override
    @CacheEvict(cacheNames = "step", allEntries=true)
    public int update(Step model) {
        return super.update(model);
    }

    @Override
    @CacheEvict(cacheNames = "step", allEntries=true)
    public int save(Step model) {
        return super.save(model);
    }

    @Override
    @CacheEvict(cacheNames = "step", allEntries=true)
    public int save(List<Step> models) {
        return super.save(models);
    }

    @Override
    @CacheEvict(cacheNames = "step", allEntries=true)
    public int delete(String id) {
        return super.delete(id);
    }

    @Override
    @CacheEvict(cacheNames = "step", allEntries=true)
    public int batchDelete(String ids) {
        return super.batchDelete(ids);
    }
}
