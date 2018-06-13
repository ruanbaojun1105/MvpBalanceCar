package com.hwx.balancingcar.balancingcar.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hwx.balancingcar.balancingcar.mvp.contract.GuidContract;
import com.hwx.balancingcar.balancingcar.mvp.model.GuidModel;


@Module
public class GuidModule {
    private GuidContract.View view;

    /**
     * 构建GuidModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public GuidModule(GuidContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    GuidContract.View provideGuidView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    GuidContract.Model provideGuidModel(GuidModel model) {
        return model;
    }
}