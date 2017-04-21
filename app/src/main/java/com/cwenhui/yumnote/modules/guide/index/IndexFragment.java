package com.cwenhui.yumnote.modules.guide.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cwenhui.yumnote.R;
import com.cwenhui.yumnote.base.BaseFragment;
import com.cwenhui.yumnote.base.BasePresenter;
import com.cwenhui.yumnote.databinding.FragmentGuideIndexBinding;
import com.cwenhui.yumnote.modules.guide.login.LoginFragment;
import com.cwenhui.yumnote.modules.guide.regist.RegisterFragment;
import com.cwenhui.yumnote.utils.ConvertUtils;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * Author: GIndoc on 2017/4/11 16:19
 * email : 735506583@qq.com
 * FOR   :
 */

public class IndexFragment extends BaseFragment {
    private FragmentGuideIndexBinding mBinding;
    private static final int VIEWPAGER_CONTENT_SIZE = 2;
    private static final int VIEWPAGER_CONTENTS[] = {R.drawable.guide_img1, R.drawable.guide_img2};
    //    private int mCurrentItem;
    @Inject
    LoginFragment loginFragment;

    @Inject
    RegisterFragment registerFragment;

    @Inject
    public IndexFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        mBinding = FragmentGuideIndexBinding.inflate(inflater, container, false);
        mBinding.setView(this);
        initViewPager();
        return mBinding.getRoot();
    }

    private void initViewPager() {
        mBinding.llDots.removeAllViews();
        final List<View> views = new ArrayList<>();
        final List<View> dots = new ArrayList<>();
        for (int i = 0; i < VIEWPAGER_CONTENT_SIZE; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(VIEWPAGER_CONTENTS[i]);
            views.add(imageView);

            ImageView dotView = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams
                    .WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.rightMargin = ConvertUtils.dip2px(getContext(), 15);
            params.leftMargin = ConvertUtils.dip2px(getContext(), 15);
            mBinding.llDots.addView(dotView, params);
            dots.add(dotView);
            if (i == 0) {
                dotView.setBackgroundResource(R.drawable.white_oval);
            } else {
                dotView.setBackgroundResource(R.drawable.gray_oval);
            }
        }
        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(views.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = views.get(position);
                container.addView(view);
                return view;
            }
        };
        ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
            boolean isAutoPlay = false;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int pos) {
                for (int i = 0; i < dots.size(); i++) {
                    if (i == pos) {
                        (dots.get(pos)).setBackgroundResource(R.drawable.white_oval);
                    } else {
                        (dots.get(i)).setBackgroundResource(R.drawable.gray_oval);
                    }
                }
//                mCurrentItem = pos;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case 1:// 手势滑动，空闲中
                        isAutoPlay = false;
                        break;
                    case 2:// 界面切换中
                        isAutoPlay = true;
                        break;
                    case 0:// 滑动结束，即切换完毕或者加载完毕
                        // 当前为最后一张，此时从右向左滑，则切换到第一张
                        if (mBinding.viewPager.getCurrentItem() == mBinding.viewPager.getAdapter().getCount
                                () - 1 && !isAutoPlay) {
                            mBinding.viewPager.setCurrentItem(0);
                        }
                        // 当前为第一张，此时从左向右滑，则切换到最后一张
                        else if (mBinding.viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                            mBinding.viewPager.setCurrentItem(mBinding.viewPager.getAdapter().getCount() - 1);
                        }
                        break;
                }
            }
        };
        mBinding.viewPager.setAdapter(pagerAdapter);
        mBinding.viewPager.addOnPageChangeListener(pageChangeListener);
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

    public void toLogin(View view) {
        start(loginFragment);
    }

    public void toRegister(View view) {
        start(registerFragment);
    }
}
