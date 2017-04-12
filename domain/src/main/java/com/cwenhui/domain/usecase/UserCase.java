package com.cwenhui.domain.usecase;

import com.cwenhui.domain.model.response.Response;
import com.cwenhui.domain.repository.UserRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Author: GIndoc on 2017/4/9 21:31
 * email : 735506583@qq.com
 * FOR   :
 */

public class UserCase {
    private UserRepository repository;

    @Inject
    public UserCase(UserRepository repository) {
        this.repository = repository;
    }

    public Observable<Response<String>> login(String userName, String userPassword) {
        return repository.login(userName, userPassword);
    }
}
