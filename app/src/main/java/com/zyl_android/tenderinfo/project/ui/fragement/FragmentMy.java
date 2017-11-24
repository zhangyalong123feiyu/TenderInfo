package com.zyl_android.tenderinfo.project.ui.fragement;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.application.Constants;
import com.zyl_android.tenderinfo.project.ui.activity.LoginActivity;
import com.zyl_android.tenderinfo.project.ui.activity.SettingActivity;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseFragement;
import com.zyl_android.tenderinfo.project.utils.AnimationManager;
import com.zyl_android.tenderinfo.project.utils.B64PhotoUtils;

import butterknife.BindView;
import butterknife.OnClick;

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
    @Override
    public void onResume() {
        super.onResume();
        if (islogin()) {
            doLogin.setText(Constants.loginResultInfo.getUser().getEnterprise().getContactName());
            userPhoto.setImageBitmap(B64PhotoUtils.makeRoundCorner(B64PhotoUtils.stringToBitmap(Constants.loginResultInfo.getUser().getImage())));
        		}else {
            doLogin.setText("点击请登录");
            userPhoto.setImageResource(R.mipmap.wode_toux);
        }
    }
    private boolean islogin(){
        if (Constants.loginResultInfo!=null) {
        			return true;
        		}
        		return false;
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
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.userPhoto:
                break;
            case R.id.doLogin:
                if (!islogin()) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
        }
    }
    public void startAnimation(){
        int baseTime=500;
        int increaseTime=60;
        AnimationManager.topToBottom(fraMyTopMove,1000,baseTime);
        AnimationManager.bottomToTop(fraMyBottomMove,2000,baseTime);
        AnimationManager.bottomToTop(companyInfo, 2000, baseTime);
        AnimationManager.bottomToTop(foucsMy, 2000, baseTime + (increaseTime * 1));
        AnimationManager.bottomToTop(aboutOur, 2000, baseTime + (increaseTime * 2));
        AnimationManager.bottomToTop(serviceTerm, 2000, baseTime + (increaseTime * 3));
        AnimationManager.bottomToTop(legalStatement, 2000, baseTime + (increaseTime * 4));
        AnimationManager.bottomToTop(setting, 2000, baseTime + (increaseTime * 5));
    }
}
