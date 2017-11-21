package com.zyl_android.tenderinfo.mvp.model;

import com.zyl_android.tenderinfo.project.api.SearchResultActivityApi;
import com.zyl_android.tenderinfo.project.application.Constants;
import com.zyl_android.tenderinfo.project.bean.SearchResultBean;
import com.zyl_android.tenderinfo.project.utils.RetrofitUtil;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by bibinet on 2017-11-9.
 */

public class SearchResultAcitvityModel {
    public Observable<SearchResultBean> searchHotWords(String pageNumb,String content){
        SearchResultActivityApi searchResultInofApi = RetrofitUtil.creatHttpApi(SearchResultActivityApi.class);
        RequestBody requestBodyKey=RequestBody.create(MediaType.parse("text/plain"),pageNumb);
        RequestBody requestBodyValue=RequestBody.create(MediaType.parse("text/plain"),content);
        return searchResultInofApi.doSearch(requestBodyKey,requestBodyValue);
    }
}
