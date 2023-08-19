package com.andrelcode.ibmmqsandbox;

import org.apache.camel.*;
import org.apache.camel.language.constant.ConstantLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {

    @Autowired
    CamelContext context;

    @Autowired
    ProducerTemplate producerTemplate;

    public Exchange executeRequestReply(String targetQueue, String destination, String payload) {
        Exchange out = producerTemplate.send(targetQueue, ExchangePattern.InOut, exchange -> {
            Message in = exchange.getIn();
            in.setBody(payload);
            in.setHeader("CamelJmsDestinationName", destination);
            in.setHeader("JMS_IBM_MsgType", ConstantLanguage.constant(8));
            in.setHeader("JMS_IBM_Format", "MQSTR");
        });

        return out;
    }
}