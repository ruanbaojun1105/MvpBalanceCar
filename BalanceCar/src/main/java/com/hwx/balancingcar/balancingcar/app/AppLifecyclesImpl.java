package com.hwx.balancingcar.balancingcar.app;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.ImageView;

import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.aop.AdviceBinder;
import com.alibaba.mobileim.aop.PointCutEnum;
import com.alibaba.mobileim.aop.custom.YWSDKGlobalConfig;
import com.alibaba.mobileim.contact.IYWContactService;
import com.alibaba.tcms.service.WXForegroundBaseService;
import com.alibaba.wxlib.util.SysUtil;
import com.baidu.mapapi.SDKInitializer;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.hwx.balancingcar.balancingcar.app.utils.AppConfig;
import com.hwx.balancingcar.balancingcar.app.utils.Cockroach;
import com.hwx.balancingcar.balancingcar.app.utils.Constants;
import com.hwx.balancingcar.balancingcar.app.utils.LocationService;
import com.hwx.balancingcar.balancingcar.app.utils.NetWorkManager;
import com.hwx.balancingcar.balancingcar.app.utils.UserHelper;
import com.hwx.balancingcar.balancingcar.mvp.im.ChattingOperationCustom;
import com.hwx.balancingcar.balancingcar.mvp.im.ChattingUICustom;
import com.hwx.balancingcar.balancingcar.mvp.im.ConversationListOperationCustom;
import com.hwx.balancingcar.balancingcar.mvp.im.ConversationListUICustom;
import com.hwx.balancingcar.balancingcar.mvp.im.LoginHelper;
import com.hwx.balancingcar.balancingcar.mvp.im.NotificationInitHelper;
import com.hwx.balancingcar.balancingcar.mvp.model.api.service.HomePageServer;
import com.hwx.balancingcar.balancingcar.mvp.model.entity.user.HomePageManager;
import com.hwx.balancingcar.balancingcar.mvp.model.entity.user.UserToken;
import com.hwx.balancingcar.balancingcar.mvp.model.entity.user.UserTokenManager;
import com.hwx.balancingcar.balancingcar.mvp.model.entity.user.UsersSelf;
import com.hwx.balancingcar.balancingcar.mvp.model.entity.user.UsersSelfManager;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;

import com.hwx.balancingcar.balancingcar.BuildConfig;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.PlatformConfig;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.AlbumLoader;

import java.io.File;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

