package com.x.dhc;

import java.io.Serializable;
import java.util.List;

public class Tree implements Serializable{
	
	

	

	/**  
	* 字段:      字段名称
	* @Fields serialVersionUID : TODO 
	*/
	private static final long serialVersionUID = 1L;
	private List<TreeNode> root;
	
	private String name;
	
	public List<TreeNode> getRoot() {
		return root;
	}

	public void setRoot(List<TreeNode> root) {
		this.root = root;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
