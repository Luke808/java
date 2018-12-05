package com.accenture.masterdata.core.inentity;

import java.util.List;

import lombok.Data;

@Data
public class BatchDeleteInput {
	private List<Long> ids;
}
