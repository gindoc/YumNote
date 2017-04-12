package com.cwenhui.data.remote.repository.impl;

import com.cwenhui.data.BaseRepository;
import com.cwenhui.data.remote.service.Api;
import com.cwenhui.domain.model.response.Response;
import com.cwenhui.domain.repository.UserRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Author: GIndoc on 2017/4/9 21:35
 * email : 735506583@qq.com
 * FOR   :
 */

public class UserDataRepository extends BaseRepository implements UserRepository {
    private Api api;

    @Inject
    public UserDataRepository(Api api) {
        this.api = api;
    }

    @Override
    public Observable<Response<String>> login(String userName, String userPassword) {
//        User user = new User();
//        user.setUserName(userName);
//        user.setUserPassword(userPassword);
        return api.login(userName, userPassword);
    }

}
