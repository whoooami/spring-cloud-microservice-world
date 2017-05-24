package com.whoami.stream.service.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Whoami on 2017/5/23.
 */
public interface SmsUnitSink {

    String CHANNEL_NAME = "sms_system_channel";
    @Input
    SubscribableChannel sms_system_channel();
}
