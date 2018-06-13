package com.hwx.balancingcar.balancingcar.mvp.im;

import java.util.Comparator;

/**
 * Created by bj on 2015/12/28.
 */
public class SortNotificationList implements Comparator<IMConversation> {
    @Override
    public int compare(IMConversation category0, IMConversation category1) {
        long arg0num=0,arg1num=0;
        arg0num=category0.getNewestDatetime_gmt();
        arg1num=category1.getNewestDatetime_gmt();
        long a=arg1num-arg0num;
        return a==0?0:((a>0)?1:-1);
    }
}