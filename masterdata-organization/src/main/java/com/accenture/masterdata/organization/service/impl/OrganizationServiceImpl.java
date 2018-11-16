package com.accenture.masterdata.organization.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.masterdata.common.querybuilder.BuilderParam;
import com.accenture.masterdata.core.inEntity.OrganizationIn;
import com.accenture.masterdata.core.inEntity.QueryParam;
import com.accenture.masterdata.core.mapper.OrganizationMapper;
import com.accenture.masterdata.core.outEntity.OrganizationOut;
import com.accenture.masterdata.organization.service.OrganizationService;
import com.accenture.smsf.framework.boot.stereotype.Service;
import com.accenture.smsf.framework.starter.web.principal.TenantHolder;
import com.accenture.smsf.model.exception.ApplicationException;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private BuilderParam builderParm;
	
	@Autowired
	private OrganizationMapper organization;

	@Override
	public int saveOrganization(OrganizationIn params) throws Exception {
		
		// 返回值定义
		int result = 0;
		
		// 重复项check
		duplicationCheck(params);
		
		// 设置租户id
		params.setTenantId(Long.valueOf(TenantHolder.get()));
		
		// 保存数据
		// 更新
		if ( params.getId() != null && params.getId() > 0 ) {
			result = organization.updateOrganization(params);
		}
		// 新增
		else {
			result = organization.insertOrganization(params);
		}

		return result;
	}

	@Override
	public int deleteOrganization(Long id) {
		return organization.deleteOrganization(id);
	}

	@Override
	public List<OrganizationOut> selectOrganizations(QueryParam params) {
		String strParmWithPageing = builderParm.buildParmWithPageing(params);
		return organization.selectOrganizationList(strParmWithPageing);
	}

	@Override
	public OrganizationOut selectOrganization(Long id) {
		return organization.selectOrganization(id);
	}

	@Override
	public int selectOrganizationCount(QueryParam params) {
		String strParmWithPageing = builderParm.buildParmNoPageing(params);
		return organization.selectOrganizationCount(strParmWithPageing);
	}

	@Override
	public void inputInfoCheck(OrganizationIn params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void duplicationCheck(OrganizationIn params) throws Exception {
		
		// 姓名重复check
		String where = " and name = '" + params.getName() + "' and tenantId = " + TenantHolder.get();
		if ( params.getId() != null && params.getId() > 0 ) {
			where += " and id <>" + params.getId().toString();
		}
		int count = organization.selectOrganizationCount(where);

		if( count > 0 ) {
			throw new ApplicationException(90001);
		}
		
		// code重复check
		where = " and code = '" + params.getCode() + "' and tenantId = " + TenantHolder.get();
		if ( params.getId() != null && params.getId() > 0 ) {
			where += " and id <>" + params.getId().toString();
		}
		count = organization.selectOrganizationCount(where);
		
		if( count > 0 ) {
			throw new ApplicationException(90002);
		}
		
	}


}
