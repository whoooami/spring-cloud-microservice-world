package com.whoami.stream.client.controller;

import com.whoami.stream.client.producer.SmsUnitGateway;
import com.whoami.stream.vo.SmsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Whoami on 2017/5/23.
 */
@RestController
public class SmsStreamController {

    @Autowired
    public SmsUnitGateway smsUnitGateway;

    @RequestMapping(value = "/sms/send", method = RequestMethod.GET)
    public ResponseEntity send() {
        SmsMessage smsMessage = new SmsMessage(1, "911", "White house.", new Date());
        smsUnitGateway.send(smsMessage);
        return ResponseEntity.ok(smsMessage);
    }
}
