package com.zyl_android.tenderinfo.project.api;

import com.zyl_android.tenderinfo.project.bean.HotWordsBean;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by bibinet on 2017-11-9.
 */

public interface SearchActivityApi{
    @POST("appKeywords/selectKeywords.json")
    Observable<HotWordsBean> getHotWords();
}
