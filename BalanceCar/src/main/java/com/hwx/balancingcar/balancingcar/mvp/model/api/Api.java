package com.hwx.balancingcar.balancingcar.mvp.model.api;

/**
 * ================================================
 * 存放一些与 API 有关的东西,如请求地址,请求码等
 * <p>
 * Created by MVPArmsTemplate
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface Api {

    String ip="http://192.168.3.5:";//BuildConfig.DEBUG
    String server= false?(ip+"8082/"):"http://39.108.182.251:28080/";//社区
    String server8083= false?(ip+"8083/"):"http://39.108.182.251:38080/";//商城
    String server8082= server;
    String server8084= false?(ip+"8084/"):"http://39.108.182.251:48080/";//订单

    String APP_DOMAIN = Api.server;

    String SERVER8082_NAME = "server8082";
    String SERVER8083_NAME = "server8083";
    String SERVER8084_NAME = "server8084";
}
