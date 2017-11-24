package com.zyl_android.tenderinfo.project.api;

import com.zyl_android.tenderinfo.project.bean.BannerBean;

import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by bibinet on 2017-11-6.
 */

public interface BannerApi {
    @Headers("Cache-Control: max-age=3600,public")
    @POST("appData/getHomeImg.json")
    Observable<BannerBean> getBannerData();
}
