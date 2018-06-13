package com.hwx.balancingcar.balancingcar.mvp.model.entity.user;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * creat by bj on 2018/3/23 0023 18:08
 * 401763159@qq.com
 * com.hwx.balancingcar.balancingcar.bean.user
 * 注释：单条行驶轨迹
 */

public class TrackItem implements Serializable{
    long uploadTime;
    float mileage;
    long deviceNo;
    int deviceType;
    String loatude;
    List<String> points;

    public static CopyOnWriteArrayList<TrackItem> creatBeanByJson(JSONArray jsonArr){
        CopyOnWriteArrayList<TrackItem> list=new CopyOnWriteArrayList<>();
        if (jsonArr != null){
            for (int i = 0; i < jsonArr.length(); i++) {
                try {
                    list.add(creatBeanByJson(jsonArr.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static TrackItem creatBeanByJson(JSONObject jsonObject){
        Gson gson=new Gson();
        TrackItem item= null;
        try {
            item = gson.fromJson(jsonObject.toString(), TrackItem.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return item;
    }

    public TrackItem() {
    }

    public long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    public long getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(long deviceNo) {
        this.deviceNo = deviceNo;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public List<String> getPoints() {
        return ImageItem.creatStrByJsonStr(loatude);
    }

    public void setPoints(List<String> points) {
        this.points = points;
    }
}
