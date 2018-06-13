package com.hwx.balancingcar.balancingcar.mvp.im;

import com.hwx.balancingcar.balancingcar.util.DateUtils;

import java.io.Serializable;

public class IMConversation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String key;
	String imageUrl;
	String title;
	String newestDesc;
	Long newestDatetime_gmt;
	int unreadCount=0;
	String action;
	boolean isStrongRemind;
	String userId;
	String conversationId;
	boolean isTop;


	public String getConversationId() {
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

	public boolean isTop() {
		return isTop;
	}

	public void setTop(boolean top) {
		isTop = top;
	}

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNewestDesc() {
		return newestDesc;
	}
	public void setNewestDesc(String newestDesc) {
		this.newestDesc = newestDesc;
	}
	public Long getNewestDatetime_gmt() {
		return newestDatetime_gmt;
	}
	public void setNewestDatetime_gmt(Long newestDatetime_gmt) {
		this.newestDatetime_gmt = newestDatetime_gmt;
	}
	public int getUnreadCount() {
		return unreadCount;
	}
	public void setUnreadCount(int unreadCount) {
		this.unreadCount = unreadCount;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public boolean isStrongRemind() {
		return isStrongRemind;
	}
	public void setStrongRemind(boolean isStrongRemind) {
		this.isStrongRemind = isStrongRemind;
	}

	public IMConversation() {
		super();
	}
	
	public long getNewestDatetime(){
	    return DateUtils.gmtTime2LocalTime(getNewestDatetime_gmt());
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


/*public static IMConversation[] createByArray(Object[] arr){
		if (arr==null)
			return null;
		IMConversation[] retArr=new IMConversation[arr.length];
       	for (int i = 0; i < arr.length; i++) {
            Map<?, ?> map = (Map<?, ?>) arr[i];
            retArr[i] = createByMap(map);
       	}
       	
		return retArr;
	}
	
	public static IMConversation createByMap(Map<?, ?> map){
		IMConversation obj=new IMConversation();
		
		obj.setKey(MapUtils.getMapStr(map, "category_key"));
		obj.setImageUrl(MapUtils.getMapStr(map, "image_url"));
		obj.setTitle(MapUtils.getMapStr(map, "title"));
		obj.setNewestDesc(MapUtils.getMapStr(map, "newest_desc"));
		obj.setNewestDatetime_gmt(DateUtils.gmtTime2LocalTime(MapUtils.getMapDateLong(map, "newest_datetime")));
		//将服务器传下来的时间转为本地时间，阿里返回的时间就是本地时间，无需再转
		obj.setUnreadCount(MapUtils.getMapInt(map, "unread_count"));
		obj.setAction(MapUtils.getMapStr(map, "action"));
		obj.setStrongRemind(MapUtils.getMapBool(map, "is_strong_remind"));
		obj.setTribe_id(MapUtils.getMapLong(map, "tribe_id"));
		return obj;
	}*/

}
