package com.volskyioleh.test.presenter.main;

import android.view.View;

import com.volskyioleh.test.api.ServerCommunicator;
import com.volskyioleh.test.presenter.base.BasePresenterAbs;

public class MainPresenter extends BasePresenterAbs<MainContract.View>
        implements MainContract.Presenter {

    public MainPresenter(ServerCommunicator serverCommunicator) {
        super(serverCommunicator);
    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void getList(String login, String pairs, String from, String to, String passkey) {
        getView().setProgressBar(View.VISIBLE);
        getServerCommunicator().buildGetListObservable(login, pairs, from, to, passkey).subscribe(response -> {
            getView().showList(response);
            getView().setProgressBar(View.GONE);
//            getView().setProgressBar(View.GONE);
        },throwable ->{
          if(  throwable.getMessage().contains("Forbidden")){
              getView().setProgressBar(View.GONE);
             getView().out();
          };
//            getView().setProgressBar(View.GONE);
//            getView().showToast("Incorrect login or password");
        });
//        getServerCommunicator().login().subscribe(response -> {
////            getView().getResult(response.string());
////            getView().setProgressBar(View.GONE);
//        },throwable ->{
////            getView().setProgressBar(View.GONE);
////            getView().showToast("Incorrect login or password");
//        } );
    }
}
