package com.cwenhui.data.remote.repository.impl;

import com.cwenhui.data.BaseRepository;
import com.cwenhui.data.remote.service.Api;
import com.cwenhui.domain.model.Note;
import com.cwenhui.domain.model.response.Response;
import com.cwenhui.domain.repository.NoteRepository;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Author: GIndoc on 2017/4/14 20:47
 * email : 735506583@qq.com
 * FOR   :
 */

public class NoteDataRepository extends BaseRepository implements NoteRepository {
    private Api api;

    @Inject
    public NoteDataRepository(Api api) {
        this.api = api;
    }

    @Override
    public Observable<Response<List<Note>>> requestNotes(String token, int bookId) {
        return api.requestNotes(token, bookId);
    }

    @Override
    public Observable<Response> deleteNote(String token, int bookId, int noteId) {
        return api.deleteNote(token, bookId, noteId);
    }

    @Override
    public Observable<Response<List<String>>> uploadImg(String token, File file) {
        Map<String,RequestBody> params = new HashMap<String, RequestBody>();
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        params.put("file[]\"; filename=\"" + file.getName() , requestBody);
        return api.uploadImg(token, params);
    }
}
