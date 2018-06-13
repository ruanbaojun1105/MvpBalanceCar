package com.hwx.balancingcar.balancingcar.mvp.model.entity.user;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bj on 2017/8/31.
 * 读取本地是否有token，然后通过这个去判断用户是否登陆，验证这个值是否过期
 */

public class UserToken extends RealmObject {
    @PrimaryKey
    public String redisToken;
    public String password;


    public UserToken() {
    }

    public String getRedisToken() {
        return redisToken;
    }

    public UserToken(String redisToken, String password) {
        this.redisToken = redisToken;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRedisToken(String redisToken) {
        this.redisToken = redisToken;
    }
}
