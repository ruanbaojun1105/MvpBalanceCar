package com.hwx.balancingcar.balancingcar.mvp.action;

import android.content.Context;
import android.view.View;

import com.hwx.balancingcar.balancingcar.activity.ShopAllActivity;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by bj on 2016/1/11 0011.
 */
public class ShopAllPageAction extends Action implements Serializable {
    public static String ActionType="openShopAll";//查看所有商品

    public ShopAllPageAction() {
        super();
    }

    @Override
    public Action setData(String url,Map<String, Object> map) {
        // TODO Auto-generated method stub
        if (url.contains(ActionType)) {
            return this;
        }else {
            return new EmptyAction();
        }
    }

    @Override
    public Boolean excute(Context activity, View view) {
        ShopAllActivity.newInstance(activity);
        return true;
    }

    @Override
    public void resetData() {
    }

    @Override
    public String getActionType() {
        return ActionType;
    }

}
