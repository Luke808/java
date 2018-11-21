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
import com.google.common.collect.Maps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="Organization Controller",tags={"组织机构接口"})
@RestController
@Validated
@RequestMapping("/masterdata/org")
public class OrganizationController{
	
	@Autowired
	OrganizationService organization;

	Long eid = Long.parseLong("1");
	Long tenantid = Long.parseLong("1");
	
	@ApiOperation(value="通过组织ID,获取单条组织信息",notes="注意： 参数ID为必须项")
	@GetMapping("/get")
	public OrganizationOut get(@ApiParam(name="id",value="组织机构ID",required=true) @RequestParam("id") Long id) {
		return organization.selectOrganization(id);
	}

	@ApiOperation(value="根据通用组合查询条件,查询符合条件的组织信息",notes="注意： 参数param为必须项")
	@PostMapping("/getList")
	public Map<String, Object> getList(@ApiParam(name="param",value="通用查询条件对象",required=true) @RequestBody QueryParam param) {
		Map<String, Object> result = Maps.newHashMap();
		int count = organization.selectOrganizationCount(param);
		List<OrganizationOut> organizations = organization.selectOrganizations(param);
		
		result.put("count", count);
		result.put("list", organizations);
		
		return result;
	}
	
	@ApiOperation(value="插入一条组织机构信息",notes="注意： 参数param为必须项")
	@PostMapping("/add")
	public int insert(@ApiParam(name="param",value="要插入的组织数据",required=true) @RequestBody OrganizationIn params) throws Exception {
		return organization.saveOrganization(params);
	}

	@ApiOperation(value="修改一条组织机构信息",notes="注意： 参数param为必须项")
	@PutMapping("/save")
	public int update(@ApiParam(name="param",value="要修改的组织数据",required=true) @RequestBody OrganizationIn params) throws Exception {
		return organization.saveOrganization(params);
	}
	
	@ApiOperation(value="删除指定ID的机构信息",notes="注意： 参数id为必须项")
	@DeleteMapping("/delete")
	public int delete(@ApiParam(name="id",value="组织机构ID",required=true) @RequestParam("id") Long id) throws Exception {
		try
		{
			return organization.deleteOrganization(id);
		}
		catch(Exception ex)
		{
			throw ex;
		}
	}
	
	@ApiOperation(value="根据通用组合查询条件,查询符合条件的组织节点",notes="注意： 参数param为必须项")
	@PostMapping("getOrganizationTree")
	public  Map<String, Object> getOrganizationTree(@ApiParam(name="param",value="通用查询条件对象",required=true) @RequestBody QueryParam param) {
		Map<String, Object> result = Maps.newHashMap();
		List<OrganizationTree> organizations = organization.getOrganizationTree(param);
		result.put("list", organizations);
		return result;
	}

	@ApiOperation(value="根据父结点ID,获得组织机构树",notes="注意： 参数id为必须项")
	@PostMapping("getOrganizationTreeByParentId")
	public  Map<String, Object> getOrganizationTreeByParentId(@ApiParam(name="id",value="父结点ID",required=true) @RequestParam("id") Long id) {
		Map<String, Object> result = Maps.newHashMap();
		List<OrganizationTree> organizations = organization.getOrganizationTreeByParentId(id);
		result.put("list", organizations);
		return result;
	}	
	
	@ApiOperation(value="根据结点ID,获得该组织下的节点，返回TreeTable数据格式",notes="注意： 参数id为必须项")
	@GetMapping("getOrganizationTreeTable")
	public Map<String, Object> getOrganizationTreeTable(@ApiParam(name="id",value="组织机构ID",required=true) @RequestParam("id") Long id) {
		Map<String, Object> result = Maps.newHashMap();
		List<OrganizationTreeTable> organizations = organization.getOrganizationTreeTable(id);
		result.put("list", organizations);
		return result;
	}
	
	@ApiOperation(value="根据父结点ID,获得该组织下的节点，返回Ngx-tree-select数据格式",notes="注意： 参数parentId为必须项")
	@GetMapping("getOrganizationTreeSelect")
	public Map<String, Object> getOrganizationTreeSelect(@ApiParam(name="parentId",value="父结点ID",required=true) @RequestParam("parentId") Long parentId) {
		Map<String, Object> result = Maps.newHashMap();
		List<OrganizationTreeSelect> organizations = organization.getOrganizationTreeSelect(parentId);
		result.put("list", organizations);
		return result;
	}
}
