package com.cwenhui.yumnote.modules.guide.regist;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cwenhui.yumnote.R;
import com.cwenhui.yumnote.base.BaseFragment;
import com.cwenhui.yumnote.databinding.FragmentGuideRegisterBinding;
import com.cwenhui.yumnote.modules.main.MainActivity;
import com.cwenhui.yumnote.utils.StringUtils;
import com.trello.rxlifecycle.LifecycleTransformer;

import javax.inject.Inject;

/**
 * Author: GIndoc on 2017/4/11 18:02
 * email : 735506583@qq.com
 * FOR   :
 */

public class RegisterFragment extends BaseFragment<RegisterContract.View, RegisterPresenter>
        implements RegisterContract.View {
    private FragmentGuideRegisterBinding mBinding;

    @Inject
    RegisterPresenter mPresenter;

    @Inject
    public RegisterFragment() {
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
        mBinding = FragmentGuideRegisterBinding.inflate(inflater, container, false);
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
    protected RegisterPresenter createPresent() {
        return mPresenter;
    }

    @Override
    public <T> LifecycleTransformer<T> getBindToLifecycle() {
        return bindToLifecycle();
    }

    @Override
    public void showError(String error) {

    }

    public void toRegister() {
        if (StringUtils.isSpace(mBinding.tilAccount.getEditText().getText().toString())) {
            mBinding.tilAccount.setError("账号不能为空");
        } else if (StringUtils.isSpace(mBinding.tilPwd.getEditText().getText().toString())) {
            mBinding.tilPwd.setError("密码不能为空");
        } else {
            mBinding.tilAccount.setErrorEnabled(false);
            mBinding.tilPwd.setErrorEnabled(false);
            mPresenter.toRegister(mBinding.tilAccount.getEditText().getText().toString(),
                    mBinding.tilPwd.getEditText().getText().toString());
        }
    }

    @Override
    public void registerSuccessful() {
        startActivity(MainActivity.getStartIntent(getContext()));
        getActivity().finish();
    }
}
