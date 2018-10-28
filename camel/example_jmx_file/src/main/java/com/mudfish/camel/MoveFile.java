package com.mudfish.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Created by JiangWeiGen on 2018/10/23 0023.
 */
public class MoveFile {
    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file://test?noop=true")
                        .to("file://testCopy");
            }
        });
        camelContext.start();
        Thread.sleep(500000);
        camelContext.stop();
    }
}
