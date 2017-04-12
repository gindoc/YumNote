package com.cwenhui.yumnote.dagger.components;

import com.cwenhui.yumnote.dagger.modules.FragmentModule;
import com.cwenhui.yumnote.modules.guide.index.IndexFragment;
import com.cwenhui.yumnote.modules.guide.login.LoginFragment;
import com.cwenhui.yumnote.modules.guide.regist.RegisterFragment;

import dagger.Subcomponent;

/**
 * 作者: 陈文辉
 * 日期: 2017/1/17 23:39
 * 作用:
 */
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(IndexFragment indexFragment);

    void inject(LoginFragment loginFragment);

    void inject(RegisterFragment registerFragment);
}
