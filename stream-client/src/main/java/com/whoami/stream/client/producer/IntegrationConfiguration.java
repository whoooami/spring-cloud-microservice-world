package com.whoami.stream.client.producer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;

/**
 * Created by Whoami on 2017/5/23.
 */
@Configuration
@EnableBinding(SmsUnitSource.class)
@IntegrationComponentScan
public class IntegrationConfiguration {
}
