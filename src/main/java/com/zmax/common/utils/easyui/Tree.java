package com.zmax.common.utils.easyui;

import java.util.ArrayList;
import java.util.List;

/**
 * EasyUI tree模型
•id: node id, which is important to load remote data
•text: node text to show
•state: node state, 'open' or 'closed', default is 'open'. When set to 'closed', the node have children nodes and will load them from remote site
•checked: Indicate whether the node is checked selected.
•attributes: custom attributes can be added to a node
•children: an array nodes defines some children nodes
 * @author zmax
 * 
 */
public class Tree implements java.io.Serializable {
	/**ID*/
	private String id;
	/**显示的文本*/
	private String text;
	/**状态 'open' or 'closed', default is 'open'*/
	private String state = "open";// open,closed
	/**是否选中*/
	private boolean checked = false;
	/**用户自定义属性*/
	private Object attributes;
	/**孩子*/
	private List<Tree> children=new ArrayList<Tree>();
	/**图标类型*/
	private String iconCls;
	/**自己加的：父id*/
	private String pid;

	
	public Tree() {

	}

	
	public Tree(String id, String text, String state, boolean checked,
			Object attributes, List<Tree> children, String iconCls, String pid) {
		this.id = id;
		this.text = text;
		this.state = state;
		this.checked = checked;
		this.attributes = attributes;
		this.children = children;
		this.iconCls = iconCls;
		this.pid = pid;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public List<Tree> getChildren() {
		children=(children==null)?new ArrayList<Tree>():children;
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

}
