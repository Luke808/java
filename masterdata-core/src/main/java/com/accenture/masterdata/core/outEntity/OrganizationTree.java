package com.accenture.masterdata.core.outEntity;

import java.util.List;
import lombok.Data;

@Data
public class OrganizationTree {
	private String label;
	private OrganizationOut data;
	private String expandedIcon;
	private String collapsedIcon;
	private List<OrganizationTree> children;
}
