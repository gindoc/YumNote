package com.cwenhui.yumnote.modules.guide.login;

import com.cwenhui.yumnote.base.IBasePresenter;
import com.cwenhui.yumnote.base.IBaseView;

/**
 * Author: GIndoc on 2017/4/11 16:55
 * email : 735506583@qq.com
 * FOR   :
 */

public interface LoginContract {
    interface View extends IBaseView {

        void loginSuccessful();

    }

    interface Presenter extends IBasePresenter {

        void toLogin(String username, String pwd);
    }

}
