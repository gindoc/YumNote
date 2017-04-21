package com.cwenhui.yumnote.modules.editor;

import android.graphics.Bitmap;

import com.cwenhui.domain.model.response.Response;
import com.cwenhui.domain.usecase.NoteCase;
import com.cwenhui.yumnote.base.BasePresenter;
import com.cwenhui.yumnote.utils.ImageUtils;
import com.cwenhui.yumnote.utils.Saver;
import com.cwenhui.yumnote.utils.rx.RxResultHelper;
import com.cwenhui.yumnote.utils.rx.RxSubscriber;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Author: GIndoc on 2017/4/20 14:52
 * email : 735506583@qq.com
 * FOR   :
 */

public class NoteEditPresenter extends BasePresenter<NoteEditContract.View>
        implements NoteEditContract.Presenter {

    @Inject
    NoteCase noteCase;

    @Inject
    public NoteEditPresenter() {
    }

    @Override
    public void uploadImg(String mImageCapturePath) {
        noteCase.uploadImg(Saver.getToken(), new File(mImageCapturePath))
                .compose(getView().<Response<List<String>>>getBindToLifecycle())
                .compose(RxResultHelper.<Response<List<String>>>handleResult())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<Response<List<String>>>() {
                    @Override
                    public void _onNext(Response<List<String>> response) {
                        getView().loadImg(response.getData());
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Timber.e(throwable.getMessage());
                    }
                });
    }

    @Override
    public void compressImg(final String filePath) {
        Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                Bitmap bitmapDes = ImageUtils.compress(filePath, 800, 800);
                try {
                    bitmapDes.compress(Bitmap.CompressFormat.PNG, 100,
                            new FileOutputStream(filePath, false));
                    subscriber.onNext(null);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        })
                .compose(getView().<Void>getBindToLifecycle())
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<Void>() {
                    @Override
                    public void _onNext(Void aVoid) {
                        uploadImg(filePath);
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Timber.e(throwable.getMessage());
                    }
                });
    }

    @Override
    public void updateNote(int noteId, String title, String content) {
        Map<String, Object> params = new HashMap<>();
        params.put("noteId", noteId);
        params.put("noteTitle", title);
        params.put("noteContent", content);
        params.put("token", Saver.getToken());
        noteCase.updateNote(params)
                .compose(getView().<Response>getBindToLifecycle())
                .compose(RxResultHelper.<Response>handleResult())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<Response>() {
                    @Override
                    public void _onNext(Response response) {
                        getView().updateNoteSuccessful();
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Timber.e(throwable.getMessage());
                    }
                });
    }
}
