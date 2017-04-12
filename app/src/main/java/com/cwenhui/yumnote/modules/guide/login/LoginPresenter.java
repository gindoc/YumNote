package com.cwenhui.yumnote.modules.guide.login;

import com.cwenhui.domain.model.User;
import com.cwenhui.domain.model.response.Response;
import com.cwenhui.domain.usecase.UserCase;
import com.cwenhui.yumnote.base.BasePresenter;
import com.cwenhui.yumnote.utils.Saver;
import com.cwenhui.yumnote.utils.rx.RxResultHelper;
import com.cwenhui.yumnote.utils.rx.RxSubscriber;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Author: GIndoc on 2017/4/11 16:56
 * email : 735506583@qq.com
 * FOR   :
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    @Inject
    UserCase userCase;

    @Inject
    public LoginPresenter() {
    }

    @Override
    public void toLogin(final String username, final String pwd) {
        userCase.login(username, pwd)
                .compose(getView().<Response<String>>getBindToLifecycle())
                .compose(RxResultHelper.<Response<String>>handleResult())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<Response<String>>() {
                    @Override
                    public void _onNext(Response<String> stringResponse) {
                        User user = new User();
                        user.setUserName(username);
                        user.setUserPassword(pwd);
                        Saver.login(user, stringResponse.getData());
                        getView().loginSuccessful();
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Timber.e(throwable.getMessage());
                    }
                });
    }
}
