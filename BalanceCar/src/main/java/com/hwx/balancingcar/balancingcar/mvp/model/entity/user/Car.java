package com.hwx.balancingcar.balancingcar.mvp.model.entity.user;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * bj
 * 17 08 30
 */
public class Car extends RealmObject {
    private Long carId;

    private String carNo;

    private String carName;

    private String carType;

    private Date sellTime;

    private String fixRecord;

    private String carImage;

    private Place place;

    public static RealmList<Car> creatBeanByJson(JSONArray jsonArr){
        RealmList<Car> carRealmList=new RealmList<>();
        if (jsonArr != null){
            for (int i = 0; i < jsonArr.length(); i++) {
                try {
                    Car car=creatBeanByJson(jsonArr.getJSONObject(i));
                    if (car.getCarNo().equals("0")||car.getCarNo().equals("0.0"))
                        continue;
                    carRealmList.add(car);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return carRealmList;
    }

    public static Car creatBeanByJson(JSONObject jsonObject){
        Car car=new Car();
        try {
            if (jsonObject != null)
                car = new Car(
                        jsonObject.getLong("carId"),
                        jsonObject.getString("carNo"),
                        jsonObject.getString("carName"),
                        jsonObject.getString("carType"),
                        new Date(jsonObject.getLong("sellTime")),
                        jsonObject.getString("fixRecord"),
                        jsonObject.getString("carImage"),
//                        new Place(jsonObject.getLong("pId"),
//                                jsonObject.getString("placeName"),
//                                jsonObject.getDouble("latitude"),
//                                jsonObject.getDouble("lonitude"))
                        jsonObject.get("place") instanceof JSONObject?Place.creatBeanByJson(jsonObject.getJSONObject("place")):null
                );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return car;
    }

    public Car(Long carId, String carNo, String carName, String carType, Date sellTime, String fixRecord, String carImage, Place place) {
        this.carId = carId;
        this.carNo = carNo;
        this.carName = carName;
        this.carType = carType;
        this.sellTime = sellTime;
        this.fixRecord = fixRecord;
        this.carImage = carImage;
        this.place = place;
    }

    public Car() {
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType == null ? null : carType.trim();
    }

    public Date getSellTime() {
        return sellTime;
    }

    public void setSellTime(Date sellTime) {
        this.sellTime = sellTime;
    }

    public String getFixRecord() {
        return fixRecord;
    }

    public void setFixRecord(String fixRecord) {
        this.fixRecord = fixRecord == null ? null : fixRecord.trim();
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}