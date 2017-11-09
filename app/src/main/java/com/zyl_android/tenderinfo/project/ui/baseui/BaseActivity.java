package com.zyl_android.tenderinfo.project.ui.baseui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.bezierradar.WaveView;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.zyl_android.generalutils.coustomview.WaitView;
import com.zyl_android.tenderinfo.R;

import butterknife.ButterKnife;

/**
 * Created by bibinet on 2017-11-4.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private SmartRefreshLayout smartRefreshLayout;
    public ImageView netErrorView;
    public WaitView waitView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initBaseView();
        ButterKnife.bind(this);
        initBaseLoadData();
        initView();
        loadData();
    }

    private void initBaseView() {
        FrameLayout mainView =(FrameLayout)findViewById(R.id.fra_base_main);
         netErrorView =(ImageView)findViewById(R.id.netErrorView);
         waitView =(WaitView) findViewById(R.id.waitView);
        View childLayoutView = LayoutInflater.from(this).inflate(getChildlayout(), null);
        smartRefreshLayout= (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        smartRefreshLayout.setEnabled(false);
        smartRefreshLayout.setEnableLoadmore(false);//默认关闭刷新，需要刷新时子类去实现
        mainView.addView(childLayoutView);
        initStateBarTransparent();
    }
    private void initBaseLoadData() {
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                toast("上拉加载");
                onloadMore();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                toast("下拉刷新");
                onrefresh();
            }
        });
    }

    protected abstract void onrefresh();

    protected abstract void onloadMore();

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.tr_entry,R.anim.tr_void);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.tr_void,R.anim.tr_exit);
    }
    void toast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
    public void initStateBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    protected abstract int getChildlayout();

    protected abstract void initView();

    protected abstract void loadData();
}
