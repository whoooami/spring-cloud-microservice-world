package com.whoami.stream.client.producer;

import com.whoami.stream.vo.SmsMessage;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

/**
 * Created by Whoami on 2017/5/23.
 */
@MessagingGateway
public interface SmsUnitGateway {

    @Gateway(requestChannel = SmsUnitSource.CHANNEL_NAME)
    void send(SmsMessage smsMessage);
}
