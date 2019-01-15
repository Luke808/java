package com.accenture.masterdata.service.impl;

import com.ac.smsf.codegen.core.service.impl.AbstractMapperServiceImpl;
import com.accenture.masterdata.core.entity.Process;
import com.accenture.masterdata.service.ProcessService;
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
public class ProcessServiceImpl extends AbstractMapperServiceImpl<Process> implements ProcessService {

    @Override
    @Cacheable(cacheNames = "process")
    public Map<String, String> getIdNameMapping() {
        return this.list().parallelStream().collect(Collectors.toMap(Process::getId, Process::getName));
    }

    @Override
    @CacheEvict(cacheNames = "process")
    public int save(Process model) {
        return super.save(model);
    }

    @Override
    @CacheEvict(cacheNames = "process")
    public int save(List<Process> models) {
        return super.save(models);
    }

    @Override
    @CacheEvict(cacheNames = "process")
    public int update(Process model) {
        return super.update(model);
    }

    @Override
    @CacheEvict(cacheNames = "process")
    public int delete(String id) {
        return super.delete(id);
    }

    @Override
    @CacheEvict(cacheNames = "process")
    public int batchDelete(String ids) {
        return super.batchDelete(ids);
    }
}
