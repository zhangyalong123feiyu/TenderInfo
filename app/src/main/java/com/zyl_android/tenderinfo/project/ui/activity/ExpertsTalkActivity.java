package com.zyl_android.tenderinfo.project.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.zyl_android.generalutils.NetworkUtils;
import com.zyl_android.generalutils.PhoneNumberUtils;
import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.mvp.presenter.ExpertsTalkActivityPresenter;
import com.zyl_android.tenderinfo.mvp.view.ExpertsTalkActivityView;
import com.zyl_android.tenderinfo.project.adapter.ExpertsTalkActivityAdapter;
import com.zyl_android.tenderinfo.project.bean.ExpertsDataBean;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-11-25.
 */

public class ExpertsTalkActivity extends BaseActivity implements ExpertsTalkActivityView {
    @BindView(R.id.expertsRecyler)
    RecyclerView expertsRecyler;
    @BindView(R.id.netErrorView)
    AppCompatImageView netErroView;
    @Override
    protected int getChildlayout() {
        return R.layout.activity_expertstalk;
    }

    @Override
    protected void initView() {
        title.setText("专家约谈");
        titleBackImage.setVisibility(View.VISIBLE);
        if (NetworkUtils.isNetworkAvailable(this)) {
            waitView.setVisibility(View.VISIBLE);
            waitView.start();
            expertsRecyler.setLayoutManager(new LinearLayoutManager(this));
        		}else {
            netErroView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void loadData(boolean isLoadMore) {
        super.loadData(isLoadMore);
        ExpertsTalkActivityPresenter expertsTalkActivityPresenter=new ExpertsTalkActivityPresenter(this);
        expertsTalkActivityPresenter.getExpertsData();
    }

    @Override
    public void onGetExpertsDataSucess(List<ExpertsDataBean.ItemsBean> expertsData) {
        waitView.stop();
        waitView.setVisibility(View.GONE);
        ExpertsTalkActivityAdapter adapter=new ExpertsTalkActivityAdapter(expertsData,this);
        expertsRecyler.setAdapter(adapter);
        adapter.setOnPostDataClickListioner(new ExpertsTalkActivityAdapter.OnPostDataClickListioner() {
            @Override
            public void onPostDataClickListioner(String askContent,String askTitle) {
                toast("提交数据");
            }
        });
    }

    @Override
    public void onGetExpertsDataFailed(String msg) {
        log("专家约谈错误",msg);
        waitView.stop();
        waitView.setVisibility(View.GONE);
    }
}
