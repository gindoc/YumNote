package com.cwenhui.yumnote.modules.guide;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cwenhui.yumnote.R;
import com.cwenhui.yumnote.base.BaseActivity;
import com.cwenhui.yumnote.base.BasePresenter;
import com.cwenhui.yumnote.databinding.ActivityGuideBinding;
import com.cwenhui.yumnote.modules.guide.index.IndexFragment;
import com.trello.rxlifecycle.LifecycleTransformer;

import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * Author: GIndoc on 2017/4/9 17:12
 * email : 735506583@qq.com
 * FOR   :
 */

public class GuideActivity extends BaseActivity {
    private ActivityGuideBinding mBinding;

    @Inject
    IndexFragment indexFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getComponent().inject(this);
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_guide);
        loadRootFragment(R.id.rl_content, indexFragment);
    }

    @Override
    protected BasePresenter createPresent() {
        return null;
    }

    @Nonnull
    @Override
    public LifecycleTransformer bindUntilEvent(@Nonnull Object event) {
        return null;
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, GuideActivity.class);
    }
}
