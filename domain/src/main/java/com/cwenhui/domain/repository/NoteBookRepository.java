package com.cwenhui.domain.repository;


import com.cwenhui.domain.model.NoteBook;
import com.cwenhui.domain.model.response.Response;

import java.util.List;

import rx.Observable;

/**
 * 作者: GIndoc
 * 日期: 2017/2/4 17:17
 * 作用:
 */

public interface NoteBookRepository {
    Observable<Response<List<NoteBook>>> requestNoteBooks(String token);

    Observable<Response<NoteBook>> addNoteBook(String token, String bookName);

    Observable<Response> deleteNoteBook(String token, int id);

    Observable<Response> updateNoteBook(String token, NoteBook book);
}
