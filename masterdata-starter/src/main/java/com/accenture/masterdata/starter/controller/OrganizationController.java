package com.accenture.masterdata.starter.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accenture.masterdata.core.inEntity.OrganizationIn;
import com.accenture.masterdata.core.outEntity.OrganizationOut;
import com.accenture.masterdata.organization.service.OrganizationService;
import com.accenture.smsf.framework.starter.web.core.annotation.RestController;
import com.google.common.collect.Maps;

@RestController
@Validated
@RequestMapping("/masterdata/org")
public class OrganizationController {
	
	@Autowired
	OrganizationService organization;

	@GetMapping("/get")
	public OrganizationOut getOrganization(@RequestParam("id") int id) {
		return organization.selectOrganization(id);
	}
	
	@GetMapping("/getList")
	public Map<String, Object> getOrganizations(OrganizationIn param) {
		Map<String, Object> result = Maps.newHashMap();
		int count = organization.selectOrganizationCount(param);
		List<OrganizationOut> organizations = organization.selectOrganizations(param);
		
		result.put("count", count);
		result.put("list", organizations);
		
		return result;
	}
	
	@PostMapping("/add")
	public int insertOrg(@RequestParam("org_data") OrganizationIn params) {
		return organization.addOrganization(params);
	}
	
	@PutMapping("/save")
	public int updateOrg(@RequestParam("org_data") OrganizationIn params) {
		return organization.saveOrganization(params);
	}
	
	@DeleteMapping("/delete")
	public int deleteOrg(@RequestParam("id")int id) {
		return organization.deleteOrganization(id);
	}
}
