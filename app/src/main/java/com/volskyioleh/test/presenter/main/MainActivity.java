package com.volskyioleh.test.presenter.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.volskyioleh.test.App;
import com.volskyioleh.test.R;
import com.volskyioleh.test.api.models.ItemModel;
import com.volskyioleh.test.di.components.ApiComponent;
import com.volskyioleh.test.di.components.DaggerCommonComponent;
import com.volskyioleh.test.di.modules.AppApiModule;
import com.volskyioleh.test.di.modules.CommonModule;
import com.volskyioleh.test.presenter.ListAdapter;
import com.volskyioleh.test.presenter.login.LoginActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Inject
    MainContract.Presenter mPresenter;


    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.recyclerViewFavorite)
    RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private SharedPreferences mSaredPref;

    private static final String DATE_FORM = "1479860023";
    private static final String DATE_TO = "1480066860";
    private String mKey;
    private String mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        onInjectDependencies(App.get(this).getApiComponent());
        mPresenter.attachPresenter(this);
        mSaredPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        mKey =  mSaredPref.getString(LoginActivity.KEY, "null");
        mLogin =  mSaredPref.getString(LoginActivity.LOGIN, "");

        Calendar calendar = Calendar.getInstance();
        long currentDate = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_MONTH, -3); // get last 3 month period
        long fromDate = calendar.getTimeInMillis();

        if (mKey.equals("null")) {
           startLogin();
        } else {
           createList();
        }

    }

    public void startLogin(){
        Intent i = new Intent(this, LoginActivity.class);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
               mLogin =data.getStringExtra("result");
               mKey =data.getStringExtra("result_key");
                createList();
            }
        }
    }

    private void createList() {
        mPresenter.getList(mLogin, getPairsList(),DATE_FORM, DATE_TO, mKey); //api worked only with this dates
        mAdapter = new ListAdapter(new ArrayList<>(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        recyclerView.setAdapter(mAdapter);
    }


    public void onInjectDependencies(ApiComponent apiComponent) {
        //     super.onInjectDependencies(apiComponent);
        DaggerCommonComponent.builder()
                .apiComponent(apiComponent)
                .commonModule(new CommonModule())
                .appApiModule(new AppApiModule())
                .build().inject(this);
    }

    @Override
    public void handleInternetDisabled() {

    }

    @Override
    public void setProgressBar(int visibility) {
        progressBar.setVisibility(visibility);
    }

    private String getPairsList() {
        String pairs = "EURUSD,GBPUSD,USDJPY,USDCHF,USDCAD,AUDUSD,NZDUSD";

        return pairs;
    }


    @Override
    public void showList(List<ItemModel> itemModelList) {

            mAdapter.setItems(itemModelList);

    }

    @Override
    public void out() {
        SharedPreferences.Editor editor = mSaredPref.edit();
        editor.putString(LoginActivity.KEY, "null");
        editor.putString(LoginActivity.LOGIN,"");
        editor.commit();

        startLogin();
    }
}
