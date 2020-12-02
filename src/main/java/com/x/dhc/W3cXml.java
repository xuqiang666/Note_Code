package com.x.dhc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.dhcc.framework.util.StringUtils;
//import com.dhcc.template.common.XmlUtil;


/**
 *
 * <p>标题: XmlUtil.java</p>
 * <p>业务描述:数据采集协调平台</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2013</p>
 * @author chenbo
 * @date 2018年6月20日
 * @version V1.0
 */
public class W3cXml {

    private static Log logger = LogFactory.getLog(W3cXml.class);

    private static String head=null;

    private static String componetStart=null;

    private static String componetEnd=null;

    private static String schemaEnd=null;

    public static String error_type_xml="XML无法解析";

    public static String error_type_schemal="Schemal校验错误";

    public static String error_type_docType="文档类别与测试用例不一致";

    public static String error_type_noteMust="验证格式正确，缺少必填内容";

    public static String error_type_contentError="验证格式正确，包含全部必填项，但数据元内容不符";

    public static String error_type_notFullError="验证格式正确，缺少必填项，数据元内容不符";

    public static String allMustright="验证格式正确，包含全部必填项，必填项数据元内容正确";

    public static String allRight="验证格式正确，包含全部必填项，数据元内容正确";

    public static String error_type_nodeError="节点错误";

