/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hwx.balancingcar.balancingcar.app.utils;

import com.hwx.balancingcar.balancingcar.mvp.model.entity.user.HomePageManager;
import com.hwx.balancingcar.balancingcar.mvp.model.entity.user.Homepage;
import com.hwx.balancingcar.balancingcar.mvp.model.entity.user.UserToken;
import com.hwx.balancingcar.balancingcar.mvp.model.entity.user.UserTokenManager;
import com.hwx.balancingcar.balancingcar.mvp.model.entity.user.UsersSelf;
import com.hwx.balancingcar.balancingcar.mvp.model.entity.user.UsersSelfManager;

import java.util.List;

public class UserHelper {

    public UserToken userToken;
    public UsersSelf user;
    public Homepage homePage;
    public String redisToken;
    public String deviceToken="";//友盟token

    private static class UserManagerHolder {
        private static final UserHelper INSTANCE = new UserHelper();
    }

    public static final UserHelper getInstance() {
        return UserManagerHolder.INSTANCE;
    }

    private UserHelper() {
        init();
    }

    private void init() {
        //初始化tonken
        List<UserToken> userTokens = UserTokenManager.getManager().queryListAll();
        if (userTokens != null && userTokens.size() > 0) {
            userToken = userTokens.get(0);
            redisToken = userToken.getRedisToken();
        }
        List<UsersSelf> userses = UsersSelfManager.getManager().queryListAll();
        {
            if (userses != null && userses.size() > 0) {
                for (UsersSelf usersSelf:userses) {
                    if (usersSelf!=null&&usersSelf.getuId()!=null) {
                        user = usersSelf;
                        break;
                    }
                }
                if (user!=null)
                    homePage = HomePageManager.getManager().getItem(user.getPhoneNo());
            }
        }
    }


    public void setSelfUser(UsersSelf user){
        this.user=user;
    }

    public void setUserToken(UserToken userToken) {
        this.userToken = userToken;
    }

    public void setHomePage(Homepage homePage) {
        this.homePage = homePage;
    }

    public void setRedisToken(String redisToken) {
        this.redisToken = redisToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
