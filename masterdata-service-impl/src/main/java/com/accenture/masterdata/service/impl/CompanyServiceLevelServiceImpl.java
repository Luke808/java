package com.accenture.masterdata.service.impl;

import com.ac.smsf.codegen.core.service.impl.AbstractMapperServiceImpl;
import com.accenture.masterdata.core.entity.CompanyServiceLevel;
import com.accenture.masterdata.service.CompanyServiceLevelService;
import com.accenture.smsf.framework.boot.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author s.c.gao
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class CompanyServiceLevelServiceImpl extends AbstractMapperServiceImpl<CompanyServiceLevel> implements CompanyServiceLevelService {


}
