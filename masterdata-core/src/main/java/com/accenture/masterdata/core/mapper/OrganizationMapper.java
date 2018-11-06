package com.accenture.masterdata.core.mapper;

import java.util.List;

import com.accenture.masterdata.core.inEntity.QueryParam;
import com.accenture.masterdata.core.inEntity.OrganizationIn;
import com.accenture.masterdata.core.outEntity.OrganizationOut;

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
	public List<OrganizationOut> selectOrganizationList(QueryParam params);

	/**
	 * 
	 * @param param
	 */
	public OrganizationOut selectOrganization(int param);

	/**
	 * 
	 * @param params
	 */
	public int selectOrganizationCount(QueryParam params);

	/**
	 * 
	 * @param param
	 */
	public int deleteOrganization(int param);

	/**
	 * 
	 * @param id
	 */
	public List<OrganizationOut> selectOrganizationChild(int id);

}