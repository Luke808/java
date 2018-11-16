package com.accenture.masterdata.organization.service;

import java.util.List;

import com.accenture.masterdata.core.inEntity.OrganizationIn;
import com.accenture.masterdata.core.inEntity.QueryParam;
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
	 * @throws Exception 
	 */
	public int saveOrganization(OrganizationIn params) throws Exception;

	/**
	 * 
	 * @param id
	 */
	public int deleteOrganization(Long id);

	/**
	 * 
	 * @param params
	 */
	public List<OrganizationOut> selectOrganizations(QueryParam params);

	/**
	 * 
	 * @param id
	 */
	public OrganizationOut selectOrganization(Long id);

	/**
	 * 
	 * @param params
	 */
	public int selectOrganizationCount(QueryParam params);

	/**
	 * 
	 * @param params
	 */
	void inputInfoCheck(OrganizationIn params);

	/**
	 * 
	 * @param params
	 * @throws Exception 
	 */
	void duplicationCheck(OrganizationIn params) throws Exception;

}