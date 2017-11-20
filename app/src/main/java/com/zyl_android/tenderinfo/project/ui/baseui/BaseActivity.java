package com.zyl_android.tenderinfo.project.ui.baseui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.zyl_android.generalutils.coustomview.WaitView;
import com.zyl_android.tenderinfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bibinet on 2017-11-4.
 */

public abstract class BaseActivity extends MPermissionsActivity {
    private SmartRefreshLayout smartRefreshLayout;
    public WaitView waitView;
    public RelativeLayout titleLayout;
    public TextView title;
    public ImageView titleImageRight;
    public ImageView titleBackImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initBaseView();
        ButterKnife.bind(this);
        initBaseLoadData();
        initView();
        loadData(false);
    }

    private void initBaseView() {
        FrameLayout mainView = (FrameLayout) findViewById(R.id.fra_base_main);
        titleLayout=(RelativeLayout)findViewById(R.id.titleView);
        title=(TextView)findViewById(R.id.title);
        titleBackImage=(ImageView)findViewById(R.id.title_imageleft);
        titleImageRight=(ImageView)findViewById(R.id.title_imageright);
        waitView = (WaitView) findViewById(R.id.waitView);
        View childLayoutView = LayoutInflater.from(this).inflate(getChildlayout(), null);
        smartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        smartRefreshLayout.setEnableRefresh(false);//设置默认关闭下拉刷新功能
        smartRefreshLayout.setEnableLoadmore(false);//默认关闭刷新，需要刷新时子类去实现
        mainView.addView(childLayoutView);
        initStateBarTransparent();
    }

    private void initBaseLoadData() {
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                onloadMore();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                onrefresh();
            }
        });
    }

    protected  void onrefresh(){};

    protected  void onloadMore(){};

    protected SmartRefreshLayout getSmartRefreshLayout() {
        return smartRefreshLayout;

    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.tr_entry, R.anim.tr_void);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.tr_void, R.anim.tr_exit);
    }

    public void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void log(String descrp, String str) {
        Log.i("TAG", descrp + "=======================" + str);
    }

    public void initStateBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    protected abstract int getChildlayout();

    protected abstract void initView();

    protected  void loadData(boolean isLoadMore){};

    /*---------------------------------------------------------------------------以下是android6.0动态授权的封装十分好用---------------------------------------------------------------------------*/
    private int  mPermissionIdx = 0x10;//请求权限索引
    private SparseArray<GrantedResult> mPermissions   = new SparseArray<>();//请求权限运行列表
    @SuppressLint("Override")
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        GrantedResult runnable = mPermissions.get(requestCode);
        if (runnable == null) {
            return;
        }
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            runnable.mGranted = true;
        }
        runOnUiThread(runnable);
    }

    public void requestPermission(String[] permissions, String reason, GrantedResult runnable) {
        if(runnable == null){
            return;
        }
        runnable.mGranted = false;
        if (Build.VERSION.SDK_INT < 23 || permissions == null || permissions.length == 0) {
            runnable.mGranted = true;//新添加
            runOnUiThread(runnable);
            return;
        }
        final int requestCode = mPermissionIdx++;
        mPermissions.put(requestCode, runnable);

		/*
			是否需要请求权限
		 */
        boolean granted = true;
        for (String permission : permissions) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                granted = granted && checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
            }
        }

        if (granted) {
            runnable.mGranted = true;
            runOnUiThread(runnable);
            return;
        }

		/*
			是否需要请求弹出窗
		 */
        boolean request = true;
        for (String permission : permissions) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                request = request && !shouldShowRequestPermissionRationale(permission);
            }
        }

        if (!request) {
            final String[] permissionTemp = permissions;
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setMessage(reason)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(permissionTemp, requestCode);
                            }
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            GrantedResult runnable = mPermissions.get(requestCode);
                            if (runnable == null) {
                                return;
                            }
                            runnable.mGranted = false;
                            runOnUiThread(runnable);
                        }
                    }).create();
            dialog.show();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissions, requestCode);
            }
        }
    }

    public static abstract class GrantedResult implements Runnable{
        private boolean mGranted;
        public abstract void onResult(boolean granted);
        @Override
        public void run(){
            onResult(mGranted);
        }
    }
}
