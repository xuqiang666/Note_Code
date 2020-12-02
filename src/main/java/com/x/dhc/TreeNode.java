package com.x.dhc;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;



@XmlRootElement(name="ClinicalDocument")
public class TreeNode implements Serializable{
	
	/**  
	* 字段:      字段名称
	* @Fields serialVersionUID : TODO 
	*/
	private static final long serialVersionUID = 1L;
	
	private List<String> excludeAttr=Arrays.asList(new String[]{"extension","content","value","xsi:type"});

	private String id;
	//节点本身名称

	private String text;
	//备注中文名称
	private String State;
	@JsonIgnore
	private String tempDisplayName;

	@JsonIgnore
	private  TreeNode parentNode;
	

	List<TreeNode> children=null;
	
	private NodeAttribute attributes=new NodeAttribute();
	
	
	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTempDisplayName() {
		return tempDisplayName;
	}

	public void setTempDisplayName(String tempDisplayName) {
		this.tempDisplayName = tempDisplayName;
	}

	public TreeNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(TreeNode parentNode) {
		this.parentNode = parentNode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public NodeAttribute getAttributes() {
		return attributes;
	}
	public void setAttribute(NodeAttribute attributes) {
		this.attributes = attributes;
	}
	
	@JsonIgnore
	public TreeNode getcopy(){
		TreeNode node=new TreeNode();
		node.setAttribute(this.getAttributes());
		node.setId(this.getId());
		return node;
		
	}
	
	public Boolean eq(TreeNode treeNode){
		if(this.getAttributes().getComponentDislayName().equals(treeNode.getAttributes().getComponentDislayName())){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	//如果两个节点的code和dispath路径都一样接仍然有多个节点匹配时，用全部属性来匹配
		public Boolean perfectMatch(TreeNode treeNode){
			/*if(this.getText().equals(treeNode.getText())&&treeNode.getText().equals("ClinicalDocument")){
				return Boolean.TRUE;
			}*/
			if(this.eq(treeNode)){
				if(treeNode.getAttributes().getAttributeInfos()!=null&&this.getAttributes().getAttributeInfos()!=null){
					Map<String, String> curMap=new HashMap<String, String>();
					Map<String, String> nodeMap=new HashMap<String, String>();
					for(AttributeInfo info:this.getAttributes().getAttributeInfos()){
						if(!excludeAttr.contains(info.getName())){
							curMap.put(info.getName(), info.getValue());
						}
						
					}
					for(AttributeInfo info:treeNode.getAttributes().getAttributeInfos()){
						if(!excludeAttr.contains(info.getName())){
							nodeMap.put(info.getName(), info.getValue());
						}
					}
					if(curMap.size()==nodeMap.size()){
						for(String key:curMap.keySet()){
							if(!curMap.get(key).equals(nodeMap.get(key))){
								return Boolean.FALSE;
							}
						}
						return Boolean.TRUE;
					}
				}else{
					return Boolean.TRUE;
				}
			}
			
			return Boolean.FALSE;
			
		}
		
		@JsonIgnore
		public  String getNodeAttr(){
			StringBuffer sBuffer=new StringBuffer();
			sBuffer.append("[");
			if(this.getAttributes().getAttributeInfos()!=null){
				for(AttributeInfo attributeInfo:this.getAttributes().getAttributeInfos()){
					sBuffer.append(attributeInfo.getName());
					sBuffer.append(" :"+attributeInfo.getValue()+" ");
				}
				
			}
			sBuffer.append("]");
			return sBuffer.toString();
			
		}
}
