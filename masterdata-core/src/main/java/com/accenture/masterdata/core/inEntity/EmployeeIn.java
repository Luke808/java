package com.accenture.masterdata.core.inEntity;

import java.util.Date;

import lombok.Data;

/**
 * @version 1.0
 * @created 02-Nov-2018 5:48:58 PM
 */
@Data
public class EmployeeIn {
	private Long id;
	private String name;
	private String eId;
	private String email;
	private Long userId;
	private Long organizationId;
	private String cardNo;
	private String sex;
	private String address;
	private String tel;
	private String orgLikeCode;
	private String comment;
	private Long creatorUserId;
	private Date creationTime;
	private Long lastModifierUserId;
	private Date lastModificationTime;
	private int isDeleted;
	private Long deleterUserId;
	private Date deletionTime;
	private Long tenantId;

}