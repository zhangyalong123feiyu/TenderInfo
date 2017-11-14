package com.zyl_android.tenderinfo.project.ui.baseui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.zyl_android.tenderinfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by bibinet on 2017-11-6.
 */

public abstract class BaseFragement extends Fragment {
    private SmartRefreshLayout smartRefreshLayout;
    public RelativeLayout titleLayout;
    public TextView title_textView;
    public ImageView title_backImage;

    @Nullable
    @Override//初始化view，将view初始化好以后传递给onviewCreated方法
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_base, container, false);
        FrameLayout mainLayout = (FrameLayout) view.findViewById(R.id.fra_base_main);
        titleLayout = (RelativeLayout) view.findViewById(R.id.titleView);
        title_textView=(TextView)view.findViewById(R.id.title);
        title_backImage=(ImageView)view.findViewById(R.id.title_imageleft);
        smartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        View fragememtHomeView = LayoutInflater.from(getActivity()).inflate(getFragementHomeLayout(), null);
        mainLayout.addView(fragememtHomeView);
        initBaseView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initView();
        initData();
    }

    private void initBaseView() {
        smartRefreshLayout.setEnabled(false);//设置下拉刷新状态
        smartRefreshLayout.setEnableAutoLoadmore(false);//设置自动上拉加载为不可用
        smartRefreshLayout.setEnableLoadmore(false);//设置上拉加载
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                loadMoreData();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshData();
            }
        });
    }

    protected abstract void refreshData();

    protected abstract void loadMoreData();

    protected abstract int getFragementHomeLayout();

    protected SmartRefreshLayout getSmartRefreshLayout() {
        return smartRefreshLayout;

    }

    protected abstract void initView();

    protected abstract void initData();

    void toast(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

}