/**
 * ================================================
 * 展示 {@link AppLifecycles} 的用法
 * <p>
 * Created by MVPArmsTemplate
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class AppLifecyclesImpl implements AppLifecycles {

    @Override
    public void attachBaseContext(@NonNull Context base) {
          MultiDex.install(base);  //这里比 onCreate 先执行,常用于 MultiDex 初始化,插件化框架的初始化
    }

    @Override
    public void onCreate(@NonNull Application application) {
        if (mustRunFirstInsideApplicationOnCreate(application)) {
            return;
        }
        Cockroach.install((thread, throwable) -> new Handler(Looper.getMainLooper()).post(() -> {
            try {
                LogUtils.warnInfo(thread + "\n" + throwable.toString());
                //Log.d("Cockroach", thread + "\n" + throwable.toString());
                throwable.printStackTrace();
                if (!BuildConfig.DEBUG) {
                    MobclickAgent.reportError(application, throwable);
                }
            } catch (Throwable e) {
            }
        }));
        if (LeakCanary.isInAnalyzerProcess(application)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        if (BuildConfig.LOG_DEBUG) {//Timber初始化
            //Timber 是一个日志框架容器,外部使用统一的Api,内部可以动态的切换成任何日志框架(打印策略)进行日志打印
            //并且支持添加多个日志框架(打印策略),做到外部调用一次 Api,内部却可以做到同时使用多个策略
            //比如添加三个策略,一个打印日志,一个将日志保存本地,一个将日志上传服务器
            Timber.plant(new Timber.DebugTree());
            // 如果你想将框架切换为 Logger 来打印日志,请使用下面的代码,如想切换为其他日志框架请根据下面的方式扩展
//                    Logger.addLogAdapter(new AndroidLogAdapter());
//                    Timber.plant(new Timber.DebugTree() {
//                        @Override
//                        protected void log(int priority, String tag, String message, Throwable t) {
//                            Logger.log(priority, tag, message, t);
//                        }
//                    });
            ButterKnife.setDebug(true);
        }
        //LeakCanary 内存泄露检查
        ArmsUtils.obtainAppComponentFromContext(application).extras().put(RefWatcher.class.getName(), BuildConfig.USE_CANARY ? LeakCanary.install(application) : RefWatcher.DISABLED);
        //扩展 AppManager 的远程遥控功能
//        ArmsUtils.obtainAppComponentFromContext(application).appManager().setHandleListener((appManager, message) -> {
//            switch (message.what) {
        //case 0:
        //do something ...
        //   break;
//            }
//        });
        //Usage:
        //Message msg = new Message();
        //msg.what = 0;
        //AppManager.post(msg); like EventBus

        //-----------------------------------IM--------------------------------------

        try {
            initOpenIM(application);
            initUmeng(application);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //-----------------------------------END-------------------------------------

        if (!SysUtil.isMainProcess())
            return;

        //x5内核初始化接口
        QbSdk.initX5Environment(application, new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("aerlang-app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        });
        LocationService.getInstance(application);
        SDKInitializer.initialize(application);
        Utils.init(application);
        initRealmAndData(application);
        Iconify.with(new FontAwesomeModule());
        AppConfig.getInstance(application);
        Album.initialize(AlbumConfig.newBuilder(application).setAlbumLoader(new AlbumLoader() {
                    @Override
                    public void load(ImageView imageView, AlbumFile albumFile) {
                        String imagePath = albumFile.getPath();
                        if (URLUtil.isNetworkUrl(imagePath)) {
                            Glide.with(imageView.getContext())
                                    .load(imagePath)
                                    .into(imageView);
                        } else {
                            Glide.with(imageView.getContext())
                                    .load(new File(imagePath))
                                    .into(imageView);
                        }
                    }

                    @Override
                    public void load(ImageView imageView, String url) {
                        Glide.with(imageView.getContext())
                                .load(url)
                                .into(imageView);
                    }
                }) // 设置Album加载器。
                .setLocale(Locale.CHINA) // 比如强制设置在任何语言下都用中文显示。
                .build()
        );
    }

    private boolean mustRunFirstInsideApplicationOnCreate(Application application) {
        SysUtil.setApplication(application);
        WXForegroundBaseService.setEnableForeground(false);
        return SysUtil.isTCMSServiceProcess(application);
    }

    private void initUmeng(Application application) {
        UMConfigure.setLogEnabled(BuildConfig.DEBUG);
        UMConfigure.setEncryptEnabled(true);
        //初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
//        UMConfigure.init(this, "5968271e4544cb22b50000af", BuildConfig.DEBUG?"testreas": "rela", UMConfigure.DEVICE_TYPE_PHONE, "980d0b9f0f7e4d31bf7065e40386d684");
        //开启ShareSDK debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        PushAgent mPushAgent = PushAgent.getInstance(application);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                LogUtils.warnInfo("deviceToken:" + deviceToken);
                HomePageServer.senInfo(deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {
                new Handler().post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        // 对自定义消息的处理方式，点击或者忽略
                        boolean isClickOrDismissed = true;
                        if (isClickOrDismissed) {//统计自定义消息的打开
                            UTrack.getInstance(application).trackMsgClick(msg);
                        } else {//统计自定义消息的忽略
                            UTrack.getInstance(application).trackMsgDismissed(msg);
                        }
                        LogUtils.warnInfo("接收到一条自定义消息通知：" + msg.custom);
                    }
                });
            }

            //自定义通知样式
            @Override
            public Notification getNotification(Context context,
                                                UMessage msg) {
                LogUtils.warnInfo(msg.builder_id+"builder_id"+msg.text+"text"+msg.toString());
                switch (msg.builder_id) {
                    //自定义通知样式编号
                    case 0:
                        return super.getNotification(context, msg);
                    default:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
//                        Intent intent = new Intent(context, NewsNotificationActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent.putExtra("states", msg.custom);
//                        NotificationUtils notificationUtils = new NotificationUtils(context);
//                        notificationUtils.sendNotification(context,msg.title,msg.text,intent);
                        return null;
                }
            }
        };
        //如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            //点击通知的自定义行为
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                LogUtils.warnInfo("消息通知：" + msg.custom);
//                Intent intent = new Intent(mContext, NewsNotificationActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("states", msg.custom);
//                startActivity(intent);
            }
        };
        mPushAgent.setNotificaitonOnForeground(UserHelper.getInstance().redisToken == null ? false : AppConfig.getInstance(application).getBoolean("check_notification", true));
        mPushAgent.setMessageHandler(messageHandler);
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
    }

    private void initRealmAndData(Application application) {
        //realm
        Realm.init(application);//初始化数据库
//        File dir = new File("/sdcard/Aerlang/realm");
//        if(!dir.exists()){
//            dir.mkdirs();
//        }
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("hwx_balancingcar_mvp_rbj.realm")//.migration(new MyMigration())
                .deleteRealmIfMigrationNeeded()//如果数据有冲突，则删除数据重新开始，工具类有升级的方法
                .build();
        Realm.setDefaultConfiguration(config);
        UserHelper.getInstance();
    }

    private void initOpenIM(Application application) {
        if(SysUtil.isMainProcess()){
            Log.i("", "is UI process");
            // ------[todo step1]-------------
            //［IM定制初始化］，如果不需要定制化，可以去掉此方法的调用
            //注意：由于增加全局初始化，该配置需最先执行
            //聊天界面相关自定义-------
            //聊天界面的自定义风格1：［图片、文字小猪气泡］风格
            AdviceBinder.bindAdvice(PointCutEnum.CHATTING_FRAGMENT_UI_POINTCUT, ChattingUICustom.class);
            //聊天界面的自定义风格2：［图片切割气泡、文字小猪气泡］风格
            //        AdviceBinder.bindAdvice(PointCutEnum.CHATTING_FRAGMENT_UI_POINTCUT, ChattingUICustomSample2.class);
            //-----------------------
            //聊天业务相关
            AdviceBinder.bindAdvice(PointCutEnum.CHATTING_FRAGMENT_OPERATION_POINTCUT, ChattingOperationCustom.class);
            //会话列表UI相关
            AdviceBinder.bindAdvice(PointCutEnum.CONVERSATION_FRAGMENT_UI_POINTCUT, ConversationListUICustom.class);
            //会话列表业务相关
            AdviceBinder.bindAdvice(PointCutEnum.CONVERSATION_FRAGMENT_OPERATION_POINTCUT, ConversationListOperationCustom.class);
            //消息通知栏
            AdviceBinder.bindAdvice(PointCutEnum.NOTIFICATION_POINTCUT, NotificationInitHelper.class);
            //AdviceBinder.bindAdvice(PointCutEnum.FRIENDS_POINTCUT, FriendsCustomAdvice.class);
            //全局配置修改
            AdviceBinder.bindAdvice(PointCutEnum.YWSDK_GLOBAL_CONFIG_POINTCUT, YWSDKGlobalConfig.class);

            // ------[todo step2]-------------
            //SDK初始化
            try {
                LoginHelper.getInstance().initSDK(application);
                //后期将使用Override的方式进行集中配置，请参照YWSDKGlobalConfigSample
                YWAPI.enableSDKLogOutput(true);
                IYWContactService.enableBlackList();
            } catch (Exception e) {
                e.printStackTrace();
            }
//            YWAPI.setEnableAutoLogin2(true);
        }

//		int pid = android.os.Process.myPid();
//		String processName = "";
//		ActivityManager mActivityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
//		for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
//			if (appProcess.pid == pid) {
//				processName = appProcess.processName;
//				break;
//			}
//		}
//		String packageName = mContext.getPackageName();
//		if(processName.equals(packageName)) {//由於使用友盟推送，導致程序有多個進程，所以需要在此判斷是否在主進程中
//			//
//			//初始化云旺SDK
//		} else {
//			Log.i("", "is not UI process");
//		}
    }

    //各个平台的配置，建议放在全局Application或者程序入口
    {
        PlatformConfig.setWeixin(Constants.WXappKey, Constants.WXsecret);
        PlatformConfig.setQQZone("1106411576", "Hf8yEFWRgXZkrGVK");
    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
