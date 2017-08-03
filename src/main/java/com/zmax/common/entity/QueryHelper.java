/**
 * 
 */
package com.zmax.common.entity;

/**
 * 查询帮助类，where order cmd
 * @author zmax
 *
 */
public class QueryHelper {
	/**条件 ex aaa=1*/
	String where;
	/**排序 ex id desc*/
	String order;
	/**指令*/
	String cmd;
	/**条件 ex aaa=1*/
	public String getWhere() {
		return where;
	}
	/**条件 ex aaa=1*/
	public void setWhere(String where) {
		this.where = where;
	}
	/**排序 ex id desc*/
	public String getOrder() {
		return order;
	}
	/**排序 ex id desc*/
	public void setOrder(String order) {
		this.order = order;
	}
	/**指令*/
	public String getCmd() {
		return cmd;
	}
	/**指令*/
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
}
