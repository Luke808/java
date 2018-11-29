package com.accenture.masterdata.organization.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.masterdata.common.querybuilder.BuilderParam;
import com.accenture.masterdata.core.inentity.OrganizationIn;
import com.accenture.masterdata.core.inentity.QueryParam;
import com.accenture.masterdata.core.mapper.OrganizationMapper;
import com.accenture.masterdata.core.outentity.OrganizationHierarchy;
import com.accenture.masterdata.core.outentity.OrganizationOut;
import com.accenture.masterdata.core.outentity.OrganizationTree;
import com.accenture.masterdata.core.outentity.OrganizationTreeSelect;
import com.accenture.masterdata.core.outentity.OrganizationTreeSelectState;
import com.accenture.masterdata.core.outentity.OrganizationTreeTable;
import com.accenture.masterdata.organization.service.OrganizationHierarchyService;
import com.accenture.masterdata.organization.service.OrganizationService;
import com.accenture.smsf.framework.boot.stereotype.Service;
import com.accenture.smsf.framework.starter.web.principal.PrincipalHolder;
import com.accenture.smsf.framework.starter.web.principal.TenantHolder;
import com.accenture.smsf.model.exception.ApplicationException;
import com.google.common.collect.Lists;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private BuilderParam builderParm;
	
	@Autowired
	private OrganizationMapper organization;
	
	@Autowired
	private OrganizationHierarchyService oh;

	@Override
	public int saveOrganization(OrganizationIn params) throws Exception {
		
		// 返回值定义
		int result = 0;
		
		// 重复项check
		duplicationCheck(params);
		
		//判断当前节点下的子节点是否允许挂在当前
		
		// 设置租户id
		params.setTenantId(Long.valueOf(TenantHolder.get()));
		
		// 保存数据
		// 更新
		if ( params.getId() != null && params.getId() > 0 ) {

			//判断上级节点是否等于其本身
			if(params.getId().equals(params.getParentId())) {
				throw new ApplicationException(90005);
			}
			//判断上级是否选择了目前的子节点
			checkIsChild(params.getId(), params.getParentId());
			//判断是否存在子节点不能挂在当前HierarcyLevel下
			OrganizationHierarchy ohRow =  oh.selectOrganizationHierarchy(params.getHierarchyId());
			if(ohRow != null) {
				List<OrganizationHierarchy> nextLevel = oh.getNextLevel(ohRow.getLevel());
				int count = 0; 
				if(nextLevel != null && nextLevel.size() > 0) {
					String strNextLevel = "";
					for(OrganizationHierarchy nl: nextLevel) {
						if(strNextLevel.equals("")) {
							strNextLevel = nl.getId().toString();
						}
						else {
							strNextLevel += "," + nl.getId().toString();
						}
					}
					String strParmWithPageing = " and org.parentId = " + params.getId() + " and org.hierarchyId not in (" + strNextLevel + ")";
					count = organization.selectOrganizationCount(strParmWithPageing);
				}
				else {
					String strParmWithPageing = " and org.parentId = " + params.getParentId();
					count = organization.selectOrganizationCount(strParmWithPageing);
				}
				if (count > 0) {
					throw new ApplicationException(90006);
				}
			}
			
			// 如果此节点不是根节点，取得此节点的组织信息 设置组织的lickcode
			if ( params.getParentId() > 0 ) {
				OrganizationOut activeOrg = organization.selectOrganization(params.getId());
				String oldLickParent = activeOrg.getParentId().toString() + ",";
				String newLickParent = params.getParentId().toString() + ",";
				params.setLikeCode(activeOrg.getLikeCode().replace(oldLickParent + ",", newLickParent));
			}
			
			// 更新人EID追加
			params.setLastModifierUserId(PrincipalHolder.get());
			result = organization.updateOrganization(params);
		}
		// 新增
		else {
			// 如果此节点不是根节点，取得父id的组织信息 设置组织的lickcode
			if ( params.getParentId() > 0 ) {
				OrganizationOut parentOrg = organization.selectOrganization(params.getParentId());
				params.setLikeCode(parentOrg.getLikeCode() == null ? "" : parentOrg.getLikeCode()  + params.getParentId().toString() +",");
			} 
			
			// 创建人EID追加
			params.setCreatorUserId(PrincipalHolder.get());
			result = organization.insertOrganization(params);
		}

		return result;
	}

	@Override
	public int deleteOrganization(Long id) throws Exception {
		if(hasChildCheck(id))
		{
			throw new ApplicationException(90003);
		}
		return organization.deleteOrganization(id);
	}

	@Override
	public List<OrganizationOut> selectOrganizations(QueryParam params) {
		String strParmWithPageing = builderParm.buildParmNoPageing(params);
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

	private boolean hasChildCheck(Long id){
		boolean lb_hasChild = false;
		String strWhere = " and org.tenantId = " + TenantHolder.get() + " and org.parentId = " + id;
		List<OrganizationOut> orgList = organization.selectOrganizationList(strWhere);
		if(orgList != null && orgList.size() > 0) {
			lb_hasChild = true;
		}
		return lb_hasChild;
	}
	
	@Override
	public List<OrganizationTree> getOrganizationTreeByParentId(Long id){
		List<OrganizationTree> trees = Lists.newArrayList();
		//获得符合条件的所有结点
		String strParm = " and org.tenantId = " + TenantHolder.get() + " and org.parentId = " + id;
		List<OrganizationOut> orgs = organization.selectOrganizationList(strParm);
		for(OrganizationOut node : orgs) {
			OrganizationTree t = new OrganizationTree();
			t.setLabel(node.getName());
			t.setCollapsedIcon(node.getHierarchyIcon());
			t.setExpandedIcon(node.getHierarchyIcon());
			t.setData(node);
			t.setChildren(getOrganizationTreeByParentIdSub(node.getId()));
			trees.add(t);
		}
		return trees;
	}

	
	private List<OrganizationTree> getOrganizationTreeByParentIdSub(Long id){
		List<OrganizationTree> trees = Lists.newArrayList();
		//获得符合条件的所有结点
		String strParm = " and org.tenantId = " + TenantHolder.get() + " and org.parentId = " + id;
		List<OrganizationOut> orgs = organization.selectOrganizationList(strParm);
		for(OrganizationOut node : orgs) {
			OrganizationTree t = new OrganizationTree();
			t.setLabel(node.getName());
			t.setCollapsedIcon(node.getHierarchyIcon());
			t.setExpandedIcon(node.getHierarchyIcon());
			t.setData(node);
			t.setChildren(getOrganizationTreeByParentIdSub(node.getId()));
			trees.add(t);
		}
		return trees;
	}
	
	@Override
	public List<OrganizationTree> getOrganizationTree(QueryParam param){
		List<OrganizationTree> trees = Lists.newArrayList();
		//获得符合条件的所有结点
		List<OrganizationOut> orgs = selectOrganizations(param);
		//找到这些节点中的最上层
		List<OrganizationOut> fistLevel = Lists.newArrayList();
		for(OrganizationOut node : orgs) {
			boolean lb_noParent = true;
			for(OrganizationOut nodeSub : orgs) {
				if(nodeSub.getId().equals(node.getParentId()))
				{
					lb_noParent = false;
					break;
				}
			}
			if(lb_noParent) {
				fistLevel.add(node);
			}
		}
		//从最上层结点开始组织树
		for(OrganizationOut node : fistLevel) {
			OrganizationTree t = new OrganizationTree();
			t.setLabel(node.getName());
			t.setCollapsedIcon(node.getHierarchyIcon());
			t.setExpandedIcon(node.getHierarchyIcon());
			t.setData(node);
			t.setChildren(getOrganizationTreeSub(orgs, node));
			trees.add(t);
		}
		
		// TODO 为组织树增加一个虚拟的根节点，当前系统中不需要此功能
//		List<OrganizationTree> targetTree = Lists.newArrayList();
//		//虚拟组织机构
//		OrganizationTree root = new OrganizationTree();
//		root.setLabel("组织机构");
//		root.setCollapsedIcon("fa fa-folder-open");
//		root.setExpandedIcon("fa fa-folder");
//		root.setChildren(trees);
//		OrganizationOut rootData = new OrganizationOut();
//		rootData.setId(0L);
//		rootData.setParentId(-1L);
//		rootData.setHierarchyLevel(0L);
//		rootData.setHierarchyId(0L);
//		rootData.setCode("");
//		rootData.setName("组织机构");
//		rootData.setComment("");
//		root.setData(rootData);
//		targetTree.add(root);
		
		return trees;
	}
	
	private List<OrganizationTree> getOrganizationTreeSub(List<OrganizationOut> orgs, OrganizationOut parentNode){
		List<OrganizationTree> treeTable = Lists.newArrayList();	
		for(OrganizationOut node : orgs) {
			if(node.getParentId().equals(parentNode.getId())){
				OrganizationTree child = new OrganizationTree();
				child.setLabel(node.getName());
				child.setCollapsedIcon(node.getHierarchyIcon());
				child.setExpandedIcon(node.getHierarchyIcon());
				child.setData(node);
				child.setChildren(getOrganizationTreeSub(orgs, node));
				treeTable.add(child);
			}			
		}
		return treeTable;
	}
	
	@Override
	public List<OrganizationTreeTable> getOrganizationTreeTable(Long id){

		List<OrganizationTreeTable> treeTable = Lists.newArrayList();	
		String strWhere = " and org.tenantId = " + TenantHolder.get() + " and org.parentId = " + id;
		List<OrganizationOut> subOrgList = organization.selectOrganizationList(strWhere);
		if(subOrgList != null && subOrgList.size() > 0) {
			OrganizationOut lineno = new OrganizationOut();
			lineno.setLineno(1L);
			for(OrganizationOut node : subOrgList) {
				OrganizationTreeTable treeTableRow = new OrganizationTreeTable();
				lineno.setLineno(lineno.getLineno() + 1);
				node.setLineno(lineno.getLineno());
				treeTableRow.setData(node);
//				treeTableRow.setChildren(getOrganizationTreeTableSub(node, lineno)); TODO
				treeTable.add(treeTableRow);
			}
		}
		List<OrganizationTreeTable> targetTreeTable = Lists.newArrayList();

		//  TODO 为组织树增加一个虚拟的根节点，当前系统中不需要此功能
//		if(id.equals(0L)) {
//			OrganizationOut rootItem = new OrganizationOut();
//			rootItem.setLineno(1L);
//			rootItem.setCode("Root");
//			rootItem.setName("组织机构");
//			rootItem.setParentId(-1L);
//			rootItem.setHierarchyId(0L);
//			rootItem.setHierarchyLevel(0L);
//			rootItem.setHierarchyName("Root");
//			rootItem.setComment("");
//			root.setData(rootItem);
//			root.setChildren(treeTable);
//		}
		if ( id.equals(0L) ) {
			targetTreeTable = treeTable;
		}
		else
		{
			OrganizationTreeTable root = new OrganizationTreeTable();
			OrganizationOut rootItem = selectOrganization(id);
			rootItem.setLineno(1L);
			root.setData(rootItem);
			root.setChildren(treeTable);
			targetTreeTable.add(root);
		}
		
		return targetTreeTable;
	}
	
	// TODO 展开整个层级关系树时的方法，暂时只展开当前节点的树
//	private List<OrganizationTreeTable> getOrganizationTreeTableSub(OrganizationOut parentNode, OrganizationOut lineno){
//		List<OrganizationTreeTable> treeTable = Lists.newArrayList();		
//		String strWhere = " and org.tenantId = " + TenantHolder.get() + " and org.parentId = " + parentNode.getId();
//		List<OrganizationOut> subOrgList = organization.selectOrganizationList(strWhere);
//		if(subOrgList != null && subOrgList.size() > 0) {
//			for(OrganizationOut node : subOrgList) {
//				OrganizationTreeTable treeTableRow = new OrganizationTreeTable();
//				lineno.setLineno(lineno.getLineno() + 1);
//				node.setLineno(lineno.getLineno());
//				treeTableRow.setData(node);
//				treeTableRow.setChildren(getOrganizationTreeTableSub(node, lineno));
//				treeTable.add(treeTableRow);
//			}
//		}
//		return treeTable;
//	}
	
	@Override
	public OrganizationTreeSelect getOrganizationTreeSelectOne(Long id) {
	
		// 取得当前节点数据
		OrganizationOut organData = organization.selectOrganization(id);
		
		// 设置前台treeSelect数据
		OrganizationTreeSelect treeSelectRow = new OrganizationTreeSelect();
		treeSelectRow.setId(organData.getId());
		treeSelectRow.setText(organData.getName());
		treeSelectRow.setParent(String.valueOf(organData.getParentId()));
		treeSelectRow.setHierarchyLevel(organData.getHierarchyLevel());
		treeSelectRow.setChildren(getOrganizationTreeSelectSub(organData));
		
		// 设置当前状态
		OrganizationTreeSelectState state = new OrganizationTreeSelectState();
		state.setDisabled(false);
		state.setOpened(false);
		state.setSelected(false);
		treeSelectRow.setState(state);
		
		return treeSelectRow;
	}
	
	@Override
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
				treeSelectRow.setHierarchyLevel(node.getHierarchyLevel());
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
			rootNode.setHierarchyLevel(0L);
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
				treeSelectRow.setHierarchyLevel(node.getHierarchyLevel());
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
	
	private void checkIsChild(Long id, Long parentId) throws Exception {
		String strWhere = " and org.tenantId = " + TenantHolder.get() + " and org.parentId = " + id;
		List<OrganizationOut> subOrgList = organization.selectOrganizationList(strWhere);
		if(subOrgList != null && subOrgList.size() > 0) {
			for(OrganizationOut node : subOrgList) {
				if(node.getId().equals(parentId)) {
					throw new ApplicationException(90004); 
				}
				checkIsChildSub(node.getId(), parentId);
			}
		}
		
	}
	private void checkIsChildSub(Long id, Long parentId) throws Exception {
		String strWhere = " and org.tenantId = " + TenantHolder.get() + " and org.parentId = " + id;
		List<OrganizationOut> subOrgList = organization.selectOrganizationList(strWhere);
		if(subOrgList != null && subOrgList.size() > 0) {
			for(OrganizationOut node : subOrgList) {
				if(node.getId().equals(parentId)) {
					throw new ApplicationException(90004); 
				}
				checkIsChildSub(node.getId(), parentId);
			}
		}
		
	}

}
