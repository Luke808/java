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

import com.accenture.masterdata.core.inEntity.BatchDeleteInput;
import com.accenture.masterdata.core.inEntity.QueryParam;
import com.accenture.masterdata.core.outEntity.OrganizationHierarchy;
import com.accenture.masterdata.core.outEntity.dropdownList;
import com.accenture.masterdata.organization.service.OrganizationHierarchyService;
import com.accenture.masterdata.starter.Permissions;
import com.accenture.smsf.authority.permission.loader.annotation.Permission;
import com.accenture.smsf.framework.starter.web.core.annotation.RestController;
import com.google.common.collect.Maps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="Hierarchy Controller",tags={"组织层级接口"})
@RestController
@Validated
@RequestMapping("/masterdata/orgHierarchy")
public class OrganizationHierarchyController{
	@Autowired
	OrganizationHierarchyService hierarchy;

	@Permission(values= {Permissions.MASTERDATA_HIERARCHY_VIEW})
	@ApiOperation(value="通过层级ID,获取单条层级信息",notes="注意： 参数ID为必须项")
	@GetMapping("/get")
	public Map<String, Object> get(@ApiParam(name="id",value="组织层级ID",required=true) @RequestParam("id") Long id) {
		OrganizationHierarchy hierarchies = hierarchy.selectOrganizationHierarchy(id);
		Map<String, Object> result = Maps.newHashMap();
		result.put("data", hierarchies);
		return result;
	}

	@Permission(values= {Permissions.MASTERDATA_HIERARCHY_VIEW})
	@ApiOperation(value="根据通用组合查询条件,获取符合条件的层级信息",notes="注意： 参数param为必须项")
	@PostMapping("/getList")
	public Map<String, Object> getList(@ApiParam(name="param",value="通用查询条件对象",required=true) @RequestBody QueryParam param) {
		Map<String, Object> result = Maps.newHashMap();
		int count = hierarchy.selectOrganizationHierCount(param);
		List<OrganizationHierarchy> hierarchies = hierarchy.selectOrganizationHierarchys(param);
		
		result.put("count", count);
		result.put("list", hierarchies);
		
		return result;
	}
	
	@Permission(values= {Permissions.MASTERDATA_HIERARCHY_VIEW})
	@ApiOperation(value="获取层级下拉列表",notes="注意： 无需参数")
	@GetMapping("/getDropDown")
	public Map<String, Object> getDropDown() {
		Map<String, Object> result = Maps.newHashMap();
		List<dropdownList> hierarchies = hierarchy.getDropDown();
		result.put("list", hierarchies);		
		return result;
	}

	@Permission(values= {Permissions.MASTERDATA_HIERARCHY_MANAGE_EDIT, Permissions.MASTERDATA_HIERARCHY_MANAGE_ADD})
	@ApiOperation(value="新建或修改Hierarchy信息;新建时,ID请填0;修改时,ID请为要修改记录的ID",notes="注意： 参数params为必须项")
	@PutMapping("/createOrUpdate")
	public void createOrUpdateOrganizationHierarchy(@ApiParam(name="params",value="要修改的层级数据",required=true) @RequestBody OrganizationHierarchy params) throws Exception {
		try
		{
			hierarchy.createOrUpdateOrganizationHierarchy(params);
		}
		catch (Exception ex) {
			throw ex;
		}
	}
	
	@Permission(values= {Permissions.MASTERDATA_HIERARCHY_MANAGE_DELETE})
	@ApiOperation(value="删除指定ID的组织层级",notes="注意： 参数id为必须项")
	@DeleteMapping("/delete")
	public int delete(@ApiParam(name="id",value="组织层级ID",required=true) @RequestParam("id") Long id)  throws Exception {
		return hierarchy.deleteOrganizationHierarchy(id);
	}
	
	@Permission(values= {Permissions.MASTERDATA_HIERARCHY_MANAGE_DELETE})
	@ApiOperation(value="批量删除组织层级",notes="注意： 参数idList为必须项")
	@PostMapping("/batchDelete")
	public void batchDeleteOrganizationHierarchy(@ApiParam(name="idList",value="层级ID list",required=true) @RequestBody BatchDeleteInput idList)  throws Exception {
		hierarchy.batchDeleteOrganizationHierarchy(idList);
	}
	
	@Permission(values= {Permissions.MASTERDATA_HIERARCHY_VIEW})
	@ApiOperation(value="获得指定层级下可添加的下级层级列表",notes="注意： 参数curLevel为必须项")
	@GetMapping("/getNextLevel")
	public Map<String, Object> getNextLevel(@ApiParam(name="curLevel",value="指定层级(非ID)",required=true) @RequestParam("curLevel") Long curLevel) {
		Map<String, Object> result = Maps.newHashMap();
		List<OrganizationHierarchy> hierarchies = hierarchy.getNextLevel(curLevel);
		result.put("list", hierarchies);
		return result;
	}
	
}
