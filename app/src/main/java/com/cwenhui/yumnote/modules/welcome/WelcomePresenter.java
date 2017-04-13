package com.cwenhui.yumnote.modules.welcome;

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
 * Author: GIndoc on 2017/4/9 21:24
 * email : 735506583@qq.com
 * FOR   :
 */

public class WelcomePresenter extends BasePresenter<WelcomeContract.View> implements WelcomeContract.Presenter{

    @Inject
    UserCase userCase;

    @Inject
    public WelcomePresenter() {
    }

    public void login(final String userName, final String userPassword) {
        userCase.login(userName, userPassword)
                .compose(getView().<Response<String>>getBindToLifecycle())
                .compose(RxResultHelper.<Response<String>>handleResult())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<Response<String>>() {
                    @Override
                    public void _onNext(Response<String> stringResponse) {
                        User user = new User();
                        user.setUserName(userName);
                        user.setUserPassword(userPassword);
                        Saver.login(user, stringResponse.getData());
                        getView().loginSuccessful();
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Timber.e(throwable.getMessage());
                        getView().loginFailed();
                    }
                });
    }
}
