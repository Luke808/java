
package com.accenture.masterdata.common.querybuilder;

import java.util.List;

import com.accenture.masterdata.core.inEntity.FilterRule;
import com.accenture.masterdata.core.inEntity.QueryParam;
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
		String strtenantIdRules = " AND " + query.getTenantTable() + ".tenantId = " + TenantHolder.get() + " ";
		
		String strSubCurrentRules = "";
		if(query.filterrule != null && query.filterrule.rules.size() > 0) {
			strSubCurrentRules = buildFilterRules(query.filterrule.getCondition(), query.filterrule.getRules());
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
			for (FilterRule o : rules) {
				i = i + 1;
				if(o.condition != null && o.rules != null) {
					buildFilterRules(o.condition, o.rules);
				}
				if(o.operator != null && o.field != null && o.value != null && o.type != null) {
					String strOperator = changeOperator(o.operator);
					strCurrentRules += o.field + " " + strOperator + " " + changeValue(strOperator, o.value, o.type);
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
		switch(operator.toLowerCase()){
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
