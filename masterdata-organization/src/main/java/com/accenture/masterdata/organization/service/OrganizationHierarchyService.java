package com.accenture.masterdata.organization.service;

import java.util.List;

import com.accenture.masterdata.core.inEntity.BatchDeleteInput;
import com.accenture.masterdata.core.inEntity.QueryParam;
import com.accenture.masterdata.core.outEntity.OrganizationHierarchy;

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
	public OrganizationHierarchy selectOrganizationHierarchy(Long id);

	/**
	 * 
	 * @param params
	 */
	public List<OrganizationHierarchy> selectOrganizationHierarchys(QueryParam params);

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
	List<OrganizationHierarchy> getNextLevel( int curLevel);
}
