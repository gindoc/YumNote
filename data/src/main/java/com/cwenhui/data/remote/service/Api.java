package com.cwenhui.data.remote.service;

import com.cwenhui.domain.model.NoteBook;
import com.cwenhui.domain.model.response.Response;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Author: GIndoc on 2017/1/18 0:25
 * email : 735506583@qq.com
 * FOR   :
 */
public interface Api {
    @GET("noteBooks")
    Observable<Response<List<NoteBook>>> requestNoteBooks();

    @FormUrlEncoded
    @POST("user")
    Observable<Response<String>> login(/*@Field("user") User user*/@Field("userName")String name,
                                       @Field("userPassword")String pwd);
}
