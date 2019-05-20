package com.volskyioleh.test.presenter.login;

import android.view.View;

import com.volskyioleh.test.api.ServerCommunicator;
import com.volskyioleh.test.api.models.LoginModel;
import com.volskyioleh.test.presenter.base.BasePresenterAbs;

public class LoginPresenter extends BasePresenterAbs<LoginContract.View>
        implements LoginContract.Presenter {

    public LoginPresenter(ServerCommunicator serverCommunicator) {
        super(serverCommunicator);
    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void login(LoginModel loginModel) {
        getView().setProgressBar(View.VISIBLE);
        getServerCommunicator().login(loginModel).subscribe(response -> {
            getView().getResult(response.string());
            getView().setProgressBar(View.GONE);
        },throwable ->{
            getView().setProgressBar(View.GONE);
getView().showToast("Incorrect login or password");
        } );
//        getServerCommunicator().buildGetArticlesObservable()
//                .subscribe(response -> {
//                            insertArticles(response);
//                            getView().showList(getAllItems(0));
//                        },
//                        Throwable::printStackTrace);
        // getView().showList(ArticlesDB.getDatabase(App.getAppContext()).articlesDao().getFavoriteItems(true));
    }
}
