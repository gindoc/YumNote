package com.cwenhui.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Author: GIndoc on 2017/4/14 20:55
 * email : 735506583@qq.com
 * FOR   :
 */
public class Note implements Cloneable, Serializable {
    private int noteId;

    private String noteTitle;

    @SerializedName("noteCreatetime")
    private long noteCreateTime;

    @SerializedName("noteModifytime")
    private long noteModifyTime;

    private String noteContent;

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public long getNoteCreateTime() {
        return noteCreateTime;
    }

    public void setNoteCreateTime(long noteCreateTime) {
        this.noteCreateTime = noteCreateTime;
    }

    public long getNoteModifyTime() {
        return noteModifyTime;
    }

    public void setNoteModifyTime(long noteModifyTime) {
        this.noteModifyTime = noteModifyTime;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

}
