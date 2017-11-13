package com.zyl_android.tenderinfo.project.api;

import com.zyl_android.tenderinfo.project.bean.SearchResultBean;

import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by bibinet on 2017-11-9.
 */

public interface SearchResultActivityApi {
    @Multipart
    @POST("appData/selectPage.json")
    Observable<SearchResultBean> doSearch(@Part("pageIndex") RequestBody pageNum, @Part("messageLike") RequestBody content);
}
