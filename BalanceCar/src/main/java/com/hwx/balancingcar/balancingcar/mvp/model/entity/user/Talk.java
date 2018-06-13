package com.hwx.balancingcar.balancingcar.mvp.model.entity.user;

import android.text.TextUtils;

import com.hwx.balancingcar.balancingcar.util.DateUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * bj
 * 17 08 30
 */
public class Talk  extends RealmObject {

    private  String listTag;

    private Long talkId;

    private Users user;

    private Date reportTime;

    private String textContent;

    private boolean isLike;

    private Integer likedCount;

    private Integer replyCount;

    private RealmList<Comment> commentRealmList;

    private RealmList<ImageItem> imageArr;

    private Integer topmark;

    private boolean isAttention;


    @Ignore
    private List<UserIcon> userIconList;

    private Place place;
    @Ignore
    private BannerItem tagItem;

    public Talk() {
    }

    public static List<Talk> creatListBeanByJson(JSONArray jsonArr,String listTag){
        List<Talk> carRealmList=new ArrayList<>();
        if (jsonArr != null){
            for (int i = 0; i < jsonArr.length(); i++) {
                try {
                    carRealmList.add(creatBeanByJson(jsonArr.getJSONObject(i),listTag));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return carRealmList;
    }

    public static RealmList<Talk> creatBeanByJson(JSONArray jsonArr, String listTag){
        RealmList<Talk> carRealmList=new RealmList<>();
        if (jsonArr != null){
            for (int i = 0; i < jsonArr.length(); i++) {
                try {
                    carRealmList.add(creatBeanByJson(jsonArr.getJSONObject(i),listTag));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return carRealmList;
    }
    public static Talk creatBeanByJson(JSONObject jsonObject) {
        return creatBeanByJson(jsonObject,"");
    }
    public static Talk creatBeanByJson(JSONObject jsonObject,String listTag) {
        Talk talk = null;
        try {
            if (jsonObject != null)
                talk = new Talk(jsonObject.getLong("tId"),
                        !jsonObject.has("user")?new Users():Users.creatBeanByJson(jsonObject.getJSONObject("user")),
                        new Date(jsonObject.getLong("reportTime")),
                        jsonObject.getString("textContent"),
                        ImageItem.creatBeanByJsonStr(jsonObject.getString("imageContent")),
                        jsonObject.getInt("isLike")==0?false:true,
                        jsonObject.getInt("likedCount"),
                        jsonObject.getInt("commentCount"),
                        !jsonObject.has("comments")?new RealmList<Comment>(): Comment.creatBeanByJson(jsonObject.getJSONArray("comments")),
                        jsonObject.getInt("topmark"),
                        null,
                        null,
                        listTag);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return talk;
    }

    public Talk(Long talkId, Users user, Date reportTime, String textContent, RealmList<ImageItem> imageArr,
                boolean isLike, Integer likedCount,Integer replyCount, RealmList<Comment> commentRealmList,
                Integer topmark,Place place,BannerItem tagItem,String listTag) {
        this.talkId = talkId;
        this.user = user;
        this.reportTime = reportTime;
        this.textContent = textContent;
        this.imageArr = imageArr;
        this.isLike = isLike;
        this.likedCount = likedCount;
        this.replyCount=replyCount;
        this.commentRealmList = commentRealmList;
        this.topmark = topmark;
        this.place = place;
        this.tagItem = tagItem;
        this.listTag = listTag;
    }

    public boolean isAttention() {
        return isAttention;
    }

    public void setAttention(boolean attention) {
        isAttention = attention;
    }

    public String getListTag() {
        return listTag;
    }

    public void setListTag(String listTag) {
        this.listTag = listTag;
    }

    public BannerItem getTagItem() {
        return tagItem;
    }

    public void setTagItem(BannerItem tagItem) {
        this.tagItem = tagItem;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public List<UserIcon> getUserIconList() {
        return userIconList==null?new ArrayList<UserIcon>():userIconList;
    }

    public void setUserIconList(List<UserIcon> userIconList) {
        this.userIconList = userIconList;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Long getTalkId() {
        return talkId;
    }

    public void setTalkId(Long talkId) {
        this.talkId = talkId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public RealmList<Comment> getCommentRealmList() {
        return commentRealmList;
    }

    public void setCommentRealmList(RealmList<Comment> commentRealmList) {
        this.commentRealmList = commentRealmList;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public String getReportTimeStr() {
        return DateUtils.getCountnumber(reportTime.getTime());
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getTextContent() {
        if (TextUtils.isEmpty(textContent))
            return "";
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent == null ? null : textContent.trim();
    }

    public RealmList<ImageItem> getImageArr() {
        return imageArr;
    }

    public ArrayList<String> getImageArrList() {
        ArrayList<String> photos=new ArrayList<>();
        for(ImageItem item:imageArr){
            photos.add(item.getImagePath());
        }
        return photos;
    }

    public void setImageArr(RealmList<ImageItem> imageArr) {
        this.imageArr = imageArr;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public Integer getLikedCount() {
        return likedCount;
    }

    public void setLikedCount(Integer likedCount) {
        this.likedCount = likedCount;
    }

    public Integer getTopmark() {
        return topmark;
    }

    public void setTopmark(Integer topmark) {
        this.topmark = topmark;
    }
}