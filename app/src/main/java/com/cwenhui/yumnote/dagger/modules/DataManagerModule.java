package com.cwenhui.yumnote.dagger.modules;

import android.content.res.AssetManager;
import android.content.res.Resources;

import com.cwenhui.data.remote.service.Api;
import com.cwenhui.yumnote.BuildConfig;
import com.cwenhui.yumnote.R;
import com.cwenhui.yumnote.utils.ComponentHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;


/**
 * Created by louiszgm-pc on 2016/9/22.
 */
@Module
public class DataManagerModule {

    @Provides
    @Named("api_url")
    HttpUrl providesApiUrl(Resources resources) {
        return HttpUrl.parse(resources.getString(R.string.api));
    }

//    @Provides
//    Converter.Factory providesLoganSquareConverter() {
//        return LoganSquareConverterFactory.create();
//    }

    @Provides
    @Named("httpLogger")
    public HttpLoggingInterceptor providesHttpLogger() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor.Level basic = BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.HEADERS :
                HttpLoggingInterceptor.Level.NONE;
        interceptor.setLevel(basic);
        return interceptor;
    }


    private String createResponseBody(Interceptor.Chain chain) {
        HttpUrl uri = chain.request().url();
        String path = uri.url().getPath();
        StringBuffer response = new StringBuffer();
        BufferedReader reader;
        AssetManager assetManager = ComponentHolder.getAppComponent().getContext().getAssets();
        try {
            String fileName;
//            if (path.matches("^(/noteBooks)")){
//                fileName = "NoteBooks.json";
//            }else{
                fileName = "SlideUrl.json";
//            }
            reader = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }

    @Provides
    @Named("localdata")
    public Interceptor provideLocalDataInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String responseString = createResponseBody(chain);
                Request request = chain.request();
                Request realRequest = null;
                Timber.d("requestBody : %s", bodyToString(request.body()));
                Response intercepterResponse = null;
                if (request.url().toString().contains("user")||
                        request.url().toString().contains("notebooks")) {
                    realRequest = request.newBuilder().build();
                } else {
                    intercepterResponse = new Response.Builder()
                            .code(200)
                            .message(responseString)
                            .request(request)
                            .protocol(Protocol.HTTP_1_0)
                            .body(ResponseBody.create(MediaType.parse("application/json"), responseString
                                    .getBytes()))
                            .addHeader("content-type", "application/json")
                            .build();
                }
                return intercepterResponse == null ? chain.proceed(realRequest) : intercepterResponse;
            }
        };
        return BuildConfig.DEBUG ? interceptor : null;
    }

    private String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    @Provides
    public OkHttpClient provideHttpClient(@Named("httpLogger") HttpLoggingInterceptor loggingInterceptor,
                                          @Named("localdata") Interceptor localDataInterceptor) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(loggingInterceptor);
        if (localDataInterceptor != null) {
            builder.addInterceptor(localDataInterceptor);
        }
        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;
    }

    @Provides
    public Retrofit providesRetrofit(/*Converter.Factory converterFactory, */@Named("api_url") HttpUrl url,
                                     OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
//                .addConverterFactory(converterFactory)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    public Api providesApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

//    @Provides
//    public DBManager provideDBManager(Context context) {
//        return new DBManager(context);
//    }
//    @Provides
//    public LocalApi provideLocalApi(DBManager dbManager) {
//        return new LocalApi(dbManager);
//    }
}
