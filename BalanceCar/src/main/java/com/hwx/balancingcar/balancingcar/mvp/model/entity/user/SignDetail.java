package com.hwx.balancingcar.balancingcar.mvp.model.entity.user;

import com.blankj.utilcode.util.TimeUtils;
import com.hwx.balancingcar.balancingcar.bean.shop.ShopCoupon;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * creat by bj on 2017/12/30 09:48
 * 401763159@qq.com
 * com.hwx.balancingcar.balancingcar.bean.user
 * 注释：签到详情数据
 */

public class SignDetail implements Serializable {
    List<OneDaySign> oneDaySignList;//七天签到列表
    List<ShopCoupon> shopCouponList;//推荐兑换的优惠券
    int sign_day_continuity;//连续签到天数
    int sign_day_integral;//当天签到的积分
    int sign_integral_count;//用户总积分
    int sign_integral_canuse_count;//可使用的总积分

    public SignDetail() {
    }

    public List<OneDaySign> getOneDaySignList() {
        return oneDaySignList;
    }

    public void setOneDaySignList(List<OneDaySign> oneDaySignList) {
        this.oneDaySignList = oneDaySignList;
    }

    public List<ShopCoupon> getShopCouponList() {
        return shopCouponList;
    }

    public void setShopCouponList(List<ShopCoupon> shopCouponList) {
        this.shopCouponList = shopCouponList;
    }

    public int getSign_day_continuity() {
        return sign_day_continuity;
    }

    public void setSign_day_continuity(int sign_day_continuity) {
        this.sign_day_continuity = sign_day_continuity;
    }

    public int getSign_day_integral() {
        return sign_day_integral;
    }

    public void setSign_day_integral(int sign_day_integral) {
        this.sign_day_integral = sign_day_integral;
    }

    public int getSign_integral_count() {
        return sign_integral_count;
    }

    public void setSign_integral_count(int sign_integral_count) {
        this.sign_integral_count = sign_integral_count;
    }

    public int getSign_integral_canuse_count() {
        return sign_integral_canuse_count;
    }

    public void setSign_integral_canuse_count(int sign_integral_canuse_count) {
        this.sign_integral_canuse_count = sign_integral_canuse_count;
    }

    public class OneDaySign{
        boolean isdefult;//
        long signDate2;
        long signDate;
        int signIntegral;

        public long getSignDate() {
            return signDate;
        }

        public void setSignDate(long signDate) {
            this.signDate = signDate;
        }

        public boolean isSign() {
            return isdefult;
        }

        public void setSign(boolean sign) {
            isdefult = sign;
        }

        public long getSignTime() {
            return signDate2;
        }

        public String getSignTimeStr(SimpleDateFormat simpleDateFormat) {
            return TimeUtils.millis2String((signDate2==0?signDate:signDate2),simpleDateFormat);
        }

        public void setSignTime(long signTime) {
            this.signDate2 = signTime;
        }

        public int getSignIntegral() {
            return signIntegral;
        }

        public void setSignIntegral(int signIntegral) {
            this.signIntegral = signIntegral;
        }

        public OneDaySign() {
        }
    }
}

