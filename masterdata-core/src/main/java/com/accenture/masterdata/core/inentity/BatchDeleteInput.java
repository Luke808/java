package com.accenture.masterdata.core.inentity;

import java.util.List;

import lombok.Data;

@Data
public class BatchDeleteInput {
	public List<Long> ids;
}
