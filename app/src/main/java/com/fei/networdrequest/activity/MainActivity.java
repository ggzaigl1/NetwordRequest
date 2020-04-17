package com.fei.networdrequest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.fei.networdrequest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import framework.base.activity.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_test)
    TextView tv_test;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        getSwipeBackLayout().setEnableGesture(false);
        tv_test.setText("test_one");
    }

    @OnClick({R.id.btn_post, R.id.bt_get})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_post:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            case R.id.bt_get:
                startActivity(new Intent(MainActivity.this, ChaptersActivity.class));
                break;
            default:
                break;
        }
    }
}
