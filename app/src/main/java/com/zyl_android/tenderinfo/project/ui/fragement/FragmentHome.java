package com.zyl_android.tenderinfo.project.ui.fragement;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zyl_android.generalutils.BannerUtils;
import com.zyl_android.generalutils.coustomview.MyViewPager;
import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.mvp.presenter.FragementHomePresenter;
import com.zyl_android.tenderinfo.mvp.view.FragmentHomeView;
import com.zyl_android.tenderinfo.project.adapter.FragmentHomeAdapter;
import com.zyl_android.tenderinfo.project.application.Constants;
import com.zyl_android.tenderinfo.project.bean.BannerBean;
import com.zyl_android.tenderinfo.project.bean.HomeFiveProjectBean;
import com.zyl_android.tenderinfo.project.builder.CustomerRecyclerview;
import com.zyl_android.tenderinfo.project.builder.ObservableScrollView;
import com.zyl_android.tenderinfo.project.ui.activity.PlatformActivity;
import com.zyl_android.tenderinfo.project.ui.activity.SearchActivity;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseFragement;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends BaseFragement implements FragmentHomeView {
    @BindView(R.id.viewpager)
    MyViewPager viewpager;
    @BindView(R.id.group_contain)
    LinearLayout groupContain;
    @BindView(R.id.banner_layout)
    RelativeLayout bannerLayout;
    @BindView(R.id.bibiPlatform)
    LinearLayout bibiPlatform;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.companyPlatform)
    LinearLayout companyPlatform;
    @BindView(R.id.finacePlatform)
    LinearLayout finacePlatform;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.servicePlatform)
    LinearLayout servicePlatform;
    @BindView(R.id.platform_linearLayout)
    LinearLayout platformLinearLayout;
    @BindView(R.id.projectImage)
    ImageView projectImage;
    @BindView(R.id.projcetInfo_layout)
    RelativeLayout projcetInfoLayout;
    @BindView(R.id.niProject_text)
    TextView niProjectText;
    @BindView(R.id.pProject_text)
    TextView pProjectText;
    @BindView(R.id.hostProject_text)
    TextView hostProjectText;
    @BindView(R.id.projcetInfo_text)
    TextView projcetInfoText;
    @BindView(R.id.tenderProjectImage)
    ImageView tenderProjectImage;
    @BindView(R.id.tenderProjcetInfo_layout)
    RelativeLayout tenderProjcetInfoLayout;
    @BindView(R.id.tenderPublic_text)
    TextView tenderPublicText;
    @BindView(R.id.changePublic_text)
    TextView changePublicText;
    @BindView(R.id.getPublci_text)
    TextView getPublciText;
    @BindView(R.id.publicPeople_text)
    TextView publicPeopleText;
    @BindView(R.id.tenderSubject_text)
    TextView tenderSubjectText;
    @BindView(R.id.buyProjectImage)
    ImageView buyProjectImage;
    @BindView(R.id.buyInfo_text)
    TextView buyInfoText;
    @BindView(R.id.buyProjectInfo_layout)
    RelativeLayout buyProjectInfoLayout;
    @BindView(R.id.governmentBuy_text)
    TextView governmentBuyText;
    @BindView(R.id.companyBuy_text)
    TextView companyBuyText;
    @BindView(R.id.buyInfoSubject_text)
    TextView buyInfoSubjectText;
    @BindView(R.id.newInfo_text)
    TextView newInfoText;
    //    @BindView(R.id.homeRecyclerview)
