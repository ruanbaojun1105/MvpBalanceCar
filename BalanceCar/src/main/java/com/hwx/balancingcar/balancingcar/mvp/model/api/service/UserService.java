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
package com.hwx.balancingcar.balancingcar.mvp.model.api.service;

import com.hwx.balancingcar.balancingcar.mvp.model.entity.SplashImage;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.hwx.balancingcar.balancingcar.mvp.model.api.Api.SERVER8082_NAME;
import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

/**
 * ================================================
 * 展示 {@link Retrofit#create(Class)} 中需要传入的 ApiService 的使用方式
 * 存放关于用户的一些 API
 * <p>
 * Created by JessYan on 08/05/2016 12:05
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface UserService {

    /**
     * 获取首页广告图
     * @return
     */
    @Headers({DOMAIN_NAME_HEADER + SERVER8082_NAME})
    @FormUrlEncoded
    @POST("Screen/get")
    Observable<SplashImage> getScreen();

    /**
     * 获取登录百川的信息
     * @return
     */
    @Headers({DOMAIN_NAME_HEADER + SERVER8082_NAME})
    @FormUrlEncoded
    @POST("orderInPWD")
    Observable<SplashImage> getScreen(@Field("userId") String userId);

    /**
     * 检查版本更新
     * @return
     */
    @Headers({DOMAIN_NAME_HEADER + SERVER8082_NAME})
    @FormUrlEncoded
    @POST("home/getUpdate")
    Observable<Response> checkVersion(@Field("versionNum") String versionNum);

}
