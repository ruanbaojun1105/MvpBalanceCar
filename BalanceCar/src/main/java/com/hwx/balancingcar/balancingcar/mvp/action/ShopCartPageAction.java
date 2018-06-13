package com.hwx.balancingcar.balancingcar.mvp.action;

import android.content.Context;
import android.view.View;

import com.hwx.balancingcar.balancingcar.activity.ShopCartActivity;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by bj on 2016/1/11 0011.
 */
public class ShopCartPageAction extends Action implements Serializable {
    public static String ActionType="openShopCart";//打开购物车

    public ShopCartPageAction() {
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
        ShopCartActivity.newInstance(activity);
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
