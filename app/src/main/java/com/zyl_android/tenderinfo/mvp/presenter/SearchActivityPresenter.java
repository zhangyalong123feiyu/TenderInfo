package com.zyl_android.tenderinfo.mvp.presenter;

import com.zyl_android.tenderinfo.mvp.model.SearchActivityModel;
import com.zyl_android.tenderinfo.mvp.view.SearchActivityView;
import com.zyl_android.tenderinfo.project.bean.HotWordsBean;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
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
        searchActivityModel.getHotWords().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<HotWordsBean>() {
            @Override
            public void call(HotWordsBean hotWordsBean) {
                searchActivityView.onGetHotWordsSucess(hotWordsBean.getItems());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                searchActivityView.onGetHotWordsFailed(throwable.getMessage());
            }
        });
    }
}
