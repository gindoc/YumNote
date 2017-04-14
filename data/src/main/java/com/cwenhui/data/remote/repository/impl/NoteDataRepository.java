package com.cwenhui.data.remote.repository.impl;

import com.cwenhui.data.BaseRepository;
import com.cwenhui.data.remote.service.Api;
import com.cwenhui.domain.model.Note;
import com.cwenhui.domain.model.response.Response;
import com.cwenhui.domain.repository.NoteRepository;

import java.util.List;

import javax.inject.Inject;

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
}
