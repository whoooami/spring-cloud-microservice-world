package com.whoami.stream.client.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by Whoami on 2017/5/23.
 */
public interface SmsUnitSource {

    String CHANNEL_NAME = "sms_system_channel";

    @Output
    MessageChannel sms_system_channel();
}
