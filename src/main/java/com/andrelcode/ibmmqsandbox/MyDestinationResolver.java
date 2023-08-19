package com.andrelcode.ibmmqsandbox;

import org.springframework.jms.support.destination.DestinationResolver;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

public class MyDestinationResolver implements DestinationResolver {

        @Override
        public Destination resolveDestinationName(Session session, String destinationName, boolean pubSubDomain) throws JMSException {
            return session.createQueue("queue:///"+destinationName+"?targetClient=1");
            //return session.createQueue(destinationName);
        }
}
