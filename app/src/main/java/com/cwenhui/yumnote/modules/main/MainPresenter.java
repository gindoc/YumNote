package com.cwenhui.yumnote.modules.main;

import com.cwenhui.domain.model.NoteBook;
import com.cwenhui.domain.model.response.Response;
import com.cwenhui.domain.usecase.NoteBooksCase;
import com.cwenhui.domain.usecase.TestCase;
import com.cwenhui.yumnote.base.BasePresenter;
import com.cwenhui.yumnote.utils.Saver;
import com.cwenhui.yumnote.utils.rx.RxResultHelper;
import com.cwenhui.yumnote.utils.rx.RxSubscriber;

import java.util.List;

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
        noteBooksCase.requestNoteBooks(Saver.getToken())
                .compose(getView().<Response<List<NoteBook>>>getBindToLifecycle())
                .compose(RxResultHelper.<Response<List<NoteBook>>>handleResult())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<Response<List<NoteBook>>>() {
                    @Override
                    public void _onNext(Response<List<NoteBook>> response) {
                        getView().loadNoteBookList(response.getData());
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Timber.e(throwable.getMessage());
                    }
                });
    }
}
