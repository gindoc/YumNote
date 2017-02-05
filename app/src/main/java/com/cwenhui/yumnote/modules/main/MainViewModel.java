package com.cwenhui.yumnote.modules.main;

import android.databinding.BaseObservable;

import com.cwenhui.domain.model.NoteBook;

/**
 * 作者: GIndoc
 * 日期: 2017/2/5 16:48
 * 作用:
 */

public class MainViewModel extends BaseObservable {
    private NoteBook noteBook;

    public NoteBook getNoteBook() {
        return noteBook;
    }

    public void setNoteBook(NoteBook noteBook) {
        this.noteBook = noteBook;
    }
}
