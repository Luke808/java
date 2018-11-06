package com.accenture.masterdata.core.inEntity;

import java.util.Date;

import lombok.Data;

/**
 * @version 1.0
 * @created 05-Nov-2018 2:44:29 PM
 */
@Data
public class HierarchyPropertiesIn {

	private int id;
	private String name;
	private int hierarchyId;
	private int type;
	private int controlType;
	private String selectValue;
	private String mappingCol;
	private int creatorUserId;
	private Date creationTime;
	private int lastModifierUserId;
	private Date lastModificationTime;
	private boolean isDeleted;
	private int deleterUserId;
	private Date deletionTime;
	private int tenantId;

}