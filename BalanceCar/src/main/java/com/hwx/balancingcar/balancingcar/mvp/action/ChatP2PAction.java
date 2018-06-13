package com.hwx.balancingcar.balancingcar.mvp.action;

import android.content.Context;
import android.view.View;

import com.hwx.balancingcar.balancingcar.activity.ChatP2PActivity;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by bj on 2016/1/11 0011.
 */
public class ChatP2PAction extends Action implements Serializable {
    public static String ActionType="openChatP2P";//单聊
    String userId;
    String userName;

    public ChatP2PAction() {
        super();
    }

    @Override
    public Action setData(String url,Map<String, Object> map) {
        // TODO Auto-generated method stub
        if (url.contains(ActionType)) {
            if (map.containsKey("userId"))
                this.userId=(String)map.get("userId");
            if (map.containsKey("userName"))
                this.userName=(String)map.get("userName");
            return this;
        }else {
            return new EmptyAction();
        }
    }

    @Override
    public Boolean excute(Context activity, View view) {
        ChatP2PActivity.newInstance(activity,userId,userName);
        return true;
    }

    @Override
    public void resetData() {
        userId=null;
        userName=null;
    }

    @Override
    public String getActionType() {
        return ActionType;
    }

}
