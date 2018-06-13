package com.hwx.balancingcar.balancingcar.mvp.im;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.IYWP2PPushListener;
import com.alibaba.mobileim.IYWTribePushListener;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWConstants;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.YWLoginParam;
import com.alibaba.mobileim.channel.LoginParam;
import com.alibaba.mobileim.channel.constant.B2BConstant;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.contact.IYWContact;
import com.alibaba.mobileim.contact.IYWContactHeadClickListener;
import com.alibaba.mobileim.contact.IYWContactService;
import com.alibaba.mobileim.conversation.IYWConversationService;
import com.alibaba.mobileim.conversation.IYWConversationUnreadChangeListener;
import com.alibaba.mobileim.conversation.YWConversation;
import com.alibaba.mobileim.conversation.YWCustomMessageBody;
import com.alibaba.mobileim.conversation.YWMessage;
import com.alibaba.mobileim.gingko.model.tribe.YWTribe;
import com.alibaba.mobileim.gingko.model.tribe.YWTribeMember;
import com.alibaba.mobileim.login.YWLoginState;
import com.alibaba.mobileim.login.YWPwdType;
import com.alibaba.mobileim.utility.IMAutoLoginInfoStoreUtil;
import com.alibaba.mobileim.utility.IMNotificationUtils;
import com.alibaba.tcms.env.EnvManager;
import com.alibaba.tcms.env.TcmsEnvType;
import com.alibaba.tcms.env.YWEnvManager;
import com.alibaba.tcms.env.YWEnvType;
import com.alibaba.wxlib.util.SysUtil;
import com.hwx.balancingcar.balancingcar.App;
import com.hwx.balancingcar.balancingcar.Constants;
import com.hwx.balancingcar.balancingcar.activity.UserOtherHomeActivity;
import com.hwx.balancingcar.balancingcar.bean.event.EventComm;
import com.hwx.balancingcar.balancingcar.bean.rpc.UserRPC;
import com.hwx.balancingcar.balancingcar.bean.user.Users;
import com.hwx.balancingcar.balancingcar.util.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SDK 初始化和登录
 *
 * @author jing.huai
 */
public class LoginHelper {

    private static LoginHelper sInstance = new LoginHelper();

    public static LoginHelper getInstance() {
        return sInstance;
    }

    // 应用APPKEY，这个APPKEY是申请应用时获取的
    public static  String APP_KEY = Constants.IMappKey;

    //以下两个内容是测试环境使用，开发无需关注
//    public static final String APP_KEY_TEST = "4272";  //60026702

    public static final String APP_KEY_TEST = B2BConstant.APPKEY.APPKEY_B2B;  //60026702    60028148


    public static YWEnvType sEnvType = YWEnvType.TEST;

    // openIM UI解决方案提供的相关API，创建成功后，保存为全局变量使用
    private YWIMKit mIMKit;

    private Application mApp;

    private List<Map<YWTribe, YWTribeMember>> mTribeInviteMessages = new ArrayList<Map<YWTribe, YWTribeMember>>();

    public YWIMKit getIMKit() {
        return mIMKit;
    }

    public void setIMKit(YWIMKit imkit) {
        mIMKit = imkit;
    }

    public void initIMKit(String userId, String appKey) {
//        mIMKit = YWAPI.getIMKitInstance(userId, appKey);
        addPushMessageListener();
        //添加联系人通知和更新监听 todo 在初始化后、登录前添加监听，离线的联系人系统消息才能触发监听器
        //addContactListeners();

    }

    private YWLoginState mAutoLoginState = YWLoginState.idle;

    public YWLoginState getAutoLoginState() {
        return mAutoLoginState;
    }

    public void setAutoLoginState(YWLoginState state) {
        this.mAutoLoginState = state;
    }

    /**
     * 初始化SDK
     *
     * @param context
     */
    public void initSDK(Application context) {
        mApp = context;
        sEnvType = YWEnvManager.getEnv(context);

        //初始化IMKit
        final String userId = IMAutoLoginInfoStoreUtil.getLoginUserId();
        final String appkey = IMAutoLoginInfoStoreUtil.getAppkey();
        if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(appkey)){
            initIMKit(userId, appkey);
            NotificationInitHelper.init();
        }
        TcmsEnvType type = EnvManager.getInstance().getCurrentEnvType(mApp);
        if(type== TcmsEnvType.ONLINE || type == TcmsEnvType.PRE){
            YWAPI.init(mApp, APP_KEY);
        }
        else if(type== TcmsEnvType.TEST){
            YWAPI.init(mApp, APP_KEY);
        }

