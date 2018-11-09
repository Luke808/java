package com.accenture.masterdata.organization.service.impl;

import java.util.List;

import org.omg.CORBA.PrincipalHolder;
import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.masterdata.common.querybuilder.BuilderParam;
import com.accenture.masterdata.core.inEntity.OrganizationHierarchyIn;
import com.accenture.masterdata.core.inEntity.OrganizationIn;
import com.accenture.masterdata.core.inEntity.QueryParam;
import com.accenture.masterdata.core.mapper.OrganizationHierarchyMapper;
import com.accenture.masterdata.core.outEntity.OrganizationHierarchyOut;
import com.accenture.masterdata.core.outEntity.OrganizationOut;
import com.accenture.masterdata.organization.service.OrganizationService;
import com.accenture.smsf.framework.boot.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private BuilderParam builderParm;

	@Autowired
	private OrganizationHierarchyMapper hierarchyMapper;
	
	@Override
	public int addOrganizationHierarchy(OrganizationHierarchyIn params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteOrganizationHierarchy(Long eid, Long id) {
		return hierarchyMapper.deleteOrganizationHierarchy(eid, id);
	}

	@Override
	public int saveOrganizationHierarchy(OrganizationHierarchyIn params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OrganizationHierarchyOut selectOrganizationHierarchy(Long id) {
		return hierarchyMapper.selectOrganizationHierarchy(id);
	}

	@Override
	public List<OrganizationHierarchyOut> selectOrganizationHierarchys(QueryParam params) {
		String strParmWithPageing = builderParm.buildParmWithPageing(params);
		List<OrganizationHierarchyOut> list = hierarchyMapper.selectOrganizatioHierarchyieList(strParmWithPageing);
		return list;
	}

	@Override
	public int selectOrganizationHierCount(QueryParam params) {
		String strParmNoPageing = builderParm.buildParmNoPageing(params);
		return hierarchyMapper.selectOrganizationHierarchyCount(strParmNoPageing);
	}

	@Override
	public int addOrganization(OrganizationIn params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveOrganization(OrganizationIn params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteOrganization(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrganizationOut> selectOrganizations(OrganizationIn params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrganizationOut selectOrganization(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectOrganizationCount(OrganizationIn params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void inputInfoCheck(OrganizationIn params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void duplicationCheck(OrganizationIn params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hierInputCheck(OrganizationHierarchyIn params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hireDuplicationCheck(OrganizationHierarchyIn params) {
		// TODO Auto-generated method stub

	}


}
