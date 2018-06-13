package com.hwx.balancingcar.balancingcar.mvp.action;

import android.content.Context;
import android.view.View;

import com.hwx.balancingcar.balancingcar.activity.ShopCouponListActivity;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by bj on 2016/1/11 0011.
 */
public class NotificationNewsAction extends Action implements Serializable {
    public static String ActionType="notificationNews";//新通知
    String title;
    String content;

    public NotificationNewsAction() {
        super();
    }

    @Override
    public Action setData(String url,Map<String, Object> map) {
        // TODO Auto-generated method stub
        if (url.contains(ActionType)) {
            if (map.containsKey("title"))
                this.title=(String)map.get("title");
            if (map.containsKey("content"))
                this.content=(String)map.get("content");
            return this;
        }else {
            return new EmptyAction();
        }
    }

    @Override
    public Boolean excute(Context activity, View view) {
        ShopCouponListActivity.showDialog(activity,title,content);
        return true;
    }

    @Override
    public void resetData() {
        title=null;
        content=null;
    }

    @Override
    public String getActionType() {
        return ActionType;
    }

}
