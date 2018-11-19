package com.accenture.masterdata.core.outEntity;

import java.util.List;
import lombok.Data;

@Data
public class OrganizationTreeTable {
	private OrganizationOut data;
	private List<OrganizationTreeTable> children;
}
