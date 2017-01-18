package com.cwenhui.yumnote.modules.main;

import android.os.Bundle;
import android.view.View;

import com.cwenhui.yumnote.R;
import com.cwenhui.yumnote.base.BaseActivity;
import com.trello.rxlifecycle.LifecycleTransformer;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainContract.View,MainPresenter> implements MainContract.View {

    @Inject
    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected MainPresenter createPresent() {
        return mPresenter;
    }

    public void test(View view) {
        mPresenter.test();
    }

    @Override
    public <T> LifecycleTransformer<T> getBindToLifecycle() {
        return null;
    }

    @Override
    public void showError(String error) {

    }
}
