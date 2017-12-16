package com.zyl_android.tenderinfo.project.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.mvp.presenter.SearchResultActivityPresenter;
import com.zyl_android.tenderinfo.mvp.view.SearchResultActivityView;
import com.zyl_android.tenderinfo.project.adapter.SearchResultActivityAdapter;
import com.zyl_android.tenderinfo.project.bean.SearchResultBean;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-11-9.
 */

public class SearchResultActivity extends BaseActivity implements SearchResultActivityView{
    @BindView(R.id.searchResultRcylerView)
    RecyclerView searchResultRcylerView;
    @BindView(R.id.noDataView)
    ImageView noDataView;
    private String content;
    private SearchResultActivityAdapter adapter;
    private SearchResultActivityPresenter searchResultActivityPresenter;
    private List<SearchResultBean.ItemsBean> datas=new ArrayList<>();

    @Override
    protected int getChildlayout() {
        return R.layout.activity_searchresult;
    }

    @Override
    protected void initView() {
        title.setText("搜索结果");
        titleBackImage.setVisibility(View.VISIBLE);
        waitView.setVisibility(View.VISIBLE);
        waitView.start();
        content = getIntent().getStringExtra("content");
        getSmartRefreshLayout().setEnableLoadmore(true);
        getSmartRefreshLayout().setEnableRefresh(true);
        searchResultActivityPresenter=new SearchResultActivityPresenter(this);
        searchResultRcylerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override//数据加载
    protected void loadData(boolean isLoadMore) {
        super.loadData(isLoadMore);
        searchResultActivityPresenter.getSearchResult(String.valueOf(pageNumb),content);
    }

    @OnClick(R.id.title_imageleft)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onGetSearchResultSucess(List<SearchResultBean.ItemsBean> searchResultInfo) {
        waitView.stop();
        waitView.setVisibility(View.GONE);
        if (adapter==null) {
            adapter=new SearchResultActivityAdapter(this);
            searchResultRcylerView.setAdapter(adapter);
        }
        if (isLoadMore) {
            noDataViewisShow(searchResultInfo);
            if (searchResultInfo.size()==0) {//判断是否完成加载
                getSmartRefreshLayout().setLoadmoreFinished(true);
            }
            getSmartRefreshLayout().finishLoadmore();//加载完成
            adapter.addData(searchResultInfo);
        		}else {
            noDataViewisShow(searchResultInfo);//判断是否显示view
            getSmartRefreshLayout().finishRefresh();//刷新完成
           adapter.refreshData(searchResultInfo);
        }
    }

    private void noDataViewisShow(List<SearchResultBean.ItemsBean> searchResultInfo) {
        if (searchResultInfo.size()>0) {
            noDataView.setVisibility(View.GONE);
        		}else {
            noDataView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onGetSearchResultFailed(String msg) {
        log("搜索结果错误",msg);
    }
}
