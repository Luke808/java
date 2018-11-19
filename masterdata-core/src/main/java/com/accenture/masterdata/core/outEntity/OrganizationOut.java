package com.accenture.masterdata.core.outEntity;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @version 1.0
 * @created 05-Nov-2018 2:44:30 PM
 */
@Data
public class OrganizationOut {

	private Long id;
	private String name;
	private String code;
	private String comment;
	private Long parentId;
	private String parentName;
	private String hierarchyName;
	private Long hierarchyLevel;
	private Long hierarchyId;
	private Long creatorUserId;
	private String likeCode;
	private String creatorName;
	private Date creationTime;
	private Long lastModifierUserId;
	private Date lastModificationTime;
	private boolean isDeleted;
	private Long deleterUserId;
	private String deleterName;
	private Date deletionTime;
	private Long tenantId;
	private String tenantName;
	private List<OrganizationOut> child;
	private Long lineno;

}