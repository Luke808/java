package com.accenture.masterdata.starter.controller;

import com.accenture.smsf.framework.starter.web.core.annotation.RestController;
import com.accenture.smsf.framework.starter.web.principal.PrincipalHolder;
import com.accenture.smsf.framework.starter.web.principal.TenantHolder;

@RestController
public class baseController {
//	String eid = PrincipalHolder.get();
//	String tenantid = TenantHolder.get();
	Long eid = Long.parseLong("1");
	Long tenantid = Long.parseLong("1");
}
