package com.volskyioleh.test.api;

import android.content.Context;

import com.volskyioleh.test.api.models.ItemModel;
import com.volskyioleh.test.api.models.LoginModel;
import com.volskyioleh.test.di.modules.AppApiModule;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServerCommunicator {
    public static final String API_URL = "http://client-api.instaforex.com";
    public static final String API_URL_LIST = "http://client-api.instaforex.com/clientmobile/GetAnalyticSignals/";
    public static final String KEY = "OscffFQeaJg6Z23ouzwOvfl02RiNo0Ay";

    private Retrofit mRetrofit;
    private AppApiModule.AppApiService mService;
    private Context mContext;

    public ServerCommunicator(AppApiModule.AppApiService appApiService, Context context) {
        mService = appApiService;
        mContext = context;
    }

    private AppApiModule.AppApiService getService() {
        if (mService == null) {
            mService = getRetrofit().create(AppApiModule.AppApiService.class);
        }
        return mService;
    }

    public Retrofit getRetrofit() {
        if (mRetrofit == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .addInterceptor(httpLoggingInterceptor)
                    .build();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    //           addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }


    public Observable<ResponseBody> login(LoginModel loginModel) {
        return getService().login(loginModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //
    public Observable<List<ItemModel>> buildGetListObservable(String login, String pairs, String from, String to, String passKey) {
        String url = API_URL_LIST + login;
        return getService().getList(url, "3", pairs, from, to, passKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
//
//    public Observable<Response> buildGetViewedArticlesObservable() {
//        return    getService().getMostViewediArticles(KEY)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }

}
