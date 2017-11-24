package com.zyl_android.tenderinfo.project.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.mvp.presenter.ModifyPasswordActivityPresenter;
import com.zyl_android.tenderinfo.mvp.view.ModifyPasswordActivityView;
import com.zyl_android.tenderinfo.project.application.Constants;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseActivity;
import com.zyl_android.tenderinfo.project.utils.CountDownTimerUtils;
import com.zyl_android.tenderinfo.project.utils.SharedPresUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-11-23.
 */

public class ModifyPasswordActivity extends BaseActivity implements ModifyPasswordActivityView{
    @BindView(R.id.phoneNumbEdit)
    EditText phoneNumbEdit;
    @BindView(R.id.verifCodeEdit)
    EditText verifCodeEdit;
    @BindView(R.id.getVerifyCode)
    TextView getVerifyCode;
    @BindView(R.id.newPasswordEdit)
    EditText newPasswordEdit;
    @BindView(R.id.enSureNewPasswordEdit)
    EditText enSureNewPasswordEdit;
    @BindView(R.id.SureBtn)
    Button SureBtn;
    private ModifyPasswordActivityPresenter modifyPasswordActivityPresenter;

    @Override
    protected int getChildlayout() {
        return R.layout.activity_modifypassword;
    }

    @Override
    protected void initView() {
        title.setText("修改密码");
        titleBackImage.setVisibility(View.VISIBLE);
    }

    @Override
    protected void loadData(boolean isLoadMore) {
        super.loadData(isLoadMore);
        modifyPasswordActivityPresenter=new ModifyPasswordActivityPresenter(this);
    }

    @OnClick({R.id.getVerifyCode, R.id.SureBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.getVerifyCode:
                String phoneNumb = phoneNumbEdit.getText().toString().trim();
                modifyPasswordActivityPresenter.getVerifyCode(phoneNumb);
                beginCountDown();
                break;
            case R.id.SureBtn:
                String phoneNumbs = phoneNumbEdit.getText().toString().trim();
                String verifyCode = verifCodeEdit.getText().toString().trim();
                String newPassword = newPasswordEdit.getText().toString().trim();
                String ensurePassword = enSureNewPasswordEdit.getText().toString().trim();
                if (!newPassword.equals(ensurePassword)) {
                		toast("两次密码输入不一致");
                		}else{
                    Map<String,String> requestMap=new HashMap<>();
                    requestMap.put("cellphone",phoneNumbs);
                    requestMap.put("newPassword",newPassword);
                    requestMap.put("smsCode",verifyCode);
                    modifyPasswordActivityPresenter.modifyPassword(requestMap);
                }
                break;
        }
    }

    private void beginCountDown() {//开始倒计时
        CountDownTimerUtils countDownTimerUtils=new CountDownTimerUtils();
        countDownTimerUtils.startCountDownTimer(60000,1000);
        countDownTimerUtils.setCounTimeIntevalListioner(new CountDownTimerUtils.CounTimeIntevalListioner() {
            @Override
            public void onTick(long l) {
                getVerifyCode.setText("（" + (l/ 1000) + "）秒后可重发");
                getVerifyCode.setEnabled(false);
            }

            @Override
            public void onFinish() {
                getVerifyCode.setText("获取验证码");
                getVerifyCode.setEnabled(true);
            }
        });
    }

    @Override
    public void onGetVerifyCodeSucess() {
    }

    @Override
    public void onGetVerifyCodeFailed(String msg) {
        log("获取验证码错误",msg);
    }

    @Override
    public void onModifyPasswordSucess() {
        SharedPresUtils.getsSharedPresUtils(this).putString("loginUerInfo",null);
        Constants.loginResultInfo=null;
        finish();
    }

    @Override
    public void onModifyPasswordFailed(String msg) {
        log("修改密码错误",msg);
    }
}
