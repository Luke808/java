package com.accenture.masterdata.organization.service;

import java.util.List;

import com.accenture.masterdata.core.inentity.BatchDeleteInput;
import com.accenture.masterdata.core.inentity.QueryParam;
import com.accenture.masterdata.core.outentity.OrganizationHierarchy;
import com.accenture.masterdata.core.outentity.dropdownList;

public interface OrganizationHierarchyService {
	/**
	 * 
	 * @param params
	 */
	public void createOrUpdateOrganizationHierarchy(OrganizationHierarchy params) throws Exception ;

	/**
	 * 
	 * @param inputInfo
	 */
	void duplicationCheck(OrganizationHierarchy params) throws Exception;

	/**
	 * 
	 * @param params
	 */
	void inputCheck(OrganizationHierarchy params);

	
	/**
	 * 
	 * @param id
	 */
	public int deleteOrganizationHierarchy(Long id)  throws Exception;

	/**
	 * 
	 * @param id
	 */
    //批量删除
	public void batchDeleteOrganizationHierarchy(BatchDeleteInput idList)  throws Exception ;
    
	/**
	 * 
	 * @param id
	 */
	public OrganizationHierarchy selectOrganizationHierarchy(Long id);

	/**
	 * 
	 * @param params
	 */
	public List<OrganizationHierarchy> selectOrganizationHierarchys(QueryParam params);

	public List<dropdownList> getDropDown();
	
	/**
	 * 
	 * @param params
	 */
	public int selectOrganizationHierCount(QueryParam params);
	

	/**
	 * 获得可添加的Hierarchy下级
	 * @param params
	 * curLevel： 当前级
	 */
	List<OrganizationHierarchy> getNextLevel( Long curLevel);
}
