package com.accenture.masterdata.organization.service;

import java.util.List;

import com.accenture.masterdata.core.inEntity.BatchDeleteInput;
import com.accenture.masterdata.core.inEntity.EmployeeIn;
import com.accenture.masterdata.core.inEntity.OrganizationHierarchyIn;
import com.accenture.masterdata.core.inEntity.OrganizationIn;
import com.accenture.masterdata.core.inEntity.QueryParam;
import com.accenture.masterdata.core.outEntity.OrganizationHierarchyOut;
import com.accenture.masterdata.core.outEntity.OrganizationOut;

/**
 * @author haibo.liu
 * @version 1.0
 * @created 05-Nov-2018 2:44:31 PM
 */
public interface OrganizationService {

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
	 * @param id
	 */
	public int deleteOrganizationHierarchy(Long eid, Long id);

	/**
	 * 
	 * @param id
	 */
    //批量删除
	public void batchDeleteOrganizationHierarchy(Long eid, BatchDeleteInput idList);
    
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

	/**
	 * 
	 * @param params
	 */
	public int addOrganization(OrganizationIn params);

	/**
	 * 
	 * @param params
	 */
	public int saveOrganization(OrganizationIn params);

	/**
	 * 
	 * @param id
	 */
	public int deleteOrganization(Long id);

	/**
	 * 
	 * @param params
	 */
	public List<OrganizationOut> selectOrganizations(OrganizationIn params);

	/**
	 * 
	 * @param id
	 */
	public OrganizationOut selectOrganization(Long id);

	/**
	 * 
	 * @param params
	 */
	public int selectOrganizationCount(OrganizationIn params);

	/**
	 * 
	 * @param params
	 */
	void inputInfoCheck(OrganizationIn params);

	/**
	 * 
	 * @param params
	 */
	void duplicationCheck(OrganizationIn params);

	/**
	 * 
	 * @param params
	 */
	void hierInputCheck(OrganizationHierarchyIn params);

	/**
	 * 
	 * @param params
	 */
	void hireDuplicationCheck(OrganizationHierarchyIn params);

}