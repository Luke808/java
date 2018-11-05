package com.accenture.masterdata.organization.service.impl;

import java.util.List;

import com.accenture.masterdata.core.inEntity.OrganizationHierarchyIn;
import com.accenture.masterdata.core.inEntity.OrganizationIn;
import com.accenture.masterdata.core.outEntity.HierarchyPropertiesOut;
import com.accenture.masterdata.core.outEntity.OrganizationHierarchyOut;
import com.accenture.masterdata.core.outEntity.OrganizationOut;
import com.accenture.masterdata.organization.service.OrganizationService;

public class OrganizationServiceImpl implements OrganizationService {

	@Override
	public int addOrganizationHierarchy(OrganizationHierarchyIn params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteOrganizationHierarchy(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveOrganizationHierarchy(OrganizationHierarchyIn params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OrganizationHierarchyOut selectOrganizationHierarchy(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrganizationHierarchyOut> selectOrganizationHierarchys(OrganizationHierarchyIn params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectOrganizationHierCount(OrganizationHierarchyIn params) {
		// TODO Auto-generated method stub
		return 0;
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
	public int deleteOrganization(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrganizationOut> selectOrganizations(OrganizationIn params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrganizationOut selectOrganization(int id) {
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

	@Override
	public List<HierarchyPropertiesOut> selectHierarchyProperties(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
