package com.zyl_android.tenderinfo.project.ui.baseui;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.application.Constants;
import com.zyl_android.tenderinfo.project.bean.LoginResultBean;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseActivity;
import com.zyl_android.tenderinfo.project.ui.baseui.MPermissionsActivity;
import com.zyl_android.tenderinfo.project.ui.fragement.FragmentAsk;
import com.zyl_android.tenderinfo.project.ui.fragement.FragmentHome;
import com.zyl_android.tenderinfo.project.ui.fragement.FragmentMy;
import com.zyl_android.tenderinfo.project.utils.SharedPresUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MPermissionsActivity {

    @BindView(R.id.bottomhome)
    RelativeLayout bottomhome;
    @BindView(R.id.bottomask)
    RelativeLayout bottomask;
    @BindView(R.id.bottomy)
    RelativeLayout bottomy;
    private FragmentHome framentHome;
    private  Fragment[] fragments = new Fragment[3];
    private FragmentAsk fragment_Ask;
    private FragmentMy fragment_My;
    private RelativeLayout[] mTabs;
    private int index;
    private int currentTabIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initStateBarTransparent();
        initView();
        initData();
    }


    public void initStateBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    protected void initView() {
        framentHome = new FragmentHome();
        fragment_Ask = new FragmentAsk();
        fragment_My = new FragmentMy();
        fragments = new Fragment[]{framentHome, fragment_Ask, fragment_My};
        mTabs = new RelativeLayout[]{bottomhome, bottomask, bottomy};
        mTabs[0].setSelected(true);
        getSupportFragmentManager().beginTransaction().add(R.id.fragementcontainer, framentHome).show(framentHome).
                add(R.id.fragementcontainer, fragment_Ask).hide(fragment_Ask).add(R.id.fragementcontainer, fragment_My).hide(fragment_My)
                .commit();
    }

    private void initData() {
        SharedPresUtils sharedPresUtils = SharedPresUtils.getsSharedPresUtils(this);
        String loginReseultInfo = sharedPresUtils.getString("loginUerInfo", null);
        Gson gson=new Gson();
        Constants.loginResultInfo=gson.fromJson(loginReseultInfo, LoginResultBean.class);
    }


    @OnClick({R.id.bottomhome, R.id.bottomask, R.id.bottomy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bottomhome:
                index=0;
                break;
            case R.id.bottomask:
                index=1;
                break;
            case R.id.bottomy:
                index=2;
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            if(!(fragments[index] instanceof FragmentMy)){
                trx.hide(fragments[currentTabIndex]);
                if(currentTabIndex==1){
                    trx.hide(fragments[0]);
                }else{
                    trx.hide(fragments[1]);
                }
            }
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragementcontainer, fragments[index]);
            }
            trx.show(fragments[index]).commit();
            if (fragments[index] instanceof FragmentMy) {
              fragment_My.startAnimation();
            		}
        }
        mTabs[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }

}
