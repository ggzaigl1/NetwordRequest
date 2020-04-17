package com.fei.networdrequest.moder.user.contrat;

import com.fei.networdrequest.bean.ChaptersBean;

import java.util.List;

import framework.base.mvp.presenter.BasePresenter;
import framework.base.mvp.view.BaseView;

/**
 * 登陆合约类
 * @author Administrator
 */
public interface ChaptersContract {

    interface View  extends BaseView {
        void getReportSuccess(List<ChaptersBean> chaptersBean);

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

        public abstract void chapters();
    }
}