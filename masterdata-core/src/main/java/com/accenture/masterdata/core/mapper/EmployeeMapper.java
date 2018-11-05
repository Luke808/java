package com.accenture.masterdata.core.mapper;

import java.util.List;

import com.accenture.masterdata.core.inEntity.EmployeeIn;
import com.accenture.masterdata.core.inEntity.MasterdataSelectInput;
import com.accenture.masterdata.core.outEntity.EmployeeOut;

/**
 * @author haibo.liu
 * @version 1.0
 * @created 02-Nov-2018 5:48:57 PM
 */
public interface EmployeeMapper {

	/**
	 * 
	 * @param params
	 */
	public int inertEmployee(EmployeeIn params);

	/**
	 * 
	 * @param id
	 */
	public int deleteEmployee(int id);

	/**
	 * 
	 * @param params
	 */
	public int updateEmployee(EmployeeIn params);

	/**
	 * 
	 * @param params
	 */
	public List<EmployeeOut> selectEmployeeList(MasterdataSelectInput params);

	/**
	 * 
	 * @param id
	 */
	public EmployeeOut selectEmployee(int id);

	/**
	 * 
	 * @param params
	 */
	public int selectEmployeeCount(MasterdataSelectInput params);

}