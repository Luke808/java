package com.accenture.masterdata.core.inEntity;

import java.util.List;

import lombok.Data;

@Data
public class BatchDeleteInput {
	public List<Long> ids;
}
