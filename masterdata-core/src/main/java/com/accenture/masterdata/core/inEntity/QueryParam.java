package com.accenture.masterdata.core.inEntity;

/*
 * 通用查询检索条件参数
 * */
public class QueryParam {

	//检索条件
	public FilterRule filterrule;
	
	//排序
	public String sorting;
	
	public void setSorting(String sorting) {
		
	}

	public FilterRule getFilterrule() {
		return filterrule;
	}

	public void setFilterrule(FilterRule filterrule) {
		this.filterrule = filterrule;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public int getRowsperpage() {
		return rowsperpage;
	}

	public void setRowsperpage(int rowsperpage) {
		this.rowsperpage = rowsperpage;
	}

	public String getSorting() {
		if(sorting == null || sorting.equals("")) 
		{
			return "id asc";
		}
		return this.sorting;
	}

	//第几页
	public int currentpage;
	
	//每页几条数据
	public int rowsperpage;

}
