package com.x.dhc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class NodeAttribute implements Serializable {

	
	

	public String getIsComponet() {
		return isComponet;
	}

	public void setIsComponet(String isComponet) {
		this.isComponet = isComponet;
	}
	/**  
	 * 字段:      字段名称
	 * @Fields serialVersionUID : TODO 
	 */
	private static final long serialVersionUID = 1L;

	//一直性
	private String consistence;

	//强制性
	private String imperative;

	//基数
	private String baseRule;

	//备注
	private String remark;
	
	private String nodeNameCn;
	// tex累加
	private String componentDislayName;
	
	//取component章节下，section章节的的code 节点的displayName(表示章节名称，用于区分不同的章节)，不同章节的去区分
	private String displayNamePath=null;	
	//是否是文件夹，1是文件夹，0，非文件夹
	private String isDir;
	
	// cd ,st,bl
	private String type;
	
	private Boolean isMust;
	
	List<AttributeInfo> attributeInfos;
	
	private String isComponet;//1 是componet ，用于共享文档交换标准判断是否发送请求展示树节点
	

	private String cdaTa;
	
	public String getConsistence() {
		return consistence;
	}

	public void setConsistence(String consistence) {
		this.consistence = consistence;
	}

	public String getImperative() {
		return imperative;
	}

	public void setImperative(String imperative) {
		this.imperative = imperative;
	}

	public String getBaseRule() {
		return baseRule;
	}

	public void setBaseRule(String baseRule) {
		this.baseRule = baseRule;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getNodeNameCn() {
		return nodeNameCn;
	}

	public void setNodeNameCn(String nodeNameCn) {
		this.nodeNameCn = nodeNameCn;
	}

	public String getComponentDislayName() {
		return componentDislayName;
	}

	public void setComponentDislayName(String componentDislayName) {
		this.componentDislayName = componentDislayName;
	}


	public List<AttributeInfo> getAttributeInfos() {
		return attributeInfos;
	}

	public void setAttributeInfos(List<AttributeInfo> attributeInfos) {
		this.attributeInfos = attributeInfos;
	}

	public void addAttributeInfo(AttributeInfo attributeInfo){
		if(attributeInfos==null){
			attributeInfos=new ArrayList<>();
		}
		attributeInfos.add(attributeInfo);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Boolean getIsMust() {
		if("1..1".equals(baseRule)||"1..*".equals(baseRule)){
			return Boolean.TRUE;
		}
		return isMust;
	}

	public void setIsMust(Boolean isMust) {
		this.isMust = isMust;
	}
	
	public String getIsDir() {
		return isDir;
	}

	public void setIsDir(String isDir) {
		this.isDir = isDir;
	}

	public String getDisplayNamePath() {
		return displayNamePath;
	}

	public void setDisplayNamePath(String displayNamePath) {
		this.displayNamePath = displayNamePath;
	}

	public String getCdaTa() {
		return cdaTa;
	}

	public void setCdaTa(String cdaTa) {
		this.cdaTa = cdaTa;
	}


}
