package com.fei.networdrequest.moder.user.presenter;

import android.util.Log;

import com.fei.networdrequest.api.ServiceBuild;
import com.fei.networdrequest.bean.LoginBean;
import com.fei.networdrequest.moder.user.contrat.LoginContract;

import framework.base.biz.ApiSubscriber;

/**
 * @author Administrator
 */
public class LoginPresenter extends LoginContract.Presenter {

    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void login(String userName, String password) {
        unDisposable();
        // 开始请求
        mDisposable = ServiceBuild.getUserService()
                .login(userName,password)
                .compose(getScheduler())
                .subscribeWith(new ApiSubscriber<LoginBean>() {
                    @Override
                    public void onNext(LoginBean dataBean) {
                        Log.e(TAG, "onNext: " + dataBean);
                        view.loginSuccess(dataBean);
                    }
                });
    }
}
