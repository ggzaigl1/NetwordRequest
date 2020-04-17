package com.fei.networdrequest.moder.user.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.fei.networdrequest.api.ApiGithub;

import com.fei.networdrequest.api.ServiceBuild;
import com.fei.networdrequest.bean.ChaptersBean;

import framework.base.biz.ApiSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import com.fei.networdrequest.moder.user.contrat.ChaptersContract;

import java.util.List;

/**
 * @author Administrator
 */
public class ChaptersPresenter extends ChaptersContract.Presenter {

    private static final String TAG = "ChaptersPresenter";

    public ChaptersPresenter(ChaptersContract.View view) {
        super(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void chapters() {
        // 取消上次请求
        unDisposable();
        // 开始请求
//        mDisposable = ApiGithub.getInstance()
//                .gitHubService()
//                .getChapters()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new ApiSubscriber<List<ChaptersBean>>() {
//                    @Override
//                    public void onNext(List<ChaptersBean> chaptersBeans) {
//                        Log.e(TAG, "onNext: " + chaptersBeans);
//                        view.getReportSuccess(chaptersBeans);
//                    }
//                });

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
