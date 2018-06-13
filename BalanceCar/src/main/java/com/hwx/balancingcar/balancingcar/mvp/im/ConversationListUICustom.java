package com.hwx.balancingcar.balancingcar.mvp.im;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.mobileim.aop.Pointcut;
import com.alibaba.mobileim.aop.custom.IMConversationListUI;
import com.alibaba.mobileim.conversation.YWConversation;
import com.alibaba.mobileim.conversation.YWConversationType;
import com.alibaba.mobileim.kit.contact.YWContactHeadLoadHelper;
import com.alibaba.mobileim.ui.IYWConversationFragment;
import com.alibaba.mobileim.utility.IMSmilyCache;
import com.hwx.balancingcar.balancingcar.R;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 最近会话界面的定制点(根据需要实现相应的接口来达到自定义会话列表界面)，不设置则使用openIM默认的实现
 * 调用方设置的回调，必须继承BaseAdvice 根据不同的需求实现 不同的 开放的 Advice
 * com.alibaba.mobileim.aop.pointcuts包下开放了不同的Advice.通过实现多个接口，组合成对不同的ui界面的定制
 * 这里设置了自定义会话的定制
 * 1.CustomConversationAdvice 实现自定义会话的ui定制
 * 2.CustomConversationTitleBarAdvice 实现自定义会话列表的标题的ui定制
 * <p/>
 * 另外需要在application中将这个Advice绑定。设置以下代码
 * AdviceBinder.bindAdvice(PointCutEnum.CONVERSATION_FRAGMENT_POINTCUT, CustomChattingAdviceDemo.class);
 *
 * @author jing.huai
 */
public class ConversationListUICustom extends IMConversationListUI {

    private static final String TAG = "ConversationListUICustom";

    public ConversationListUICustom(Pointcut pointcut) {
        super(pointcut);
    }

    /**
     * 返回会话列表的自定义标题
     *
     * @param fragment
     * @param context
     * @param inflater
     * @return
     */
    @Override
    public View getCustomConversationListTitle(final Fragment fragment,
                                               final Context context, LayoutInflater inflater) {
        //TODO 重要：必须以该形式初始化customView---［inflate(R.layout.**, new RelativeLayout(context),false)］------，以让inflater知道父布局的类型，否则布局xml**中定义的高度和宽度无效，均被默认的wrap_content替代
//        RelativeLayout customView = (RelativeLayout) inflater
//                .inflate(R.layout.demo_custom_conversation_title_bar, new RelativeLayout(context),false);
//        customView.setBackgroundColor(Color.parseColor("#00b4ff"));
//        TextView title = (TextView) customView.findViewById(R.id.title_txt);
//        final YWIMKit mIMKit = LoginHelper.getInstance().getIMKit();
//
//        title.setText(mIMKit.getIMCore().getShowName());
//        title.setTextColor(Color.WHITE);
//        final String loginUserId = LoginHelper.getInstance().getIMKit().getIMCore().getLoginUserId();
//        final String appKey = LoginHelper.getInstance().getIMKit().getIMCore().getAppKey();
//        if(TextUtils.isEmpty(loginUserId)||TextUtils.isEmpty(appKey)){
//            title.setText("未登录");
//        }
//        title.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//
//                if(TextUtils.isEmpty(loginUserId)||TextUtils.isEmpty(appKey)){
//                    Toast.makeText(context, "click ", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(context, "click ("+" userid = "+loginUserId+" ,appKey = "+appKey+" )", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        });
//        TextView backButton = (TextView) customView.findViewById(R.id.left_button);
//        backButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                fragment.getActivity().finish();
//            }
//        });
//        backButton.setVisibility(View.GONE);
//
//        TextView rightButton = (TextView) customView.findViewById(R.id.right_button);
//        rightButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mIMKit.getConversationService().markAllReaded();
//            }
//        });
//        return customView;
        return null;
    }

