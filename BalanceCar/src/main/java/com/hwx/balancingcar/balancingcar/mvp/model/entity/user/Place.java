package com.hwx.balancingcar.balancingcar.mvp.model.entity.user;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;

/**
 * bj
 * 17 08 30
 */
public class Place extends RealmObject {
    private Long pId;

    private String placeName;

    private double latitude;

    private double lonitude;

    public static Place creatBeanByJson(JSONObject jsonObject){
        Place place=new Place();
        try {
            if (jsonObject != null) {
                if (jsonObject.has("pId")&& jsonObject.get("pId") instanceof Long) {
                    place = new Place(
                            jsonObject.getLong("pId"),
                            jsonObject.getString("placeName"),
                            jsonObject.getDouble("latitude"),
                            jsonObject.getDouble("lonitude")
                    );
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            place=null;
        }
        return place;
    }

    public Place() {
    }
    public Place(Long pId,String placeName) {
        this.pId = pId;
        this.placeName = placeName;
    }

    public Place(Long pId, String placeName, double latitude, double lonitude) {
        this.pId = pId;
        this.placeName = placeName;
        this.latitude = latitude;
        this.lonitude = lonitude;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName == null ? null : placeName.trim();
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLonitude() {
        return lonitude;
    }

    public void setLonitude(double lonitude) {
        this.lonitude = lonitude;
    }
}