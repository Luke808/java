package com.accenture.masterdata.core.outEntity;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @version 1.0
 * @created 05-Nov-2018 2:44:29 PM
 */
@Data
public class OrganizationHierarchyOut {

	private int id;
	private String name;
	private int level;
	private int creatorUserId;
	private Date creationTime;
	private int lastModifierUserId;
	private Date lastModificationTime;
	private boolean isDeleted;
	private int deleterUserId;
	private Date deletionTime;
	private int tenantId;
	private List<HierarchyPropertiesOut> properties;
	private String creatorName;
	private String deleterName;
	private String tenantName;

}