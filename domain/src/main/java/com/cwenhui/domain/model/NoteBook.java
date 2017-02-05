package com.cwenhui.domain.model;

/**
 * 作者: GIndoc
 * 日期: 2017/2/5 10:30
 * 作用:
 */

public class NoteBook{
    private String id;
    private String name;
    private String modify_time;

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
}
