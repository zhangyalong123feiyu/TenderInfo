package com.zyl_android.tenderinfo.mvp.model;

import com.zyl_android.tenderinfo.project.api.SearchResultActivityApi;
import com.zyl_android.tenderinfo.project.bean.SearchResultBean;
import com.zyl_android.tenderinfo.project.utils.RetrofitUtil;

import rx.Observable;

/**
 * Created by bibinet on 2017-11-9.
 */

public class SearchResultAcitvityModel {
    public Observable<SearchResultBean> searchHotWords(String pageNumb,String content){
        SearchResultActivityApi searchResultInofApi = RetrofitUtil.creatApi(SearchResultActivityApi.class);
        return searchResultInofApi.doSearch(pageNumb,content);
    }
}
