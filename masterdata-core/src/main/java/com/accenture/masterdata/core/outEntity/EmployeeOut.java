package com.accenture.masterdata.core.outEntity;

import java.util.Date;

import lombok.Data;

/**
 * @version 1.0
 * @created 02-Nov-2018 5:48:58 PM
 */
@Data
public class EmployeeOut {

	private int id;
	private String name;
	private int userId;
	private int organizationId;
	private String comment;
	private String cardNo;
	private int eId;
	private String sex;
	private String email;
	private String tel;
	private String address;
	private int creatorUserId;
	private Date creationTime;
	private int lastModifierUserId;
	private int lastModificationTime;
	private boolean isDeleted;
	private int deleterUserId;
	private Date deletionTime;
	private int tenantId;
	private String userName;
	private String creatorName;
	private String deleterName;
	private String tenantName;
	private String organizationName;

}