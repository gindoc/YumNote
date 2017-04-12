package com.cwenhui.yumnote.modules.guide.login;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cwenhui.yumnote.R;
import com.cwenhui.yumnote.base.BaseFragment;
import com.cwenhui.yumnote.databinding.FragmentGuideLoginBinding;
import com.cwenhui.yumnote.modules.main.MainActivity;
import com.cwenhui.yumnote.utils.StringUtils;
import com.trello.rxlifecycle.LifecycleTransformer;

import javax.inject.Inject;

/**
 * Author: GIndoc on 2017/4/11 16:54
 * email : 735506583@qq.com
 * FOR   :
 */

public class LoginFragment extends BaseFragment<LoginContract.View, LoginPresenter> implements
        LoginContract.View {
    @Inject
    LoginPresenter mPresenter;

    private FragmentGuideLoginBinding mBinding;

    @Inject
    public LoginFragment() {
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
        mBinding = FragmentGuideLoginBinding.inflate(inflater, container, false);
        mBinding.setView(this);
        initToolbar();
        return mBinding.getRoot();
    }

    private void initToolbar() {
        mBinding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mBinding.toolbar.setTitle("YumNote");
        mBinding.toolbar.setTitleTextColor(Color.WHITE);
        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop();
            }
        });
    }

    @Override
    protected LoginPresenter createPresent() {
        return mPresenter;
    }

    @Override
    public <T> LifecycleTransformer<T> getBindToLifecycle() {
        return bindToLifecycle();
    }

    @Override
    public void showError(String error) {
    }

    public void toLogin() {
        if (StringUtils.isSpace(mBinding.tilAccount.getEditText().getText().toString())) {
            mBinding.tilAccount.setError("账号不能为空");
        } else if (StringUtils.isSpace(mBinding.tilPwd.getEditText().getText().toString())) {
            mBinding.tilPwd.setError("密码不能为空");
        } else {
            mBinding.tilAccount.setErrorEnabled(false);
            mBinding.tilPwd.setErrorEnabled(false);
            mPresenter.toLogin(mBinding.tilAccount.getEditText().getText().toString(),
                    mBinding.tilPwd.getEditText().getText().toString());
        }
    }

    @Override
    public void loginSuccessful() {
        startActivity(MainActivity.getStartIntent(getContext()));
        getActivity().finish();
    }
}
