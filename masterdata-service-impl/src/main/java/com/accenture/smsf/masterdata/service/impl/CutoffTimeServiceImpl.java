package com.accenture.smsf.masterdata.service.impl;

import com.ac.smsf.codegen.core.service.impl.AbstractMapperServiceImpl;
import com.accenture.smsf.masterdata.core.entity.CutoffTime;
import com.accenture.smsf.masterdata.service.CutoffTimeService;
import com.accenture.smsf.framework.boot.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author s.c.gao
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class CutoffTimeServiceImpl extends AbstractMapperServiceImpl<CutoffTime> implements CutoffTimeService {


}
