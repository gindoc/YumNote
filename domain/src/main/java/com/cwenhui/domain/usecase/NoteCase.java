package com.cwenhui.domain.usecase;

import com.cwenhui.domain.model.Note;
import com.cwenhui.domain.model.response.Response;
import com.cwenhui.domain.repository.NoteRepository;

import java.io.File;
import java.util.List;
import java.util.Map;

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

    public Observable<Response> deleteNote(String token, int bookId, int noteId) {
        return noteRepository.deleteNote(token, bookId, noteId);
    }

    public Observable<Response<List<String>>> uploadImg(String token, File file) {
        return noteRepository.uploadImg(token, file);
    }

    public Observable<Response> updateNote(Map<String, Object> params) {
        return noteRepository.updateNote(params);
    }

    public Observable<Response<Note>> addNote(Map<String, Object> params) {
        return noteRepository.addNote(params);
    }
}
