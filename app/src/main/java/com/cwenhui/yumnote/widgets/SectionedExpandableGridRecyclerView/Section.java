package com.cwenhui.yumnote.widgets.SectionedExpandableGridRecyclerView;

import com.cwenhui.domain.model.NoteBook;

/**
 * Created by bpncool on 2/23/2016.
 */
public class Section implements Cloneable{

    //    private final String name;
    private NoteBook noteBook;

    public NoteBook getNoteBook() {
        return noteBook;
    }

    public void setNoteBook(NoteBook noteBook) {
        this.noteBook = noteBook;
    }

    public boolean isExpanded;
    private boolean hasChildren;

    public Section(NoteBook noteBook) {
//        this.name = name;
        this.noteBook = noteBook;
        isExpanded = false;
        hasChildren = false;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getName() {
        return noteBook.getName();
    }

    @Override
    public Section clone() throws CloneNotSupportedException {
        Section section = (Section) super.clone();
        if (this.noteBook != null) {
            section.noteBook = this.noteBook.clone();
        }
        return section;
    }
}