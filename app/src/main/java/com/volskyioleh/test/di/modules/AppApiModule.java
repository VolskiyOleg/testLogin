package com.volskyioleh.test.di.modules;


import android.content.Context;

import com.volskyioleh.test.api.ServerCommunicator;
import com.volskyioleh.test.api.models.ItemModel;
import com.volskyioleh.test.api.models.LoginModel;
import com.volskyioleh.test.di.CommonScope;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

@Module
public class AppApiModule {

    @Provides
    @CommonScope
    public AppApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(AppApiService.class);
    }

    @Provides
    @CommonScope
    public ServerCommunicator provideServerCommunicator(AppApiService appApiService,
                                                        Context context) {
        return new ServerCommunicator(appApiService, context);
    }

    public interface AppApiService {

        @POST("http://client-api.instaforex.com/api/Authentication/RequestMoblieCabinetApiToken")
        Observable<ResponseBody> login(
                @Body LoginModel loginModel
        );
//
//
        @GET
        Observable<List<ItemModel>> getList(@Url String url,
                @Query("tradingsystem") String tradingSystems,
                @Query("pairs") String pairs,
                @Query("from") String from,
                @Query("to") String tp,
                @Header("passkey") String passKey
        );
//
//        @GET("viewed/30.json")
//        Observable<Response> getMostViewediArticles(
//                @Query("api-key") String key
//        );

    }
}