package com.zyl_android.tenderinfo.project.ui.fragement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zyl_android.generalutils.coustomview.MyViewPager;
import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.builder.CustomerRecyclerview;
import com.zyl_android.tenderinfo.project.builder.ObservableScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends BaseFragement {
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
    @BindView(R.id.homeRecyclerview)
    CustomerRecyclerview homeRecyclerview;
    @BindView(R.id.scrollview)
    ObservableScrollView scrollview;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.location_text)
    LinearLayout locationText;
    @BindView(R.id.search_layout)
    LinearLayout searchLayout;
    Unbinder unbinder;

    @Override
    protected void onchildViewCreated(View view, Bundle savedInstanceState) {
        FrameLayout mainLayout = (FrameLayout)view.findViewById(R.id.fra_base_main);
        View fragememtHomeView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null);
        mainLayout.addView(fragememtHomeView);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.companyPlatform, R.id.finacePlatform, R.id.servicePlatform, R.id.platform_linearLayout, R.id.projcetInfo_layout, R.id.niProject_text, R.id.pProject_text, R.id.hostProject_text, R.id.projcetInfo_text, R.id.tenderProjcetInfo_layout, R.id.tenderPublic_text, R.id.changePublic_text, R.id.getPublci_text, R.id.publicPeople_text, R.id.tenderSubject_text, R.id.buyProjectInfo_layout, R.id.governmentBuy_text, R.id.companyBuy_text, R.id.buyInfoSubject_text, R.id.location_text, R.id.search_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.companyPlatform:
                break;
            case R.id.finacePlatform:
                break;
            case R.id.servicePlatform:
                break;
            case R.id.platform_linearLayout:
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
                break;
        }
    }
}
