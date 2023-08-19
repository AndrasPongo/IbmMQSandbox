package com.andrelcode.ibmmqsandbox.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="ibm.mq")
@Data
public class IbmMqConfig {
    private String id;
    private String hostname;
    private Integer port;
    private String queueManager;
    private String channel;
    private String username;
    private String password;
    private String cypherSuite;

}
