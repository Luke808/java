package com.accenture.masterdata.employee.service.impl;

import java.util.List;

import com.accenture.masterdata.core.inEntity.EmployeeIn;
import com.accenture.masterdata.core.inEntity.MasterdataSelectInput;
import com.accenture.masterdata.core.outEntity.EmployeeOut;
import com.accenture.masterdata.employee.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public void inputCheck(EmployeeIn inputInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void duplicationCheck(EmployeeIn inputInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public int addEmployee(EmployeeIn params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveEmployee(EmployeeIn params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmployee(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EmployeeOut> selectEmployees(MasterdataSelectInput params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeOut selectEmployeeDetail(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectEmployeeCount(MasterdataSelectInput params) {
		// TODO Auto-generated method stub
		return 0;
	}

}
