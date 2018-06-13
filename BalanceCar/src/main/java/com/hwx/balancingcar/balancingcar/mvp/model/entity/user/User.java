package com.hwx.balancingcar.balancingcar.mvp.model.entity.user;

import io.realm.RealmObject;

public class User extends RealmObject {

    private Long uId;

    private Long phoneNo;

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

    public User(Long uId, Long phoneNo, String password, String nickname, String photo, String descr, String bgImage,
                long registTime, Integer mark, Integer status, Integer level, long lastLogin) {
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

    public User() {
    }

    public static User creatUserBySelf(UsersSelf usersSelf){
        User users=new User(usersSelf);
        return users;
    }

    public User(UsersSelf usersSelf) {
        this.uId =usersSelf. getuId();
        this.phoneNo = usersSelf.getPhoneNo();
        this.password = usersSelf.getPassword();
        this.nickname = usersSelf.getNickname();
        this.photo = usersSelf.getPhoto();
        this.descr = usersSelf.getDescr();
        this.bgImage = usersSelf.getBgImage();
        this.registTime = usersSelf.getRegistTime();
        this.mark = usersSelf.getMark();
        this.status = usersSelf.getStatus();
        this.level = usersSelf.getLevel();
        this.lastLogin = usersSelf.getLastLogin();
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getBgImage() {
        return bgImage;
    }

    public void setBgImage(String bgImage) {
        this.bgImage = bgImage;
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
        return level;
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