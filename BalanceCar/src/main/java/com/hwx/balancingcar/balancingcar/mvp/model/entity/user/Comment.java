package com.hwx.balancingcar.balancingcar.mvp.model.entity.user;

import android.text.TextUtils;

import com.hwx.balancingcar.balancingcar.util.DateUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * 0170925
 * bj
 */
public class Comment extends RealmObject {
    private Long cId;

    private Users user;

    private Date replyTime;

    private String comContent;

    private Users replyUser;

    private boolean isLike;

    private Integer likedCount;

    private boolean isTop;//是否精选


    public static RealmList<Comment> creatBeanByJson(JSONArray jsonArr){
        RealmList<Comment> carRealmList=new RealmList<>();
        if (jsonArr != null){
            for (int i = 0; i < jsonArr.length(); i++) {
                try {
                    carRealmList.add(creatBeanByJson(jsonArr.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return carRealmList;
    }

    public static Comment creatBeanByJson(JSONObject jsonObject){
        Comment comment=new Comment();
        try {
            if (jsonObject != null)
                comment = new Comment(
                        jsonObject.getLong("cId"),
                        jsonObject.has("user")?Users.creatBeanByJson(jsonObject.getJSONObject("user")):null,
                        new Date(jsonObject.getLong("replyTime")),
                        jsonObject.getString("comContent"),
                        jsonObject.has("replyUser")?Users.creatBeanByJson(jsonObject.getJSONObject("replyUser")):null,
                        jsonObject.getInt("isLike")==0?false:true,
                        jsonObject.getInt("likedCount"),
                        jsonObject.getInt("istop")==0?false:true
                );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return comment;
    }

    public Comment() {
    }

    public Comment(Long cId, Users user, Date replyTime, String comContent, Users replyUser, boolean isLike, Integer likedCount,boolean isTop) {
        this.cId = cId;
        this.user = user;
        this.replyTime = replyTime;
        this.comContent = comContent;
        this.replyUser = replyUser;
        this.isLike = isLike;
        this.likedCount = likedCount;
        this.isTop = isTop;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public String getReplyTimeStr() {
        return DateUtils.getCountnumber(replyTime.getTime());
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public String getComContent() {
        if (TextUtils.isEmpty(comContent))
            return "";
        return comContent;
    }

    public void setComContent(String comContent) {
        this.comContent = comContent;
    }

    public Users getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(Users replyUser) {
        this.replyUser = replyUser;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public Integer getLikedCount() {
        return likedCount;
    }

    public void setLikedCount(Integer likedCount) {
        this.likedCount = likedCount;
    }
}