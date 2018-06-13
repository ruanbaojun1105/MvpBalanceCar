package com.hwx.balancingcar.balancingcar.mvp.action;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.hwx.balancingcar.balancingcar.activity.ShopDetailActivity;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by bj on 2016/1/11 0011.
 */
public class ShopAction extends Action implements Serializable {
    public static String ActionType="ShopSingal";//单个商品详情
    String id;
    String cid;

    public ShopAction() {
        super();
    }

    @Override
    public Action setData(String url,Map<String, Object> map) {
        // TODO Auto-generated method stub
        if (url.contains(ActionType)) {
            if (map.containsKey("id"))
                this.id=(String)map.get("id");
            if (map.containsKey("cid"))
                this.cid=(String)map.get("cid");
            return this;
        }else {
            return new EmptyAction();
        }
    }

    @Override
    public Boolean excute(Context activity, View view) {
        try {
            ShopDetailActivity.newInstance((Activity) activity,Long.parseLong(id),view,null);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void resetData() {
        id=null;
        cid=null;
    }

    @Override
    public String getActionType() {
        return ActionType;
    }

    public ShopAction(String id, String cid) {
        this.id = id;
        this.cid = cid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
