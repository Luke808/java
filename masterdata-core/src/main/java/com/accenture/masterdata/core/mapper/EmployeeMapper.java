package com.accenture.masterdata.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.accenture.masterdata.core.inEntity.EmployeeIn;
import com.accenture.masterdata.core.inEntity.QueryParam;
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
	public int deleteEmployee(@Param(value="tenantid") Long tenantid, @Param(value="id") Long id, @Param(value="deleterUserId") Long uid);


	/**
	 * 
	 * @param idList: keylist
	 * 		  deleterUserId: user'id to do delete
	 */
	public void batchDeleteEmployees(@Param(value="tenantid") Long tenantid, @Param(value="deleterUserId") Long deleterUserId, @Param(value="idList") List<Long> idList);
	
	/**
	 * 
	 * @param params
	 */
	public int updateEmployee(EmployeeIn params);

	/**
	 * 
	 * @param params
	 */
	public List<EmployeeOut> selectEmployeeList(@Param(value="tenantid") Long tenantid, @Param(value="strParm") String strParm);

	/**
	 * 
	 * @param id
	 */
	public EmployeeOut selectEmployee(@Param(value="tenantid") Long tenantid, @Param(value="id") Long id);

	/**
	 * 
	 * @param params
	 */
	public int selectEmployeeCount(@Param(value="tenantid") Long tenantid, @Param(value="strParm") String strParm);

	/**
	 * 
	 * @param param
	 */
	public int checkEmployeeEid(@Param(value="tenantid") Long tenantid, @Param(value="id") Long id, @Param(value="eid") String eid);

	/**
	 * 
	 * @param param
	 */
	public int checkEmployeeName(@Param(value="tenantid") Long tenantid, @Param(value="id") Long id, @Param(value="name") String name);
}