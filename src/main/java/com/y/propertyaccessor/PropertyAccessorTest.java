package com.y.propertyaccessor;

import org.springframework.beans.DirectFieldAccessor;
import org.springframework.beans.PropertyAccessor;

/**
 * https://cloud.tencent.com/developer/article/1497713
 * Spring中的数据绑定 --- 属性访问器PropertyAccessor和实现类DirectFieldAccessor的使用
 */
public class PropertyAccessorTest {
    public static void main(String[] args) {
        Apple apple = new Apple();

        PropertyAccessor accessor = new DirectFieldAccessor(apple);

        // 设置普通属性
        accessor.setPropertyValue("color", "红色");

        // 设置嵌套属性（注意：此处能够正常work是因为有= new Size()，
        // 否则报错：Value of nested property 'size' is null 下同~）
        accessor.setPropertyValue("size.height", 10);

        // 设置集合/数组属性
        accessor.setPropertyValue("arrStr[0]", "arrStr");
        // 注意：虽然初始化时初始化过数组了，但是仍以此处的为准
        accessor.setPropertyValue("arrStr[1]", "arrStr1");
        accessor.setPropertyValue("listStr[0]", "listStr");
        // 如果角标index一样，后面覆盖前面的
        accessor.setPropertyValue("listStr[0]", "listStr1");
        // 虽然listStr是String的List，但是反射绕过了泛型  可以set进去，但一get就报错~~~需要注意这一点
        //accessor.setPropertyValue("listStr[0]", new Size());
        //accessor.setPropertyValue("listStr[1]", 20);
        //System.out.println(apple.getListStr().get(0)); //Cannot convert value of type 'com.fsx.bean.Size' to required type 'java.lang.String'


        // 设置Map：key只能是数值才行，否则是不好使的~~~~
        //accessor.setPropertyValue("map['aaa']","myValue1"); //Caused by: java.lang.NumberFormatException: For input string: "aaa"
        accessor.setPropertyValue("map[1]", "myValue2");

        // 设置listList这种集合里的集合
        accessor.setPropertyValue("listList[0][0]", "listList00");
        accessor.setPropertyValue("listList[0][1]", "listList01");
        //accessor.setPropertyValue("listList[1][0]","listList10"); //IndexOutOfBoundsException: Index: 1, Size: 1
        //accessor.setPropertyValue("listList[1][1]","listList11"); //IndexOutOfBoundsException: Index: 1, Size: 1

        // 设置listMap这种集合里面放Map
        accessor.setPropertyValue("listMap[0][0]", "listMap00");
        //accessor.setPropertyValue("listMap[0]['myKey']","listMapkey"); //For input string: "myKey"


        // =========打印输出
        System.out.println(apple);
        //Apple(color=红色, size=Size(height=10, width=null), arrStr=[arrStr, arrStr1], listStr=[listStr1], map={1=myValue2}, listList=[[listList00, listList01]], listMap=[{0=listMap00}])
    }
}
