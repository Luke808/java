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

	private int id;
	private String name;
	private int parentId;
	private String parentName;
	private String hierarchyName;
	private int hierarchyLevel;
	private int hierarchyId;
	private int creatorUserId;
	private String likeCode;
	private String creatorName;
	private Date creationTime;
	private int lastModifierUserId;
	private Date lastModificationTime;
	private boolean isDeleted;
	private int deleterUserId;
	private String deleterName;
	private Date deletionTime;
	private int tenantId;
	private String tenantName;
	private List<OrganizationOut> child;
	private int numProperty1;
	private int numProperty2;
	private int numProperty3;
	private String property1;
	private String property2;
	private String property3;
	private String property4;
	private String property5;
	private String property6;
	private String property7;
	private String property8;
	private String property9;

}