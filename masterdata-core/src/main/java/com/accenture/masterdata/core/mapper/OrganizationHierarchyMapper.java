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
	public OrganizationHierarchyOut selectOrganizationHierarchy(@Param(value="id") Long id);

	/**
	 * 
	 * @param params
	 */
	public Integer selectOrganizationHierarchyCount(@Param(value="strParm") String queryQarm);

	/**
	 * 
	 * @param param
	 */
	public int deleteOrganizationHierarchy(@Param(value="deleterUserId") Long deleterUserId, @Param(value="id") Long id);

	/**
	 * 
	 * @param param
	 */
	public void batchDeleteOrganizationHierarchy(@Param(value="deleterUserId") Long deleterUserId, @Param(value="idList") List<Long> idList);

	/**
	 * 
	 * @param param
	 */
	public int checkOrganizationHierLevel(@Param(value="id") Long id, @Param(value="level") Long level);

	/**
	 * 
	 * @param param
	 */
	public int checkOrganizationHierName(@Param(value="id") Long id, @Param(value="name") String name);
	
}