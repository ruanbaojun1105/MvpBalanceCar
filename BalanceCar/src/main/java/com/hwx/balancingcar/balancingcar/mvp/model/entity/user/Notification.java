package com.hwx.balancingcar.balancingcar.mvp.model.entity.user;

import com.hwx.balancingcar.balancingcar.bean.UsersRe;
import com.hwx.balancingcar.balancingcar.util.DateUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bj on 2017/9/21.
 * type//1留言2评论3关注4系统5特殊用好友方式显示
 */

public class Notification implements Serializable{

    public static final int LEAVINGMESSAGE = 1;
    public static final int COMMENT = 2;
    public static final int FOLLOW = 3;
    public static final int SYSTEM = 4;
    public static final int FRIEND = 5;

    long no_id;
    UsersRe user;
    UsersRe replyuser;
    Date report_time;
    int type;
    String content;
    String temp;//json数据


    public static List<Notification> creatBeanByJson(JSONArray jsonArr){
        List<Notification> bannerList=new ArrayList<>();
        if (jsonArr != null){
            for (int i = 0; i < jsonArr.length(); i++) {
                try {
                    bannerList.add(creatBeanByJson(jsonArr.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return bannerList;
    }

    public static Notification creatBeanByJson(JSONObject jsonObject){
        Notification notification=null;
        try {
            if (jsonObject != null) {
                Users reu= null;
                try {
                    reu = jsonObject.get("replyuser") instanceof JSONObject? Users.creatBeanByJson(jsonObject.getJSONObject("replyuser")):null;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                notification = new Notification(
                        jsonObject.getLong("noId"),
                        new UsersRe(Users.creatBeanByJson(jsonObject.getJSONObject("user"))),
                        reu==null?null:new UsersRe(reu),
                        new Date(jsonObject.getLong("reportTime")),
                        jsonObject.getInt("type"),
                        jsonObject.getString("content"),
                        jsonObject.getString("temp")
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return notification;
    }

    public Notification(long no_id, UsersRe user, UsersRe replyuser, Date report_time, int type, String content, String temp) {
        this.no_id = no_id;
        this.user = user;
        this.replyuser = replyuser;
        this.report_time = report_time;
        this.type = type;
        this.content = content;
        this.temp = temp;
    }

    public Notification() {
    }

    public long getNo_id() {
        return no_id;
    }

    public void setNo_id(long no_id) {
        this.no_id = no_id;
    }

    public UsersRe getUser() {
        return user;
    }

    public void setUser(UsersRe user) {
        this.user = user;
    }

    public UsersRe getReplyuser() {
        return replyuser;
    }

    public void setReplyuser(UsersRe replyuser) {
        this.replyuser = replyuser;
    }

    public Date getReport_time() {
        return report_time;
    }

    public String getReport_timeStr() {
        return DateUtils.getCountnumber(report_time.getTime());
    }

    public void setReport_time(Date report_time) {
        this.report_time = report_time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
