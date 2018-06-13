package com.hwx.balancingcar.balancingcar.mvp.im;

/**
 * Created by zhaoxu on 2015/12/1.
 * DEMO工程的一些持久化存储
 */
public class SimpleKVStore {
    private static String NEED_SOUND = "need_sound";//是否静音
    private static String NEED_VIBRATION = "need_vibration";//是否震动

    public static int getNeedSound(){
        return com.alibaba.wxlib.util.SimpleKVStore.getIntPrefs(getUserId() + NEED_SOUND, 1);
    }

    public static void setNeedSound(int value){
        com.alibaba.wxlib.util.SimpleKVStore.setIntPrefs(getUserId() + NEED_SOUND, value);
    }

    public static int getNeedVibration(){
        return com.alibaba.wxlib.util.SimpleKVStore.getIntPrefs(getUserId() + NEED_VIBRATION, 1);
    }

    public static void setNeedVibration(int value){
        com.alibaba.wxlib.util.SimpleKVStore.setIntPrefs(getUserId() + NEED_VIBRATION, value);
    }

    private static String getUserId(){
        return LoginHelper.getInstance().getIMKit().getIMCore().getLongLoginUserId();
    }
}
