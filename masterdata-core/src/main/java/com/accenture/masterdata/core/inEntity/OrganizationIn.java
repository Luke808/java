package com.accenture.masterdata.core.inEntity;

import java.util.Date;

import lombok.Data;

/**
 * @version 1.0
 * @created 05-Nov-2018 2:44:30 PM
 */
@Data
public class OrganizationIn {

	private Long id;
	private Long parentId;
	private String code;
	private String name;
	private String comment;
	private String likeCode;
	private Long hierarchyLevel;
	private Long hierarchyId;
	private Long creatorUserId;
	private Date creationTime;
	private Long lastModifierUserId;
	private Date lastModificationTime;
	private boolean isDeleted;
	private Long deleterUserId;
	private Date deletionTime;
	private Long tenantId;

}