package com.zyl_android.tenderinfo.project.utils;

import android.content.Context;
import android.os.Environment;
import android.view.animation.BaseInterpolator;

import com.zyl_android.generalutils.NetworkUtils;
import com.zyl_android.tenderinfo.project.application.CachePath;
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
                    retrofitInstanceHttp=new Retrofit.Builder().client(getOkhttpClient()).baseUrl(Constants.baseUrl_pis).
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
                    retrofitInstanceHttps=new Retrofit.Builder().client(getOkhttpClient()).baseUrl(Constants.baseUrl_iip).
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
        /**
         * 获取缓存
         */
        Interceptor baseInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtils.isNetworkAvailable(TenderApplication.context)) {
                    /**
                     * 离线缓存控制  总的缓存时间=在线缓存时间+设置离线缓存时间
                     */
                    int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周,单位:秒
                    CacheControl tempCacheControl = new CacheControl.Builder()
                            .onlyIfCached()
                            .maxStale(maxStale, TimeUnit.SECONDS)
                            .build();
                    request = request.newBuilder()
                            .cacheControl(tempCacheControl)
                            .build();
                }
                return chain.proceed(request);
            }
        };
        //只有 网络拦截器环节 才会写入缓存写入缓存,在有网络的时候 设置缓存时间
        Interceptor rewriteCacheControlInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response originalResponse = chain.proceed(request);
                int maxAge = 1 * 60; // 在线缓存在1分钟内可读取 单位:秒
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            }
        };
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(getCacheDirectory())
                .addInterceptor(baseInterceptor)
                .addNetworkInterceptor(rewriteCacheControlInterceptor)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
        return  client;
    }

    private static Cache getCacheDirectory() {
        File cacheFile=new File(CachePath.HTTPCACHE_PATH,"CacheUtil");
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
