package com.cwenhui.yumnote.dagger.components;

import com.cwenhui.yumnote.modules.main.MainActivity;
import com.cwenhui.yumnote.dagger.modules.ActivityModule;
import com.cwenhui.yumnote.dagger.modules.FragmentModule;

import dagger.Subcomponent;

/**
 * 作者: 陈文辉
 * 日期: 2017/1/17 23:39
 * 作用:
 */
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    FragmentComponent plus(FragmentModule module);


}
