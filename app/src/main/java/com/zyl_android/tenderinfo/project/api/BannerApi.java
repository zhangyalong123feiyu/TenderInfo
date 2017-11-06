package com.zyl_android.tenderinfo.project.api;

import com.zyl_android.tenderinfo.project.bean.BannerBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by bibinet on 2017-11-6.
 */

public interface BannerApi {
    @POST("appData/getHomeImg.json")
    Observable<BannerBean> getBannerData();
}
