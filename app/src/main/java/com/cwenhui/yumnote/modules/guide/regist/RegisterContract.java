package com.cwenhui.yumnote.modules.guide.regist;

import com.cwenhui.yumnote.base.IBasePresenter;
import com.cwenhui.yumnote.base.IBaseView;

/**
 * Author: GIndoc on 2017/4/11 18:01
 * email : 735506583@qq.com
 * FOR   :
 */

public interface RegisterContract {
    interface View extends IBaseView {

        void registerSuccessful();
    }

    interface Presenter extends IBasePresenter {

        void toRegister(String username, String pwd);
    }

}
