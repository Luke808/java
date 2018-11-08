package com.accenture.masterdata.core.inEntity;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @version 1.0
 * @created 05-Nov-2018 2:44:29 PM
 */
@Data
public class OrganizationHierarchyIn {

	private int id;
	private String name;
	private int level;
	private String icon;
	private int allowConcurrently;
	private String comments;
	private Long creatorUserId;
	private Date creationTime;
	private int lastModifierUserId;
	private Date lastModificationTime;
	private int isDeleted;
	private int deleterUserId;
	private Date deletionTime;
	private int tenantId;
	
}