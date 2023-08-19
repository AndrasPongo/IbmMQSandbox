package com.andrelcode.ibmmqsandbox;

import com.andrelcode.ibmmqsandbox.config.IbmMqConfig;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class IbmMqFactory {

    private  final IbmMqConfig ibmMqConfig;

    public IbmMqFactory(IbmMqConfig ibmMqConfig) {
        this.ibmMqConfig = ibmMqConfig;
    }

    @Bean
    public MQQueueConnectionFactory mqQueueConnectionFactory() {
        MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
        mqQueueConnectionFactory.setHostName(ibmMqConfig.getHostname());
        try {
            mqQueueConnectionFactory.setTransportType(1);
            mqQueueConnectionFactory.setCCSID(1208);
            mqQueueConnectionFactory.setChannel(ibmMqConfig.getChannel());
            mqQueueConnectionFactory.setPort(ibmMqConfig.getPort());
            mqQueueConnectionFactory.setQueueManager(ibmMqConfig.getQueueManager());
            mqQueueConnectionFactory.setAppName(ibmMqConfig.getId());

            log.info(mqQueueConnectionFactory.toString());
        } catch (Exception e) {
            log.error("Error while creating MQQueueConnectionFactory", e);
            throw new RuntimeException(e);
        }
        return mqQueueConnectionFactory;
    }
}
