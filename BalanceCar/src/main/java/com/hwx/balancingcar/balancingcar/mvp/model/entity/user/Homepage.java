package com.hwx.balancingcar.balancingcar.mvp.model.entity.user;


import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * bj
 * 17 08 30
 */
public class Homepage extends RealmObject {

    @PrimaryKey
    private long homeId;
    private UsersSelf user;
//    private long my_talk_id;
//    private long my_comment_id;

    //private RealmList<Talk> talkRealmList;
    //private RealmList<Comment> commentRealmList;

    private RealmList<Car> carRealmList;

    public static Homepage creatBeanByJson(JSONObject jsonObject){
        Homepage homepage=null;
        try {
            if (jsonObject != null) {
                UsersSelf users=UsersSelf.creatBeanByJson(jsonObject.getJSONObject("users"));
                RealmList<Car> cars=Car.creatBeanByJson(jsonObject.getJSONArray("cars"));
                homepage = new Homepage(users.getPhoneNo(),users, cars);//用手机号做id
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return homepage;
    }

    public Homepage(long homeId,UsersSelf user, RealmList<Car> carRealmList) {
        this.homeId=homeId;
        this.user = user;
        this.carRealmList = carRealmList;
    }


    public Homepage() {
    }

    public long getHomeId() {
        return homeId;
    }

    public void setHomeId(long homeId) {
        this.homeId = homeId;
    }

    public UsersSelf getUser() {
        return user;
    }

    public void setUser(UsersSelf user) {
        this.user = user;
    }

    public RealmList<Car> getCarRealmList() {
        return carRealmList;
    }

    public void setCarRealmList(RealmList<Car> carRealmList) {
        this.carRealmList = carRealmList;
    }
}