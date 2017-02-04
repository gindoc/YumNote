package com.cwenhui.data.service;

import com.cwenhui.domain.response.Response;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Author: GIndoc on 2017/1/18 0:25
 * email : 735506583@qq.com
 * FOR   :
 */
public interface Api {
    @GET("noteBooks")
    Observable<Response> requestNoteBooks();
}
