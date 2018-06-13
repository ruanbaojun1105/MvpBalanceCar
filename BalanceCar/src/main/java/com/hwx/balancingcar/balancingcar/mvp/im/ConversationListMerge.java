package com.hwx.balancingcar.balancingcar.mvp.im;

import com.alibaba.mobileim.channel.cloud.contact.YWProfileInfo;
import com.alibaba.mobileim.conversation.YWConversation;
import com.alibaba.mobileim.conversation.YWConversationType;
import com.alibaba.mobileim.conversation.YWP2PConversationBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by bj
 */
public class ConversationListMerge {
    private static ConversationListMerge merge;
    private static SortNotificationList sortNotificationList=new SortNotificationList();

    public static ConversationListMerge getInstance() {
        if (merge == null)
            merge = new ConversationListMerge();
        return merge;
    }
    public interface IMConversationRunnable {
        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see     Thread#run()
         */
        void run(List<IMConversation> newImList);
    }

    /**
     * 获得最新转换会话后的NotificationCategory的列表项
     */
    public void syncNtcLatestList(List<YWConversation> ywConversationList, List<YWProfileInfo> ywProfileInfoList, IMConversationRunnable runnable) {
        if (ywProfileInfoList==null)
            ywProfileInfoList=new ArrayList<>();
        List<IMConversation> newImList=new ArrayList<>();
        List<IMConversation> topImList=new ArrayList<>();
        if (ywConversationList != null && ywConversationList.size() > 0) {
            for (YWConversation conversation:ywConversationList) {
                YWProfileInfo ywProfileInfo=null;
                for (YWProfileInfo info:ywProfileInfoList){
                    YWP2PConversationBody p2pBody = (YWP2PConversationBody) conversation.getConversationBody();
                    if (p2pBody.getContact()!=null&&info.userId.equals(p2pBody.getContact().getUserId())){
                        ywProfileInfo=info;
                        break;
                    }
                }
                if (ywProfileInfo!=null) {
                    IMConversation category = converToNotica(conversation, ywProfileInfo);
                    if (category.isTop)
                        topImList.add(category);
                    else
                        newImList.add(category);
                }
            }
        }
        Collections.sort(topImList, sortNotificationList);
        Collections.sort(newImList, sortNotificationList);
        topImList.addAll(newImList);
        if (runnable!=null)
            runnable.run(topImList);
    }

    public List<String> getContactIdList(List<YWConversation> list) {
        List<String> idList=new ArrayList<>();

        //List<IMConversation> itemList2=delConversationList(itemList);
        //云旺
        if (list != null && list.size() > 0) {
            for (YWConversation conversation:list) {
                YWP2PConversationBody p2pBody = (YWP2PConversationBody) conversation.getConversationBody();
                if (p2pBody.getContact()!=null)
                    idList.add(p2pBody.getContact().getUserId());
            }
        }
        return idList;
    }

    private IMConversation converToNotica(YWConversation conversation, YWProfileInfo ywProfileInfo) {
        IMConversation category=new IMConversation();
        category.setKey("Conversation_Key");
        category.setNewestDatetime_gmt(conversation.getLatestTimeInMillisecond());//或者conversation.getLatestTime()*1000
        category.setUnreadCount(conversation.getUnreadCount());
        category.setTop(conversation.isTop());
        category.setConversationId(conversation.getConversationId());
        //String time1 = IMUtil.getFormatTime(conversation.getLatestTime() * 1000L, WXAPI.getInstance().getServerTime());
            // 获取会话管理类
        if(conversation.getConversationType()== YWConversationType.P2P) {
            String username = ywProfileInfo.nick;
            String url = ywProfileInfo.icon;
            String[][] aa = {{"userId", ywProfileInfo.userId}, {"userName", username}};
            category.setAction(Action.newActionLink(aa, ChatP2PAction.ActionType));
            category.setImageUrl(url);
            category.setTitle(username);
            category.setStrongRemind(true);//默认是强提醒
            category.setNewestDesc(conversation.getLatestContent());
            category.setUserId(ywProfileInfo.userId);
        }

        return  category;
    }



    public  int getUnreadCount(List<IMConversation> listNC){
        int unread=0;
        for(IMConversation category:listNC)
            unread+=category.isStrongRemind() ?category.getUnreadCount():0;
        return unread;
    }

}
