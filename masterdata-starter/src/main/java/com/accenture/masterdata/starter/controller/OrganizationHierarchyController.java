package com.accenture.masterdata.starter.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accenture.masterdata.core.inEntity.OrganizationHierarchyIn;
import com.accenture.masterdata.core.inEntity.QueryParam;
import com.accenture.masterdata.core.outEntity.OrganizationHierarchyOut;
import com.accenture.masterdata.organization.service.OrganizationService;
import com.accenture.smsf.framework.starter.web.core.annotation.RestController;
import com.google.common.collect.Maps;

@RestController
@Validated
@RequestMapping("/masterdata/orgHierarchy")
public class OrganizationHierarchyController {
	@Autowired
	OrganizationService organization;

	@GetMapping("/get")
	public OrganizationHierarchyOut get(@RequestParam("id") int id) {
		return organization.selectOrganizationHierarchy(id);
	}
	
	@PostMapping("/getList")
	public Map<String, Object> getList(@RequestBody QueryParam param) {
		Map<String, Object> result = Maps.newHashMap();
		int count = organization.selectOrganizationHierCount(param);
		List<OrganizationHierarchyOut> hierarchies = organization.selectOrganizationHierarchys(param);
		
		result.put("count", count);
		result.put("list", hierarchies);
		
		return result;
	}
	
	@PostMapping("/add")
	public int insert(@RequestBody @RequestParam("org_data") OrganizationHierarchyIn params) {
		return organization.addOrganizationHierarchy(params);
	}
	
	@PutMapping("/save")
	public int update(@RequestBody @RequestParam("org_data") OrganizationHierarchyIn params) {
		return organization.saveOrganizationHierarchy(params);
	}
	
	@DeleteMapping("/delete")
	public int delete(@RequestParam("id")int id) {
		return organization.deleteOrganizationHierarchy(id);
	}
}
