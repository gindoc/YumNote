package com.cwenhui.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者: GIndoc
 * 日期: 2017/2/5 10:30
 * 作用:
 */

public class NoteBook {
    private String id;
    private String name;
    private String modify_time;

    @SerializedName(value="subNoteBooks", alternate = {"sub_note_books"})
    private List<NoteBook> subNoteBooks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public List<NoteBook> getSubNoteBooks() {
        return subNoteBooks;
    }

    public void setSubNoteBooks(List<NoteBook> subNoteBooks) {
        this.subNoteBooks = subNoteBooks;
    }
}
