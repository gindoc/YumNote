package com.cwenhui.yumnote.modules.editor;

import com.cwenhui.yumnote.base.IBasePresenter;
import com.cwenhui.yumnote.base.IBaseView;

import java.util.List;

/**
 * Author: GIndoc on 2017/4/20 14:51
 * email : 735506583@qq.com
 * FOR   :
 */

public interface NoteEditContract {
    interface View extends IBaseView {

        void loadImg(List<String> urls);
    }

    interface Presenter extends IBasePresenter {
        void uploadImg(String mImageCapturePath);

        void compressImg(String mImageCapturePath);
    }

}
