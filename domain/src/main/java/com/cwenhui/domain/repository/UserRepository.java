package com.cwenhui.domain.repository;

import com.cwenhui.domain.model.response.Response;

import rx.Observable;

/**
 * Author: GIndoc on 2017/4/9 21:34
 * email : 735506583@qq.com
 * FOR   :
 */
public interface UserRepository {

    Observable<Response<String>> login(String userName, String userPassword);
}
