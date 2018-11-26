package com.accenture.masterdata.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.accenture.masterdata.core.outentity.OrganizationHierarchy;

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
	public int insertOrganizationHierarchy(OrganizationHierarchy param);

	/**
	 * 
	 * @param param
	 */
	public int updateOrganizationHierarchy(OrganizationHierarchy param);

	/**
	 * 
	 * @param params
	 */
	public List<OrganizationHierarchy> selectOrganizatioHierarchyieList(@Param(value="strParm") String strParm);

	/**
	 * 
	 * @param param
	 */
	public OrganizationHierarchy selectOrganizationHierarchy(@Param(value="id") Long id);

	/**
	 * 
	 * @param params
	 */
	public Integer selectOrganizationHierarchyCount(@Param(value="strParm") String queryQarm);

	/**
	 * 
	 * @param param
	 */
	public int deleteOrganizationHierarchy(@Param(value="id") Long id, @Param(value="deleterUserId") String eId);

	/**
	 * 
	 * @param param
	 */
	public void batchDeleteOrganizationHierarchy(@Param(value="idList") List<Long> idList, @Param(value="deleterUserId") String eId);

	
}