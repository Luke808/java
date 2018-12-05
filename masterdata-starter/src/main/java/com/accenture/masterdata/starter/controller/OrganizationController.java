package com.accenture.masterdata.starter.controller;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accenture.masterdata.core.inentity.OrganizationIn;
import com.accenture.masterdata.core.inentity.QueryParam;
import com.accenture.masterdata.core.outentity.OrganizationOut;
import com.accenture.masterdata.core.outentity.OrganizationTree;
import com.accenture.masterdata.core.outentity.OrganizationTreeSelect;
import com.accenture.masterdata.core.outentity.OrganizationTreeTable;
import com.accenture.masterdata.organization.service.OrganizationService;
import com.accenture.masterdata.starter.Permissions;
import com.accenture.smsf.authority.permission.loader.annotation.Permission;
import com.accenture.smsf.framework.starter.web.core.annotation.RestController;
import com.google.common.collect.Maps;

@Api(value="Organization Controller",tags={"组织机构接口"})
@RestController
@Validated
@RequestMapping("/masterdata/org")
public class OrganizationController{
	
	@Autowired
	OrganizationService organization;

	@Permission(values= {Permissions.MASTERDATA_ORGANIZATION_VIEW})	
	@ApiOperation(value="通过组织ID,获取单条组织信息",notes="参数：id")
	@GetMapping("/get")
	public OrganizationOut get( @RequestParam("id") Long id) {
		return organization.selectOrganization(id);
	}

	@Permission(values= {Permissions.MASTERDATA_ORGANIZATION_VIEW})	
	@ApiOperation(value="根据通用组合查询条件,查询符合条件的组织信息",notes="参数：QueryParam对象")
	@PostMapping("/getList")
	public Map<String, Object> getList(@RequestBody QueryParam param) {
		Map<String, Object> result = Maps.newHashMap();
		int count = organization.selectOrganizationCount(param);
		List<OrganizationOut> organizations = organization.selectOrganizations(param);
		
		result.put("count", count);
		result.put("list", organizations);
		
		return result;
	}
	
	@Permission(values= {Permissions.MASTERDATA_ORGANIZATION_MANAGE_ADD})
	@ApiOperation(value="插入一条组织机构信息",notes="参数:OrganizationIn对象 ")
	@PostMapping("/add")
	public int insert(@RequestBody OrganizationIn params) throws Exception {
		return organization.saveOrganization(params);
	}

	@Permission(values= {Permissions.MASTERDATA_ORGANIZATION_MANAGE_EDIT})
	@ApiOperation(value="修改一条组织机构信息",notes="注意： 参数：OrganizationIn对象")
	@PutMapping("/save")
	public int update(@RequestBody OrganizationIn params) throws Exception {
		return organization.saveOrganization(params);
	}
	
	@Permission(values= {Permissions.MASTERDATA_ORGANIZATION_MANAGE_DELETE})
	@ApiOperation(value="删除指定ID的机构信息",notes="参数:id")
	@DeleteMapping("/delete")
	public int delete(Long id) throws Exception {
		try
		{
			return organization.deleteOrganization(id);
		}
		catch(Exception ex)
		{
			throw ex;
		}
	}
	
	@Permission(values= {Permissions.MASTERDATA_ORGANIZATION_VIEW})
	@ApiOperation(value="根据通用组合查询条件,查询符合条件的组织节点",notes="参数:param 通用查询条件")
	@PostMapping("/getOrganizationTree")
	public  Map<String, Object> getOrganizationTree(@RequestBody QueryParam param) {
		Map<String, Object> result = Maps.newHashMap();
		List<OrganizationTree> organizations = organization.getOrganizationTree(param);
		result.put("list", organizations);
		return result;
	}

	@Permission(values= {Permissions.MASTERDATA_ORGANIZATION_VIEW})
	@ApiOperation(value="根据父结点ID,获得组织机构树",notes="参数:id")
	@PostMapping("/getOrganizationTreeByParentId")
	public  Map<String, Object> getOrganizationTreeByParentId(@RequestParam("id") Long id) {
		Map<String, Object> result = Maps.newHashMap();
		List<OrganizationTree> organizations = organization.getOrganizationTreeByParentId(id);
		result.put("list", organizations);
		return result;
	}	
	
	@Permission(values= {Permissions.MASTERDATA_ORGANIZATION_VIEW})
	@ApiOperation(value="根据结点ID,获得该组织下的节点，返回TreeTable数据格式",notes="参数:id")
	@GetMapping("/getOrganizationTreeTable")
	public Map<String, Object> getOrganizationTreeTable(@RequestParam("id") Long id) {
		Map<String, Object> result = Maps.newHashMap();
		List<OrganizationTreeTable> organizations = organization.getOrganizationTreeTable(id);
		result.put("list", organizations);
		return result;
	}
	
	@Permission(values= {Permissions.MASTERDATA_ORGANIZATION_VIEW})
	@ApiOperation(value="根据父结点ID,获得该组织下的节点，返回Ngx-tree-select数据格式",notes="注意： 参数parentId为必须项")
	@GetMapping("/getOrganizationTreeSelect")
	public Map<String, Object> getOrganizationTreeSelect(@RequestParam("parentId") Long parentId) {
		Map<String, Object> result = Maps.newHashMap();
		List<OrganizationTreeSelect> organizations = organization.getOrganizationTreeSelect(parentId);
		result.put("list", organizations);
		return result;
	}
	
	@Permission(values= {Permissions.MASTERDATA_ORGANIZATION_VIEW})
	@ApiOperation(value="根据结点ID,获得该组织下的节点，返回Ngx-tree-select数据格式",notes="参数:id")
	@GetMapping("/getOrganizationTreeOne")
	public Map<String, Object> getOrganizationTreeOne(@RequestParam("id") Long id) {
		Map<String, Object> result = Maps.newHashMap();
		OrganizationTree organizationData = organization.getOrganizationTreeOne(id);
		result.put("data", organizationData);
		return result;
	}
}
