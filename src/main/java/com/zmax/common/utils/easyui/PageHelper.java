package com.zmax.common.utils.easyui;

/**
 * EasyUI 分页帮助类
 * 
 * @author 张闽昕
 * 
 */
public class PageHelper implements java.io.Serializable {

	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段
	private String order;// asc/desc

	public PageHelper(){}
	public PageHelper(int page,int rows){
		this.page=page;
		this.rows=rows;
	}
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort==null?"":sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order==null?"":order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * 如果是xxxString，把String去掉
	 * @return
	 */
	public String realSort(){
		String str=getSort();
		String search="String";
		int endIndex=str.lastIndexOf(search);
		if(endIndex==-1)
			return str;
		if(endIndex+search.length()==str.length()){
			String real=str.substring(0, endIndex);
			return real;
		}
		return str;
	}
}
