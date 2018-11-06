package com.accenture.masterdata.core.inEntity;

import java.util.Date;

import lombok.Data;

/**
 * @version 1.0
 * @created 02-Nov-2018 5:48:58 PM
 */
@Data
public class EmployeeIn {

	private String name;
	private String cardNo;
	private int eId;
	private int organizationId;
	private String sex;
	private String email;
	private String tel;
	private String address;
	private String comment;
	private int userId;
	private int creatorUserId;
	private Date creationTime;
	private int lastModifierUserId;
	private int lastModificationTime;
	private boolean isDeleted;
	private int deleterUserId;
	private Date deletionTime;
	private int tenantId;

}