        if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(appkey)){
            LoginHelper.getInstance().initIMKit(userId, appkey);
        }
        //通知栏相关的初始化
        NotificationInitHelper.init();
    }

    public interface OnHttpIMLoginInterFace {
        void onSuccess(YWIMKit mIMKit, Users users);

        void onFail(int status, String message);
    }
    /**
     * 登录操作
     * @param userId   用户id
     * @param password 密码
     * @param callback 登录操作结果的回调
     */
    //------------------请特别注意，OpenIMSDK会自动对所有输入的用户ID转成小写处理-------------------
    //所以开发者在注册用户信息时，尽量用小写
    public static boolean isSignIM=false;
    private Users users=null;
    public void login_Sample(final OnHttpIMLoginInterFace callback) {
        if (App.users==null) {
            isSignIM=false;
            if (callback!=null)
                callback.onFail(500,"请登录");
            return;
        }
        if (App.users.getuId()==null) {
            isSignIM=false;
            return;
        }
        mIMKit=getYWIMKit(String.valueOf(App.users.getuId()));

        if (isSignIM) {
            LogUtils.e("has login im");
            if (callback!=null)
                callback.onSuccess(mIMKit,users);
            return;
        }
        if ( mIMKit== null) {
            LogUtils.e("err login im");
            isSignIM=false;
            if (callback!=null)
                callback.onFail(500,"IM登陆失败");
            return;
        }
        SysUtil.setCnTaobaoInit(true);
        // openIM SDK提供的登录服务
        final IYWLoginService mLoginService = mIMKit.getLoginService();
        UserRPC.getUserChatInfo(App.users.getuId(), new UserRPC.OnUserHttpInterFace() {
            @Override
            public void onSuccess(Users homePage) {
                LogUtils.e("服务器获取信息ok");
                users=homePage;
                final YWLoginParam loginParam = YWLoginParam.createLoginParam(String.valueOf(homePage.getuId()), homePage.getPassword());
                if (TextUtils.isEmpty(Constants.IMappKey) || Constants.IMappKey.equals(YWConstants.YWSDKAppKey)
                        || Constants.IMappKey.equals(YWConstants.YWSDKAppKeyCnHupan)) {
                    loginParam.setServerType(LoginParam.ACCOUNTTYPE_WANGXIN);
                    loginParam.setPwdType(YWPwdType.pwd);
                }
                mLoginService.login(loginParam, new IWxCallback() {
                    @Override
                    public void onSuccess(Object... objects) {
                        LogUtils.e("login ok");
                        isSignIM=true;
                        initProfileCallback();
                        if (callback!=null)
                            callback.onSuccess(mIMKit,users);
                    }

                    @Override
                    public void onError(int i, String s) {
                        LogUtils.e("login err");
                        isSignIM=false;
                        if (callback!=null)
                            callback.onFail(i, s);
                    }

                    @Override
                    public void onProgress(int i) {
                    }
                });
            }

            @Override
            public void onFail(int status, String message) {
                LogUtils.e("服务器获取信息err");
                isSignIM=false;
                if (callback!=null)
                    callback.onFail(status, message);
            }
        });
    }

    public static YWIMKit getYWIMKit(String user) {
        YWIMKit mIMKit = YWAPI.getIMKitInstance(user,Constants.IMappKey);
        return mIMKit;
    }

    private static boolean enableUseLocalUserProfile = true;

    //初始化，建议放在登录之前
    public static void initProfileCallback() {
        if (!enableUseLocalUserProfile) {
            //目前SDK会自动获取导入到OpenIM的帐户昵称和头像，如果用户设置了回调，则SDK不会从服务器获取昵称和头像
            return;
        }
        YWIMKit imKit = getInstance().getIMKit();
        if (imKit == null) {
            return;
        }
        final IYWContactService contactManager = imKit.getContactService();
        final IYWConversationService conversationService=imKit.getConversationService();
        conversationService.addP2PPushListener(new IYWP2PPushListener() {
            @Override
            public void onPushMessage(IYWContact iywContact, List<YWMessage> list) {
                LogUtils.e("onPushMessage");
//                if (TextUtils.isDigitsOnly(iywContact.getUserId()))
//                    UserOtherHomeActivity.newInstance(App.getContext(),Long.parseLong(iywContact.getUserId()));
            }
        });
        int unReadCount = conversationService.getAllUnreadCount();
        EventBus.getDefault().post(new EventComm("news_unread", unReadCount));
        conversationService.addTotalUnreadChangeListener(new IYWConversationUnreadChangeListener() {

            //当未读数发生变化时会回调该方法，开发者可以在该方法中更新未读数
            @Override
            public void onUnreadChange() {
                int unReadCount = conversationService.getAllUnreadCount();
                EventBus.getDefault().post(new EventComm("news_unread", unReadCount));
            }
        });
        //头像点击的回调（开发者可以按需设置）
        contactManager.setContactHeadClickListener(new IYWContactHeadClickListener() {
            @Override
            public void onUserHeadClick(Fragment fragment, YWConversation conversation, String userId, String appKey, boolean isConversationListPage) {
//                IMNotificationUtils.getInstance().showToast(fragment.getActivity(), "你点击了用户 " + userId + " 的头像");
                if (TextUtils.isDigitsOnly(userId))
                    UserOtherHomeActivity.newInstance(fragment.getActivity(),Long.parseLong(userId));
            }

            @Override
            public void onTribeHeadClick(Fragment fragment, YWConversation conversation, long tribeId) {
//                IMNotificationUtils.getInstance().showToast(fragment.getActivity(), "你点击了群 " + tribeId + " 的头像");
            }

            @Override
            public void onCustomHeadClick(Fragment fragment, YWConversation conversation) {
//                IMNotificationUtils.getInstance().showToast(fragment.getActivity(), "你点击了自定义会话 " + conversation.getConversationId() + " 的头像");
            }
        });
    }
        /**
         * 添加新消息到达监听，该监听应该在登录之前调用以保证登录后可以及时收到消息
         */
    private void addPushMessageListener(){
        if (mIMKit == null) {
            return;
        }

        IYWConversationService conversationService = mIMKit.getConversationService();
        //添加单聊消息监听，先删除再添加，以免多次添加该监听
        conversationService.removeP2PPushListener(mP2PListener);
        conversationService.addP2PPushListener(mP2PListener);

        //添加群聊消息监听，先删除再添加，以免多次添加该监听
        conversationService.removeTribePushListener(mTribeListener);
        conversationService.addTribePushListener(mTribeListener);
    }

