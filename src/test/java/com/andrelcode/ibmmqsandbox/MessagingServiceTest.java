package com.andrelcode.ibmmqsandbox;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
class MessagingServiceTest {

    @Autowired
    MessagingService messagingService;

    @Test
    void executeRequestReply() {
        String targetQueue = "jms:queue:DEV.QUEUE.1?useMessageIDAsCorrelationID=true&ReplyTo=DEV.QUEUE.2&timeToLive=15000&explicitQosEnabled=true&deliveryMode=1";
        String destination = "DEV.QUEUE.2";
        String payload = "Hello World!";

        Exchange out = messagingService.executeRequestReply(targetQueue, destination, payload);

        //Somewhat contradictory, but receiving this message means the correlationId mechanism worked, and we didn't pick up a seemingly unrelated message from the reply queue
        //The reason I had to resort to this is that currently I'm not aware of any way to grab the message from the input queue before IBM MQ acts on the ReplyTo parameter first (and thus consumes the message)
        assertTrue(out.getException().getMessage().contains("The OUT message was not received within: 20000 millis due reply message with correlationID"));
    }
}