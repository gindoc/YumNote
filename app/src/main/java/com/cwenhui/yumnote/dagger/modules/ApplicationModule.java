package com.cwenhui.yumnote.dagger.modules;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import pub.devrel.easypermissions.EasyPermissions;
import rx.subjects.BehaviorSubject;

/**
 * Created by cwenhui on 2017/1/17.
 */
@Module
public class ApplicationModule {
    private Application mApp;
    public ApplicationModule(Application app){
        mApp = app;
    }

    @Provides
    public SharedPreferences providesPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(mApp);
    }
    @Provides
    public Resources providesResources(){
        return mApp.getResources();
    }

    @Provides
    public Context provideContext(){
        return mApp;
    }


    @Provides
    public BehaviorSubject provideBehaviorSubject(){
        return BehaviorSubject.create();
    }

    @Provides
    @Named("device_id")
    public String provideDeviceId(Context context){
        if (EasyPermissions.hasPermissions(context, new String[]{Manifest.permission.READ_PHONE_STATE})) {
            TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            String DEVICE_ID = tm.getDeviceId();
            return DEVICE_ID;
        }else {
            return "0";
        }
    }

}
