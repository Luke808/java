package com.accenture.masterdata.organization.service.impl;

import java.util.Date;
import java.util.List;

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
	public OrganizationHierarchyOut createOrUpdateOrganizationHierarchy(OrganizationHierarchyIn params) {

		long id = 0;
		if(params.getId() == 0) {
			//new
			params.setId(0L);
			params.setIsDeleted(0);
			id = hierarchyMapper.insertOrganizationHierarchy(params);
		}
		else
		{
			//update
			Date date = new Date();  
			params.setLastModificationTime(date);
			params.setLastModifierUserId(1L);
			hierarchyMapper.updateOrganizationHierarchy(params);
			id = params.getId();
		}
		
		return hierarchyMapper.selectOrganizationHierarchy(id);
	}
	
	@Override
	public int deleteOrganizationHierarchy(Long eid, Long id) {
		return hierarchyMapper.deleteOrganizationHierarchy(eid, id);
	}
	
	@Override
	public OrganizationHierarchyOut selectOrganizationHierarchy(Long id) {

		OrganizationHierarchyOut hierarchy = new OrganizationHierarchyOut();
		if(id == 0) {
			hierarchy.setId(0L);
			hierarchy.setAllowConcurrently(0);
			hierarchy.setIcon("");
			hierarchy.setComments("");
			hierarchy.setTenantId(1L);
			hierarchy.setCreationTime(new Date());
			hierarchy.setCreatorUserId(1L);
		}
		else {
			hierarchy = hierarchyMapper.selectOrganizationHierarchy(id);
		}
		
		return hierarchy;
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
