package com.zmax.common.utils.easyui;

import java.util.ArrayList;
import java.util.List;

/**
 * EasyUI DataGrid模型
 * 
 * @author 
 * 
 */
public class Grid implements java.io.Serializable {
	/**总数量*/
	private Integer total = 0;
	/**列表*/
	private List rows = new ArrayList();
	/**脚 也是相同对象列表，内容是自定义，比如sum avg*/
	private List footer = null;
	private Object obj1=null;
	private Object obj2=null;
	private Object obj3=null;
	/**总数量*/
	public Integer getTotal() {
		return total;
	}
	/**总数量*/
	public void setTotal(Integer total) {
		this.total = total;
	}
	/**列表*/
	public List getRows() {
		return rows;
	}
	/**列表*/
	public void setRows(List rows) {
		this.rows = rows;
	}
	/**脚 也是相同对象列表，内容是自定义，比如sum avg*/
	public List getFooter() {
		return footer;
	}
	/**脚 也是相同对象列表，内容是自定义，比如sum avg*/
	public void setFooter(List footer) {
		this.footer = footer;
	}
	public Object getObj1() {
		return obj1;
	}
	public void setObj1(Object obj1) {
		this.obj1 = obj1;
	}
	public Object getObj2() {
		return obj2;
	}
	public void setObj2(Object obj2) {
		this.obj2 = obj2;
	}
	public Object getObj3() {
		return obj3;
	}
	public void setObj3(Object obj3) {
		this.obj3 = obj3;
	}

}
