package com.hwx.balancingcar.balancingcar.mvp.action;

import android.content.Context;
import android.view.View;

import com.hwx.balancingcar.balancingcar.App;
import com.hwx.balancingcar.balancingcar.activity.WebviewActivity;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by bj on 2016/1/11 0011.
 */
public class WebviewAction extends Action implements Serializable {
    public static String ActionType="weburl";//打开浏览器
    String url;

    public WebviewAction() {
        super();
    }

    @Override
    public Action setData(String url,Map<String, Object> map) {
        // TODO Auto-generated method stub
        if (url.contains(ActionType)) {
            if (map.containsKey("url"))
                this.url=(String)map.get("url")+"";
            Map<String, Object> header = Action.getHeader(this.url);
            this.url=this.url+(header.keySet().size()>0?"&":"?")+"userId="+String.valueOf(App.users==null?0L:App.users.getuId());
            return this;
        }else {
            return new EmptyAction();
        }
    }

    @Override
    public Boolean excute(Context activity, View view) {
        WebviewActivity.newInstance(activity, url);
        return true;
    }

    @Override
    public void resetData() {
        url=null;
    }

    @Override
    public String getActionType() {
        return ActionType;
    }

}
