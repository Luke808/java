package com.accenture.masterdata.starter;

import com.accenture.smsf.authority.permission.loader.annotation.PermissionClass;
import com.accenture.smsf.authority.permission.loader.annotation.PermissionId;

/**
 * @author donglai,ding
 * @date 2018/12/19 09:14
 * @since 1.0.0
 */
@PermissionClass
public class Permissions {
	
	// ClientServiceLevel权限设置
    @PermissionId(value = "masterdata.client.service.level.view")
    public static final String MASTERDATA_CLIENT_SERVICE_LEVEL_VIEW = "masterdata.client.service.level.view";
    @PermissionId(value = "masterdata.client.service.level.manage")
    public static final String MASTERDATA_CLIENT_SERVICE_LEVEL_MANAGE = "masterdata.client.service.level.manage";
    // Process权限设置
    @PermissionId(value = "masterdata.process.view")
    public static final String MASTERDATA_PROCESS_VIEW = "masterdata.process.view";
    @PermissionId(value = "masterdata.process.manage")
    public static final String MASTERDATA_PROCESS_MANAGE = "masterdata.process.manage";
    // Step权限设置
    @PermissionId(value = "masterdata.step.view")
    public static final String MASTERDATA_STEP_VIEW = "masterdata.step.view";
    @PermissionId(value = "masterdata.step.manage")
    public static final String MASTERDATA_STEP_MANAGE = "masterdata.step.manage";
    // Tat权限设置
    @PermissionId(value = "masterdata.tat.view")
    public static final String MASTERDATA_TAT_VIEW = "masterdata.tat.view";
    @PermissionId(value = "masterdata.tat.manage")
    public static final String MASTERDATA_TAT_MANAGE = "masterdata.tat.manage";
    // CutoffTime权限设置
    @PermissionId(value = "masterdata.cutoff.time.view")
    public static final String MASTERDATA_CUTOFF_TIME_VIEW = "masterdata.cutoff.time.view";
    @PermissionId(value = "masterdata.cutoff.time.manage")
    public static final String MASTERDATA_CUTOFF_TIME_MANAGE = "masterdata.cutoff.time.manage";
    // Spt权限设置
    @PermissionId(value = "masterdata.spt.view")
    public static final String MASTERDATA_SPT_VIEW = "masterdata.spt.view";
    @PermissionId(value = "masterdata.spt.manage")
    public static final String MASTERDATA_SPT_MANAGE = "masterdata.spt.manage";
    // CompanyServiceLevel权限设置
    @PermissionId(value = "masterdata.company.service.level.view")
    public static final String MASTERDATA_COMPANY_SERVICE_LEVEL_VIEW = "masterdata.company.service.level.view";
    @PermissionId(value = "masterdata.company.service.level.manage")
    public static final String MASTERDATA_COMPANY_SERVICE_LEVEL_MANAGE = "masterdata.company.service.level.manage";
    // NcType权限设置
    @PermissionId(value = "masterdata.nc.type.view")
    public static final String MASTERDATA_NC_TYPE_VIEW = "masterdata.nc.type.view";
    @PermissionId(value = "masterdata.nc.type.manage")
    public static final String MASTERDATA_NC_TYPE_MANAGE = "masterdata.nc.type.manage";

}
