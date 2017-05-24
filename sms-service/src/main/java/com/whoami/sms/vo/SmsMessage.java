package com.whoami.sms.vo;

import java.util.Date;

/**
 * Created by Whoami on 2017/5/23.
 */
public class SmsMessage {
    private Integer id;
    private String mobile;
    private String message;
    private Date createTime;

    public SmsMessage() {
    }

    public SmsMessage(Integer id, String mobile, String message, Date createTime) {
        this.id = id;
        this.mobile = mobile;
        this.message = message;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
