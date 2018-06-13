package com.hwx.balancingcar.balancingcar.mvp.action;

import android.content.Context;
import android.view.View;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by bj on 2016/1/11 0011.
 */
public class AnalysisParamsAction extends Action implements Serializable {
    public static String ActionType="analysisParams";//解析字符串
    Map<String,Object> objectMap;

    public AnalysisParamsAction() {
        super();
    }

    @Override
    public Action setData(String url,Map<String, Object> map) {
        // TODO Auto-generated method stub
        if (url.contains(ActionType)) {
            objectMap=map;
            return this;
        }else {
            return new EmptyAction();
        }
    }

    @Override
    public Boolean excute(Context activity, View view) {
        return true;
    }

    @Override
    public void resetData() {
        objectMap=null;
    }

    @Override
    public String getActionType() {
        return ActionType;
    }

}
