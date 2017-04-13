package com.cwenhui.domain.usecase;

import com.cwenhui.domain.model.NoteBook;
import com.cwenhui.domain.model.response.Response;
import com.cwenhui.domain.repository.NoteBookRepository;

import java.util.List;

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

    public Observable<Response<List<NoteBook>>> requestNoteBooks(String token) {
        return noteBookRepository.requestNoteBooks(token);
    }
}
