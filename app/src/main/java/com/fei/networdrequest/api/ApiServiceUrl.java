package com.fei.networdrequest.api;

import com.fei.networdrequest.bean.ChaptersBean;
import com.fei.networdrequest.bean.LoginBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @Query 主要用于Get请求数据，用于拼接在拼接在Url路径后面的查询参数，一个@Query相当于拼接一个参数，多个参数中间用，隔开
 * @QueryMap 主要的效果等同于多个@Query参数拼接，主要也用于Get请求网络数据。
 * @Body 非表单请求体，是结合post请求的
 * @Field 表单字段，@Field的用法类似于@Query，就不在重复列举了，主要不同的是@Field主要用于Post请求数据。
 * @FieldMap 表单字段，@FieldMap的用法类似于@QueryMap。两者主要区别是：如果请求为post实现，那么最好传递参数时使用@Field、@FieldMap和@FormUrlEncoded。因为@Query和或QueryMap都是将参数拼接在url后面的，而@Field或@FieldMap传递的参数时放在请求体的。
 * @Part 表单字段，与 PartMap 配合，适合文件上传情况
 * @PartMap 表单字段，与 Part 配合，适合文件上传情况；默认接受 Map<String, RequestBody> 类型，非 RequestBody 会通过 Converter 转换
 * ————————————————
 * 原文链接：https://blog.csdn.net/ouyang_peng/article/details/82879221
 */
public interface ApiServiceUrl {

    @FormUrlEncoded
    @POST("user/login")
    Flowable<LoginBean> login(@Field("username") String username, @Field("password") String password);

    @GET("wxarticle/chapters/json")
    Flowable<List<ChaptersBean>> getChapters();

    /**
     * 获取公众号列表
     *
     * @return
     */
//    @Headers({"url_name:user"})
//    @GET("wxarticle/chapters/json")
//    Observable<BeanModule<List<OfficialAccountBean>>> getChapters();


//    @FormUrlEncoded
//    @Headers({"url_name:user"})
//    @POST("user/login")
//    Observable<BaseDataModel<LoginBean>> getLogin(@FieldMap Map<String, Object> options);

}
