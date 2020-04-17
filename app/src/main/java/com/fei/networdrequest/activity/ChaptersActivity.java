package com.fei.networdrequest.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
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
        getPresenter().chapters();
        initEvent();
    }

    private void initEvent() {
    }

    @OnClick({R.id.mSendRequestBtn})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mSendRequestBtn:

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mChaptersPresenter.unDisposable();
    }

    @Override
    protected ChaptersPresenter createPresenter() {
        return new ChaptersPresenter(this);
    }

    @Override
    public void getReportSuccess(List<ChaptersBean> chaptersBean) {
        mAdapter.setNewData(chaptersBean);
        Log.e(TAG, "请求成功，repoCount:" + chaptersBean.size());
    }
}
