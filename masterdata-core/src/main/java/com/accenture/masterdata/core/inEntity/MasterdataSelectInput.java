package com.accenture.masterdata.core.inEntity;

import lombok.Data;

/**
 * @author haibo.liu
 * @version 1.0
 * @created 05-Nov-2018 1:27:49 PM
 */
@Data
public class MasterdataSelectInput {

	private int page;
	private int pageSize;
	private String orderBy;
	private MasterdataFilterRule filterRule;
	public MasterdataFilterRule m_MasterdataFilterRule;

}