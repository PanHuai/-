package com.lyl.model;

import java.time.LocalDateTime;

/**
 * Created by 潘淮  on 2018/12/13.<br>
 */
public class User {

    private int id; // 用户ID

    private String userName;  // 用户昵称

    private String tel;  // 手机号码

    private String openid;  //用户的唯一标识

    private String unionid; //只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。

    private LocalDateTime createTime; // 账号创建时间

    private String logo;  // 用户头像

    private String city;  //普通用户个人资料填写的城市

    private String sex;  //用户的性别，值为1时是男性，值为2时是女性，值为0时是未知

    private LocalDateTime updateTime;  // 更新时间

    private int version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
