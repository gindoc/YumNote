package com.cwenhui.yumnote.modules.main;

import com.cwenhui.domain.model.NoteBook;
import com.cwenhui.domain.model.response.Response;
import com.cwenhui.domain.usecase.NoteBooksCase;
import com.cwenhui.domain.usecase.TestCase;
import com.cwenhui.yumnote.base.BasePresenter;
import com.cwenhui.yumnote.utils.Saver;
import com.cwenhui.yumnote.utils.rx.RxResultHelper;
import com.cwenhui.yumnote.utils.rx.RxSubscriber;
import com.cwenhui.yumnote.widgets.SectionedExpandableGridRecyclerView.Section;

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

    @Override
    public void addNoteBook(String input) {
        noteBooksCase.addNoteBook(Saver.getToken(), input)
                .compose(getView().<Response<NoteBook>>getBindToLifecycle())
                .compose(RxResultHelper.<Response<NoteBook>>handleResult())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<Response<NoteBook>>() {
                    @Override
                    public void _onNext(Response<NoteBook> response) {
                        getView().addSuccessful(response.getData());
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Timber.e(throwable.getMessage());
                    }
                });
    }

    @Override
    public void deleteNoteBook(final NoteBook noteBook) {
        noteBooksCase.deleteNoteBook(Saver.getToken(), noteBook.getId())
                .compose(getView().<Response>getBindToLifecycle())
                .compose(RxResultHelper.<Response>handleResult())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<Response>() {
                    @Override
                    public void _onNext(Response response) {
                        getView().deleteSuccessful(noteBook);
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Timber.e(throwable.getMessage());
                    }
                });
    }

    @Override
    public void renameNoteBook(Section section, final String input) {
        final NoteBook noteBook = section.getNoteBook();
        final String oldName = noteBook.getName();
        noteBook.setName(input);
        noteBooksCase.renameNoteBook(Saver.getToken(), noteBook)
                .compose(getView().<Response>getBindToLifecycle())
                .compose(RxResultHelper.<Response>handleResult())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<Response>() {
                    @Override
                    public void _onNext(Response sponse) {
                        noteBook.setModify_time(System.currentTimeMillis());
                        getView().renameSuccessful(oldName, noteBook);
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Timber.e(throwable.getMessage());
                    }
                });

    }
}
