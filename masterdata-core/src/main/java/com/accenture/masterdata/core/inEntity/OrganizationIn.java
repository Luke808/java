package com.accenture.masterdata.core.inEntity;

import java.util.Date;

import lombok.Data;

/**
 * @version 1.0
 * @created 05-Nov-2018 2:44:30 PM
 */
@Data
public class OrganizationIn {

	private int id;
	private int parentId;
	private String code;
	private String name;
	private int organizationType;
	private int legalEntity;
	private String likeCode;
	private int creatorUserId;
	private Date creationTime;
	private int lastModifierUserId;
	private Date lastModificationTime;
	private boolean isDeleted;
	private int deleterUserId;
	private Date deletionTime;
	private int tenantId;
	private String property1;
	private String property2;
	private String property3;
	private String property4;
	private String property5;
	private String property6;
	private String property7;
	private String property8;
	private String property9;
	private int numProperty1;
	private int numProperty2;
	private int numProperty3;

}