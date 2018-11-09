package com.accenture.masterdata.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	public List<OrganizationHierarchyOut> selectOrganizatioHierarchyieList(@Param(value="strParm") String strParm);

	/**
	 * 
	 * @param param
	 */
	public OrganizationHierarchyOut selectOrganizationHierarchy(@Param(value="strParm") String strParm);

	/**
	 * 
	 * @param params
	 */
	public Integer selectOrganizationHierarchyCount(String queryQarm);

	/**
	 * 
	 * @param param
	 */
	public int deleteOrganizationHierarchy(int param);

}