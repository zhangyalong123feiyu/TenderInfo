package com.zyl_android.tenderinfo.project.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.mvp.presenter.TenderHistoryActivityPresenter;
import com.zyl_android.tenderinfo.mvp.view.TenderHelpHistoryActivityView;
import com.zyl_android.tenderinfo.project.adapter.TenderHistoryActivityAdapter;
import com.zyl_android.tenderinfo.project.application.Constants;
import com.zyl_android.tenderinfo.project.bean.DeleteHistoryBean;
import com.zyl_android.tenderinfo.project.bean.HelpTenderHistoryReusltBean;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseActivity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by bibinet on 2017-12-16.
 */

public class TenderHelpHistoryActivity extends BaseActivity implements TenderHelpHistoryActivityView,TenderHistoryActivityAdapter.DeleteHistory {
    @BindView(R.id.searchResultRcylerView)
    RecyclerView historyRecyclerView;
    @BindView(R.id.noDataView)
    ImageView noDataView;
    private TenderHistoryActivityAdapter adapter;
    private TenderHistoryActivityPresenter tenderHistoryActivityPresenter;

    @Override
    protected int getChildlayout() {
        return R.layout.activity_searchresult;
    }

    @Override
    protected void initView() {
        titleBackImage.setVisibility(View.VISIBLE);
        title.setText("投标历史");
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        getSmartRefreshLayout().setEnableRefresh(true);
        getSmartRefreshLayout().setEnableLoadmore(true);
        waitView.setVisibility(View.VISIBLE);
        waitView.start();
    }

    @Override
    protected void loadData(boolean isLoadMore) {
        super.loadData(isLoadMore);
        tenderHistoryActivityPresenter = new TenderHistoryActivityPresenter(this);
        tenderHistoryActivityPresenter.getTenderHistoryData(this, Constants.loginResultInfo.getUser().getUserId(), pageNumb);
    }

    @Override
    public void onLoadTenderHistoryDataSucess(List<HelpTenderHistoryReusltBean.ItemBean> helpHistoryInfo) {
        waitView.stop();
        waitView.setVisibility(View.GONE);
        if (adapter == null) {
            adapter = new TenderHistoryActivityAdapter(this);
            adapter.setOnDeleteHistoryData(this);
            historyRecyclerView.setAdapter(adapter);
        }
        if (isLoadMore) {
            if (helpHistoryInfo.size()==0) {
                getSmartRefreshLayout().setLoadmoreFinished(true);
            }
            adapter.addData(helpHistoryInfo);
            getSmartRefreshLayout().finishLoadmore();
        }else {
            adapter.refreshData(helpHistoryInfo);
            getSmartRefreshLayout().finishRefresh();
        }
    }

    @Override
    public void onLoadTenderHistoryDataFailed(String msg) {
            log("历史信息错误",msg);
    }

    @Override
    public void onDeleteHistorySucess(DeleteHistoryBean delteHistoryInfo) {
        toast("历史信息删除成功");
        loadData(false);
    }

    @Override
    public void onDeleteHistoryFailed(String msg) {

    }

    @Override
    public void onDeletHistory(int objectId) {
        tenderHistoryActivityPresenter.deleteHistoryData(this,String.valueOf(objectId));
    }
}
