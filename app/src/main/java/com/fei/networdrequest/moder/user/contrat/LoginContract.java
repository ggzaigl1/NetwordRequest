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
        /**
         * 登陆成功
         *
         * @param loginBean 成功回调的信息
         */
        void loginSuccess(LoginBean loginBean);

        /**
         * 登陆失败
         *
         * @param message 失败消息
         */
        void loginFailure(String message);
    }

    abstract class Presenter extends BasePresenter {
        /**
         * 持有View层
         */
        protected View view;

        public Presenter(View view) {
            super(view);
            this.view = view;
        }

        /**
         * 登陆
         *
         * @param userName 用户名
         * @param password 密码
         */
        public abstract void login(String userName, String password);
    }
}