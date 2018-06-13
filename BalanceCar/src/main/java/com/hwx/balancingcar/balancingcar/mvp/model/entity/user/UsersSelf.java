package com.hwx.balancingcar.balancingcar.mvp.model.entity.user;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * bj
 * 17 08 30
 */
public class UsersSelf extends RealmObject {
    @PrimaryKey
    private Long uId;

    private Long phoneNo;//同时也是home每个用户的个人中心id

    private String password;

    private String nickname;

    private String photo;

    private String descr;//个人签名

    private String bgImage;

    private long registTime;

    private Integer mark;

    private Integer status;

    private Integer level;

    private long lastLogin;



    public UsersSelf(Long uId, Long phoneNo, String password, String nickname, String photo, String descr,
                     String bgImage, long registTime, Integer mark, Integer status, Integer level, long lastLogin) {
        this.uId = uId;
        this.phoneNo = phoneNo;
        this.password = password;
        this.nickname = nickname;
        this.photo = photo;
        this.descr = descr;
        this.bgImage = bgImage;
        this.registTime = registTime;
        this.mark = mark;
        this.status = status;
        this.level = level;
        this.lastLogin = lastLogin;
    }

    public UsersSelf() {
    }

    public String getBgImage() {
        return bgImage;
    }

    public void setBgImage(String bgImage) {
        this.bgImage = bgImage;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }


    public Long getuId() {
        return uId;
    }

    public UsersSelf setuId(Long uId) {
        this.uId = uId;
        return this;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    /**
     * 个人中心Id
     * @return
     */
    public Long getHomePageId() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public UsersSelf setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public long getRegistTime() {
        return registTime;
    }

    public void setRegistTime(long registTime) {
        this.registTime = registTime;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLevel() {
        return level==0?1:level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(long lastLogin) {
        this.lastLogin = lastLogin;
    }

}