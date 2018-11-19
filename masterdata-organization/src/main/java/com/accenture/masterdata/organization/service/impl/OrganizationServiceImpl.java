package com.accenture.masterdata.organization.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.masterdata.common.querybuilder.BuilderParam;
import com.accenture.masterdata.core.inEntity.OrganizationIn;
import com.accenture.masterdata.core.inEntity.QueryParam;
import com.accenture.masterdata.core.mapper.OrganizationMapper;
import com.accenture.masterdata.core.outEntity.OrganizationOut;
import com.accenture.masterdata.core.outEntity.OrganizationTreeSelect;
import com.accenture.masterdata.core.outEntity.OrganizationTreeSelectState;
import com.accenture.masterdata.core.outEntity.OrganizationTreeTable;
import com.accenture.masterdata.organization.service.OrganizationService;
import com.accenture.smsf.framework.boot.stereotype.Service;
import com.accenture.smsf.framework.starter.web.principal.TenantHolder;
import com.accenture.smsf.model.exception.ApplicationException;
import com.google.common.collect.Lists;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private BuilderParam builderParm;
	
	@Autowired
	private OrganizationMapper organization;

	@Override
	public int saveOrganization(OrganizationIn params) throws Exception {
		
		// 返回值定义
		int result = 0;
		
		// 重复项check
		duplicationCheck(params);
		
		// 设置租户id
		params.setTenantId(Long.valueOf(TenantHolder.get()));
		
		// 保存数据
		// 更新
		if ( params.getId() != null && params.getId() > 0 ) {
			result = organization.updateOrganization(params);
		}
		// 新增
		else {
			result = organization.insertOrganization(params);
		}

		return result;
	}

	@Override
	public int deleteOrganization(Long id) {
		return organization.deleteOrganization(id);
	}

	@Override
	public List<OrganizationOut> selectOrganizations(QueryParam params) {
		String strParmWithPageing = builderParm.buildParmWithPageing(params);
		return organization.selectOrganizationList(strParmWithPageing);
	}

	@Override
	public OrganizationOut selectOrganization(Long id) {
		return organization.selectOrganization(id);
	}

	@Override
	public int selectOrganizationCount(QueryParam params) {
		String strParmWithPageing = builderParm.buildParmNoPageing(params);
		return organization.selectOrganizationCount(strParmWithPageing);
	}

	@Override
	public void inputInfoCheck(OrganizationIn params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void duplicationCheck(OrganizationIn params) throws Exception {
		
		// 姓名重复check
		String where = " and org.name = '" + params.getName() + "' and org.tenantId = " + TenantHolder.get();
		if ( params.getId() != null && params.getId() > 0 ) {
			where += " and org.id <>" + params.getId().toString();
		}
		int count = organization.selectOrganizationCount(where);

		if( count > 0 ) {
			throw new ApplicationException(90001);
		}
		
		// code重复check
		where = " and org.code = '" + params.getCode() + "' and org.tenantId = " + TenantHolder.get();
		if ( params.getId() != null && params.getId() > 0 ) {
			where += " and org.id <>" + params.getId().toString();
		}
		count = organization.selectOrganizationCount(where);
		
		if( count > 0 ) {
			throw new ApplicationException(90002);
		}
		
	}

	public List<OrganizationTreeTable> getOrganizationTreeTable(Long id){
		List<OrganizationTreeTable> treeTable = Lists.newArrayList();		
		String strWhere = " and org.tenantId = " + TenantHolder.get() + " and org.parentId = " + id;
		List<OrganizationOut> subOrgList = organization.selectOrganizationList(strWhere);
		if(subOrgList != null && subOrgList.size() > 0) {
			OrganizationOut lineno = new OrganizationOut();
			lineno.setLineno(0L);
			for(OrganizationOut node : subOrgList) {
				OrganizationTreeTable treeTableRow = new OrganizationTreeTable();
				lineno.setLineno(lineno.getLineno() + 1);
				node.setLineno(lineno.getLineno());
				treeTableRow.setData(node);
//				treeTableRow.setChildren(getOrganizationTreeTableSub(node, lineno));
				treeTable.add(treeTableRow);
			}
		}
		return treeTable;
	}
	
	private List<OrganizationTreeTable> getOrganizationTreeTableSub(OrganizationOut parentNode, OrganizationOut lineno){
		List<OrganizationTreeTable> treeTable = Lists.newArrayList();		
		String strWhere = " and org.tenantId = " + TenantHolder.get() + " and org.parentId = " + parentNode.getId();
		List<OrganizationOut> subOrgList = organization.selectOrganizationList(strWhere);
		if(subOrgList != null && subOrgList.size() > 0) {
			for(OrganizationOut node : subOrgList) {
				OrganizationTreeTable treeTableRow = new OrganizationTreeTable();
				lineno.setLineno(lineno.getLineno() + 1);
				node.setLineno(lineno.getLineno());
				treeTableRow.setData(node);
				treeTableRow.setChildren(getOrganizationTreeTableSub(node, lineno));
				treeTable.add(treeTableRow);
			}
		}
		return treeTable;
	}
	
	public List<OrganizationTreeSelect> getOrganizationTreeSelect(Long parentId){
		List<OrganizationTreeSelect> treeTable = Lists.newArrayList();		
		String strWhere = " and org.tenantId = " + TenantHolder.get() + " and org.parentId = " + parentId;
		List<OrganizationOut> subOrgList = organization.selectOrganizationList(strWhere);
		if(subOrgList != null && subOrgList.size() > 0) {
			for(OrganizationOut node : subOrgList) {
				OrganizationTreeSelect treeSelectRow = new OrganizationTreeSelect();
				treeSelectRow.setId(node.getId());
				treeSelectRow.setText(node.getName());
				treeSelectRow.setParent(String.valueOf(node.getParentId()));
				OrganizationTreeSelectState state = new OrganizationTreeSelectState();
				state.setDisabled(false);
				state.setOpened(false);
				state.setSelected(false);
				treeSelectRow.setState(state);
				treeSelectRow.setChildren(getOrganizationTreeSelectSub(node));
				treeTable.add(treeSelectRow);
			}
		}

		if(parentId == 0)
		{
			List<OrganizationTreeSelect> nodes = Lists.newArrayList();
			OrganizationTreeSelect rootNode = new OrganizationTreeSelect();
			rootNode.setId(-1L);
			rootNode.setText("组织机构");
			rootNode.setParent("");
			rootNode.setChildren(treeTable);
			OrganizationTreeSelectState state = new OrganizationTreeSelectState();
			state.setDisabled(false);
			state.setOpened(false);
			state.setSelected(false);
			rootNode.setState(state);
			nodes.add(rootNode);
			treeTable = nodes;
		}
		
		return treeTable;
	}
	
	private List<OrganizationTreeSelect> getOrganizationTreeSelectSub(OrganizationOut parentNode){
		List<OrganizationTreeSelect> treeTable = Lists.newArrayList();			
		String strWhere = " and org.tenantId = " + TenantHolder.get() + " and org.parentId = " + parentNode.getId();
		List<OrganizationOut> subOrgList = organization.selectOrganizationList(strWhere);
		if(subOrgList != null && subOrgList.size() > 0) {
			for(OrganizationOut node : subOrgList) {
				OrganizationTreeSelect treeSelectRow = new OrganizationTreeSelect();
				treeSelectRow.setId(node.getId());
				treeSelectRow.setText(node.getName());
				treeSelectRow.setParent(String.valueOf(node.getParentId()));
				OrganizationTreeSelectState state = new OrganizationTreeSelectState();
				state.setDisabled(false);
				state.setOpened(false);
				state.setSelected(false);
				treeSelectRow.setState(state);
				treeSelectRow.setChildren(getOrganizationTreeSelectSub(node));
				treeTable.add(treeSelectRow);
			}
		}
		return treeTable;
	}
	
}
