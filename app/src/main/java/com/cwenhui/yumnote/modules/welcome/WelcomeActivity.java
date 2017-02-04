package com.cwenhui.yumnote.modules.welcome;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cwenhui.yumnote.R;
import com.cwenhui.yumnote.base.BaseActivity;
import com.cwenhui.yumnote.base.BasePresenter;
import com.cwenhui.yumnote.modules.main.MainActivity;

/**
 * Author: GIndoc on 2017/1/30 15:57
 * email : 735506583@qq.com
 * FOR   :
 */

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(R.drawable.welcome);
        setContentView(imageView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(MainActivity.getStartIntent(WelcomeActivity.this));
                finish();
                overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
            }
        }, 1000);
    }

    @Override
    protected BasePresenter createPresent() {
        return null;
    }


}
