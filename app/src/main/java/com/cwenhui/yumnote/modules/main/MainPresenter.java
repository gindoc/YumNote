package com.cwenhui.yumnote.modules.main;

import com.cwenhui.domain.usecase.TestCase;
import com.cwenhui.yumnote.base.BasePresenter;

import javax.inject.Inject;

/**
 * Author: GIndoc on 2017/1/18 23:49
 * email : 735506583@qq.com
 * FOR   :
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private TestCase testCase;

    @Inject
    public MainPresenter(TestCase testCase) {
        this.testCase = testCase;
    }

    public void test() {
        testCase.test();
    }
}
