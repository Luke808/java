package com.accenture.masterdata.starter;

import com.accenture.smsf.authority.permission.loader.annotation.PermissionClass;
import com.accenture.smsf.authority.permission.loader.annotation.PermissionId;

/**
 * @author lhb
 * @date 2018/7/20 11:29
 * @since 1.0.0
 */
@PermissionClass
public class Permissions {
	
	// hierarchy权限设置
    @PermissionId(value = "masterdata.hierarchy.view")
    public static final String MASTERDATA_HIERARCHY_VIEW = "masterdata.hierarchy.view";
    @PermissionId(value = "masterdata.hierarchy.manage.edit")
    public static final String MASTERDATA_HIERARCHY_MANAGE_EDIT = "masterdata.hierarchy.manage.edit";
    @PermissionId(value = "masterdata.hierarchy.manage.add")
    public static final String MASTERDATA_HIERARCHY_MANAGE_ADD = "masterdata.hierarchy.manage.add";
    @PermissionId(value = "masterdata.hierarchy.manage.delete")
    public static final String MASTERDATA_HIERARCHY_MANAGE_DELETE = "masterdata.hierarchy.manage.delete";
    
    // organiztion权限设置
    @PermissionId(value = "masterdata.organization.view")
    public static final String MASTERDATA_ORGANIZATION_VIEW = "masterdata.organization.view";
    @PermissionId(value = "masterdata.organization.manage.edit")
    public static final String MASTERDATA_ORGANIZATION_MANAGE_EDIT = "masterdata.organization.manage.edit";
    @PermissionId(value = "masterdata.organization.manage.add")
    public static final String MASTERDATA_ORGANIZATION_MANAGE_ADD = "masterdata.organization.manage.add";
    @PermissionId(value = "masterdata.organization.manage.delete")
    public static final String MASTERDATA_ORGANIZATION_MANAGE_DELETE = "masterdata.organization.manage.delete";
    
}
