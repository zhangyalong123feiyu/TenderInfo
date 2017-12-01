package com.zyl_android.tenderinfo.project.api;

import com.zyl_android.tenderinfo.project.bean.BannerBean;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by bibinet on 2017-11-6.
 */

public interface BannerApi {
    @Headers("Cache-Control:public,max-age")
    @GET("appData/getHomeImg.json")
    Observable<BannerBean> getBannerData();
    //下载banner
    @GET()
    Observable<ResponseBody> downLoadBanner(@Url String url);
}
