package com.fei.networdrequest.api;

public class ServiceBuild {

    /**
     * 用户服务
     */
    private static ApiServiceUrl mUserService;

    /**
     * 获取用户服务
     */
    public static synchronized ApiServiceUrl getUserService() {
        if (null == mUserService) {
            mUserService = BaseApi.createRetrofit().create(ApiServiceUrl.class);
        }
        return mUserService;
    }
}
