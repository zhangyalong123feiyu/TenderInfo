package com.zyl_android.tenderinfo.project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zyl_android.generalutils.PhoneNumberUtils;
import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.mvp.presenter.RegistActivityPresenter;
import com.zyl_android.tenderinfo.mvp.view.RegistActivityView;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseActivity;
import com.zyl_android.tenderinfo.project.utils.CountDownTimerUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-11-24.
 */

public class RegistActivity extends BaseActivity implements RegistActivityView {
    @BindView(R.id.inputUserName)
    EditText inputUserName;
    @BindView(R.id.inputPhone)
    EditText inputPhone;
    @BindView(R.id.inputVerifCode)
    EditText inputVerifCode;
    @BindView(R.id.verifCodeBtn)
    Button verifCodeBtn;
    @BindView(R.id.inputCompanyName)
    EditText inputCompanyName;
    @BindView(R.id.completeRegist)
    Button completeRegist;
    private RegistActivityPresenter registActivityPresenter;

    @Override
    protected int getChildlayout() {
        return R.layout.activity_regist;
    }

    @Override
    protected void initView() {
        title.setText("注册");
        titleBackImage.setVisibility(View.VISIBLE);
        registActivityPresenter=new RegistActivityPresenter(this);
    }

    @OnClick({R.id.verifCodeBtn, R.id.completeRegist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.verifCodeBtn:
                String phoneNumb = inputPhone.getText().toString().trim();
                if (PhoneNumberUtils.isMobileNumber(phoneNumb)) {
                    registActivityPresenter.getVerifyCode(phoneNumb);
                }else {
                    toast("手机号格式不正确");
                }
                beginCountDown();
                break;
            case R.id.completeRegist:
                break;
        }
    }

    private void beginCountDown() {//开始倒计时
        CountDownTimerUtils countDownTimerUtils=new CountDownTimerUtils();
        countDownTimerUtils.startCountDownTimer(60000,1000);
        countDownTimerUtils.setCounTimeIntevalListioner(new CountDownTimerUtils.CounTimeIntevalListioner() {
            @Override
            public void onTick(long l) {
                verifCodeBtn.setText("（" + (l/ 1000) + "）秒后可重发");
                verifCodeBtn.setEnabled(false);
            }

            @Override
            public void onFinish() {
                verifCodeBtn.setText("获取验证码");
                verifCodeBtn.setEnabled(true);
            }
        });
    }
    @Override
    public void onGetVerifyCodeSucess() {

    }

    @Override
    public void onGetVerifyCodeFailed(String msg) {

    }

    @Override
    public void onDoRegistSucess() {
        toast("注册成功");
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void onDoRegistFailed(String msg) {
        log("注册失败", msg);
    }

}
