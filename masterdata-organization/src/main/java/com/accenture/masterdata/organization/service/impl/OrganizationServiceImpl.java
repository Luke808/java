package com.accenture.masterdata.organization.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.masterdata.common.querybuilder.BuilderParam;
import com.accenture.masterdata.core.inEntity.BatchDeleteInput;
import com.accenture.masterdata.core.inEntity.EmployeeIn;
import com.accenture.masterdata.core.inEntity.OrganizationHierarchyIn;
import com.accenture.masterdata.core.inEntity.OrganizationIn;
import com.accenture.masterdata.core.inEntity.QueryParam;
import com.accenture.masterdata.core.mapper.OrganizationHierarchyMapper;
import com.accenture.masterdata.core.outEntity.OrganizationHierarchyOut;
import com.accenture.masterdata.core.outEntity.OrganizationOut;
import com.accenture.masterdata.organization.service.OrganizationService;
import com.accenture.smsf.framework.boot.stereotype.Service;
import com.accenture.smsf.model.exception.ApplicationException;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private BuilderParam builderParm;

	@Autowired
	private OrganizationHierarchyMapper hierarchyMapper;
	
	@Override
	public void createOrUpdateOrganizationHierarchy(OrganizationHierarchyIn params)  throws Exception {

		long id = 0;
		if(params.getId() == null || params.getId() == 0) {
			//new
			params.setId(0L);
			params.setIsDeleted(0);
			params.setCreationTime(new Date());
			params.setCreatorUserId(1L);
			duplicationCheck(params);
			hierarchyMapper.insertOrganizationHierarchy(params);
		}
		else
		{
			//update
			Date date = new Date();  
			params.setLastModificationTime(date);
			params.setLastModifierUserId(1L);
			duplicationCheck(params);
			hierarchyMapper.updateOrganizationHierarchy(params);
		}
		
	}
	

	@Override
	public void duplicationCheck(OrganizationHierarchyIn params) throws Exception {

		//level check
		if (hierarchyMapper.checkOrganizationHierLevel(params.getTenantId(),params.getId(), params.getLevel())>0)
		{
			throw new ApplicationException(90001);
		}
		//name check
		if (hierarchyMapper.checkOrganizationHierName(params.getTenantId(),params.getId(), params.getName())>0)
		{
			throw new ApplicationException(90002);
		}	
	}

	@Override
	public int deleteOrganizationHierarchy(Long tenantid, Long eid, Long id) {
		return hierarchyMapper.deleteOrganizationHierarchy(tenantid, eid, id);
	}
	
	@Override
	public void batchDeleteOrganizationHierarchy(Long tenantid, Long eid,BatchDeleteInput idList) {
		hierarchyMapper.batchDeleteOrganizationHierarchy(tenantid, eid, idList.getIds());
	}
	
	@Override
	public OrganizationHierarchyOut selectOrganizationHierarchy(Long tenantid, Long id) {

		OrganizationHierarchyOut hierarchy = new OrganizationHierarchyOut();
		if(id == 0) {
			hierarchy.setId(0L);
			hierarchy.setAllowConcurrently(0);
			hierarchy.setIcon("");
			hierarchy.setComments("");
			hierarchy.setTenantId(tenantid);
			hierarchy.setCreationTime(new Date());
			hierarchy.setCreatorUserId(1L);
		}
		else {
			hierarchy = hierarchyMapper.selectOrganizationHierarchy(tenantid, id);
		}
		
		return hierarchy;
	}

	@Override
	public List<OrganizationHierarchyOut> selectOrganizationHierarchys(Long tenantid, QueryParam params) {
		String strParmWithPageing = builderParm.buildParmWithPageing(params);
		List<OrganizationHierarchyOut> list = hierarchyMapper.selectOrganizatioHierarchyieList(tenantid, strParmWithPageing);
		return list;
	}

	@Override
	public int selectOrganizationHierCount(Long tenantid, QueryParam params) {
		String strParmNoPageing = builderParm.buildParmNoPageing(params);
		return hierarchyMapper.selectOrganizationHierarchyCount(tenantid, strParmNoPageing);
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
