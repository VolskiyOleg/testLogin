package com.volskyioleh.test.di.components;

import com.volskyioleh.test.di.CommonScope;
import com.volskyioleh.test.di.modules.AppApiModule;
import com.volskyioleh.test.di.modules.CommonModule;
import com.volskyioleh.test.presenter.login.LoginActivity;
import com.volskyioleh.test.presenter.main.MainActivity;

import dagger.Component;


@CommonScope
@Component(modules = {CommonModule.class, AppApiModule.class},
        dependencies = {ApiComponent.class})
public interface CommonComponent {

    void inject(MainActivity activity);

    void inject(LoginActivity activity);

}
