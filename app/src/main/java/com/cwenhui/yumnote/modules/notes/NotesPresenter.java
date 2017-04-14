package com.cwenhui.yumnote.modules.notes;

import com.cwenhui.domain.model.Note;
import com.cwenhui.domain.model.response.Response;
import com.cwenhui.domain.usecase.NoteCase;
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
 * Author: GIndoc on 2017/4/14 19:32
 * email : 735506583@qq.com
 * FOR   :
 */

public class NotesPresenter extends BasePresenter<NotesContract.View> implements NotesContract.Presenter {

    @Inject
    NoteCase noteCase;
    @Inject
    public NotesPresenter() {
    }

    public void requestNotes(int id) {
        noteCase.requestNotes(Saver.getToken(), id)
        .compose(getView().<Response<List<Note>>>getBindToLifecycle())
        .compose(RxResultHelper.<Response<List<Note>>>handleResult())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new RxSubscriber<Response<List<Note>>>() {
            @Override
            public void _onNext(Response<List<Note>> response) {
                getView().loadNotes(response.getData());
            }

            @Override
            public void _onError(Throwable throwable) {
                Timber.e(throwable.getMessage());
            }
        });
    }
}
