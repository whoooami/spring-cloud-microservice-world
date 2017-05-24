package com.whoami.stream.service.consumer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Whoami on 2017/5/23.
 */
@Configuration
@EnableBinding(SmsUnitSink.class)
public class IntegrationConfiguration {
}
