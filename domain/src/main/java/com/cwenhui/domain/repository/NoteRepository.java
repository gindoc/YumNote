package com.cwenhui.domain.repository;

import com.cwenhui.domain.model.Note;
import com.cwenhui.domain.model.response.Response;

import java.util.List;

import rx.Observable;

/**
 * Author: GIndoc on 2017/4/14 20:45
 * email : 735506583@qq.com
 * FOR   :
 */
public interface NoteRepository {

    Observable<Response<List<Note>>> requestNotes(String token, int bookId);

    Observable<Response> deleteNote(String token, int bookId, int noteId);
}
