package com.fei.networdrequest.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.fei.networdrequest.R;

import com.fei.networdrequest.bean.ChaptersBean;
import com.fei.networdrequest.bean.LoginBean;
import com.fei.networdrequest.moder.user.contrat.LoginContract;
import com.fei.networdrequest.moder.user.presenter.LoginPresenter;

import java.util.List;

import butterknife.OnClick;
import framework.base.mvp.base.activity.MVPBaseActivity;
import framework.base.utlis.L;
import framework.base.utlis.T;

public class LoginActivity extends MVPBaseActivity<LoginPresenter> implements LoginContract.View, View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private LoginPresenter mLoginPresenter;
    private TextView mTextView;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mTextView = findViewById(R.id.tv_title);
        mLoginPresenter = new LoginPresenter(this);
        initEvent();
    }

    private void initEvent() {
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void loginSuccess(LoginBean loginBean) {
        Toast.makeText(this, "登陆成功：" + loginBean.toString(), Toast.LENGTH_SHORT).show();
        mTextView.setText(loginBean.getNickname() + loginBean.getId());
        L.e("登陆成功：" + loginBean.toString());

    }

    @Override
    public void loginFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        L.e("登陆失敗：" + message);
    }


    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @OnClick({R.id.btn_login})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                T.showToast(this, "正在登陆");
                mLoginPresenter.login("ggzaigl1", "tmdligen");
                break;
        }
    }
}
