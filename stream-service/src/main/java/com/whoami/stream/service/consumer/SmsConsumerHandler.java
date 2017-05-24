package com.whoami.stream.service.consumer;

import com.whoami.stream.vo.SmsMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * Created by Whoami on 2017/5/23.
 */
@Service
public class SmsConsumerHandler {
    private static final Logger logger = LoggerFactory.getLogger(SmsConsumerHandler.class);

    @StreamListener(SmsUnitSink.CHANNEL_NAME)
    public void process(SmsMessage smsMessage) {

        logger.info("#### sms id: {}, mobile: {}, content:{}", smsMessage.getId(), smsMessage.getMobile(), smsMessage.getMessage());
    }
}
