package com.cwenhui.data.dagger;

import android.content.Context;

import com.cwenhui.data.BaseApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by louiszgm on 2016/9/29.
 */
@Singleton
@Component(modules = {DataManagerModule.class})
public interface ApplicationComponent {

    void inject(BaseApplication application);


    Context getContext();
}
