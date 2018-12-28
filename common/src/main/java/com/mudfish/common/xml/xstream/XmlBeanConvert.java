package com.mudfish.common.xml.xstream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.thoughtworks.xstream.XStream;

/**
 * Created by JiangWeiGen on 2018/11/18 0018.
 */
public class XmlBeanConvert {

    public static void main(String[] args) {
        beanToXml();
//        xmlToBean();
    }

    private static void xmlToBean() {
        //模拟一个xml格式字符串
        String xml = "<user>\n" +
                "  <first_name>beyondLi</first_name>\n" +
                "  <birthday>2018-09-08</birthday>\n" +
                "  <age>23</age>\n" +
                "  <customer>\n" +
                "    <Customer>\n" +
                "      <commodity>商品1</commodity>\n" +
                "    </Customer>\n" +
                "    <Customer>\n" +
                "      <commodity>商品2</commodity>\n" +
                "    </Customer>\n" +
                "  </customer>\n" +
                "</user>";
        //创建xStream对象
        XStream xstream = new XStream();
        xstream.processAnnotations(User.class);
//        xstream.autodetectAnnotations(true);
        User user2 = (User) xstream.fromXML(xml);
        System.out.println(user2);
    }

    private static void beanToXml() {
        //创建user对象与customer对象并赋值
        User user = new User();
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        customer1.setCommodity("商品1");
        customer2.setCommodity("商品2");
        List<Customer> list = new ArrayList<>();
        list.add(customer1);
        list.add(customer2);
        user.setName("beyondLi");
        user.setAge(23);
        user.setBirthday(new Date());
        user.setCustomer(list);
        //创建xStream对象
        XStream xStream = new XStream();
        xStream.processAnnotations(User.class);
        //调用toXML 将对象转成字符串
        String s = xStream.toXML(user);
        System.out.println(s);
    }
}
