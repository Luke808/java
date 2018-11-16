package com.accenture.masterdata.core.inEntity;

import java.util.Date;

import lombok.Data;

/**
 * @version 1.0
 * @created 05-Nov-2018 2:44:30 PM
 */
@Data
public class OrganizationIn {

	private Integer id;
	private Integer parentId;
	private String code;
	private String name;
	private String comment;
	private String likeCode;
	private Integer organizationType;
	private Integer legalEntity;
	private Integer creatorUserId;
	private Date creationTime;
	private Integer lastModifierUserId;
	private Date lastModificationTime;
	private boolean isDeleted;
	private Integer deleterUserId;
	private Date deletionTime;
	private Integer tenantId;

}