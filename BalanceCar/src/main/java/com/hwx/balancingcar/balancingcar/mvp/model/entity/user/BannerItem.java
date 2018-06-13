package com.hwx.balancingcar.balancingcar.mvp.model.entity.user;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BannerItem implements Serializable {
    private Long bId;

    private String image;

    private String title;

    private String content;

    private String action;

    public BannerItem() {
    }

    public BannerItem(Long bId, String image, String title, String content, String action) {
        this.bId = bId;
        this.image = image;
        this.title = title;
        this.content = content;
        this.action = action;
    }

    public Long getbId() {
        return bId;
    }

    public void setbId(Long bId) {
        this.bId = bId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

}