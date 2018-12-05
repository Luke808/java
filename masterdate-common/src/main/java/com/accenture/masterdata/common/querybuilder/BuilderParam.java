
package com.accenture.masterdata.common.querybuilder;

import java.util.List;

import com.accenture.masterdata.core.inentity.FilterRule;
import com.accenture.masterdata.core.inentity.QueryParam;
import com.accenture.smsf.framework.boot.stereotype.Component;
import com.accenture.smsf.framework.starter.web.principal.TenantHolder;

@Component
public class BuilderParam {

	private String strCurrentRules = "";
	
	//带分页的SQL参数
	public String buildParmWithPageing(QueryParam query) {
		strCurrentRules = "";
		strCurrentRules = buildParmNoPageing(query);
		if(query.getSorting() != null && query.getSorting() != "") {
			strCurrentRules += " ORDER BY " + query.getSorting();
		}
		strCurrentRules += " limit " + (query.getCurrentpage() - 1) * query.getRowsperpage() + " , " + query.getRowsperpage();
		return strCurrentRules;
	}
	
	//不带分页的SQL参数
	public String buildParmNoPageing(QueryParam query) {
		strCurrentRules = "";
		// 追加租户ID的条件
		String strtenantIdRules = " AND " + (query.getTenantTable() == null || query.getTenantTable().trim().equals("") ? "" : (query.getTenantTable() + ".")) + "tenantId = " + TenantHolder.get() + " ";
		
		String strSubCurrentRules = "";
		if(query.getFilterrule() != null && query.getFilterrule().getRules().size() > 0) {
			strSubCurrentRules = buildFilterRules(query.getFilterrule().getCondition(), query.getFilterrule().getRules());
			strSubCurrentRules = strSubCurrentRules.replace(" () ", "");	//这种情况属于无条件的
		}
		String strTargetRules = strtenantIdRules;
		if(!strCurrentRules.isEmpty()) {
			strTargetRules += " AND " + strCurrentRules;
		}
		return strTargetRules;
	}
	
	//Build QueryParm
	private String buildFilterRules(String condition, List<FilterRule> rules) {
		if(condition != null) {
			strCurrentRules += " (";
			int i = 0;
			int iCount = rules.size();
			for (FilterRule rule : rules) {
				i = i + 1;
				if(rule.getCondition() != null && rule.getRules() != null) {
					buildFilterRules(rule.getCondition(), rule.getRules());
				}
				if(rule.getOperator() != null && rule.getField() != null && rule.getValue() != null && rule.getType() != null) {
					String strOperator = changeOperator(rule.getOperator());
					strCurrentRules += rule.getField() + " " + strOperator + " " + changeValue(strOperator, rule.getValue(), rule.getType());
				}
				if (i < iCount) {
					strCurrentRules += " " + condition + " ";
				}
			};
			strCurrentRules += ") ";
		}
	
		return strCurrentRules;
	}
	
	//Change operator
	private String changeOperator(String operator) {
		String newOperator = "";
		switch( operator.toLowerCase() ){
		case "equal":
			newOperator = "=";
		    break;
		case "not_equal":
			newOperator = "<>";
		    break;
		case "greater":
			newOperator = ">";
		    break;
		case "greater_or_equal":
			newOperator = ">=";
		    break;
		case "less":
			newOperator = "<";
		    break;
		case "less_or_equal":
			newOperator = "<=";
		    break;
		case "contains":
			newOperator = "like";
		    break;
		default:
		    break;
		}
		return newOperator;
	}
	
	//Change Value
	private String changeValue(String strOperator, String value, String valueType) {
		String factValue = value;
		switch(valueType.toLowerCase()){
		case "string":
			if(strOperator == "like") {
				factValue = "N'%" + value + "%'";
			}
			else
			{
				factValue = "N'" + value + "'";
			}
		    break;
		case "number":
		    break;
		case "int":
		    break;
		case "long":
		    break;
		case "date":
			factValue = "'" + value + "'";
		    break;
		case "boolean":
			if(value.toLowerCase() == "false") {
				factValue = "'0'";
			}
			else
			{
				factValue = "'1'";
			}
		    break;
		default:
		    break;
		}
		return factValue;
	}
}
