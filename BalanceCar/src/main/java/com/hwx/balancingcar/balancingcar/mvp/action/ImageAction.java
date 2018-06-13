package com.hwx.balancingcar.balancingcar.mvp.action;

import android.content.Context;
import android.view.View;

import com.hwx.balancingcar.balancingcar.activity.ImagePreviewActivity;
import com.hwx.balancingcar.balancingcar.bean.user.ImageItem;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by bj on 2016/1/11 0011.
 */
public class ImageAction extends Action implements Serializable {
    public static String ActionType="openImage";//打开一串图
    String image;

    public ImageAction() {
        super();
    }

    @Override
    public Action setData(String url,Map<String, Object> map) {
        // TODO Auto-generated method stub
        if (url.contains(ActionType)) {
            StringBuilder builder=new StringBuilder(0);
            for (String string:map.keySet())
                builder.append(map.get(string));
            this.image = builder.toString();
            builder.setLength(0);
            return this;
        }else {
            return new EmptyAction();
        }
    }

    @Override
    public Boolean excute(Context activity, View view) {
        ImagePreviewActivity.newInstance(activity, ImageItem.creatStrByJsonStr(image),view,0);
        return true;
    }

    @Override
    public void resetData() {
        image=null;
    }

    @Override
    public String getActionType() {
        return ActionType;
    }

}
