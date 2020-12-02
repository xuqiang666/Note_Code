package com.x.dhc;

import java.io.Serializable;

public class AttributeInfo implements Serializable {
	
	/**  
	* 字段:      字段名称
	* @Fields serialVersionUID : TODO 
	*/
	private static final long serialVersionUID = 1L;
	
	
	private String id;

	//属性名称，如果xmlType=content，name=content
	private String name;

	private String value;

	//是attribute 还是content，<a b="c"> xmlType=attribute,<a>c</a> xmlType=content
	private String xmlType;

	//内部标识符
	private String innerMark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getXmlType() {
		return xmlType;
	}

	public void setXmlType(String xmlType) {
		this.xmlType = xmlType;
	}

	public String getInnerMark() {
		return innerMark;
	}

	public void setInnerMark(String innerMark) {
		this.innerMark = innerMark;
	}

}
