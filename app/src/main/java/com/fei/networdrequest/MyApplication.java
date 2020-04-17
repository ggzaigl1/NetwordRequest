package com.fei.networdrequest;

import android.app.Application;
import android.content.Context;

import com.fei.networdrequest.api.BaseApi;
import com.fei.networdrequest.constan.Constant;

import framework.base.biz.NetConfig;
import framework.base.utlis.L;
import okhttp3.Interceptor;

public class MyApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        L.init(true);
        BaseApi.registerConfig(new NetConfig() {
            @Override
            public String configBaseUrl() {
                return Constant.Url;
            }

            @Override
            public Interceptor[] configInterceptors() {
                return new Interceptor[0];
            }

            @Override
            public long configConnectTimeoutMills() {
                return 45 * 1000;
            }

            @Override
            public long configReadTimeoutMills() {
                return 45 * 1000;
            }

            @Override
            public boolean configLogEnable() {
                return true;
            }
        });
    }
}
