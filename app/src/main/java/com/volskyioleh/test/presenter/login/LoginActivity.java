package com.volskyioleh.test.presenter.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.volskyioleh.test.App;
import com.volskyioleh.test.R;
import com.volskyioleh.test.api.models.LoginModel;
import com.volskyioleh.test.di.components.ApiComponent;
import com.volskyioleh.test.di.components.DaggerCommonComponent;
import com.volskyioleh.test.di.modules.AppApiModule;
import com.volskyioleh.test.di.modules.CommonModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    public static final String KEY = "auth_key";
    public static final String LOGIN = "login";
    @Inject
    LoginContract.Presenter mPresenter;

    @BindView(R.id.login)
    EditText login;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.loginBtn)
    TextView loginBtn;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        onInjectDependencies(App.get(this).getApiComponent());
        mPresenter.attachPresenter(this);
    }

    public void onInjectDependencies(ApiComponent apiComponent) {
        //     super.onInjectDependencies(apiComponent);
        DaggerCommonComponent.builder()
                .apiComponent(apiComponent)
                .commonModule(new CommonModule())
                .appApiModule(new AppApiModule())
                .build().inject(this);
    }

    @OnClick(R.id.loginBtn)
    public void login() {
        mPresenter.login(new LoginModel(login.getText().toString(), password.getText().toString()));
    }

    @Override
    public void handleInternetDisabled() {

    }

    @Override
    public void onBackPressed() {
        if (false){
            super.onBackPressed();
        }
    }


    @Override
    public void getResult(String key) {
        SharedPreferences sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(KEY, key);
        editor.putString(LOGIN, login.getText().toString());
        editor.commit();

        Log.d("ShowKey", key);

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",login.getText().toString());
        returnIntent.putExtra("result_key",key);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    @Override
    public void setProgressBar(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
