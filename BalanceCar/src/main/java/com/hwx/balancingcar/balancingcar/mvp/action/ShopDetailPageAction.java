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
public class ShopDetailPageAction extends Action implements Serializable {
    public static String ActionType="openShopDetail";//打开商品详情
    String shopId;

    public ShopDetailPageAction() {
        super();
    }

    @Override
    public Action setData(String url,Map<String, Object> map) {
        // TODO Auto-generated method stub
        if (url.contains(ActionType)) {
            if (map.containsKey("shopId"))
                this.shopId=(String)map.get("shopId");
            return this;
        }else {
            return new EmptyAction();
        }
    }

    @Override
    public Boolean excute(Context activity, View view) {
        ShopDetailActivity.newInstance((Activity) activity,Long.parseLong(shopId),null,null);
        return true;
    }

    @Override
    public void resetData() {
        shopId=null;
    }

    @Override
    public String getActionType() {
        return ActionType;
    }

}
