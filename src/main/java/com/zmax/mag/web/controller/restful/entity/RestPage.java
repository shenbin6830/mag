package com.zmax.mag.web.controller.restful.entity;

import com.zmax.mag.domain.conf.Conf;

/**
 * REST的页码信息，包括页码，排序，查询条件，指令等
 * @author zmax
 *
 */
public class RestPage {
	/**当前页码，从1开始*/
	Integer pageNo;
	/**每页数据量*/
	Integer pageSize;
	/**排序方式，ex. id desc*/
	String orderstr;
	/**查询条件*/
	String where;
	/**指令*/
	String cmd;
	/**当前页码，从1开始*/
	public Integer getPageNo() {
		return pageNo;
	}
	/**当前页码，从1开始*/
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	/**每页数据量*/
	public Integer getPageSize() {
		return pageSize;
	}
	/**每页数据量*/
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**排序方式，ex. id desc*/
	public String getOrderstr() {
		return orderstr;
	}
	/**排序方式，ex. id desc*/
	public void setOrderstr(String orderstr) {
		this.orderstr = orderstr;
	}
	/**查询条件*/
	public String getWhere() {
		return where;
	}
	/**查询条件*/
	public void setWhere(String where) {
		this.where = where;
	}
	/**指令*/	
	public String getCmd() {
		return cmd;
	}
	/**指令*/
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	/**
	 * 修整
	 */
	public void fix(){
		pageNo=(pageNo==null)?1:pageNo;
		pageSize=(pageSize==null)?Conf.PAGE_SIZE:pageSize;
		orderstr=(orderstr==null)?"id desc":orderstr;
		where=(where==null)?"":where;	
		cmd=(cmd==null)?"":cmd;	
	}
	
	
}
