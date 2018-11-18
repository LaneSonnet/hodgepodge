package com.mudfish.common.xml.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Created by JiangWeiGen on 2018/11/18 0018.
 */
@XStreamAlias("Customer")
public class Customer {
//    @XStreamAsAttribute
    private String commodity;

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "commodity='" + commodity + '\'' +
                '}';
    }
}
