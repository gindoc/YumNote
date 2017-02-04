package com.cwenhui.domain.repository;


import com.cwenhui.domain.response.Response;

import rx.Observable;

/**
 * 作者: GIndoc
 * 日期: 2017/2/4 17:17
 * 作用:
 */

public interface NoteBookRepository {
    Observable<Response> requestNoteBooks();
}
