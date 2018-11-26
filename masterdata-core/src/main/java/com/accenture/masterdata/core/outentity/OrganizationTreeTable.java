package com.accenture.masterdata.core.outentity;

import java.util.List;
import lombok.Data;

@Data
public class OrganizationTreeTable {
	private OrganizationOut data;
	private List<OrganizationTreeTable> children;
}
