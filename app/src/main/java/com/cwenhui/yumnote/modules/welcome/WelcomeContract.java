package com.cwenhui.yumnote.modules.welcome;

import com.cwenhui.yumnote.base.IBasePresenter;
import com.cwenhui.yumnote.base.IBaseView;

/**
 * Author: GIndoc on 2017/4/9 21:23
 * email : 735506583@qq.com
 * FOR   :
 */

public interface WelcomeContract {
    interface View extends IBaseView{

        void loginSuccessful();
    }

    interface Presenter extends IBasePresenter {

    }

}
