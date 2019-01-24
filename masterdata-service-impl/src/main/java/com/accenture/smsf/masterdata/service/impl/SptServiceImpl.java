package com.accenture.smsf.masterdata.service.impl;

import com.ac.smsf.codegen.core.service.impl.AbstractMapperServiceImpl;
import com.accenture.smsf.masterdata.core.entity.Spt;
import com.accenture.smsf.masterdata.service.SptService;
import com.accenture.smsf.framework.boot.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author s.c.gao
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class SptServiceImpl extends AbstractMapperServiceImpl<Spt> implements SptService {


}
