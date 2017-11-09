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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-11-9.
 */

public class SearchResultActivity extends BaseActivity implements SearchResultActivityView{
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;
    @BindView(R.id.searchResultRcylerView)
    RecyclerView searchResultRcylerView;
    private String content;
    private int pageNumb=1;
    private SearchResultActivityAdapter adapter;
    private SearchResultActivityPresenter searchResultActivityPresenter;

    @Override
    protected void onrefresh() {
        pageNumb=1;
        loadData();
    }

    @Override
    protected void onloadMore() {
        pageNumb++;
        loadData();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected int getChildlayout() {
        return R.layout.activity_searchresult;
    }

    @Override
    protected void initView() {
        title.setText("搜索结果");
        titleImageleft.setVisibility(View.VISIBLE);
        content = getIntent().getStringExtra("content");
        smartRefreshLayout.setEnableLoadmore(true);
        smartRefreshLayout.setEnabled(true);
        searchResultActivityPresenter=new SearchResultActivityPresenter(this);
    }

    @Override
    protected void loadData() {
        searchResultActivityPresenter.getSearchResult(String.valueOf(pageNumb),content);
    }

    @OnClick(R.id.title_imageleft)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onGetSearchResultSucess(List<SearchResultBean.ItemsBean> searchResultInfo) {
        searchResultRcylerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new SearchResultActivityAdapter(searchResultInfo,this);
        searchResultRcylerView.setAdapter(adapter);
        log("收索内容",searchResultInfo.size()+"");
    }

    @Override
    public void onGetSearchResultFailed(String msg) {
        log("搜索结果错误",msg);
    }
}
