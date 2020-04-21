package com.fei.networdrequest.moder.user.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.fei.networdrequest.api.ServiceBuild;
import com.fei.networdrequest.bean.ChaptersBean;
import com.fei.networdrequest.bean.GanBean;
import com.fei.networdrequest.moder.user.contrat.ChaptersContract;

import java.util.List;

import framework.base.biz.ApiSubscriber;

/**
 * @author Administrator
 */
public class ChaptersPresenter extends ChaptersContract.Presenter {

    public ChaptersPresenter(ChaptersContract.View view) {
        super(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void chapters() {
        // 取消上次请求
        unDisposable();
        // 开始请求
        mDisposable = ServiceBuild
                .getUserService()
                .getChapters()
                .compose(getScheduler())
                .subscribeWith(new ApiSubscriber<List<ChaptersBean>>() {
                    @Override
                    public void onNext(List<ChaptersBean> dataBean) {
                        Log.e(TAG, "onNext: " + dataBean);
                        view.getReportSuccess(dataBean);
                    }
                });
    }
}