    /**
     * 高级功能，通知调用方 消息漫游接收的状态 （可选 ）
     * 可以通过 fragment.getActivity() 拿到Context
     *
     * @param mCustomTitleView 用户设置的自定义标题 View
     * @param isVisible        是否显示“正在接收消息中” true:调用方需要去显示“消息接收中的菊花” false:调方用需要隐藏“消息接收中的菊花”
     */
    @Override
    public void setCustomTitleProgressBar(Fragment fragment, View mCustomTitleView, boolean isVisible) {
    }

    @Override
    public boolean needHideTitleView(Fragment fragment) {
        return true;
    }

    @Override
    public boolean needHideNullNetWarn(Fragment fragment) {
        return false;
    }

    /**
     * 是否支持下拉刷新
     */
    @Override
    public  boolean getPullToRefreshEnabled(){
        return true;
    }
     /**
     * 返回默认的群头像
     * @param fragment
     * @param conversation
     * @return
     */
    @Override
    public int getTribeConversationHead(Fragment fragment, YWConversation conversation){
        return R.drawable.aliwx_tribe_head_default;
    }

    /**
     * 返回自定义置顶回话的背景色(16进制字符串形式)
     * @return
     */
    @Override
    public String getCustomTopConversationColor() {
        return "#e1f5fe";
    }

    @Override
    public boolean enableSearchConversations(Fragment fragment){
        return true;
    }

    /**
     * 获取自定义会话要展示的自定义View，该方法的实现类完全似于BaseAdapter中getView()方法实现
     * @param context
     * @param conversation
     *          自定义展示View的会话
     * @param convertView
     *          {@link android.widget.BaseAdapter#getView(int, View, ViewGroup)}的第二个参数
     * @param parent
     *          {@link android.widget.BaseAdapter#getView(int, View, ViewGroup)}的第三个参数
     * @return
     */
    @Override
    public View getCustomView(Context context, YWConversation conversation, View convertView, ViewGroup parent) {
        return null;
    }

    /**
     * 会话列表onDestroy事件
     * @param fragment
     */
    @Override
    public void onDestroy(Fragment fragment) {
        super.onDestroy(fragment);
    }

    /**
     * 会话列表Activity创建事件
     * @param savedInstanceState
     * @param fragment
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState, Fragment fragment) {
        super.onActivityCreated(savedInstanceState, fragment);
    }

    /**
     * 会话列表onResume事件
     * @param fragment
     */
    @Override
    public void onResume(Fragment fragment) {
        super.onResume(fragment);
    }


    private IYWConversationFragment mConversationFragment;
    /**
     * 会话列表初始化完成回调
     * @param fragment  会话列表Fragment
     */
    @Override
    public void onInitFinished(IYWConversationFragment fragment) {
//      //TODO 为了防止内存泄露这里请使用弱引用方式
        WeakReference<IYWConversationFragment> reference = new WeakReference<IYWConversationFragment>(fragment);
        //获取IYWConversationFragment实例，开发者可以通过该实例主动调用该接口内的方法
        mConversationFragment = reference.get();
//        //TODO 由于是弱引用，所以conversationFragment可能为null，因此使用时一定要判空
        if (mConversationFragment != null){
            //刷新adapter
            mConversationFragment.refreshAdapter();
        }
    }

    /**
     * 该方法可以构造一个会话列表为空时的展示View
     * @return
     *      empty view
     */
    @Override
    public View getCustomEmptyViewInConversationUI(Context context) {
        /** 以下为示例代码，开发者可以按需返回任何view*/
        TextView textView = new TextView(context);
        textView.setText("还没有会话哦，快去找人聊聊吧!");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(18);
        return textView;
    }

    /**
     * 返回设置最近联系人界面背景的资源Id,返回0则使用默认值
     * @return
     *      资源Id
     */
    @Override
    public int getCustomBackgroundResId() {
        return R.color.white;
    }

