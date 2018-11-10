package com.accenture.masterdata.employee.service;

import java.util.List;

import com.accenture.masterdata.core.inEntity.BatchDeleteInput;
import com.accenture.masterdata.core.inEntity.EmployeeIn;
import com.accenture.masterdata.core.inEntity.OrganizationHierarchyIn;
import com.accenture.masterdata.core.inEntity.QueryParam;
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
	void duplicationCheck(EmployeeIn inputInfo) throws Exception ;

	/**
	 * 
	 * @param params
	 */
	public void createOrUpdateEmployee(EmployeeIn params) throws Exception ;

	/**
	 * 
	 * @param id
	 */
	public int deleteEmployee(Long id, Long uid);

	public void batchDeleteEmployees(BatchDeleteInput idList, Long uid);
	
	/**
	 * 
	 * @param params
	 */
	public List<EmployeeOut> selectEmployees(QueryParam params);

	/**
	 * 
	 * @param params
	 */
	public EmployeeOut selectEmployee(Long id);

	/**
	 * 
	 * @param params
	 */
	public int selectEmployeeCount(QueryParam params);

}