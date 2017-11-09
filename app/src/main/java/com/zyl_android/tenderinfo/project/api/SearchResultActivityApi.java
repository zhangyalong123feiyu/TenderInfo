package com.zyl_android.tenderinfo.project.api;

import com.zyl_android.tenderinfo.project.bean.SearchResultBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by bibinet on 2017-11-9.
 */

public interface SearchResultActivityApi {
    @POST("appData/selectPage.json")
    Observable<SearchResultBean> doSearch(@Query("pageIndex") String pageNum,@Query("messageLike") String content);
}