    /*********** 以下是定制会话item view的示例代码 ***********/
    //有几种自定义，数组元素就需要几个，数组元素值从0开始
    //private final int[] viewTypeArray = {0,1,2,3}，这样就有4种自定义View
    /**
     * 自定义item view的种类数
     * @return 种类数
     */
    @Override
    public int getCustomItemViewTypeCount() {
        return 0;
    }

    /**
     * 自定义item的viewType
     * @param conversation
     * @return
     */
    @Override
    public int getCustomItemViewType(YWConversation conversation) {
        //todo 若修改 YWConversationType.Tribe为自己type，SDK认为您要在｛@link #getCustomItemView｝中完全自定义，针对群的自定义，如getTribeConversationHead会失效。
        //todo 该原则同样适用于 YWConversationType.P2P等其它内部类型，请知晓！
        if (conversation.getConversationType() == YWConversationType.Custom) {
//            return viewTypeArray[0];
        }
//        else if (conversation.getConversationType() == YWConversationType.P2P){
//            return viewTypeArray[1];
//        }
        //这里必须调用基类方法返回！！
        return super.getCustomItemViewType(conversation);
    }



    /**
     * 根据viewType自定义item的view
     * @param fragment
     * @param conversation      当前item对应的会话
     * @param convertView       convertView
     * @param viewType          当前itemView的viewType
     * @param headLoadHelper    加载头像管理器，用户可以使用该管理器设置头像
     * @param parent            getView中的ViewGroup参数
     * @return
     */
    @Override
    public View getCustomItemView(Fragment fragment, YWConversation conversation, View convertView, int viewType, YWContactHeadLoadHelper headLoadHelper, ViewGroup parent) {

            return null;
        }

    private Map<String, CharSequence> mSmilyContentCache = new HashMap<String, CharSequence>();  //表情的本地缓存，加速读取速度用
    IMSmilyCache smilyManager;
    int defaultSmilySize = 0;
    private int contentWidth;



    private void initSmilyManager(Context context){
        if (smilyManager == null){
            smilyManager = IMSmilyCache.getInstance();
            defaultSmilySize = (int) context.getResources().getDimension(R.dimen.aliwx_smily_column_width);
            int width = context.getResources().getDisplayMetrics().widthPixels;
            contentWidth = width
                    - context.getResources().getDimensionPixelSize(R.dimen.aliwx_column_up_unit_margin)*2
                    - context.getResources().getDimensionPixelSize(R.dimen.aliwx_common_head_size)
                    - context.getResources().getDimensionPixelSize(R.dimen.aliwx_message_content_margin_right);
        }
    }


    /*********** 以上是定制会话item view的示例代码 ***********/


    /**
     * 自定义会话列表item中name view
     * @param fragment      会话列表fragment
     * @param conversation  当前item的会话对象
     * @param convertView   自定义name view
     * @param defaultView   SDK中默认的name view，开发者可以从该textView中获取text、textColor、textSize等信息
     * @return 自定义name view
     */
    @Override
    public View getCustomConversationTitleView(Fragment fragment, YWConversation conversation, View convertView, TextView defaultView) {
//        if (conversation.getConversationType() == YWConversationType.P2P){
//            if (convertView == null) {
//                convertView = new TextView(fragment.getActivity());
//            }
//            TextView textView = (TextView) convertView;
//            textView.setText(defaultView.getText() + "_custom");
//            return convertView;
//        }
        return null;
    }

    /**
     * 自定义会话列表搜索框样式
     * @param fragment          会话列表fragment
     * @param onClickListener   SDK中默认的搜索框点击事件，开发者可以使用该onClickListener，也可以自定义点击事件
     * @return 自定义搜索框view
     */
    @Override
    public View getCustomSearchView(Fragment fragment, OnClickListener onClickListener) {
//        View view = View.inflate(fragment.getActivity(), R.layout.demo_custom_search_view, null);
//        //TODO 开发者必须自己设置点击事件，对于自定义搜索框SDK内部不会设置点击事件
//        view.setOnClickListener(onClickListener);
//        return view;
        return null;
    }
}
