package com.accenture.masterdata.core.outentity;


import lombok.Data;

import java.util.List;

@Data
public class OrganizationTreeSelect {
	private String text;
	private OrganizationTreeSelectState State;
	private String parent;
	private String likeCode;
	private List<OrganizationTreeSelect> children ;
	private Long id;
	private Long hierarchyLevel;
}
