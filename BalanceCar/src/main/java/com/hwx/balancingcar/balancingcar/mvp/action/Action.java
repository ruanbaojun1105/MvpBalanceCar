package com.hwx.balancingcar.balancingcar.mvp.action;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;

import java.io.Serializable;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;


public abstract class Action implements Serializable {
//	a:打开浏览器，对应action值:weburl，返回参数：url 为链接
//	b:打开话题详情，对应action值:talk，返回参数：talkId 话题Id
//	c:打开购物车，对应action值:openShopCart
//	d:打开商城所有商品页面，对应action值:openShopAll
//	e:打开商品详情，对应action值:openShopDetail，返回参数：shopId 商品Id
//	f:打开单聊，对应action值:openChatP2P,返回参数：userId 百川用户ID ，userName 百川昵
    /**
     * bj
     */
    private static final long serialVersionUID = 1L;

    private static String real_url;//url

    //排除emptyaction
    private static Action[] actions = new Action[]{
            new ShopAction(), new TalkAction(),new WebviewAction(),new ImageAction(),new NotificationNewsAction(),
            new ChatP2PAction(), new ShopAllPageAction(), new ShopCartPageAction(), new ShopDetailPageAction()
    };
    private static EmptyAction emptyAction=new EmptyAction();

//    private List<Action > actionList =getClassAll();

    private boolean needCheckUrl = false;//是否检查url是否具有与app服务器相同的域名

    public abstract Action setData(String url, Map<String, Object> map);

    public abstract Boolean excute(final Context activity, View view);

    public abstract String getActionType();

    public abstract void resetData();

    public Action() {
    }

    @Deprecated
    public static Action createAction(String url, boolean needCheckUrl) {
        LogUtils.e("action执行的链接：" + url + "是否需要检查url的服务器~~~~~~" + needCheckUrl);
//		if (needCheckUrl) {
        //int ex = url.indexOf("//");
        //int ed = Constants.MY_BABY_SITE_URL_CHECK.length();
//			if (!StringUtils.getHost(url).contains(Constants.MY_BABY_SITE_URL_CHECK))
//				return null;
//		}
        try {
            return createAction(url).setReal_url(url);
        } catch (Exception e) {
            return emptyAction;
        }
    }

    public static Action createAction(String real_url) {
        if (TextUtils.isEmpty(real_url))
            return emptyAction;
        LogUtils.e("real_url------" + real_url);
        int start = real_url.indexOf("?");
        String actionType;
        if (start > 0)
            actionType = real_url.substring(0, start);
        else
            return emptyAction;
        for (Action action : actions) {
            if (actionType.contains(action.getActionType())) {
                Map<String, Object> map = Action.getHeader(real_url);
                action.resetData();//还原一遍数据
                action.setData(real_url, map);
                return action;
            }
        }
        return emptyAction;
    }


//将获取链接转为map匹配

    /**
     * 方法名称:transStringToMap
     * 传入参数:mapString 形如 username=chenziwen&password=1234
     * 返回值:Map
     */
    public static Map<String, Object> getHeader(String url) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //url = URLDecoder.decode(url, "UTF-8");//后一步解码
            int start = 0;
            try {
                start = url.indexOf("?");
                if (url.length() - start >= 0) {
                    String str = url.substring(start + 1);
                    String[] paramsArr = str.split("&");
                    if (paramsArr != null && paramsArr.length > 0) {
                        for (String param : paramsArr) {
                            String[] temp = param.split("=");
                            if (temp != null) {
                                if (temp.length > 1)
                                    map.put(temp[0], URLDecoder.decode(param.substring(temp[0].length() + 1), "UTF-8"));//此处取所有参数
                                    //map.put(temp[0], temp[1]);
//                                else map.put(temp[0], "");
                            } else
                                map.put(param, param);
                        }
                    } else {
                        String[] temp = str.split("=");
                        if (temp != null && temp.length > 0)
                            map.put(temp[0], "");
//                        else
//                            map.put(str, str);
                    }
                }
            } catch (Exception e) {
                //map.put(url, url);
            }
            return map;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return map;
        }
    }

//    public static String newActionLink(String[][] strings, String actionType) {
//        String link = HttpUtil.getHttpRequestUrl("") + "/" + actionType;
//        if (strings != null && strings.length > 0) {
//            for (int i = 0; i < strings.length; i++) {
//                link += (i == 0 ? "?" : "&") + strings[i][0] + "=" + strings[i][1];
//            }
//        } else LogUtils.e("link creat fail!");
//        return link;
//    }

    public String getReal_url() {
        return real_url;
    }

    public Action setReal_url(String real_url) {
        this.real_url = real_url;
        return this;
    }


    /*public List<Action > getClassAll(){
        List<String >classNameList=new ArrayList<String >();
        try {

            DexFile df = new DexFile(getClass().getPackage()+".ex");//通过DexFile查找当前的APK中可执行文件
            Enumeration<String> enumeration = df.entries();//获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
            while (enumeration.hasMoreElements()) {//遍历
                String className = enumeration.nextElement();
                classNameList.add(className);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Action > actions=new ArrayList<Action >();
        for (String c:classNameList){
            Class onwClass = null;
            try {
                onwClass = Class.forName(c);
                Object o = onwClass.newInstance();
                actions.add((Action) o);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return  actions;
    }*/
}
