package com.accenture.masterdata.organization.service;

import java.util.List;

import com.accenture.masterdata.core.inEntity.BatchDeleteInput;
import com.accenture.masterdata.core.inEntity.OrganizationHierarchyIn;
import com.accenture.masterdata.core.inEntity.QueryParam;
import com.accenture.masterdata.core.outEntity.OrganizationHierarchyOut;

public interface OrganizationHierarchyService {
	/**
	 * 
	 * @param params
	 */
	public void createOrUpdateOrganizationHierarchy(OrganizationHierarchyIn params) throws Exception ;

	/**
	 * 
	 * @param inputInfo
	 */
	void duplicationCheck(OrganizationHierarchyIn params) throws Exception;

	/**
	 * 
	 * @param params
	 */
	void inputCheck(OrganizationHierarchyIn params);

	
	/**
	 * 
	 * @param id
	 */
	public int deleteOrganizationHierarchy(Long id);

	/**
	 * 
	 * @param id
	 */
    //批量删除
	public void batchDeleteOrganizationHierarchy(BatchDeleteInput idList);
    
	/**
	 * 
	 * @param id
	 */
	public OrganizationHierarchyOut selectOrganizationHierarchy(Long id);

	/**
	 * 
	 * @param params
	 */
	public List<OrganizationHierarchyOut> selectOrganizationHierarchys(QueryParam params);

	/**
	 * 
	 * @param params
	 */
	public int selectOrganizationHierCount(QueryParam params);
}
