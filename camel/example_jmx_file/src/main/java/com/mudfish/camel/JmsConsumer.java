package com.mudfish.camel;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Created by gyrx-dskf15 on 2018/11/6.
 */
public class JmsConsumer {

	public static void main(String[] args) throws Exception {
		DefaultCamelContext camelContext = new DefaultCamelContext();
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		camelContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		camelContext.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("jms:queue:test.queue")
						.process(new Processor() {
							@Override
							public void process(Exchange exchange) throws Exception {
								Message message = exchange.getIn();
								System.out.println(message.getBody().toString());
//                                exchange.getOut().setBody("consumer return");
							}
						})
						.to("file://test")
						.bean(ParseWord.class, "prase2");

			}
		});
		camelContext.start();

		Thread.sleep(100000);

	}
}
