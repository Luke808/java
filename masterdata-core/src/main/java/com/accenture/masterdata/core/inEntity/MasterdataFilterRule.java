package com.accenture.masterdata.core.inEntity;

import java.util.List;

import lombok.Data;

/**
 * @author haibo.liu
 * @version 1.0
 * @created 05-Nov-2018 1:27:33 PM
 */
@Data
public class MasterdataFilterRule {

	/**
	 * “and” 和 “or”
	 */
	private String condition;
	/**
	 * 检索字段名
	 */
	private String field;
	/**
	 * 检索字段的值
	 */
	private String value;
	/**
	 * 操作符
	 */
	private String operator;
	/**
	 * 字段值类型："integer", "double", "string", "date", "datetime","boolean"
	 */
	private String valueType;
	private String backupStr1;
	private String backupStr2;
	private List<MasterdataFilterRule> subRule;
	public MasterdataFilterRule m_MasterdataFilterRule;

}