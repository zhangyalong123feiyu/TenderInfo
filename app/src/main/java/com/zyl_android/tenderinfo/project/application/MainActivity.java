package com.zyl_android.tenderinfo.project.application;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseActivity;
import com.zyl_android.tenderinfo.project.ui.fragement.FragmentAsk;
import com.zyl_android.tenderinfo.project.ui.fragement.FragmentHome;
import com.zyl_android.tenderinfo.project.ui.fragement.FragmentMy;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

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
    }

    @Override
    protected void onrefresh() {

    }

    @Override
    protected void onloadMore() {

    }

    @Override
    protected int getChildlayout() {
        return R.layout.activity_main;
    }

    @Override
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

    @Override
    protected void loadData() {

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
        }
        mTabs[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }

}
