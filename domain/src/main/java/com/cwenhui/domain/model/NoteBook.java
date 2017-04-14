package com.cwenhui.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 作者: GIndoc
 * 日期: 2017/2/5 10:30
 * 作用:
 */

public class NoteBook implements Cloneable, Serializable{
    private int notebookId;
    private String notebookName;
    @SerializedName("notebookModifytime")
    private long notebookModifyTime;
    @SerializedName("notebookCreatetime")
    private long notebookCreateTime;

    @SerializedName(value = "subNoteBooks", alternate = {"sub_note_books"})
    private ArrayList<NoteBook> subNoteBooks;

    public NoteBook(NoteBook noteBook) {
        this.notebookId = noteBook.notebookId;
        this.notebookName = noteBook.notebookName;
        this.notebookCreateTime = noteBook.notebookCreateTime;
        this.notebookModifyTime = noteBook.notebookModifyTime;
        if (noteBook.subNoteBooks != null) {
            this.subNoteBooks = (ArrayList<NoteBook>) noteBook.subNoteBooks.clone();
        }
    }

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

    public ArrayList<NoteBook> getSubNoteBooks() {
        return subNoteBooks;
    }

    public void setSubNoteBooks(ArrayList<NoteBook> subNoteBooks) {
        this.subNoteBooks = subNoteBooks;
    }

    public long getNotebookCreateTime() {
        return notebookCreateTime;
    }

    public void setNotebookCreateTime(long notebookCreateTime) {
        this.notebookCreateTime = notebookCreateTime;
    }

    @Override
    public NoteBook clone() throws CloneNotSupportedException {
        return new NoteBook(this);
    }
}
