package com.accenture.masterdata.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.accenture.masterdata.employee.service.impl.EmployeeServiceImpl;

@Configuration
public class EmployeeConfig {

	@Bean
	public EmployeeServiceImpl employeeServiceImpl() {
		return new EmployeeServiceImpl();
	}
}
