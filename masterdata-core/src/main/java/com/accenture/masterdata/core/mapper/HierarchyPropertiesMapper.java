package com.accenture.masterdata.core.mapper;

import java.util.List;

import com.accenture.masterdata.core.inEntity.HierarchyPropertiesIn;
import com.accenture.masterdata.core.outEntity.HierarchyPropertiesOut;

/**
 * @author haibo.liu
 * @version 1.0
 * @created 05-Nov-2018 2:44:29 PM
 */
public interface HierarchyPropertiesMapper {

	/**
	 * 
	 * @param param
	 */
	public int insertProperty(HierarchyPropertiesIn param);

	/**
	 * 
	 * @param param
	 */
	public int updateProperties(HierarchyPropertiesIn param);

	/**
	 * 
	 * @param params
	 */
	public List<HierarchyPropertiesOut> selectPropertyList(HierarchyPropertiesIn params);

	/**
	 * 
	 * @param param
	 */
	public HierarchyPropertiesOut selectProperty(int param);

	/**
	 * 
	 * @param params
	 */
	public int selectPropertiesCount(HierarchyPropertiesIn params);

	/**
	 * 
	 * @param param
	 */
	public int deleteProperty(int param);

}