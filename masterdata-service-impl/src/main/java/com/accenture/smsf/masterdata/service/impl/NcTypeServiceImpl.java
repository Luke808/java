package com.accenture.smsf.masterdata.service.impl;

import com.ac.smsf.codegen.core.service.impl.AbstractMapperServiceImpl;
import com.accenture.smsf.masterdata.core.entity.NcType;
import com.accenture.smsf.masterdata.service.NcTypeService;
import com.accenture.smsf.framework.boot.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author s.c.gao
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class NcTypeServiceImpl extends AbstractMapperServiceImpl<NcType> implements NcTypeService {


}
