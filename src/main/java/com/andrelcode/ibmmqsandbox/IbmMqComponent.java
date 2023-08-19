package com.andrelcode.ibmmqsandbox;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.stereotype.Component;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.component.jms.JmsComponent;

@Component
@Slf4j
public class IbmMqComponent {

    private final MQQueueConnectionFactory connectionFactory;
    public IbmMqComponent(MQQueueConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    private UserCredentialsConnectionFactoryAdapter userCredentialsConnectionFactoryAdapter() {
        UserCredentialsConnectionFactoryAdapter adapter = new UserCredentialsConnectionFactoryAdapter();
        adapter.setUsername("app");
        adapter.setPassword("pw");
        adapter.setTargetConnectionFactory(connectionFactory);

        return adapter;
    }

    private JmsConfiguration jmsConfiguration() {
        JmsConfiguration jmsConfiguration = new JmsConfiguration();
        jmsConfiguration.setConnectionFactory(userCredentialsConnectionFactoryAdapter());
        jmsConfiguration.setDestinationResolver(new MyDestinationResolver());

        return jmsConfiguration;
    }

    @Bean
    public JmsComponent jms() {
        return new JmsComponent(jmsConfiguration());
    }

}
