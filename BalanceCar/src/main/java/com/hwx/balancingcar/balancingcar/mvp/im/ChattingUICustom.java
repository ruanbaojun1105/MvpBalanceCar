package com.hwx.balancingcar.balancingcar.mvp.im;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.mobileim.aop.Pointcut;
import com.alibaba.mobileim.aop.custom.IMChattingPageUI;
import com.alibaba.mobileim.conversation.YWConversation;
import com.alibaba.mobileim.conversation.YWConversationType;
import com.alibaba.mobileim.conversation.YWMessage;
import com.hwx.balancingcar.balancingcar.Constants;
import com.hwx.balancingcar.balancingcar.R;


/**
 *
 * 聊天界面的自定义风格1：文字和图片小猪气泡风格
 * Created by mayongge on 15-9-23.
 *
 *
 */
public class ChattingUICustom extends IMChattingPageUI {

    public ChattingUICustom(Pointcut pointcut) {
        super(pointcut);
    }
    /**
     * 设置左边图片消息汽泡背景图，需要.9图
     * @return
     *      0: 默认背景
     *      <br>
     *      －1:透明背景（无背景）
     *      <br>
     *      resId：使用resId对应图片做背景
     */
    @Override
    public int getLeftImageMsgBackgroundResId() {
//        return R.drawable.lms_comment_l_nested;
		return 0;
//		return -1;
    }


    @Override
    public int getLeftTextMsgBackgroundResId() {
//        return R.drawable.lms_comment_l_nested;
		return 0;
    }

    @Override
    public int getLeftGeoMsgBackgroundResId(YWConversation conversation) {
        //return R.drawable.aliwx_comment_l_bg;
    	return 0;
    }

    @Override
    public int getLeftCustomMsgBackgroundResId(YWConversation conversation) {
        //return R.drawable.aliwx_comment_l_bg;
    	return 0;
    }

    /**
     * 设置右边图片消息汽泡背景图，需要.9图
     * @return
     *      0: 默认背景
     *      <br>
     *      －1:透明背景（无背景）
     *      <br>
     *      resId：使用resId对应图片做背景
     */
    @Override
    public int getRightImageMsgBackgroundResId() {
//        return R.drawable.lms_comment_r_nested;
		return 0;
//		return -1;
    }
    @Override
    public int getRightTextMsgBackgroundResId() {
//        return R.drawable.lms_comment_r_green_press;
		return 0;
    }

    @Override
    public int getRightGeoMsgBackgroundResId(YWConversation conversation) {
//       return R.drawable.aliwx_comment_r_bg;
    	return 0;
    }

    @Override
    public int getRightCustomMsgBackgroundResId(YWConversation conversation) {
//        return R.drawable.aliwx_comment_r_bg;
    	return 0;
    }


    /**
     * 建议使用{@link #processBitmapOfLeftImageMsg｝和{@link #processBitmapOfRightImageMsg｝灵活修改Bitmap，达到对图像进行［圆角处理］,［裁减］等目的,这里建议return false
     * 设置是否需要将聊天界面的图片设置为圆角
     * @return
     *      false: 不做圆角处理
     *      <br>
     *      true：做圆角处理（重要：返回true时不会做{@link #processBitmapOfLeftImageMsg｝和{@link #processBitmapOfRightImageMsg｝二次图片处理，两者互斥！）
     */

    @Override
    public boolean needRoundChattingImage() {
        return true;
    }

    /**
     * 设置聊天界面图片圆角的边角半径的长度(单位：dp)
     * @return
     */
    @Override
    public float getRoundRadiusDps() {
        return 12.6f;
    }

    @Override
    public int getChattingBackgroundResId() {
        //聊天窗口背景，默认不显示
//        return 0;
         return R.drawable.chatting_bg;

    }

    /**
     *
     * 用于更灵活地加工［左边图片消息］的Bitmap用于显示，SDK内部会缓存之，后续直接使用缓存的Bitmap显示。例如：对图像进行［裁减］，［圆角处理］等等
     * 重要：使用该方法时：
     * 1.请将 {@link #needRoundChattingImage}设为return false(不裁剪圆角)，两者是互斥关系
     * 2.建议将{@link #getLeftImageMsgBackgroundResId}设为return－1（背景透明）
     * @param input 网络获取的聊天图片
     * @return  供显示的Bitmap
     */
   /* public  Bitmap processBitmapOfLeftImageMsg(Bitmap input) {
        Bitmap output = Bitmap.createBitmap(input.getWidth(),
                input.getHeight(), Bitmap.Config.ARGB_8888);
        //为提高性能，对取得的resource图片做缓存
        Bitmap distBitmap = YWIMImageUtils.getFromCacheOrDecodeResource(R.drawable.left_bubble);
        NinePatch np = new NinePatch(distBitmap, distBitmap.getNinePatchChunk(), null);
        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        final Rect rectSrc = new Rect(0, 0, input.getWidth(), input.getHeight());
        final RectF rectDist = new RectF(0, 0, input.getWidth(), input.getHeight());
        np.draw(canvas, rectDist);
        canvas.drawARGB(0, 0, 0, 0);
        //设置Xfermode
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(input, rectSrc, rectSrc, paint);
        return output;
    }*/

