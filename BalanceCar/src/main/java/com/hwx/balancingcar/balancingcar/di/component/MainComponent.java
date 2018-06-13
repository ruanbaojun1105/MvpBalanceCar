package com.hwx.balancingcar.balancingcar.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.hwx.balancingcar.balancingcar.di.module.MainModule;

import com.jess.arms.di.scope.ActivityScope;
import com.hwx.balancingcar.balancingcar.mvp.ui.activity.MainActivity;

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}