package com.volskyioleh.test;

import android.app.Application;
import android.content.Context;

import com.volskyioleh.test.api.ServerCommunicator;
import com.volskyioleh.test.di.components.ApiComponent;
import com.volskyioleh.test.di.components.DaggerApiComponent;
import com.volskyioleh.test.di.modules.ApiModule;
import com.volskyioleh.test.di.modules.AppModule;

public class App extends Application {
    private static Context context;
    private ApiComponent apiComponent;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initSystemModules();
    }


    public static Context getAppContext() {
        return App.context;
    }
    private void initSystemModules() {
        App.context = getApplicationContext();
        apiComponent = DaggerApiComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(ServerCommunicator.API_URL))
                .build();
    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }
}
