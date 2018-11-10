package com.accenture.masterdata.employee.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.masterdata.common.querybuilder.BuilderParam;
import com.accenture.masterdata.core.inEntity.BatchDeleteInput;
import com.accenture.masterdata.core.inEntity.EmployeeIn;
import com.accenture.masterdata.core.inEntity.OrganizationHierarchyIn;
import com.accenture.masterdata.core.inEntity.QueryParam;
import com.accenture.masterdata.core.mapper.EmployeeMapper;
import com.accenture.masterdata.core.outEntity.EmployeeOut;
import com.accenture.masterdata.employee.service.EmployeeService;
import com.accenture.smsf.framework.boot.stereotype.Service;
import com.accenture.smsf.model.exception.ApplicationException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private BuilderParam builderParm;

	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Override
	public void inputCheck(EmployeeIn inputInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void duplicationCheck(EmployeeIn params) throws Exception  {

		//eid check
		if (employeeMapper.checkEmployeeEid(params.getId(), params.getEId())>0)
		{
			throw new ApplicationException(90001);
		}
		//name check
		if (employeeMapper.checkEmployeeName(params.getId(), params.getName())>0)
		{
			throw new ApplicationException(90002);
		}

	}

	@Override
	public void createOrUpdateEmployee(EmployeeIn params) throws Exception {

		long id = 0;
		if(params.getId() == null || params.getId() == 0) {
			//new
			params.setId(0L);
			params.setIsDeleted(0);
			params.setCreationTime(new Date());
			params.setCreatorUserId(1L);
			duplicationCheck(params);
			employeeMapper.inertEmployee(params);
		}
		else
		{
			//update
			Date date = new Date();  
			params.setLastModificationTime(date);
			params.setLastModifierUserId(1L);
			duplicationCheck(params);
			employeeMapper.updateEmployee(params);
		}
	}
	

	@Override
	public int deleteEmployee(Long id, Long uid) {
		return employeeMapper.deleteEmployee(id, uid);
	}
	
	@Override
	public void batchDeleteEmployees(BatchDeleteInput idList, Long uid) {
		employeeMapper.batchDeleteEmployees(idList.ids, uid);
	}

	@Override
	public List<EmployeeOut> selectEmployees(QueryParam params) {
		String strParmWithPageing = builderParm.buildParmWithPageing(params);
		List<EmployeeOut> list = employeeMapper.selectEmployeeList(strParmWithPageing);
		return list;
	}

	@Override
	public EmployeeOut selectEmployee(Long id) {

		EmployeeOut employee = new EmployeeOut();
		if(id == 0) {
			employee.setId(0L);
			employee.setTenantId(1L);
			employee.setCreationTime(new Date());
			employee.setCreatorUserId(1L);
		}
		else {
			employee = employeeMapper.selectEmployee(id);
		}
		
		return employee;
	}

	@Override
	public int selectEmployeeCount(QueryParam params) {
		// TODO Auto-generated method stub
		return 0;
	}

}
