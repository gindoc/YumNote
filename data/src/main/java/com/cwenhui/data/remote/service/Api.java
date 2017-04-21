package com.cwenhui.data.remote.service;

import com.cwenhui.domain.model.Note;
import com.cwenhui.domain.model.NoteBook;
import com.cwenhui.domain.model.response.Response;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author: GIndoc on 2017/1/18 0:25
 * email : 735506583@qq.com
 * FOR   :
 */
public interface Api {
    @GET("notebooks")
    Observable<Response<List<NoteBook>>> requestNoteBooks(@Query("token") String token);

    @Headers("Content-Type: application/x-www-form-urlencoded; charset=UTF-8;")
    @FormUrlEncoded
    @POST("user")
    Observable<Response<String>> login(/*@Field("user") User user*/@Field("userName") String name,
                                       @Field("userPassword") String pwd);

    @Headers("Content-Type: application/x-www-form-urlencoded; charset=UTF-8;")
    @FormUrlEncoded
    @POST("notebook")
    Observable<Response<NoteBook>> addNoteBook(@Field("token") String token, @Field("notebookName") String
            bookName);

    @DELETE("notebook")
    Observable<Response> deleteNoteBook(@Query("token") String token,
                                        @Query("bookId") int id);

    @Headers("Content-Type: application/x-www-form-urlencoded; charset=UTF-8;")
    @FormUrlEncoded
    @POST("notebook")
    Observable<Response> updateNoteBook(@FieldMap Map<String, Object> map);

    @GET("notes")
    Observable<Response<List<Note>>> requestNotes(@Query("token") String token, @Query("notebookId") int bookId);

    @DELETE("note")
    Observable<Response> deleteNote(@Query("token") String token,
                                    @Query("bookId") int bookId,
                                    @Query("noteId") int noteId);

    @Multipart
    @POST("note/image")
    Observable<Response<List<String>>> uploadImg(@Query("token")String token,
                                                 @PartMap Map<String, RequestBody> params);

    @Headers("Content-Type: application/x-www-form-urlencoded; charset=UTF-8;")
    @FormUrlEncoded
    @POST("note")
    Observable<Response> updateNote(@FieldMap Map<String, Object> note);
}
