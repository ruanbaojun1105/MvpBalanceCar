package com.hwx.balancingcar.balancingcar.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.hwx.balancingcar.balancingcar.di.module.GuidModule;

import com.jess.arms.di.scope.ActivityScope;
import com.hwx.balancingcar.balancingcar.mvp.ui.activity.GuidActivity;

@ActivityScope
@Component(modules = GuidModule.class, dependencies = AppComponent.class)
public interface GuidComponent {
    void inject(GuidActivity activity);
}