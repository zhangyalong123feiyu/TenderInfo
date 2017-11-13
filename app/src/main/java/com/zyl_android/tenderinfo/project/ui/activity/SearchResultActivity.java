package com.zyl_android.tenderinfo.project.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.mvp.presenter.SearchResultActivityPresenter;
import com.zyl_android.tenderinfo.mvp.view.SearchResultActivityView;
import com.zyl_android.tenderinfo.project.adapter.SearchResultActivityAdapter;
import com.zyl_android.tenderinfo.project.bean.SearchResultBean;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-11-9.
 */

public class SearchResultActivity extends BaseActivity implements SearchResultActivityView{
    @BindView(R.id.searchResultRcylerView)
    RecyclerView searchResultRcylerView;
    private String content;
    private int pageNumb=1;
    private SearchResultActivityAdapter adapter;
    private SearchResultActivityPresenter searchResultActivityPresenter;
    private List<SearchResultBean.ItemsBean> datas=new ArrayList<>();
    private boolean isloadmore;
    @Override//下拉刷新
    protected void onrefresh() {
        pageNumb=1;
        loadData(false);
    }

    @Override//上拉加载数据
    protected void onloadMore() {
        loadData(true);//加载数据
    }

    @Override
    protected int getChildlayout() {
        return R.layout.activity_searchresult;
    }

    @Override
    protected void initView() {
        title.setText("搜索结果");
        titleBackImage.setVisibility(View.VISIBLE);
        content = getIntent().getStringExtra("content");
        getSmartRefreshLayout().setEnableLoadmore(true);
        getSmartRefreshLayout().setEnableRefresh(true);
        searchResultActivityPresenter=new SearchResultActivityPresenter(this);
        searchResultRcylerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override//数据加载
    protected void loadData(boolean isLoadMore) {
        isloadmore=isLoadMore;
        if (isLoadMore) {
            pageNumb++;
        }else {
            pageNumb=1;
        }
        searchResultActivityPresenter.getSearchResult(String.valueOf(pageNumb),content);
    }

    @OnClick(R.id.title_imageleft)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onGetSearchResultSucess(List<SearchResultBean.ItemsBean> searchResultInfo) {
        if (isloadmore) {
            if (searchResultInfo.size()==0) {//判断是否完成加载
                getSmartRefreshLayout().finishLoadmore();
            }
            getSmartRefreshLayout().finishLoadmore();//加载完成
            datas.addAll(searchResultInfo);
        		}else {
            getSmartRefreshLayout().finishRefresh();//刷新完成
            datas.clear();
            datas.addAll(searchResultInfo);
        }
        if (adapter==null) {
            adapter=new SearchResultActivityAdapter(datas,this);
            searchResultRcylerView.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onGetSearchResultFailed(String msg) {
        log("搜索结果错误",msg);
    }
}
