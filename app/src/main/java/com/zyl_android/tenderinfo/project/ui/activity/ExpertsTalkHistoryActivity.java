package com.zyl_android.tenderinfo.project.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.mvp.presenter.ExpertsTalkHistoryPresenter;
import com.zyl_android.tenderinfo.mvp.view.ExpertsTalkHistoryActivityView;
import com.zyl_android.tenderinfo.project.adapter.ExpertsTalkHistoryAdapter;
import com.zyl_android.tenderinfo.project.application.Constants;
import com.zyl_android.tenderinfo.project.bean.ExpertsAskAnswerResultBean;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bibinet on 2017-12-15.
 * 使用okhttp请求，测试使用
 */

public class ExpertsTalkHistoryActivity extends BaseActivity implements ExpertsTalkHistoryActivityView{
    @BindView(R.id.searchResultRcylerView)
    RecyclerView historyRecyclerView;
    @BindView(R.id.noDataView)
    ImageView noDataView;
    private ExpertsTalkHistoryAdapter adapter;

    @Override
    protected int getChildlayout() {
        return R.layout.activity_searchresult;
    }

    @Override
    protected void initView() {
        title.setText("专家问答历史页面");
        titleBackImage.setVisibility(View.VISIBLE);
        getSmartRefreshLayout().setEnableRefresh(true);
        getSmartRefreshLayout().setEnableLoadmore(true);
        waitView.setVisibility(View.VISIBLE);
        waitView.start();
    }

    @Override
    protected void loadData(boolean isLoadMore) {
        super.loadData(isLoadMore);
        ExpertsTalkHistoryPresenter expertsTalkHistoryPresenter=new ExpertsTalkHistoryPresenter(this);
        expertsTalkHistoryPresenter.getHistoryData(this, Constants.loginResultInfo.getUser().getUserId(),pageNumb);
    }

    @Override
    public void onLodaTalkHistorySucess(List<ExpertsAskAnswerResultBean.ItemsBean> answerinfo) {
        waitView.stop();
        waitView.setVisibility(View.GONE);
        if (adapter==null) {
            adapter=new ExpertsTalkHistoryAdapter(this);
            historyRecyclerView.setAdapter(adapter);
        }
        noDataViewisShow(answerinfo);
        if (isLoadMore) {
            if (answerinfo.size()==0) {
                getSmartRefreshLayout().setLoadmoreFinished(true);
            }
            adapter.addData(answerinfo);
            getSmartRefreshLayout().finishLoadmore();
        }else {
            adapter.refreshData(answerinfo);
            getSmartRefreshLayout().finishRefresh();
        }
    }

    @Override
    public void onLodaTalkHistoryFailed(String msg) {
        log("历史数据加载出错",msg);
    }

    private boolean noDataViewisShow(List<ExpertsAskAnswerResultBean.ItemsBean> ansewerInfo) {
        if (ansewerInfo.size()>0) {
            noDataView.setVisibility(View.GONE);
            return true;
        }else {
            noDataView.setVisibility(View.VISIBLE);
            return false;
        }
    }

}
