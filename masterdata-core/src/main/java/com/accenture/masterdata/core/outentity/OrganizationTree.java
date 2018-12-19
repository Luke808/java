package com.accenture.masterdata.core.outentity;

import java.util.List;
import lombok.Data;

@Data
public class OrganizationTree {
	private String label;
	private OrganizationOut data;
	private String expandedIcon;
	private String collapsedIcon;
	private OrganizationTree parent;
	private boolean expanded;
	private List<OrganizationTree> children;
}
