package com.accenture.masterdata.core.outEntity;

import java.util.Date;

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
	private String icon;
	private int allowConcurrently;
	private String comments;
	private Long creatorUserId;
	private String creatorUserName;
	private Date creationTime;
	private int lastModifierUserId;
	private String lastModifierUserName;
	private Date lastModificationTime;
	private int isDeleted;
	private String deleterUserName;
	private int deleterUserId;
	private Date deletionTime;
	private int tenantId;
	private String tenantName;
	
}