package com.lyl.model;

import java.time.LocalDateTime;

/**
 * Created by 潘淮  on 2018/12/31.<br>
 */
public class Active {

    private int id;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;

    private String address;

    private String headerPic;

    private String title;

    private int state;

    public Active() {
    }

    public Active(int id, LocalDateTime beginTime, LocalDateTime endTime, String address, String headerPic,String title) {
        this.id = id;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.address = address;
        this.headerPic = headerPic;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeaderPic() {
        return headerPic;
    }

    public void setHeaderPic(String headerPic) {
        this.headerPic = headerPic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
