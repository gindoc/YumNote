package com.cwenhui.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者: GIndoc
 * 日期: 2017/2/5 10:30
 * 作用:
 */

public class NoteBook {
    private int notebookId;
    private String notebookName;
    @SerializedName("notebookModifytime")
    private long notebookModifyTime;
    @SerializedName("notebookCreatetime")
    private long notebookCreateTime;

    @SerializedName(value = "subNoteBooks", alternate = {"sub_note_books"})
    private List<NoteBook> subNoteBooks;

    public int getId() {
        return notebookId;
    }

    public void setId(int id) {
        this.notebookId = id;
    }

    public String getName() {
        return notebookName;
    }

    public void setName(String name) {
        this.notebookName = name;
    }

    public long getModify_time() {
        return notebookModifyTime;
    }

    public void setModify_time(long modify_time) {
        this.notebookModifyTime = modify_time;
    }

    public List<NoteBook> getSubNoteBooks() {
        return subNoteBooks;
    }

    public void setSubNoteBooks(List<NoteBook> subNoteBooks) {
        this.subNoteBooks = subNoteBooks;
    }

    public long getNotebookCreateTime() {
        return notebookCreateTime;
    }

    public void setNotebookCreateTime(long notebookCreateTime) {
        this.notebookCreateTime = notebookCreateTime;
    }
}
