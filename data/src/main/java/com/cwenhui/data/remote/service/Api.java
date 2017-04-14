package com.cwenhui.data.remote.service;

import com.cwenhui.domain.model.NoteBook;
import com.cwenhui.domain.model.response.Response;

import java.util.List;
import java.util.Map;

import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author: GIndoc on 2017/1/18 0:25
 * email : 735506583@qq.com
 * FOR   :
 */
public interface Api {
    @GET("notebooks")
    Observable<Response<List<NoteBook>>> requestNoteBooks(@Query("token")String token);

    @Headers("Content-Type: application/x-www-form-urlencoded; charset=UTF-8;")
    @FormUrlEncoded
    @POST("user")
    Observable<Response<String>> login(/*@Field("user") User user*/@Field("userName")String name,
                                       @Field("userPassword")String pwd);

    @Headers("Content-Type: application/x-www-form-urlencoded; charset=UTF-8;")
    @FormUrlEncoded
    @POST("notebook")
    Observable<Response<NoteBook>> addNoteBook(@Field("token") String token, @Field("notebookName") String bookName);


//    @FormUrlEncoded
    @DELETE("notebook")
    Observable<Response> deleteNoteBook(@Query("token") String token, @Query("bookId") int id);

    @Headers("Content-Type: application/x-www-form-urlencoded; charset=UTF-8;")
    @FormUrlEncoded
    @POST("notebook")
    Observable<Response> updateNoteBook(@FieldMap Map<String, Object> map);

}
