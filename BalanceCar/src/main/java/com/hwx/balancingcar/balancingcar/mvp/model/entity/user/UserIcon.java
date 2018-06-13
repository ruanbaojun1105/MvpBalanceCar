package com.hwx.balancingcar.balancingcar.mvp.model.entity.user;

import com.hwx.balancingcar.balancingcar.util.ViewUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/25.
 */

public class UserIcon implements Serializable {
    private long uId;
    private String photo;
    private String nickName;
    private Date time;

    public static List<UserIcon> creatBeanByJson(JSONArray jsonArr){
        List<UserIcon> carRealmList=new ArrayList<>();
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

    public static UserIcon creatBeanByJson(JSONObject jsonObject){
        UserIcon userIcon=new UserIcon();
        try {
            if (jsonObject != null) {
                Map<String, Object> map= ViewUtil.getHeader(jsonObject.getString("temp"));
                String image = !map.containsKey("photo") ? "" : (String) map.get("photo");
                String nickname = !map.containsKey("nickname") ? "" : (String) map.get("nickname");
                String time= !map.containsKey("time") ? "0" : (String) map.get("time");
                userIcon = new UserIcon(
                        jsonObject.getLong("replyuserId"),
                        image,
                        nickname,
                        new Date(Long.parseLong(time))
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return userIcon;
    }

    public UserIcon(long uId, String photo, String nickName) {
        this.uId = uId;
        this.photo = photo;
        this.nickName = nickName;
    }

    public UserIcon(long uId, String photo, String nickName,Date time) {
        this.uId = uId;
        this.photo = photo;
        this.nickName = nickName;
        this.time = time;
    }

    public UserIcon() {
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public long getuId() {
        return uId;
    }

    public void setuId(long uId) {
        this.uId = uId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
