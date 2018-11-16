package com.accenture.masterdata.organization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.accenture.masterdata.organization.service.impl.OrganizationHierarchyServiceImpl;
import com.accenture.masterdata.organization.service.impl.OrganizationServiceImpl;

@Configuration
public class OrganizationConfig {
	
	@Bean
	public OrganizationServiceImpl organizationServiceImpl() {
		return new OrganizationServiceImpl();
	}
	
	@Bean
	public OrganizationHierarchyServiceImpl organizationHierarchyServiceImpl() {
		return new OrganizationHierarchyServiceImpl();
	}
}
