package com.cwenhui.yumnote.modules.main;

import com.cwenhui.domain.model.NoteBook;
import com.cwenhui.yumnote.base.IBasePresenter;
import com.cwenhui.yumnote.base.IBaseView;
import com.cwenhui.yumnote.widgets.SectionedExpandableGridRecyclerView.Section;

import java.util.List;

/**
 * Author: GIndoc on 2017/1/18 23:48
 * email : 735506583@qq.com
 * FOR   :
 */

public interface MainContract {
    interface View extends IBaseView {

        void loadNoteBookList(List<NoteBook> data);

        void addSuccessful(NoteBook book);

        void deleteSuccessful(NoteBook noteBook);

        void renameSuccessful(String oldName, NoteBook noteBook);
    }

    interface Presenter extends IBasePresenter {

        void addNoteBook(String input);

        void deleteNoteBook(NoteBook noteBook);

        void renameNoteBook(Section noteBook, String input);
    }
}
