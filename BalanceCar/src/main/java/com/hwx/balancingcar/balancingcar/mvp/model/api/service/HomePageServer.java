package com.hwx.balancingcar.balancingcar.mvp.model.api.service;

import android.text.TextUtils;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.hwx.balancingcar.balancingcar.app.utils.NetWorkManager;
import com.hwx.balancingcar.balancingcar.app.utils.UserHelper;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.hwx.balancingcar.balancingcar.mvp.model.api.Api.SERVER8082_NAME;
import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

public interface HomePageServer {
    /**
     * 发送系统信息和发送友盟推送的设备必要信息
     * @return
     */
    @Headers({DOMAIN_NAME_HEADER + SERVER8082_NAME})
    @FormUrlEncoded
    @POST("user/getDeviceToken")
    Observable<Response> sendDeviceInfo(@Field("devicetoken") String devicetoken,
                                        @Field("userId") long userId,
                                        @Field("systemtype") String systemtype,
                                        @Field("info") String info);


    static void senInfo(String devicetoken){
        if (UserHelper.getInstance().user==null||TextUtils.isEmpty(devicetoken))
            return;
        NetWorkManager
                .getInstance()
                .getRetrofit()
                .create(HomePageServer.class)
                .sendDeviceInfo( devicetoken,UserHelper.getInstance().user.getuId(),"Android",getDeviceInfo());
    }

    static String getDeviceInfo(){
        return "当前版本号："+ AppUtils.getAppVersionName()
                +"--屏幕宽高："+ ScreenUtils.getScreenWidth()+"*"+ScreenUtils.getScreenHeight()
                +"--移动网络运营商名称:"+ NetworkUtils.getNetworkOperatorName()
                +"--设备系统版本号:"+ DeviceUtils.getSDKVersion()
                +"--设备厂商:"+ DeviceUtils.getManufacturer()
                +"--设备型号:"+ DeviceUtils.getModel()
                +"--是否root:"+ DeviceUtils.isDeviceRooted();
    }
}
