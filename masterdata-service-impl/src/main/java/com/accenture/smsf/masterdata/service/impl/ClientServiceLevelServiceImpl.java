package com.accenture.smsf.masterdata.service.impl;

import com.ac.smsf.codegen.core.service.impl.AbstractMapperServiceImpl;
import com.accenture.smsf.masterdata.core.entity.ClientServiceLevel;
import com.accenture.smsf.masterdata.service.ClientServiceLevelService;
import com.accenture.smsf.framework.boot.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
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
public class ClientServiceLevelServiceImpl extends AbstractMapperServiceImpl<ClientServiceLevel> implements ClientServiceLevelService {

    @Override
    @Cacheable(cacheNames = "clientservicelevel", key="#root.methodName")
    public Map<String, String> getIdNameMapping() {
        return this.list().parallelStream().collect(Collectors.toMap(ClientServiceLevel::getId, ClientServiceLevel::getName));
    }

    @Override
    @Cacheable(cacheNames = "clientservicelevel", key="#root.methodName")
    public Map<String, String> getIdLayeredNameMapping() {
        List<ClientServiceLevel> list = this.list();
        Map<String, ClientServiceLevel> idDtoMapping = list.stream().collect(Collectors.toMap(ClientServiceLevel::getId, dto -> dto));
        return list.stream().collect(Collectors.toMap(ClientServiceLevel::getId, dto -> {
            ClientServiceLevel node = dto;
            String layeredName = dto.getName();
            while (!StringUtils.equals("ROOT", node.getParentId())) {
                ClientServiceLevel parent = idDtoMapping.get(node.getParentId());
                layeredName = parent.getName() + "/" + layeredName;
                node = parent;
            }
            return layeredName;
        }));
    }

    @Override
    @Cacheable(cacheNames = "clientservicelevel", key="#root.methodName")
    public List<ClientServiceLevel> list() {
        return super.list();
    }

    @Override
    @CacheEvict(cacheNames = "clientservicelevel", allEntries=true)
    public int save(ClientServiceLevel model) {
        return super.save(model);
    }

    @Override
    @CacheEvict(cacheNames = "clientservicelevel", allEntries=true)
    public int save(List<ClientServiceLevel> models) {
        return super.save(models);
    }

    @Override
    @CacheEvict(cacheNames = "clientservicelevel", allEntries=true)
    public int update(ClientServiceLevel model) {
        return super.update(model);
    }

    @Override
    @CacheEvict(cacheNames = "clientservicelevel", allEntries=true)
    public int delete(String id) {
        return super.delete(id);
    }

    @Override
    @CacheEvict(cacheNames = "clientservicelevel", allEntries=true)
    public int batchDelete(String ids) {
        return super.batchDelete(ids);
    }


}
