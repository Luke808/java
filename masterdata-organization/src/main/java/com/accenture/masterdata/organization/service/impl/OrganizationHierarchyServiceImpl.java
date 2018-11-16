package com.accenture.masterdata.organization.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.masterdata.common.querybuilder.BuilderParam;
import com.accenture.masterdata.core.inEntity.BatchDeleteInput;
import com.accenture.masterdata.core.inEntity.QueryParam;
import com.accenture.masterdata.core.mapper.OrganizationHierarchyMapper;
import com.accenture.masterdata.core.outEntity.OrganizationHierarchy;
import com.accenture.masterdata.organization.service.OrganizationHierarchyService;
import com.accenture.smsf.framework.starter.web.principal.TenantHolder;
import com.accenture.smsf.model.exception.ApplicationException;

public class OrganizationHierarchyServiceImpl implements OrganizationHierarchyService {

	@Autowired
	private BuilderParam builderParm;

	@Autowired
	private OrganizationHierarchyMapper hierarchyMapper;
	
	@Override
	public void createOrUpdateOrganizationHierarchy(OrganizationHierarchy params)  throws Exception {

		duplicationCheck(params);
		
		if(params.getId() == null || params.getId() == 0) {
			//new
			params.setCreatorUserId(1L);
			hierarchyMapper.insertOrganizationHierarchy(params);
		}
		else
		{
			//update
			params.setLastModifierUserId(1L);
			hierarchyMapper.updateOrganizationHierarchy(params);
		}
		
	}

	@Override
	public int deleteOrganizationHierarchy(Long id) {
		return hierarchyMapper.deleteOrganizationHierarchy(id);
	}
	
	@Override
	public void batchDeleteOrganizationHierarchy(BatchDeleteInput idList) {
		hierarchyMapper.batchDeleteOrganizationHierarchy(idList.getIds());
	}
	
	@Override
	public OrganizationHierarchy selectOrganizationHierarchy(Long id) {

		OrganizationHierarchy hierarchy = new OrganizationHierarchy();
		if(id == 0) {
			hierarchy.setId(0L);
			hierarchy.setAllowConcurrently(0);
			hierarchy.setIcon("");
			hierarchy.setComments("");
			hierarchy.setTenantId(Integer.valueOf(TenantHolder.get()).longValue());
			hierarchy.setCreationTime(new Date());
			hierarchy.setCreatorUserId(1L);
		}
		else {
			hierarchy = hierarchyMapper.selectOrganizationHierarchy(id);
		}
		
		return hierarchy;
	}

	@Override
	public List<OrganizationHierarchy> selectOrganizationHierarchys(QueryParam params) {
		String strParmWithPageing = builderParm.buildParmWithPageing(params);
		List<OrganizationHierarchy> list = hierarchyMapper.selectOrganizatioHierarchyieList(strParmWithPageing);
		return list;
	}

	@Override
	public int selectOrganizationHierCount(QueryParam params) {
		String strParmNoPageing = builderParm.buildParmNoPageing(params);
		return hierarchyMapper.selectOrganizationHierarchyCount(strParmNoPageing);
	}


	@Override
	public void inputCheck(OrganizationHierarchy params) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void duplicationCheck(OrganizationHierarchy params) throws Exception {

		// 名称重复check
		String where = " and name = '" + params.getName() + "' and tenantId = " + TenantHolder.get();
		if ( params.getId() != null && params.getId() > 0 ) {
			where += " and id <>" + params.getId().toString();
		}
		int count = hierarchyMapper.selectOrganizationHierarchyCount(where);

		if( count > 0 ) {
			throw new ApplicationException(90002);
		}
		
		// level重复check
		where = " and level = " + params.getLevel().toString() + " and tenantId = " + TenantHolder.get();
		if ( params.getId() != null && params.getId() > 0 ) {
			where += " and id <> " + params.getId().toString();
		}
		count = hierarchyMapper.selectOrganizationHierarchyCount(where);
		
		if( count > 0 ) {
			throw new ApplicationException(90001);
		}

	}

}