    private static String[] schemal=new String[]{"xmlns=\"urn:hl7-org:v3\" xmlns:mif=\"urn:hl7-org:v3/mif\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"  xsi:schemaLocation","urn:hl7-org:v3 ..\\sdschemas\\CDA.xsd"};
    private static String[] xmlns=new String[]{"xmlns","urn:hl7-org:v3"};
    private static String[] mif=new String[]{"xmlns:mif","urn:hl7-org:v3/mif"};
    private static String[] xsi=new String[]{"xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance"};
    private static String[] schemaLocation=new String[]{"xsi:schemaLocation","urn:hl7-org:v3 ..\\sdschemas\\CDA.xsd"};




    public static void main(String[]args){
        try {
            Tree tree=null;
            tree=getTreeFromXmlFilePath("C:\\Users\\jack\\Desktop\\bug\\马攀\\EMR-SD-17-一般护理记录-T01.xml");
            //	tree=parseXmlToTree(xmlContent);
            buildXmlByTree(tree,"C:\\Users\\jack\\Desktop\\bug\\马攀\\一般护理copy.xml");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    /**
     *
     * 方法名:          buildXmlByTree
     * 方法功能描述:       把内存模型Tree转成xml输出
     * @param:         是包含汉字的字符串
     * @return:        其他非简体汉字返回 '0';
     * @throws ParserConfigurationException
     * @Author:         陈波
     * @Create Date:   2018年8月31日 下午5:45:55
     */
    public static void buildXmlByTree(Tree tree,String file) throws ParserConfigurationException  {
        Document document=null;
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder documentBuilder=factory.newDocumentBuilder();
        document=documentBuilder.newDocument();

        List<TreeNode> list=tree.getRoot();

        TreeNode treeNodeC=findTreeNodeByName(tree, "ClinicalDocument");
        List<AttributeInfo> list2=treeNodeC.getAttributes().getAttributeInfos();
        list2.clear();

        AttributeInfo	 attributeInfo4=new AttributeInfo();
        attributeInfo4.setName(xmlns[0]);
        attributeInfo4.setValue(xmlns[1]);

        AttributeInfo	 attributeInfo3=new AttributeInfo();
        attributeInfo3.setName(mif[0]);
        attributeInfo3.setValue(mif[1]);

        AttributeInfo	 attributeInfo2=new AttributeInfo();
        attributeInfo2.setName(xsi[0]);
        attributeInfo2.setValue(xsi[1]);

        AttributeInfo	 attributeInfo1=new AttributeInfo();
        attributeInfo1.setName(schemaLocation[0]);
        attributeInfo1.setValue(schemaLocation[1]);


        list2.add(attributeInfo1);list2.add(attributeInfo2);list2.add(attributeInfo3);list2.add(attributeInfo4);
        tree.setName(getTreeNodeAttribute(tree, "ClinicalDocument.code", "code"));


        for(TreeNode chid:list){
            buildXmlByTreeNode(chid,document);
        }
        try {
            TransformerFactory tff = TransformerFactory.newInstance();
            // 创建Transformer对象
            Transformer transformer = tff.newTransformer();
            transformer.setOutputProperty(OutputKeys.STANDALONE,"yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING,"utf-8");
            // 使用Transformer的transform()方法将DOM树转换成XML
            PrintWriter pw =new PrintWriter(new FileOutputStream(file));
            Result result = new StreamResult(pw);
            transformer.transform(new DOMSource(document),result);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }


    /**
     *
     * 方法名:          getTreeFromXmlFilePath
     * 方法功能描述:     根据xml文件路径获取Tree
     * @param:         是包含汉字的字符串
     * @return:        其他非简体汉字返回 '0';
     * @Author:         陈波
     * @Create Date:   2018年7月5日 下午6:41:33
     */
    public static Tree getTreeFromXmlFilePath(String xmlFilePaht) throws Exception{
        Tree tree=null;

        tree=parseXmlToTreeWithCdaTa(xmlFilePaht);



        return tree;
    }

    /**
     *
     * 方法名:          parseXmlToTreeWithCdaTa
     * 方法功能描述:       保留备注的xml转换
     * @param:         是包含汉字的字符串
     * @return:        其他非简体汉字返回 '0';
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @Author:         陈波
     * @Create Date:   2018年9月3日 下午5:12:27
     */
    public static Tree parseXmlToTreeWithCdaTa(String xml) throws SAXException, IOException, ParserConfigurationException{
        Document document=null;
        Tree tree=new Tree();
        List<TreeNode> roots=new  ArrayList<>();
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder documentBuilder=factory.newDocumentBuilder();
        InputStream inputStream=new FileInputStream(xml);
        document=documentBuilder.parse(inputStream);
        Element root=document.getDocumentElement();

        TreeNode treeNode=new TreeNode();
        setTreeAttribute(root, treeNode);
        //临时treenode
        TreeNode temp=new TreeNode();
        //把注解射到临近的下一个element上
        NodeList list=root.getChildNodes();
        for(int i=0;i<list.getLength();i++){
            Node nd = list.item(i);
            TreeNode Tnode=new TreeNode();
            Tnode.setParentNode(treeNode);
            TreeNode chidTemp=new TreeNode();
            String cdaTa=parseRootWithCdaTa(nd,Tnode,chidTemp);
            if(cdaTa!=null){
                temp.getAttributes().setCdaTa(cdaTa);
            }else if(Tnode.getId()!=null&&!Tnode.getId().equals("")){
                //如果是element则从父节点中取出注解
                if(treeNode.getChildren()==null){
                    treeNode.setChildren(new ArrayList<TreeNode>());
                }
                Tnode.getAttributes().setCdaTa(temp.getAttributes().getCdaTa());
                treeNode.getChildren().add(Tnode);
                temp.getAttributes().setCdaTa(null);
            }

        }
        roots.add(treeNode);
        tree.setRoot(roots);
        //XmlUtil.updateTreePath(tree);
        return tree;
    }

    /**
     *
     * 方法名:          setTreeAttribute
     * 方法功能描述:      把xml节点的属性提取到TreeNode
     * @param:         是包含汉字的字符串
     * @return:        其他非简体汉字返回 '0';
     * @Author:         陈波
     * @Create Date:   2018年9月3日 下午5:09:28
     */
    private static void setTreeAttribute(Element root,TreeNode rootNode){
        UUID uuid=UUID.randomUUID();
        rootNode.setId(uuid.toString().replace("-", ""));
        rootNode.setText(root.getNodeName());

        //节点内部属性获取
        NamedNodeMap attributes=root.getAttributes();
        //把节点之间的值当作属性处理 "content:value"
        if(root.getFirstChild()!=null&&root.getFirstChild().getNodeValue()!=null){
            String text=root.getFirstChild().getNodeValue().replaceAll("\\r|\\t|\\n", "");
            if(!StringUtils.isNullOrEmpty(text)){
                AttributeInfo attributeInfo=new AttributeInfo();
                attributeInfo.setName("content");//"content",root.getTextTrim()
                attributeInfo.setValue(root.getFirstChild().getNodeValue());
                rootNode.getAttributes().addAttributeInfo(attributeInfo);
            }

        }
        // 如果属性中包含type 且type属性值为CD或者CE 则为字典
        Boolean idDic=Boolean.FALSE;
        for(int i=0;i<attributes.getLength();i++){
            Node attribute=attributes.item(i);

            AttributeInfo attributeInfo=new AttributeInfo();
            attributeInfo.setName(attribute.getNodeName());
            attributeInfo.setValue(attribute.getNodeValue());
            rootNode.getAttributes().addAttributeInfo(attributeInfo);

        }

    }

    /**
     *
     * 方法名:          findTreeNodeByName
     * 方法功能描述:       nodeName为全路径必须保证唯一
     * @param:         是包含汉字的字符串
     * @return:        其他非简体汉字返回 '0';
     * @Author:         陈波
     * @Create Date:   2018年6月29日 下午6:38:56
     */
    public static TreeNode findTreeNodeByName(Tree tree,String nodeName){
        if(tree==null||nodeName==null||nodeName.equals("")){
            return null;
        }
        List<TreeNode> treeNodes=tree.getRoot();
        TreeNode TreeNode=null;
        for(TreeNode treeNode:treeNodes){
            TreeNode=findTreeNode(treeNode,nodeName);
            if(TreeNode!=null){
                break;
            }
        }
        return TreeNode;

    }

    public static TreeNode findTreeNode(TreeNode treeNode,String nodeName){
        if((treeNode.getAttributes().getComponentDislayName()).equals(nodeName)){
            return treeNode;
        }else{
            if(treeNode.getChildren()!=null){
                for(TreeNode chid:treeNode.getChildren()){
                    TreeNode node=findTreeNode(chid,nodeName);
                    if(node!=null){
                        return node;
                    }
                }
            }
        }
        return null;

    }

    /**
     *
     * 方法名:          parseRootWithCdaTa
     * 方法功能描述:    方法功能描述 temp 零时存放备注
     * @param:         是包含汉字的字符串
     * @return:        其他非简体汉字返回 '0';
     * @Author:         陈波
     * @Create Date:   2018年9月26日 下午2:59:47
     */
    private static String  parseRootWithCdaTa(Node node,TreeNode rootNode,TreeNode temp){
        if(node instanceof Element){
            Element root=(Element)node;
            setTreeAttribute(root,rootNode);
            NodeList nodelist=node.getChildNodes();
            for(int i=0;i<nodelist.getLength();i++){

                Node nd = nodelist.item(i);
                TreeNode treeNode=new TreeNode();
                treeNode.setParentNode(rootNode);
                TreeNode chidTeamp=new TreeNode();
                String cdaTaString=parseRootWithCdaTa(nd,treeNode,chidTeamp);
                //如果是comment则把注释射到父节点中
                if(cdaTaString!=null){
                    temp.getAttributes().setCdaTa(cdaTaString);
                }else if(treeNode.getId()!=null&&!treeNode.getId().equals("")){
                    //如果是element则从父节点中取出注解
                    if(rootNode.getChildren()==null){
                        rootNode.setChildren(new ArrayList<TreeNode>());
                    }
                    treeNode.getAttributes().setCdaTa(temp.getAttributes().getCdaTa());
                    rootNode.getChildren().add(treeNode);
                    temp.getAttributes().setCdaTa(null);
                }
            }
        }else if(node instanceof Comment){
            Comment Comment=(Comment)node;
            return Comment.getData();
        }

        return null;

    }
    private static void buildXmlByTreeNode(TreeNode treeNode,Document doc){
        //先加备注
        if(treeNode.getAttributes()!=null){
            if(treeNode.getAttributes().getCdaTa()!=null){
                doc.createComment(treeNode.getAttributes().getCdaTa());
            }

        }
        Element element=doc.createElement(treeNode.getText());
        doc.appendChild(element);
        List<AttributeInfo> attributeInfos=treeNode.getAttributes().getAttributeInfos();
        if(attributeInfos!=null){
            for(AttributeInfo attrInfo:attributeInfos){
                String nameString=attrInfo.getName();
                String valueString=attrInfo.getValue();
                if("content".equals(nameString)){
                    element.setTextContent(valueString);
                }else{
                    element.setAttribute(nameString,valueString);
                }
            }
        }

        if(treeNode.getChildren()!=null){
            for(TreeNode chid:treeNode.getChildren()){
                buildXmlByTreeNode(chid,element,doc);
            }
        }

    }


    private static void buildXmlByTreeNode(TreeNode treeNode,Element root,Document doc){
        //先加备注
        if(treeNode.getAttributes()!=null){
            if(treeNode.getAttributes().getCdaTa()!=null){
                Comment comment=doc.createComment(treeNode.getAttributes().getCdaTa());
                root.appendChild(comment);
            }	}
        Element element=doc.createElement(treeNode.getText());
        root.appendChild(element);

        List<AttributeInfo> attributeInfos=treeNode.getAttributes().getAttributeInfos();
        if(attributeInfos!=null){
            for(AttributeInfo attrInfo:attributeInfos){
                String nameString=attrInfo.getName();
                String valueString=attrInfo.getValue();
                if("content".equals(nameString)){
                    element.setTextContent(valueString);
                }else{
                    element.setAttribute(nameString, valueString);
                }
            }
        }

        if(treeNode.getChildren()!=null){
            for(TreeNode chid:treeNode.getChildren()){
                buildXmlByTreeNode(chid,element,doc);
            }
        }

    }




    public static String getTreeNodeAttribute(Tree tree, String nodeName, String attributeName){

        TreeNode node=findTreeNodeByName(tree, nodeName);

        NodeAttribute attribute=node.getAttributes();
        if(attribute.getAttributeInfos()!=null){
            for(AttributeInfo info:attribute.getAttributeInfos()){
                if(attributeName.equals(info.getName())){
                    return info.getValue();
                }
            }
        };
        return null;

    }

}
