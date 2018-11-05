package com.accenture.masterdata.core.mapper;

import java.util.List;

import com.accenture.masterdata.core.inEntity.OrganizationHierarchyIn;
import com.accenture.masterdata.core.outEntity.OrganizationHierarchyOut;

/**
 * @author haibo.liu
 * @version 1.0
 * @created 05-Nov-2018 2:44:29 PM
 */
public interface OrganizationHierarchyMapper {

	/**
	 * 
	 * @param param
	 */
	public int insertOrganizationHierarchy(OrganizationHierarchyIn param);

	/**
	 * 
	 * @param param
	 */
	public int updateOrganizationHierarchy(OrganizationHierarchyIn param);

	/**
	 * 
	 * @param params
	 */
	public List<OrganizationHierarchyOut> selectOrganizatioHierarchyieList(OrganizationHierarchyIn params);

	/**
	 * 
	 * @param param
	 */
	public OrganizationHierarchyOut selectOrganizationHierarchy(int param);

	/**
	 * 
	 * @param params
	 */
	public int selectOrganizationHierarchyCount(OrganizationHierarchyIn params);

	/**
	 * 
	 * @param param
	 */
	public int deleteOrganizationHierarchy(int param);

}