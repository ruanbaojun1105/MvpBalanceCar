package com.hwx.balancingcar.balancingcar.mvp.action;

import android.content.Context;
import android.view.View;

import com.hwx.balancingcar.balancingcar.activity.TalkDetailActivity;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by bj on 2016/1/11 0011.
 */
public class TalkAction extends Action implements Serializable {
    public static String ActionType="talk";//打开单个话题详情
    String talkId;

    public TalkAction() {
        super();
    }

    @Override
    public Action setData(String url,Map<String, Object> map) {
        // TODO Auto-generated method stub
        if (url.contains(ActionType)) {
            if (map.containsKey("talkId"))
                this.talkId=(String)map.get("talkId");
            return this;
        }else {
            return new EmptyAction();
        }
    }

    @Override
    public Boolean excute(Context activity, View view) {
        TalkDetailActivity.newInstance(activity,Long.parseLong(talkId));
        return true;
    }

    @Override
    public void resetData() {
        talkId=null;
    }

    @Override
    public String getActionType() {
        return ActionType;
    }

}
