package com.fei.networdrequest.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.fei.networdrequest.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import framework.base.activity.BaseActivity;
import framework.base.utlis.L;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

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
        List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();
        permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "", R.drawable.permission_ic_storage));
        permissionItems.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "", R.drawable.permission_ic_storage));
        permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
        permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "定位", R.drawable.permission_ic_location));
        HiPermission.create(MainActivity.this)
                .permissions(permissionItems)
                .checkMutiPermission(new PermissionCallback() {
                    public void onClose() {
                        L.i("onClose");
                        showToast("用户关闭权限申请");
                    }

                    @Override
                    public void onFinish() {
                    }

                    @Override
                    public void onDeny(String permission, int position) {
                        L.i("onDeny");
                    }

                    @Override
                    public void onGuarantee(String permission, int position) {
                        L.i("onGuarantee");
                    }
                });

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
