/*
//The idea was that my own route should be able to pick up the message from the input queue and assign the MessageID to the CorrelationID (the same way the hypothetical service would do), but IBM MQ is just faster and this doesn't work

package com.andrelcode.ibmmqsandbox.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyRouteBuilder extends RouteBuilder {

        @Override
        public void configure() throws Exception {
            from("jms:queue:DEV.QUEUE.1?useMessageIDAsCorrelationID=true&ReplyTo=DEV.QUEUE.2&timeToLive=15000&explicitQosEnabled=true&deliveryMode=1")
                    .log("Attempting to consume message from DEV.QUEUE.1")
                    .process(exchange -> {
                        String jmsMessageID = exchange.getIn().getHeader("JMSMessageID", String.class);
                        log.info("Received message from queue: " + exchange.getIn().getBody(String.class)+ " with JMSMessageID: " + jmsMessageID);
                        exchange.getOut().setHeader("JMSCorrelationID", jmsMessageID);
                    })
                    .to("jms:queue:DEV.QUEUE.2");
        }
}*/