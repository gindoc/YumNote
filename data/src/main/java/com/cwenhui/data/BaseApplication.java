package com.cwenhui.data;

import android.app.Application;

import com.cwenhui.data.dagger.ApplicationComponent;
import com.cwenhui.data.dagger.ComponentHolder;
import com.cwenhui.data.dagger.DaggerApplicationComponent;
import com.cwenhui.data.dagger.DataManagerModule;

/**
 * Created by louiszgm on 2016/9/29.
 */

public class BaseApplication extends Application {
    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        getApplicationComponent().inject(this);

    }

    public ApplicationComponent getApplicationComponent() {
        if (mComponent == null) {
            mComponent = DaggerApplicationComponent.builder()
//                    .applicationModule(new ApplicationModule(this))
                    .dataManagerModule(new DataManagerModule(this))
                    .build();
        }
        ComponentHolder.setAppComponent(mComponent);
        return mComponent;
    }

    public void setComponent(ApplicationComponent component) {
        mComponent = component;
    }

}
