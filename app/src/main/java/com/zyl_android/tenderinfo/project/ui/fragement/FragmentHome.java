package com.zyl_android.tenderinfo.project.ui.fragement;


import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zyl_android.generalutils.BannerUtils;
import com.zyl_android.generalutils.NetworkUtils;
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
import com.zyl_android.tenderinfo.project.utils.CacheUtils.ACache;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.netErrorView)
    ImageView netErrorView;
    private List<String> bannerUrl = new ArrayList<>();
    private FragementHomePresenter fragementHomePresenter;
    private Drawable drawable;
    private CustomerRecyclerview projectRecyclerview;
    private CustomerRecyclerview tenderRecyclerview;
    private CustomerRecyclerview buyRecyclerview;
    private int projcetType;
    private boolean isRefresh;

    @Override
    protected void refreshData() {
        isRefresh=true;
        initData();
    }

    @Override
    protected int getFragementHomeLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        isVisible=true;//设置fragement为可见状态
        titleLayout.setVisibility(View.GONE);
        scrollview.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(int x, int y, int oldx, int oldy) {
                searchLayout.setBackgroundColor(Color.parseColor("#f9c432"));
                if (y >= 10) {
                    searchLayout.getBackground().setAlpha(255);
                } else if (y <= 10) {
                    searchLayout.getBackground().setAlpha(0);
                }
            }
        });
        drawable= getResources().getDrawable(R.drawable.homerange_shape);//设置圆角
        fragementHomePresenter = new FragementHomePresenter(this);
        BannerUtils bannerUtils = new BannerUtils(getActivity(), viewpager, groupContain, bannerUrl);
        bannerUtils.startPlayBanner();
        getSmartRefreshLayout().setEnableRefresh(true);

    }

    @Override
    protected void initData() {
        fragementHomePresenter.getBannerData();
        if (isRefresh) {
            recyclerViewContainer.removeAllViews();
            isRefresh=false;
        		}
        if (NetworkUtils.isNetworkAvailable(getActivity())) {
            netErrorView.setVisibility(View.GONE);
            fragementHomePresenter.getHomeProjectData("1", "14000");
        }else {
            netErrorView.setVisibility(View.VISIBLE);
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.topMargin=20;
        //项目信息相关信息配置
        projectRecyclerview = new CustomerRecyclerview(getActivity());
        projectRecyclerview.setBackground(drawable);
        projectRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        //招标信息相关配置
        tenderRecyclerview = new CustomerRecyclerview(getActivity());
        tenderRecyclerview.setBackground(drawable);
        tenderRecyclerview.setLayoutParams(params);
        tenderRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        //采购信息相关配置
        buyRecyclerview=new CustomerRecyclerview(getActivity());
        buyRecyclerview.setBackground(drawable);
        buyRecyclerview.setLayoutParams(params);
        buyRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        buyRecyclerview.setLayoutParams(params);

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
                startLocation();
                break;
            case R.id.search_layout:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
        }

    }

    private void startLocation() {
        toast("dianji");
        requestPermission(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},"我要定位权限",
        new GrantedResult() {
            @Override
            public void onResult(boolean granted) {
                    if (granted) {
                    		toast("我要开始定位了");
                    		}
            }
        });
    }

    @Override
    public void onGetBannerSucess(List<BannerBean.ItemBean> bannerList) {
        for (int i = 0; i < bannerList.size(); i++) {
            String url = bannerList.get(i).getImgUrl();
            bannerUrl.add(Constants.baseUrl_pis + url);
        }
        //缓存测试
        if (bannerUrl.size()!=0) {
            fragementHomePresenter.downLoadImage(bannerUrl.get(1));
        }
    }

    @Override
    public void onGetBannerFailed(String msg) {
        Log.i("TAG", "bannermsgerr---------" + msg);
    }

    @Override
    public void onGetHomeDataSucess(List<HomeFiveProjectBean.ItemsBean> homeProjectInfo) {
        projcetType=1;
        getSmartRefreshLayout().finishRefresh();//完成刷新
        FragmentHomeAdapter adapter = new FragmentHomeAdapter(homeProjectInfo, getActivity(),projcetType);
        projectRecyclerview.setAdapter(adapter);
        recyclerViewContainer.addView(projectRecyclerview);
        fragementHomePresenter.getHomeTenderData("1", "14000");
    }
    @Override
    public void onGetHomeDataFailed(String msg) {
    }

    @Override
    public void onGetHomeTenderSucess(List<HomeFiveProjectBean.ItemsBean> homeProjectInfo) {
        projcetType=2;
        FragmentHomeAdapter adapter = new FragmentHomeAdapter(homeProjectInfo, getActivity(),projcetType);
        tenderRecyclerview.setAdapter(adapter);
        recyclerViewContainer.addView(tenderRecyclerview);
        fragementHomePresenter.getHomeBuyProjectInfo("1", "14000");
    }

    @Override
    public void onGetHomeTenderFailed(String msg) {
    }

    @Override
    public void onGetHomeBuySucess(List<HomeFiveProjectBean.ItemsBean> homeProjectInfo) {
        projcetType=3;
        FragmentHomeAdapter adapter = new FragmentHomeAdapter(homeProjectInfo, getActivity(),projcetType);
        buyRecyclerview.setAdapter(adapter);
        recyclerViewContainer.addView(buyRecyclerview);

    }

    @Override
    public void onGetHomeBuyFailed(String msg) {

    }

    @Override
    public void onDownLoadImageSucess(Bitmap bitmap) {
        ACache aCache=ACache.get(getActivity());
        aCache.put("banner1",bitmap);
    }

    @Override
    public void onDownLoadImageFailed(String msg) {
        Log.i("下载错误",msg);
    }

}
