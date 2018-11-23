package com.accenture.masterdata.core.outEntity;

import java.util.Date;

import lombok.Data;

/**
 * @version 1.0
 * @created 05-Nov-2018 2:44:29 PM
 */
@Data
public class OrganizationHierarchy {

	private Long id;
	private String name;
	private Long level;
	private String icon;
	private int allowConcurrently;
	private String comments;
	private String creatorUserId;
	private String creatorUserName;
	private Date creationTime;
	private String lastModifierUserId;
	private String lastModifierUserName;
	private Date lastModificationTime;
	private int isDeleted;
	private String deleterUserName;
	private String deleterUserId;
	private Date deletionTime;
	private Long tenantId;
	private String tenantName;
	
}