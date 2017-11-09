package com.zyl_android.tenderinfo.mvp.model;

import com.zyl_android.tenderinfo.project.api.SearchActivityApi;
import com.zyl_android.tenderinfo.project.bean.HotWordsBean;
import com.zyl_android.tenderinfo.project.utils.RetrofitUtil;

import rx.Observable;

/**
 * Created by bibinet on 2017-11-9.
 */

public class SearchActivityModel {
    public Observable<HotWordsBean> getHotWords(){
        SearchActivityApi searchActivityApi = RetrofitUtil.creatApi(SearchActivityApi.class);
        return searchActivityApi.getHotWords();
    }
}
