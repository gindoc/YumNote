package com.cwenhui.domain.usecase;

import com.cwenhui.domain.repository.NoteBookRepository;
import com.cwenhui.domain.response.Response;

import javax.inject.Inject;

import rx.Observable;

/**
 * 作者: GIndoc
 * 日期: 2017/2/4 17:17
 * 作用:
 */

public class NoteBooksCase {
    private NoteBookRepository noteBookRepository;

    @Inject
    public NoteBooksCase(NoteBookRepository noteBookRepository) {
        this.noteBookRepository = noteBookRepository;
    }

    public Observable<Response> requestNoteBooks() {
        return noteBookRepository.requestNoteBooks();
    }
}
