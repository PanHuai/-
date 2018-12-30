package com.lyl.model;

import java.time.LocalDateTime;

/**
 * Created by 潘淮  on 2018/12/29.<br>
 */
public class AccessToken {

    private int id;

    private String access_token;

    private Long expires_in;

    private LocalDateTime beginTime;

    private int version;

    public AccessToken() {
    }

    public AccessToken(int id, String access_token, Long expires_in, LocalDateTime beginTime, int version) {
        this.id = id;
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.beginTime = beginTime;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
