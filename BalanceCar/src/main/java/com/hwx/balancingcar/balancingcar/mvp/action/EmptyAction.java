package com.hwx.balancingcar.balancingcar.mvp.action;

import android.content.Context;
import android.view.View;

import com.hwx.balancingcar.balancingcar.util.LogUtils;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/22.
 */

public class EmptyAction extends Action {

    public EmptyAction() {
    }

    @Override
    public Action setData(String url, Map<String, Object> map) {
        return this;
    }

    @Override
    public Boolean excute(Context activity, View view) {
        LogUtils.e("生成空的action，不处理");
        return false;
    }

    @Override
    public String getActionType() {
        return "";
    }

    @Override
    public void resetData() {
    }
}
