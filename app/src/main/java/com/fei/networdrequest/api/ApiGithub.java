package com.fei.networdrequest.api;

import com.fei.networdrequest.constan.Constant;

import gson.DES3GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class ApiGithub {

    private static ApiGithub mApiGithub = null;

    private ApiServiceUrl mApiServiceUrl = null;

    private ApiGithub() {
    }

    public static ApiGithub getInstance() {
        synchronized (ApiGithub.class) {
            if (mApiGithub == null) {
                mApiGithub = new ApiGithub();
            }
        }
        return mApiGithub;
    }

    /**
     * 获取GitHub服务
     * @return
     */
    public ApiServiceUrl gitHubService() {
        synchronized (ApiGithub.class) {
            if (mApiServiceUrl == null) {
                mApiServiceUrl = new Retrofit.Builder()
                        .baseUrl(Constant.Url)
                        .addConverterFactory(DES3GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                        .create(ApiServiceUrl.class);
            }
        }
        return mApiServiceUrl;
    }
}
