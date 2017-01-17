package com.cwenhui.yumnote.dagger.components;

import android.content.Context;

import com.cwenhui.yumnote.base.BaseApplication;
import com.cwenhui.yumnote.dagger.modules.ActivityModule;
import com.cwenhui.yumnote.dagger.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 作者: 陈文辉
 * 日期: 2017/1/17 23:39
 * 作用:
 */
@Singleton
@Component(modules = {ApplicationModule.class/*,DataManagerModule.class*/})
public interface ApplicationComponent {

    void inject(BaseApplication application);

    ActivityComponent plus(ActivityModule module);

    Context getContext();
}
