package com.cwenhui.yumnote.dagger.modules;

import android.support.v4.app.FragmentManager;

import com.cwenhui.data.repository.impl.SampleDataRepository;
import com.cwenhui.domain.repository.SampleRepository;
import com.cwenhui.yumnote.base.BaseActivity;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;

/**
 * 作者: 陈文辉
 * 日期: 2017/1/17 23:39
 * 作用:
 */
@Module
public class ActivityModule {
    private WeakReference<BaseActivity> mActivity;
    public ActivityModule(BaseActivity activity){
        mActivity = new WeakReference<BaseActivity>(activity);
    }

    @Provides
    public BaseActivity  providesActivity(){
        return mActivity.get();
    }

    @Provides
    public FragmentManager providesFragmentManager(){
        return mActivity.get().getSupportFragmentManager();
    }

    @Provides
    public SampleRepository providesSampleRepository(SampleDataRepository repository) {
        return repository;
    }
}
