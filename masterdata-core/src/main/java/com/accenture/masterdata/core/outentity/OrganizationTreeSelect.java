package com.accenture.masterdata.core.outentity;

import java.util.List;

import lombok.Data;

@Data
public class OrganizationTreeSelect {
	private String text;
	private OrganizationTreeSelectState State;
	private String parent;
	private List<OrganizationTreeSelect> children ;
	private Long id;
	private Long hierarchyLevel;
}
