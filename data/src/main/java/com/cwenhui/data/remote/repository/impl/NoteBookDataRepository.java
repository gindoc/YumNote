package com.cwenhui.data.remote.repository.impl;

import com.cwenhui.data.BaseRepository;
import com.cwenhui.data.remote.service.Api;
import com.cwenhui.domain.model.NoteBook;
import com.cwenhui.domain.model.response.Response;
import com.cwenhui.domain.repository.NoteBookRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Observable<Response<List<NoteBook>>> requestNoteBooks(String token) {
        return api.requestNoteBooks(token);
    }

    @Override
    public Observable<Response<NoteBook>> addNoteBook(String token, String bookName) {
        return api.addNoteBook(token, bookName);
    }

    @Override
    public Observable<Response> deleteNoteBook(String token, int id) {
        return api.deleteNoteBook(token, id);
    }

    @Override
    public Observable<Response> updateNoteBook(String token, NoteBook book) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("_method", "put");
        map.put("notebookId", book.getId());
        map.put("notebookName", book.getName());
        return api.updateNoteBook(map);
    }
}
