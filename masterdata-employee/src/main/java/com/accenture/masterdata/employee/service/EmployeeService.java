package com.accenture.masterdata.employee.service;

import java.util.List;

import com.accenture.masterdata.core.inEntity.EmployeeIn;
import com.accenture.masterdata.core.inEntity.MasterdataSelectInput;
import com.accenture.masterdata.core.outEntity.EmployeeOut;

/**
 * @author haibo.liu
 * @version 1.0
 * @created 05-Nov-2018 2:52:54 PM
 */
public interface EmployeeService {

	/**
	 * 
	 * @param inputInfo
	 */
	void inputCheck(EmployeeIn inputInfo);

	/**
	 * 
	 * @param inputInfo
	 */
	void duplicationCheck(EmployeeIn inputInfo);

	/**
	 * 
	 * @param params
	 */
	public int addEmployee(EmployeeIn params);

	/**
	 * 
	 * @param params
	 */
	public int saveEmployee(EmployeeIn params);

	/**
	 * 
	 * @param id
	 */
	public int deleteEmployee(int id);

	/**
	 * 
	 * @param params
	 */
	public List<EmployeeOut> selectEmployees(MasterdataSelectInput params);

	/**
	 * 
	 * @param params
	 */
	public EmployeeOut selectEmployeeDetail(int id);

	/**
	 * 
	 * @param params
	 */
	public int selectEmployeeCount(MasterdataSelectInput params);

}