package com.fei.networdrequest.moder.user.contrat;

import com.fei.networdrequest.bean.ChaptersBean;
import com.fei.networdrequest.bean.LoginBean;

import java.util.List;

import framework.base.mvp.presenter.BasePresenter;
import framework.base.mvp.view.BaseView;

/**
 * 登陆合约类
 *
 * @author Administrator
 */
public interface LoginContract {

    interface View extends BaseView {

        void loginSuccess(LoginBean loginBean);

        void loginFailure(String message);
    }

    abstract class Presenter extends BasePresenter {

        protected View view;

        public Presenter(View view) {
            super(view);
            this.view = view;
        }

        public abstract void login(String userName, String password);
    }
}