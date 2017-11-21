package com.zyl_android.tenderinfo.project.utils;

import android.content.Context;
import android.os.Environment;

import com.zyl_android.generalutils.NetworkUtils;
import com.zyl_android.tenderinfo.project.application.Constants;
import com.zyl_android.tenderinfo.project.application.TenderApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bibinet on 2017-11-7.
 */

public class RetrofitUtil {
    private static Retrofit retrofitInstanceHttp=null;
    private static Retrofit retrofitInstanceHttps=null;
    private RetrofitUtil(){
    }
    public static Retrofit getHttpRetrofit(){
        if (retrofitInstanceHttp==null) {
            synchronized (Retrofit.class){
                if (retrofitInstanceHttp==null) {//双重锁，仅第一次调用时使用
                    OkHttpClient okHttpClient=new OkHttpClient();
                    retrofitInstanceHttp=new Retrofit.Builder().client(okHttpClient).baseUrl(Constants.baseUrl_pis).
                            addCallAdapterFactory(RxJavaCallAdapterFactory.create()).//使其支持observable,默认支持call<String>有DefaultFactory支持
                            addConverterFactory(GsonConverterFactory.create()).build();
                }
            }
        }
        return retrofitInstanceHttp;
    }
    public static Retrofit getHttpsRetrofit(){
        if (retrofitInstanceHttps==null) {
            synchronized (Retrofit.class){
                if (retrofitInstanceHttps==null) {//双重锁，仅第一次调用时使用
                    OkHttpClient okHttpClient=new OkHttpClient();
                    retrofitInstanceHttps=new Retrofit.Builder().client(okHttpClient).baseUrl(Constants.baseUrl_iip).
                            addCallAdapterFactory(RxJavaCallAdapterFactory.create()).//使其支持observable,默认支持call<String>有DefaultFactory支持
                            addConverterFactory(GsonConverterFactory.create()).build();
                }
            }
        }
        return retrofitInstanceHttps;
    }
    //创建API类
    public static <T> T creatHttpsApi(Class<T> tClass){
       return getHttpsRetrofit().create(tClass);
    }
    //创建API类
    public static <T> T creatHttpApi(Class<T> tClass){
       return getHttpRetrofit().create(tClass);
    }
    //创建okhttpclient
    public static OkHttpClient getOkhttpClient(){
        return new OkHttpClient.Builder().addNetworkInterceptor(getBuidNetworkInterceptor()).
                cache(getCacheDirectory()).//添加缓存目录
                connectTimeout(15, TimeUnit.MILLISECONDS).
                retryOnConnectionFailure(true).
                readTimeout(15,TimeUnit.MILLISECONDS).//添加读取超时时间
                writeTimeout(15,TimeUnit.MILLISECONDS).build();
    }

    private static Cache getCacheDirectory() {
        File cacheFile=new File(CacheUtil.getCachePath(),"CacheUtil");
        Cache cacheSize=new Cache(cacheFile,1024*1024*20);
        return cacheSize;
    }
    //创建缓存拦截器
    private static Interceptor getBuidNetworkInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                //无网络时,重缓存中读取
                if (!NetworkUtils.isNetworkAvailable(TenderApplication.context)) {
                        request=request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                		}
                		//获取响应体，在线时缓存5分钟，离线时缓存4周
                		Response response=chain.proceed(request);
                if (NetworkUtils.isNetworkAvailable(TenderApplication.context)) {
                			int maxTime=300;
                    response.newBuilder().header("Cache-Control", "public, max-age=" + maxTime)
                            .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                		}else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
    }
}
