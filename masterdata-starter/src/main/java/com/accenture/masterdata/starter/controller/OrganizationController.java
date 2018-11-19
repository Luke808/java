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

import com.accenture.masterdata.core.inEntity.OrganizationIn;
import com.accenture.masterdata.core.inEntity.QueryParam;
import com.accenture.masterdata.core.outEntity.OrganizationOut;
import com.accenture.masterdata.core.outEntity.OrganizationTree;
import com.accenture.masterdata.core.outEntity.OrganizationTreeSelect;
import com.accenture.masterdata.core.outEntity.OrganizationTreeTable;
import com.accenture.masterdata.organization.service.OrganizationService;
import com.accenture.smsf.framework.starter.web.core.annotation.RestController;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@RestController
@Validated
@RequestMapping("/masterdata/org")
public class OrganizationController{
	
	@Autowired
	OrganizationService organization;

	Long eid = Long.parseLong("1");
	Long tenantid = Long.parseLong("1");
	
	@GetMapping("/get")
	public OrganizationOut get(@RequestParam("id") Long id) {
		return organization.selectOrganization(id);
	}
	
	@PostMapping("/getList")
	public Map<String, Object> getList(@RequestBody QueryParam param) {
		Map<String, Object> result = Maps.newHashMap();
		int count = organization.selectOrganizationCount(param);
		List<OrganizationOut> organizations = organization.selectOrganizations(param);
		
		result.put("count", count);
		result.put("list", organizations);
		
		return result;
	}
	
	@PostMapping("/add")
	public int insert(@RequestBody OrganizationIn params) throws Exception {
		return organization.saveOrganization(params);
	}
	
	@PutMapping("/save")
	public int update(@RequestBody OrganizationIn params) throws Exception {
		return organization.saveOrganization(params);
	}
	
	@DeleteMapping("/delete")
	public int delete(@RequestParam("id") Long id) throws Exception {
		try
		{
			return organization.deleteOrganization(id);
		}
		catch(Exception ex)
		{
			throw ex;
		}
	}
	
	@PostMapping("getOrganizationTree")
	public  Map<String, Object> getOrganizationTree(@RequestBody QueryParam param) {
		Map<String, Object> result = Maps.newHashMap();
		List<OrganizationTree> organizations = organization.getOrganizationTree(param);
		result.put("list", organizations);
		return result;
	}
	
	@PostMapping("getOrganizationTreeTable")
	public Map<String, Object> getOrganizationTreeTable(@RequestParam("id") Long id) {
		Map<String, Object> result = Maps.newHashMap();
		List<OrganizationTreeTable> organizations = organization.getOrganizationTreeTable(id);
		result.put("list", organizations);
		return result;
	}
	
	@PostMapping("getOrganizationTreeSelect")
	public Map<String, Object> getOrganizationTreeSelect(@RequestParam("parentId") Long parentId) {
		Map<String, Object> result = Maps.newHashMap();
		List<OrganizationTreeSelect> organizations = organization.getOrganizationTreeSelect(parentId);
		result.put("list", organizations);
		return result;
	}
	
}
