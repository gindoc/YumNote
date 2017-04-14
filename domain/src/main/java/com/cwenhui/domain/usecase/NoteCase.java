package com.cwenhui.domain.usecase;

import com.cwenhui.domain.model.Note;
import com.cwenhui.domain.model.response.Response;
import com.cwenhui.domain.repository.NoteRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Author: GIndoc on 2017/4/14 20:44
 * email : 735506583@qq.com
 * FOR   :
 */

public class NoteCase {
    private NoteRepository noteRepository;

    @Inject
    public NoteCase(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Observable<Response<List<Note>>> requestNotes(String token, int bookId) {
        return noteRepository.requestNotes(token, bookId);
    }

}
