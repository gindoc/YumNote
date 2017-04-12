package com.cwenhui.yumnote.widgets.SectionedExpandableGridRecyclerView;

import com.cwenhui.domain.model.NoteBook;

/**
 * Created by lenovo on 2/23/2016.
 */
public interface ItemClickListener {
    void itemClicked(NoteBook item);
    void itemClicked(Section section);
}