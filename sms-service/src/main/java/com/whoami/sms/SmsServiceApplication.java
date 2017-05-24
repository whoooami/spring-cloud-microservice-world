package com.whoami.sms;

import com.whoami.sms.vo.SmsMessage;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

/**
 * Created by Whoami on 2017/5/10.
 */
@SpringBootApplication
//@EnableBinding(Source.class)
public class SmsServiceApplication {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /*@Bean
    @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1"))
    public MessageSource<SmsMessage> orderSource() {
        return () -> {
            SmsMessage o = new SmsMessage(0, "13538078157", "haha", LocalDateTime.now().toDate());
            logger.info("Sending order: " + o);
            return new GenericMessage<>(o);
        };
    }*/

    @Bean
    public AlwaysSampler defaultSampler() {
        return new AlwaysSampler();
    }

    public static void main(String[] args) {
        SpringApplication.run(SmsServiceApplication.class, args);
    }
}