//    private IYWContactOperateNotifyListener mContactOperateNotifyListener = new ContactOperateNotifyListenerImpl();
//
//    private IYWContactCacheUpdateListener mContactCacheUpdateListener = new ContactCacheUpdateListenerImpl();


    private IYWP2PPushListener mP2PListener = new IYWP2PPushListener() {
        @Override
        public void onPushMessage(IYWContact contact, List<YWMessage> messages) {
            for (YWMessage message : messages) {
                if (message.getSubType() == YWMessage.SUB_MSG_TYPE.IM_P2P_CUS) {
                    if (message.getMessageBody() instanceof YWCustomMessageBody) {
                        YWCustomMessageBody messageBody = (YWCustomMessageBody) message.getMessageBody();
                        if (messageBody.getTransparentFlag() == 1) {
                            String content = messageBody.getContent();
                            try {
                                JSONObject object = new JSONObject(content);
                                if (object.has("text")) {
                                    String text = object.getString("text");
                                    IMNotificationUtils.getInstance().showToast(App.getContext(), "透传消息，content = " + text);
                                } else if (object.has("customizeMessageType")) {
                                    String customType = object.getString("customizeMessageType");
                                    if (!TextUtils.isEmpty(customType) && customType.equals(ChattingOperationCustom.CustomMessageType.READ_STATUS)) {
                                        YWConversation conversation = mIMKit.getConversationService().getConversationByConversationId(message.getConversationId());
                                        long msgId = Long.parseLong(object.getString("PrivateImageRecvReadMessageId"));
                                        conversation.updateMessageReadStatus(conversation, msgId);
                                    }
                                }
                            } catch (JSONException e) {
                            }
                        }
                    }
                }
            }
        }
    };

    private IYWTribePushListener mTribeListener = new IYWTribePushListener() {
        @Override
        public void onPushMessage(YWTribe tribe, List<YWMessage> messages) {
            //TODO 收到群消息
        }

    };

    /**
     * 登出
     */
    public void loginOut_Sample() {
        if (mIMKit == null) {
            return;
        }


        // openIM SDK提供的登录服务
        IYWLoginService mLoginService = mIMKit.getLoginService();
        mLoginService.logout(new IWxCallback() {

            @Override
            public void onSuccess(Object... arg0) {
                isSignIM=false;
            }

            @Override
            public void onProgress(int arg0) {

            }

            @Override
            public void onError(int arg0, String arg1) {

            }
        });
    }

    public List<Map<YWTribe, YWTribeMember>> getTribeInviteMessages() {
        return mTribeInviteMessages;
    }
}
