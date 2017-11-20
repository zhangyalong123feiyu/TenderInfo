package com.zyl_android.tenderinfo.project.ui.baseui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.zyl_android.tenderinfo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by bibinet on 2017-11-6.
 */

public abstract class BaseFragement extends Fragment {
    private SmartRefreshLayout smartRefreshLayout;
    public RelativeLayout titleLayout;
    public TextView title_textView;
    public ImageView title_backImage;

    @Nullable
    @Override//初始化view，将view初始化好以后传递给onviewCreated方法
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_base, container, false);
        FrameLayout mainLayout = (FrameLayout) view.findViewById(R.id.fra_base_main);
        titleLayout = (RelativeLayout) view.findViewById(R.id.titleView);
        title_textView=(TextView)view.findViewById(R.id.title);
        title_backImage=(ImageView)view.findViewById(R.id.title_imageleft);
        smartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        View fragememtHomeView = LayoutInflater.from(getActivity()).inflate(getFragementHomeLayout(), null);
        mainLayout.addView(fragememtHomeView);
        initBaseView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initView();
        initData();
    }

    private void initBaseView() {
        smartRefreshLayout.setEnabled(false);//设置下拉刷新状态
        smartRefreshLayout.setEnableAutoLoadmore(false);//设置自动上拉加载为不可用
        smartRefreshLayout.setEnableLoadmore(false);//设置上拉加载
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                loadMoreData();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshData();
            }
        });
    }

    protected  void refreshData(){};

    protected  void loadMoreData(){};

    protected abstract int getFragementHomeLayout();

    protected SmartRefreshLayout getSmartRefreshLayout() {
        return smartRefreshLayout;

    }

    protected abstract void initView();

    protected abstract void initData();

    public void toast(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }
    /*---------------------------------------------------------------------------以下是android6.0动态授权的封装十分好用---------------------------------------------------------------------------*/
    private int mPermissionIdx = 0x10;//请求权限索引
    private SparseArray<BaseFragement.GrantedResult> mPermissions   = new SparseArray<>();//请求权限运行列表
//    @SuppressLint("Override")
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        BaseFragement.GrantedResult runnable = mPermissions.get(requestCode);
//        if (runnable == null) {
//            return;
//        }
//        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            runnable.mGranted = true;
//        }
//       getActivity().runOnUiThread(runnable);
//    }

    public void requestPermission(String[] permissions, String reason, BaseFragement.GrantedResult runnable) {
        if(runnable == null){
            return;
        }
        runnable.mGranted = false;
        if (Build.VERSION.SDK_INT < 23 || permissions == null || permissions.length == 0) {
            runnable.mGranted = true;//新添加
           getActivity().runOnUiThread(runnable);
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
                granted = granted && getActivity().checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
            }
        }

        if (granted) {
            runnable.mGranted = true;
            getActivity().runOnUiThread(runnable);
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
            AlertDialog dialog = new AlertDialog.Builder(getActivity())
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
                            BaseFragement.GrantedResult runnable = mPermissions.get(requestCode);
                            if (runnable == null) {
                                return;
                            }
                            runnable.mGranted = false;
                            getActivity().runOnUiThread(runnable);
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
    //-------------------------------------------------------------------------------------------------------------------------------



    private final String TAG = "MPermissions";
    private int REQUEST_CODE_PERMISSION = 0x00099;

    /**
     * 请求权限
     *
     * @param permissions 请求的权限
     * @param requestCode 请求权限的请求码
     */
    public void mRequestPermission(String[] permissions, int requestCode) {
        this.REQUEST_CODE_PERMISSION = requestCode;
        if (checkPermissions(permissions)) {
            permissionSuccess(REQUEST_CODE_PERMISSION);
        } else {
            List<String> needPermissions = getDeniedPermissions(permissions);
            ActivityCompat.requestPermissions(getActivity(), needPermissions.toArray(new String[needPermissions.size()]), REQUEST_CODE_PERMISSION);
        }
    }

    /**
     * 检测所有的权限是否都已授权
     *
     * @param permissions
     * @return
     */
    private boolean checkPermissions(String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getActivity(), permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     */
    private List<String> getDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getActivity(), permission) !=
                    PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)) {
                needRequestPermissionList.add(permission);
            }
        }
        return needRequestPermissionList;
    }


    /**
     * 系统请求权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (verifyPermissions(grantResults)) {
                permissionSuccess(REQUEST_CODE_PERMISSION);
            } else {
                permissionFail(REQUEST_CODE_PERMISSION);
                showTipsDialog();
            }
        }
    }

    /**
     * 确认所有的权限是否都已授权
     *
     * @param grantResults
     * @return
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 显示提示对话框
     */
    private void showTipsDialog() {
        new android.support.v7.app.AlertDialog.Builder(getActivity())
                .setTitle("提示信息")
                .setMessage("当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                }).show();
    }

    /**
     * 启动当前应用设置页面
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
        startActivity(intent);
    }

    /**
     * 获取权限成功
     *
     * @param requestCode
     */
    public void permissionSuccess(int requestCode) {
        Log.d(TAG, "获取权限成功=" + requestCode);

    }

    /**
     * 权限获取失败
     * @param requestCode
     */
    public void permissionFail(int requestCode) {
        Log.d(TAG, "获取权限失败=" + requestCode);
    }
}
