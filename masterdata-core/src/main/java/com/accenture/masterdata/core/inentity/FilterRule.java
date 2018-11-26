package com.accenture.masterdata.core.inentity;

import java.util.List;

import lombok.Data;

@Data
public class FilterRule {
	
	//AND OR
	public String condition;
	
	//字段名
	public String field;
	
	//比较符 >,>=,=,<,<=,!=
	public String operator;
	
	//子查询条件
	public List<FilterRule> rules;
	
	//字段类型(string,int,date....)
	public String type;
	
	//条件值
	public String value;

}
