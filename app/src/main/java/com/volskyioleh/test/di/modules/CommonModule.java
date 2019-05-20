package com.volskyioleh.test.di.modules;

import com.volskyioleh.test.api.ServerCommunicator;
import com.volskyioleh.test.di.CommonScope;
import com.volskyioleh.test.presenter.login.LoginContract;
import com.volskyioleh.test.presenter.login.LoginPresenter;
import com.volskyioleh.test.presenter.main.MainContract;
import com.volskyioleh.test.presenter.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class CommonModule {

    @Provides
    @CommonScope
    public MainContract.Presenter provideMainPresenter(
            ServerCommunicator serverCommunicator) {
        return new MainPresenter(serverCommunicator);
    }


    @Provides
    @CommonScope
    public LoginContract.Presenter provideLogInPresenter(
            ServerCommunicator serverCommunicator) {
        return new LoginPresenter(serverCommunicator);
    }


}
