package com.hwx.balancingcar.balancingcar.mvp.model.entity.user;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * bj
 * 17 08 30
 */
public class ImageItem extends RealmObject {

    private String imagePath;

    public static RealmList<ImageItem> list2ItemImages(List<String> item) {
        RealmList<ImageItem> carRealmList=new RealmList<>();
        for (String itemstr:item)
            carRealmList.add(new ImageItem(itemstr));
        return carRealmList;
    }

    public static RealmList<ImageItem> creatBeanByJson(JSONArray jsonArr, String value){
        RealmList<ImageItem> carRealmList=new RealmList<>();
        if (jsonArr != null){
            for (int i = 0; i < jsonArr.length(); i++) {
                try {
                    carRealmList.add(creatBeanByJson(jsonArr.getJSONObject(i),value));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return carRealmList;
    }

    public static ImageItem creatBeanByJson(JSONObject jsonObject,String value){
        ImageItem car=new ImageItem();
        try {
            if (jsonObject != null)
                car = new ImageItem(jsonObject.getString(value));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return car;
    }

    public static RealmList<ImageItem> creatBeanByJsonStr(String json){
        RealmList<ImageItem> imageItems=new RealmList<>();
        if (!TextUtils.isEmpty(json)) {
            try {
                String[] pas=json.split("<<");
                for(String a:pas){
                    ImageItem item = new ImageItem(a);
                    imageItems.add(item);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return imageItems;
    }

    public static List<String> creatStrByJsonStr(String json){
        List<String> imageItems=new ArrayList<>();
        if (!TextUtils.isEmpty(json)) {
            try {
                String[] pas=json.split("<<");
                for(String a:pas){
                    imageItems.add(a);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return imageItems;
    }

    public ImageItem(String imagePath) {
        this.imagePath = imagePath;
    }

    public ImageItem() {
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}