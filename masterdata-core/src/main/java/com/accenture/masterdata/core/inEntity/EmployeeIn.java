package com.accenture.masterdata.core.inEntity;

import java.util.Date;

import lombok.Data;

/**
 * @version 1.0
 * @created 02-Nov-2018 5:48:58 PM
 */
@Data
public class EmployeeIn {

	/**
	 * 父id
	 */
	private int parentId;
	/**
	 * 员工code
	 */
	private String code;
	private String name;
	/**
	 * 性别
	 */
	private String sex;
	private String email;
	private String tel;
	private String address;
	/**
	 * 父id-本身id
	 */
	private String likeCode;
	/**
	 * 用户id
	 */
	private int userId;
	/**
	 * 创建用户
	 */
	private int creatorUserId;
	/**
	 * 创建时间
	 */
	private Date creationTime;
	/**
	 * 修改人id
	 */
	private int lastModifierUserId;
	/**
	 * 最后修改时间
	 */
	private int lastModificationTime;
	/**
	 * 删除flag
	 */
	private boolean isDeleted;
	/**
	 * 删除用户
	 */
	private int deleterUserId;
	/**
	 * 删除时间
	 */
	private Date deletionTime;
	/**
	 * 租户id
	 */
	private int tenantId;

}