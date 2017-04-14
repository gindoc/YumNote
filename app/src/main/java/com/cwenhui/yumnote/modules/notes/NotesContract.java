package com.cwenhui.yumnote.modules.notes;

import com.cwenhui.domain.model.Note;
import com.cwenhui.yumnote.base.IBasePresenter;
import com.cwenhui.yumnote.base.IBaseView;

import java.util.List;

/**
 * Author: GIndoc on 2017/4/14 19:31
 * email : 735506583@qq.com
 * FOR   :
 */

public interface NotesContract {
    interface View extends IBaseView {

        void loadNotes(List<Note> notes);
    }

    interface Presenter extends IBasePresenter {
        void requestNotes(int id);
    }

}
