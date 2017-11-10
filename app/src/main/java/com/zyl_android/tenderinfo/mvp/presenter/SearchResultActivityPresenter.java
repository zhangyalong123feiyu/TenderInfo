package com.zyl_android.tenderinfo.mvp.presenter;

import com.zyl_android.tenderinfo.mvp.model.SearchResultAcitvityModel;
import com.zyl_android.tenderinfo.mvp.view.SearchResultActivityView;
import com.zyl_android.tenderinfo.project.bean.SearchResultBean;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by bibinet on 2017-11-9.
 */

public class SearchResultActivityPresenter {
    private SearchResultAcitvityModel searchResultAcitvityModel;
    private SearchResultActivityView searchResultActivityView;

    public SearchResultActivityPresenter(SearchResultActivityView searchResultActivityView) {
        this.searchResultActivityView = searchResultActivityView;
        this.searchResultAcitvityModel=new SearchResultAcitvityModel();
    }
    public void getSearchResult(String pageNumb,String content){
        searchResultAcitvityModel.searchHotWords(pageNumb,content).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new Observer<SearchResultBean>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        searchResultActivityView.onGetSearchResultFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(SearchResultBean searchResultBean) {
                        searchResultActivityView.onGetSearchResultSucess(searchResultBean.getItems());
                    }
                }
        );
    }
}