//    CustomerRecyclerview homeRecyclerview;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.location_text)
    LinearLayout locationText;
    @BindView(R.id.search_layout)
    LinearLayout searchLayout;
    @BindView(R.id.recyclerViewContainer)
    LinearLayout recyclerViewContainer;
    @BindView(R.id.scrollview)
    ObservableScrollView scrollview;
    Unbinder unbinder;
    private List<String> bannerUrl = new ArrayList<>();
    private FragementHomePresenter fragementHomePresenter;
    private CustomerRecyclerview customerRecyclerview;
    private CustomerRecyclerview customerRecyclerview2;

    @Override
    protected int getFragementHomeLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        titleLayout.setVisibility(View.GONE);
        fragementHomePresenter = new FragementHomePresenter(this);
        fragementHomePresenter.getBannerData();
        BannerUtils bannerUtils = new BannerUtils(getActivity(), viewpager, groupContain, bannerUrl);
        bannerUtils.startPlayBanner();
        getSmartRefreshLayout().setEnabled(true);

    }

    @Override
    protected void initData() {
        fragementHomePresenter.getHomeProjectData("1", "14000");
        fragementHomePresenter.getHomeTenderData("1", "14000");
        customerRecyclerview = new CustomerRecyclerview(getActivity());
        customerRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        customerRecyclerview2 = new CustomerRecyclerview(getActivity());
        customerRecyclerview2.setLayoutManager(new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

    }

    @OnClick({R.id.bibiPlatform, R.id.companyPlatform, R.id.finacePlatform, R.id.servicePlatform, R.id.projcetInfo_layout, R.id.niProject_text, R.id.pProject_text, R.id.hostProject_text, R.id.projcetInfo_text, R.id.tenderProjcetInfo_layout, R.id.tenderPublic_text, R.id.changePublic_text, R.id.getPublci_text, R.id.publicPeople_text, R.id.tenderSubject_text, R.id.buyProjectInfo_layout, R.id.governmentBuy_text, R.id.companyBuy_text, R.id.buyInfoSubject_text, R.id.location_text, R.id.search_layout})
    public void onViewClicked(View view) {
        Intent intentPlatf = new Intent(getActivity(), PlatformActivity.class);
        switch (view.getId()) {
            case R.id.bibiPlatform:
                intentPlatf.putExtra("Type", "1");
                getActivity().startActivity(intentPlatf);
                break;
            case R.id.companyPlatform:
                intentPlatf.putExtra("Type", "2");
                getActivity().startActivity(intentPlatf);
                break;
            case R.id.finacePlatform:
                intentPlatf.putExtra("Type", "3");
                getActivity().startActivity(intentPlatf);
                break;
            case R.id.servicePlatform:
                intentPlatf.putExtra("Type", "4");
                getActivity().startActivity(intentPlatf);
                break;
            case R.id.projcetInfo_layout:
                break;
            case R.id.niProject_text:
                break;
            case R.id.pProject_text:
                break;
            case R.id.hostProject_text:
                break;
            case R.id.projcetInfo_text:
                break;
            case R.id.tenderProjcetInfo_layout:
                break;
            case R.id.tenderPublic_text:
                break;
            case R.id.changePublic_text:
                break;
            case R.id.getPublci_text:
                break;
            case R.id.publicPeople_text:
                break;
            case R.id.tenderSubject_text:
                break;
            case R.id.buyProjectInfo_layout:
                break;
            case R.id.governmentBuy_text:
                break;
            case R.id.companyBuy_text:
                break;
            case R.id.buyInfoSubject_text:
                break;
            case R.id.location_text:
                break;
            case R.id.search_layout:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
        }
    }

    @Override
    public void onGetBannerSucess(List<BannerBean.ItemBean> bannerList) {
        for (int i = 0; i < bannerList.size(); i++) {
            String url = bannerList.get(i).getImgUrl();
            bannerUrl.add(Constants.baseUrl_pis + url);
        }

    }

    @Override
    public void onGetBannerFailed(String msg) {
        Log.i("TAG", "bannermsgerr---------" + msg);
    }

    @Override
    public void onGetHomeDataSucess(List<HomeFiveProjectBean.ItemsBean> homeProjectInfo) {
        FragmentHomeAdapter adapter = new FragmentHomeAdapter(homeProjectInfo, getActivity());
        customerRecyclerview.setAdapter(adapter);
        recyclerViewContainer.addView(customerRecyclerview);
    }

    @Override
    public void onGetHomeDataFailed(String msg) {
        Log.i("TAG", "获取首页项目信息失败---------" + msg);
    }

    @Override
    public void onGetHomeTenderSucess(List<HomeFiveProjectBean.ItemsBean> homeProjectInfo) {
        FragmentHomeAdapter adapter = new FragmentHomeAdapter(homeProjectInfo, getActivity());
        customerRecyclerview2.setAdapter(adapter);
        Log.i("TAG", "获取首页招标信息成功---------" );
        recyclerViewContainer.addView(customerRecyclerview2);
    }

    @Override
    public void onGetHomeTenderFailed(String msg) {
        Log.i("TAG", "获取首页招标信息失败---------" + msg);
    }

    @Override
    public void onGetHomeBuySucess(List<HomeFiveProjectBean.ItemsBean> homeProjectInfo) {

    }

    @Override
    public void onGetHomeBuyFailed(String msg) {

    }

}
