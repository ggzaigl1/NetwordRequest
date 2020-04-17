package com.fei.networdrequest.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fei.networdrequest.R;
import com.fei.networdrequest.adapter.OfficialAccountAdapter;

import com.fei.networdrequest.bean.ChaptersBean;

import com.fei.networdrequest.moder.user.contrat.ChaptersContract;
import com.fei.networdrequest.moder.user.presenter.ChaptersPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import framework.base.mvp.base.activity.MVPBaseActivity;
import framework.base.utlis.T;


public class ChaptersActivity extends MVPBaseActivity<ChaptersPresenter> implements ChaptersContract.View, View.OnClickListener {


    private static final String TAG = "ChaptersActivity";
    private ChaptersPresenter mChaptersPresenter;
    private OfficialAccountAdapter mAdapter;

    @BindView(R.id.rv_get)
    RecyclerView mRecyclerView;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_chapters;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mChaptersPresenter = new ChaptersPresenter(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new OfficialAccountAdapter(new ArrayList<ChaptersBean>());
        mRecyclerView.setAdapter(mAdapter);

        initEvent();
    }

    private void initEvent() {
    }

    @OnClick({R.id.mSendRequestBtn})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mSendRequestBtn:
                getPresenter().chapters();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mChaptersPresenter.unDisposable();
    }

    @Override
    public void onDialogCancelListener(AlertDialog dialog) {
        mChaptersPresenter.unDisposable();
        T.showToast(this, "取消登陆");
    }

    @Override
    protected ChaptersPresenter createPresenter() {
        return new ChaptersPresenter(this);
    }

    @Override
    public void getReportSuccess(List<ChaptersBean> chaptersBean) {
        T.showToast(this, R.string.success + chaptersBean.toString());
        mAdapter.setNewData(chaptersBean);
        Log.e(TAG, "请求成功，repoCount:" + chaptersBean.size());
    }


}
