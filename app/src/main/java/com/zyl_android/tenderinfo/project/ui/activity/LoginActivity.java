package com.zyl_android.tenderinfo.project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.mvp.presenter.LoginActivityPresenter;
import com.zyl_android.tenderinfo.mvp.view.LoginActivityView;
import com.zyl_android.tenderinfo.project.application.Constants;
import com.zyl_android.tenderinfo.project.bean.LoginResultBean;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseActivity;
import com.zyl_android.tenderinfo.project.utils.SharedPresUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-11-17.
 */

public class LoginActivity extends BaseActivity implements LoginActivityView{
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.backImage)
    ImageView backImage;
    @BindView(R.id.regester)
    TextView regester;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.textView1)
    ImageView textView1;
    @BindView(R.id.inputPhoneNumber)
    EditText inputPhoneNumber;
    @BindView(R.id.inputPassword)
    EditText inputPassword;
    @BindView(R.id.seePassword)
    ImageView seePassword;
    @BindView(R.id.relativeLayout1)
    LinearLayout relativeLayout1;
    @BindView(R.id.act_login_forget_password)
    TextView actLoginForgetPassword;
    @BindView(R.id.fogetPassword)
    LinearLayout fogetPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.fastLogin)
    Button fastLogin;
    private LoginActivityPresenter loginActivityPresenter;

    @Override
    protected int getChildlayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        titleLayout.setVisibility(View.GONE);
        title.setText("登录");
        loginActivityPresenter=new LoginActivityPresenter(this);
    }

    @OnClick({R.id.backImage, R.id.regester, R.id.btn_login, R.id.fastLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.backImage:
                finish();
                break;
            case R.id.regester:
                startActivity(new Intent(this, RegistActivity.class));
                break;
            case R.id.btn_login:
                toast("点击");
                String account = inputPhoneNumber.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                loginActivityPresenter.doLogin(account,password);
                break;
            case R.id.fastLogin:
                break;
        }
    }

    @Override
    public void onLoginSucess(LoginResultBean loginResultBean) {
        Gson gson=new Gson();
        String loginUserInfo = gson.toJson(loginResultBean);
        Constants.LoginUerinfo=loginUserInfo;
        Constants.loginResultInfo=loginResultBean;
        SharedPresUtils shareUtils = SharedPresUtils.getsSharedPresUtils(this);
        shareUtils.putString("loginUerInfo",loginUserInfo);
        finish();
    }

    @Override
    public void onLoginFailed(String errorMsg) {
        log("登录错误信息",errorMsg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginActivityPresenter.unSubScription();
    }
}
