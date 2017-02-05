package com.cwenhui.yumnote.modules.main;

import com.cwenhui.domain.model.NoteBook;
import com.cwenhui.yumnote.base.IBasePresenter;
import com.cwenhui.yumnote.base.IBaseView;

import java.util.List;

/**
 * Author: GIndoc on 2017/1/18 23:48
 * email : 735506583@qq.com
 * FOR   :
 */

public interface MainContract {
    interface View extends IBaseView {

        void loadNoteBookList(List<NoteBook> data);
    }

    interface Presenter extends IBasePresenter {

    }
}
