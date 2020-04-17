package com.fei.networdrequest.api;

import android.text.TextUtils;
import android.util.Log;

import com.fei.networdrequest.MyApplication;
import com.fei.networdrequest.constan.Constant;

import net.ServerException;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import framework.base.biz.Interceptor.NetCacheInterceptor;
import framework.base.biz.Interceptor.OfflineCacheInterceptor;
import framework.base.biz.NetConfig;
import framework.base.mvp.BaseDataModel;
import framework.base.utlis.FileUtils;
import gson.DES3GsonConverterFactory;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class BaseApi {

    /**
     * 网络配置项
     */
    private static NetConfig mConfig = null;
    /**
     * mRetrofit
     */
    private Retrofit mRetrofit;
    /**
     * mClient
     */
    private OkHttpClient mClient;
    /**
     * 默认连接超时时间
     */
    private static final long DEFAULT_CONNECT_TIMEOUT_MILLS = 40 * 1000L;
    /**
     * 默认读取超时时间
     */
    private static final long DEFAULT_READ_TIMEOUT_MILLS = 40 * 1000L;
    /**
     * 实例
     **/
    private static BaseApi instance;

    private BaseApi() {
        mRetrofit = null;
        mClient = null;

    }


    /**
     * 创建 Retrofit
     *
     * @return Retrofit
     */
    public static synchronized Retrofit createRetrofit() {
        return getInstance().getRetrofit();
    }


    /**
     * 单例 获取
     *
     * @return BaseApi
     */
    private static synchronized BaseApi getInstance() {
        if (instance == null)
            instance = new BaseApi();
        return instance;
    }


    /**
     * 创建class
     *
     * @param service 服务class
     * @param <C>     类泛型
     * @return 泛型
     */
    public static <C> C get(Class<C> service) {
        return getInstance().getRetrofit().create(service);
    }


    /**
     * 注册配置
     *
     * @param config
     */
    public static void registerConfig(NetConfig config) {
        BaseApi.mConfig = config;
        //赋值为空
        instance = null;
    }


    /**
     * 获取Retrofit
     *
     * @return 获取Retrofit
     */
    private Retrofit getRetrofit() {
        if (mRetrofit == null) {
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(mConfig.configBaseUrl())//配置BaseUrl
                    .addConverterFactory(DES3GsonConverterFactory.create())//gson转换器
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getHttpClient());// 设置client
            mRetrofit = builder.build();
        }
        return mRetrofit;
    }


    /**
     * 统一线程处理
     *
     * @param <T> 泛型
     * @return
     */
    public static <T> FlowableTransformer<T, T> getScheduler() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 对结果进行预处理
     *
     * @param <T> 泛型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<BaseDataModel<T>, T> handleResult() {
        return new ObservableTransformer<BaseDataModel<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseDataModel<T>> upstream) {
                return upstream.flatMap(new Function<BaseDataModel<T>, ObservableSource<? extends T>>() {
                    @Override
                    public ObservableSource<? extends T> apply(BaseDataModel<T> baseDataModel) throws Exception {
                        if (baseDataModel.isSuccess()) {
                            return createData(baseDataModel.getData());
                        } else {
                            return Observable.error(new ServerException(baseDataModel.getErrorMsg(), baseDataModel.getErrorCode()));
                        }
                    }
                })      //指定的是上游发送事件的线程
                        .subscribeOn(Schedulers.io())
                        //指定的是下游接收事件的线程
                        .observeOn(AndroidSchedulers.mainThread());
//              多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效, 其余的会被忽略.
//              多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次.
            }
        };
    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> subscriber) throws Exception {
                try {
                    Log.e("net", "成功 _ onNext");
                    if (null == data) {
                        subscriber.onNext((T) new Object());
                    } else {
                        subscriber.onNext(data);
                    }
                    subscriber.onComplete();
                } catch (Exception e) {
                    Log.e("net", "异常 _ onError");
                    subscriber.onError(e);
                }
            }
        });
    }


    /**
     * 获取httpclient
     *
     * @return OkHttpClient
     */
    private OkHttpClient getHttpClient() {
        if (mClient == null) {

            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            //创建日志拦截器
            File httpCacheDirectory = new File(FileUtils.getDiskCacheDir(MyApplication.mContext), "okhttpCache");
            int cacheSize = 10 * 1024 * 1024; // 10 MiB
            Cache cache = new Cache(httpCacheDirectory, cacheSize);

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//设定日志级别

            builder.addNetworkInterceptor(new NetCacheInterceptor())
                    .addInterceptor(new OfflineCacheInterceptor())
                    .cache(cache)
                    .connectTimeout(Constant.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
                    .readTimeout(Constant.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
                    .writeTimeout(Constant.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
                    .protocols(Collections.singletonList(Protocol.HTTP_1_1));

            // 连接超时时间
//            builder.connectTimeout(mConfig.configConnectTimeoutMills()
//                    != 0 ? mConfig.configConnectTimeoutMills() : DEFAULT_CONNECT_TIMEOUT_MILLS, TimeUnit.MILLISECONDS);
//            // 读取超时时间
//            builder.readTimeout(mConfig.configReadTimeoutMills()
//                    != 0 ? mConfig.configReadTimeoutMills() : DEFAULT_READ_TIMEOUT_MILLS, TimeUnit.MILLISECONDS);

            // 拦截器
            Interceptor[] interceptors = mConfig.configInterceptors();
            if (interceptors != null && interceptors.length > 0) {
                for (Interceptor interceptor : interceptors) {
                    builder.addInterceptor(interceptor);
                }
            }
            if (mConfig.configLogEnable()) {//配置打印
                HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
                logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(logInterceptor);
            }

            mClient = builder.build();
        }
        return mClient;
    }

    protected Interceptor getHeader() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = null;

                //获取request
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Content-Type", "multipart/form-data;charse=UTF-8")
//                        .addHeader("Accept-Encoding", "gzip, deflate")//根据服务器要求添加（避免重复压缩乱码）
                        .addHeader("Connection", "keep-alive")
                        .addHeader("Accept", "application/json")
                        .addHeader("Cookie", "add cookies here")
                        .build();

                //获取request的创建者builder
                Request.Builder builder = request.newBuilder();

                //从request中获取headers，通过给定的键url_name
                List<String> headerValues = request.headers("url_name");
                if (headerValues != null && headerValues.size() > 0) {
                    //如果有这个header，先将配置的header删除，因为 此header仅用作app和okhttp之间使用
                    builder.removeHeader("url_name");

                    //匹配获得新的BaseUrl
                    String headerValue = headerValues.get(0);
                    HttpUrl newBaseUrl = null;
                    if ("user".equals(headerValue) && !TextUtils.isEmpty(Constant.custom_Url)) {
                        newBaseUrl = HttpUrl.parse(Constant.custom_Url);
                        //从request中获取原有的HttpUrl实例oldHttpUrl
                        HttpUrl oldHttpUrl = request.url();
                        //重建新的HttpUrl，修改需要修改的url部分
                        HttpUrl newFullUrl = oldHttpUrl
                                .newBuilder()
                                .scheme(newBaseUrl.scheme())
                                .host(newBaseUrl.host())
                                .port(newBaseUrl.port())
                                .build();

                        //重建这个request，通过builder.url(newFullUrl).build()；
                        //然后返回一个response至此结束修改
                        response = chain.proceed(builder.url(newFullUrl).build());
                    }
                }

                if (null == response) {
                    Request.Builder requestBuilder = request.newBuilder();

                    Request newRequest = requestBuilder.build();
                    response = chain.proceed(newRequest);
                }

                return response;
            }
        };
    }
}
