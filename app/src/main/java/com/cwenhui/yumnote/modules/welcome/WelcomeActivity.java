package com.cwenhui.yumnote.modules.welcome;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cwenhui.data.local.Const;
import com.cwenhui.domain.model.User;
import com.cwenhui.yumnote.R;
import com.cwenhui.yumnote.base.BaseActivity;
import com.cwenhui.yumnote.modules.guide.GuideActivity;
import com.cwenhui.yumnote.modules.main.MainActivity;
import com.cwenhui.yumnote.utils.NetworkUtils;
import com.cwenhui.yumnote.utils.Saver;
import com.trello.rxlifecycle.LifecycleTransformer;

import javax.inject.Inject;

/**
 * Author: GIndoc on 2017/1/30 15:57
 * email : 735506583@qq.com
 * FOR   :
 */
public class WelcomeActivity extends BaseActivity<WelcomeContract.View, WelcomePresenter> implements
        WelcomeContract.View {

    @Inject
    WelcomePresenter mPresenter;
    private long currentTime;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getComponent().inject(this);
        super.onCreate(savedInstanceState);
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                .LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(R.drawable.welcome);
        setContentView(imageView);

        if (!NetworkUtils.isAvailable(this) || !NetworkUtils.isConnected(this))
            return;
        if (Saver.getLoginState()) {    // 已经登陆了的话获取新的token
            User user = Saver.getSerializableObject(Const.SharePreferenceKey.USER);
            currentTime = System.currentTimeMillis();
            mPresenter.login(user.getUserName(), user.getUserPassword());
        } else {                // 未登录的话跳转登录页面
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(GuideActivity.getStartIntent(WelcomeActivity.this));
                    finish();
                }
            }, 1000);
        }
    }

    private void toGuideActivity(long delayedTime) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(MainActivity.getStartIntent(WelcomeActivity.this));
                finish();
            }
        }, delayedTime);
    }

    @Override
    protected WelcomePresenter createPresent() {
        return mPresenter;
    }

    @Override
    public void loginSuccessful() {
        if (System.currentTimeMillis() - currentTime > 1000) {
            toGuideActivity(0);
        } else {
            toGuideActivity(1000 - System.currentTimeMillis() + currentTime);
        }
    }

    @Override
    public <T> LifecycleTransformer<T> getBindToLifecycle() {
        return bindToLifecycle();
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
