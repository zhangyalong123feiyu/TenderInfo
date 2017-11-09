package com.zyl_android.tenderinfo.mvp.presenter;

import com.zyl_android.tenderinfo.mvp.model.SearchActivityModel;
import com.zyl_android.tenderinfo.mvp.view.SearchActivityView;
import com.zyl_android.tenderinfo.project.bean.HotWordsBean;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by bibinet on 2017-11-9.
 */

public class SearchActivityPresenter {
    private SearchActivityModel searchActivityModel;
    private SearchActivityView searchActivityView;

    public SearchActivityPresenter(SearchActivityView searchActivityView) {
        this.searchActivityView = searchActivityView;
        this.searchActivityModel=new SearchActivityModel();
    }
    public void getHotWords(){
        searchActivityModel.getHotWords().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<HotWordsBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                searchActivityView.onGetHotWordsFailed(e.getMessage());
            }

            @Override
            public void onNext(HotWordsBean hotWordsBean) {
                searchActivityView.onGetHotWordsSucess(hotWordsBean.getItems());
            }
        });
    }
}
