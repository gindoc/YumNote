package com.cwenhui.data.repository.impl;

import com.cwenhui.data.BaseRepository;
import com.cwenhui.domain.model.NoteBook;
import com.cwenhui.domain.model.response.Response;
import com.cwenhui.data.service.Api;
import com.cwenhui.domain.repository.NoteBookRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * 作者: GIndoc
 * 日期: 2017/2/4 17:22
 * 作用:
 */
public class NoteBookDataRepository extends BaseRepository implements NoteBookRepository {
    private Api api;

    @Inject
    public NoteBookDataRepository(Api api) {
        this.api = api;
    }

    @Override
    public Observable<Response<List<NoteBook>>> requestNoteBooks() {
        return api.requestNoteBooks();
    }
}
