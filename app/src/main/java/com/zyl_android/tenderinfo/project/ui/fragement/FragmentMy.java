package com.zyl_android.tenderinfo.project.ui.fragement;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.ui.activity.LoginActivity;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseFragement;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMy extends BaseFragement {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.fra_my_top_move)
    LinearLayout fraMyTopMove;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.companyInfo)
    LinearLayout companyInfo;
    @BindView(R.id.privateOdering)
    LinearLayout privateOdering;
    @BindView(R.id.foucsMy)
    LinearLayout foucsMy;
    @BindView(R.id.aboutOur)
    LinearLayout aboutOur;
    @BindView(R.id.serviceTerm)
    LinearLayout serviceTerm;
    @BindView(R.id.legalStatement)
    LinearLayout legalStatement;
    @BindView(R.id.setting)
    LinearLayout setting;
    @BindView(R.id.userPhoto)
    ImageView userPhoto;
    @BindView(R.id.doLogin)
    Button doLogin;
    @BindView(R.id.fra_my_bottom_move)
    RelativeLayout fraMyBottomMove;
    Unbinder unbinder;

    @Override
    protected int getFragementHomeLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        titleLayout.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {

    }
    @OnClick({R.id.companyInfo, R.id.privateOdering, R.id.foucsMy, R.id.aboutOur, R.id.serviceTerm, R.id.legalStatement, R.id.setting, R.id.userPhoto, R.id.doLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.companyInfo:
                break;
            case R.id.privateOdering:
                break;
            case R.id.foucsMy:
                break;
            case R.id.aboutOur:
                break;
            case R.id.serviceTerm:
                break;
            case R.id.legalStatement:
                break;
            case R.id.setting:
                break;
            case R.id.userPhoto:
                break;
            case R.id.doLogin:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }
}
