package com.cwenhui.yumnote.modules.main;

import com.cwenhui.domain.response.Response;
import com.cwenhui.domain.usecase.NoteBooksCase;
import com.cwenhui.domain.usecase.TestCase;
import com.cwenhui.yumnote.base.BasePresenter;
import com.cwenhui.yumnote.utils.rx.RxResultHelper;
import com.cwenhui.yumnote.utils.rx.RxSubscriber;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Author: GIndoc on 2017/1/18 23:49
 * email : 735506583@qq.com
 * FOR   :
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private TestCase testCase;

    @Inject
    NoteBooksCase noteBooksCase;

    @Inject
    public MainPresenter(TestCase testCase) {
        this.testCase = testCase;
    }

    public void test() {
        testCase.test();
    }

    public void requestNoteBooks() {
        noteBooksCase.requestNoteBooks()
                .compose(getView().<Response>getBindToLifecycle())
                .compose(RxResultHelper.<Response>handleResult())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<Response>() {
                    @Override
                    public void _onNext(Response response) {
                        Timber.e(response.getErro_msg());
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Timber.e(throwable.getMessage());
                    }
                });
    }
}
