package com.accenture.masterdata.core.mapper;

import com.accenture.masterdata.core.inentity.OrganizationIn;
import com.accenture.masterdata.core.outentity.OrganizationOut;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author haibo.liu
 * @version 1.0
 * @created 05-Nov-2018 2:44:30 PM
 */
public interface OrganizationMapper {

	/**
	 * 
	 * @param param
	 */
	public int insertOrganization(OrganizationIn param);

	/**
	 * 
	 * @param param
	 */
	public int updateOrganization(OrganizationIn param);

	/**
	 * 
	 * @param params
	 */
	public List<OrganizationOut> selectOrganizationList(@Param(value="strParam") String strparam);
	
	/**
	 * 
	 * @param params
	 */
	public List<OrganizationOut> selectOrganizationTree(@Param(value="strParam") String strparam);

	/**
	 * 
	 * @param param
	 */
	public OrganizationOut selectOrganization(Long param);

	/**
	 * 
	 * @param strparam
	 */
	public int selectOrganizationCount(@Param(value="strParam") String strparam);

	/**
	 * 
	 * @param param
	 */
	public int deleteOrganization(Long param);

	/**
	 * 
	 * @param id
	 */
	public List<OrganizationOut> selectOrganizationChild(Long id);

	/**
	 *
	 * @param id
	 */
	public int updateOrganizaitonLikeCode(Map<String, Object> param);
}