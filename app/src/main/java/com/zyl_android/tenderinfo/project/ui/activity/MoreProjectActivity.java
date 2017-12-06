package com.zyl_android.tenderinfo.project.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyl_android.generalutils.DialogUtils;
import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.mvp.presenter.MoreProjectActivityPresenter;
import com.zyl_android.tenderinfo.mvp.view.MoreProjectActivityView;
import com.zyl_android.tenderinfo.project.adapter.MorePorjectPopAdapter;
import com.zyl_android.tenderinfo.project.adapter.MoreProjectAdapter;
import com.zyl_android.tenderinfo.project.bean.ProjectInfoBean;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseActivity;
import com.zyl_android.tenderinfo.project.utils.PopWindowUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-12-4.
 */

public class MoreProjectActivity extends BaseActivity implements MoreProjectActivityView{
    @BindView(R.id.industryTextView)
    TextView industryTextView;
    @BindView(R.id.projectIndustry)
    LinearLayout projectIndustry;
    @BindView(R.id.areaTextView)
    TextView areaTextView;
    @BindView(R.id.projectAeara)
    LinearLayout projectAeara;
    @BindView(R.id.timeTextView)
    TextView timeTextView;
    @BindView(R.id.projectTime)
    LinearLayout projectTime;
    @BindView(R.id.moreProjectRcyclerView)
    RecyclerView moreProjectRcyclerView;
    @BindView(R.id.noDataView)
    ImageView noDataView;
    private PopWindowUtils dialogUtils;
    private String[] time = new String[]{"全部", "近一周", "近一个月", "近三个月"};
    private String[] industry = new String[]{"全部", "农、林、牧、渔业", "采矿业", "制造业", "电力、热力、燃气及水生产和供应业", "建筑业",
            "批发和零售业", "交通运输、仓储和邮政业", "住宿和餐饮业", "信息传输、软件和信息技术服务业", "金融业", "房地产业", "租赁和商务服务业", "科学研究和技术服务业", "水利、环境和公共设施管理业",
            "居民服务、修理和其他服务业", "教育", "卫生和社会工作", "文化、体育和娱乐业", "公共管理、社会保障和社会组织", "国际组织"};
    private String[] area = new String[]{"全部", "北京市", "天津市", "河北省", "山西省", "内蒙古自治区", "辽宁省", "吉林省", "黑龙江省", "上海市", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省"
            , "河南省", "湖北省", "湖南省", "广东省", "广西壮族自治区", "重庆市", "四川省", "贵州省", "云南省", "西藏自治区", "陕西省", "甘肃省", "青海省", "宁夏回族自治区", "新疆维吾尔自治区",
            "台湾省", "香港特别行政区", "澳门特别行政区"};
    private List<String> data=new ArrayList<>();
    private MorePorjectPopAdapter adapter;
    private MoreProjectAdapter moreProjectAdapter;
    private MoreProjectActivityPresenter moreProjectActivityPresenter;

    @Override
    protected int getChildlayout() {
        return R.layout.activity_moreproject;
    }

    @Override
    protected void initView() {
        title.setText("更多信息");
        getSmartRefreshLayout().setEnableRefresh(true);
        getSmartRefreshLayout().setEnableLoadmore(true);
        dialogUtils= new PopWindowUtils(this);
        moreProjectRcyclerView.setLayoutManager(new LinearLayoutManager(this));
        moreProjectActivityPresenter=new MoreProjectActivityPresenter(this);
    }

    @OnClick({R.id.projectIndustry, R.id.projectAeara, R.id.projectTime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.projectIndustry:
                data=Arrays.asList(industry);
                doSelect(industryTextView);
                break;
            case R.id.projectAeara:
                data=Arrays.asList(area);
                doSelect(areaTextView);
                break;
            case R.id.projectTime:
                data=Arrays.asList(time);
                doSelect(timeTextView);
                break;
        }
    }

    private void doSelect(final TextView textView) {
        showPopWindow();
        adapter.setItemClickListioner(new MorePorjectPopAdapter.ItemClickListioner() {
            @Override
            public void onItemClickListioner(String descrp) {
                textView.setText(descrp);
            }
        });
    }
    //显示弹出框
    private void showPopWindow() {
        dialogUtils.createPopWindow(R.layout.item_moreprojcet_popwindow);
        View popView = dialogUtils.getPopview();
        RecyclerView popRecyclerView = (RecyclerView) popView.findViewById(R.id.popRecyclerView);
        adapter=new MorePorjectPopAdapter(this,data);
        popRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        popRecyclerView.setAdapter(adapter);
        dialogUtils.showPopWindow(projectAeara);
    }

    @Override
    protected void loadData(boolean isLoadMore) {
        super.loadData(isLoadMore);
        log("page","pae======"+pageNumb);
        log("加载数据","加载数据======");
        String wichProject = getIntent().getStringExtra("WhichProject");
        if (wichProject.equals("projectInfo")) {
            moreProjectActivityPresenter.getMoreProjectInfo(String.valueOf(pageNumb),String.valueOf(5),String.valueOf(0),"z",String.valueOf(140000));
        }else if (wichProject.equals("tenderInfo")) {
            moreProjectActivityPresenter.getMoreTenderInfo(String.valueOf(pageNumb),String.valueOf(5),String.valueOf(0),"z",String.valueOf(140000));
        }else if (wichProject.equals("buyInfo")) {
            moreProjectActivityPresenter.getMoreBuyInfo(String.valueOf(pageNumb),String.valueOf(5),String.valueOf(0),"z",String.valueOf(140000));
        }
    }
    @Override
    public void loadMoreSucess(List<ProjectInfoBean.ItemsBean> moreInfoList) {
        if (moreProjectAdapter==null) {//如果adapter为空，先创建
            moreProjectAdapter=new MoreProjectAdapter(this,moreInfoList);
            moreProjectRcyclerView.setAdapter(moreProjectAdapter);
        }
        if (isLoadMore) {
            moreProjectAdapter.addData(moreInfoList);
            if (moreInfoList.size()==0) {
                getSmartRefreshLayout().setLoadmoreFinished(true);
            }
            getSmartRefreshLayout().finishLoadmore();
        }else {
            moreProjectAdapter.refreshData(moreInfoList);
            getSmartRefreshLayout().finishRefresh();
        }
        log("数据多少","加载数据======"+moreInfoList.size());
    }

    @Override
    public void loadMoreFailed(String msg) {
        log("跟多数据错误",msg);
        getSmartRefreshLayout().finishLoadmore();
    }
}