    /**
     *  用于更灵活地加工［右边图片消息］的Bitmap用于显示，SDK内部会缓存之，后续直接使用缓存的Bitmap显示。例如：对图像进行［裁减］，［圆角处理］等等
     * 重要：使用该方法时：
     * 1.请将 {@link #needRoundChattingImage}设为return false(不裁剪圆角)，两者是互斥关系
     * 2.建议将{@link #getRightImageMsgBackgroundResId}设为return－1（背景透明）
     * @param input 网络获取的聊天图片
     * @return  供显示的Bitmap
     */
    /*public  Bitmap processBitmapOfRightImageMsg(Bitmap input) {
        Bitmap output = Bitmap.createBitmap(input.getWidth(),
                input.getHeight(), Bitmap.Config.ARGB_8888);
        //为提高性能，对取得的resource图片做缓存
        Bitmap distBitmap = YWIMImageUtils.getFromCacheOrDecodeResource(R.drawable.right_bubble);
        NinePatch np = new NinePatch(distBitmap, distBitmap.getNinePatchChunk(), null);
        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        final Rect rectSrc = new Rect(0, 0, input.getWidth(), input.getHeight());
        final RectF rectDist = new RectF(0, 0, input.getWidth(), input.getHeight());
        np.draw(canvas, rectDist);
        canvas.drawARGB(0, 0, 0, 0);
        //设置Xfermode
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(input, rectSrc, rectSrc, paint);
        return output;
    }*/

    /**
     * 是否隐藏标题栏
     *
     * @param fragment
     * @param conversation
     * @return
     */
    @Override
    public boolean needHideTitleView(Fragment fragment, YWConversation conversation) {
//        if (conversation.getConversationType() == YWConversationType.Tribe){
//            return true;
//        }
        //@消息功能需要展示标题栏，暂不隐藏
        return true;
    }

    /**
     * isv需要返回自定义的view. openIMSDK会回调这个方法，获取用户设置的view. Fragment 聊天界面的fragment
     */
    @Override
    public View getCustomTitleView(final Fragment fragment,
                                   final Context context, LayoutInflater inflater,
                                   final YWConversation conversation) {
    	return null;
    }


    /**
     * 定制图片预览页面titlebar右侧按钮点击事件
     *
     * @param fragment
     * @param message  当前显示的图片对应的ywmessage对象
     * @return true：使用用户定制的点击事件， false：使用默认的点击事件(默认点击事件为保持该图片到本地)
     */
    @Override
    public boolean onImagePreviewTitleButtonClick(Fragment fragment, YWMessage message) {
        //Context context = fragment.getActivity();
        //Toast.makeText(context, "你点击了该按钮~", Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     * 返回图片保存的目录
     * @param fragment
     * @param message
     * @return 如果为null,使用SDK默认的目录, 否则使用用户设置的目录
     */
    @Override
    public String getImageSavePath(Fragment fragment, YWMessage message) {
//        return Environment
//                .getExternalStorageDirectory().getAbsolutePath()
//                + "/alibaba/WXOPENI/云旺相册";
        return  Constants.IMAGE_SAVE_PATH;
    }

    /**
<<<<<<< HEAD
     * 返回单聊默认头像资源Id
     * @return
     *      0:使用SDK默认提供的
     */
    @Override
    public int getDefaultHeadImageResId() {
        return R.drawable.avatar_small;
    }

    /**
     * 是否需要圆角矩形的头像
     * @return
     *      true:需要圆角矩形
     *      <br>
     *      false:不需要圆角矩形，默认为圆形
     *      <br>
     *      注：如果返回true，则需要使用{@link #getRoundRectRadius()}给出圆角的设置半径，否则无圆角效果
     */
    @Override
    public boolean isNeedRoundRectHead() {
        return false;
    }

    /**
     * 返回设置圆角矩形的圆角半径大小
     * @return
     *      0:如果{@link #isNeedRoundRectHead()}返回true，此处返回0则表示头像显示为直角正方形
     */
    @Override
    public int getRoundRectRadius() {
        return 0;
    }


    @Override
    public boolean isUseChattingCustomViewAdvice(Fragment fragment, Intent intent) {
        if(intent!=null && intent.hasExtra("extraTribeId") && intent.hasExtra("conversationType")){
            long tribeId = intent.getLongExtra("extraTribeId", 0);
            int conversationType = intent.getIntExtra("conversationType",-1);
            if(tribeId > 0 && conversationType == YWConversationType.Tribe.getValue()){
                return true;
            }
        }
        return false;
    }


	/**	
     * getView方法内，返回View之前，对［聊天界面的右边消息item的View］做最后调整,如调整View的Padding。
     *
     * @param msg
     * @param rightItemParentView
     * @param fragment
     * @param conversation
     */
    @Override
    public void modifyRightItemParentViewAfterSetValue(YWMessage msg, RelativeLayout rightItemParentView, Fragment fragment, YWConversation conversation) {
//        if(msg!=null&&rightItemParentView!=null&&msg.getSubType()==YWMessage.SUB_MSG_TYPE.IM_IMAGE||msg.getSubType()==YWMessage.SUB_MSG_TYPE.IM_GIF){
//            rightItemParentView.setPadding(rightItemParentView.getPaddingLeft(), rightItemParentView.getPaddingTop(), 0, rightItemParentView.getPaddingBottom());
//        }
    }

    /**
     * getView方法内，返回View之前，对［聊天界面的左边消息item的View］做最后调整,如调整View的Padding。
     *
     * @param msg
     * @param leftItemParentView
     * @param fragment
     * @param conversation
     */
    @Override
    public void modifyLeftItemParentViewAfterSetValue(YWMessage msg, RelativeLayout leftItemParentView, Fragment fragment, YWConversation conversation) {

//        if(msg!=null&&leftItemParentView!=null&&msg.getSubType()==YWMessage.SUB_MSG_TYPE.IM_IMAGE||msg.getSubType()==YWMessage.SUB_MSG_TYPE.IM_GIF) {
//            leftItemParentView.setPadding(0, leftItemParentView.getPaddingTop(), leftItemParentView.getPaddingRight(), leftItemParentView.getPaddingBottom());
//        }
    }
}
