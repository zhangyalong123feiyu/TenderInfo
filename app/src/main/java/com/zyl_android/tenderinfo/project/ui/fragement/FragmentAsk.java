package com.zyl_android.tenderinfo.project.ui.fragement;


import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zyl_android.generalutils.DialogUtils;
import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.project.sql.UserTable;
import com.zyl_android.tenderinfo.project.ui.activity.ExpertsTalkActivity;
import com.zyl_android.tenderinfo.project.ui.activity.TenderHelpActivity;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseFragement;
import com.zyl_android.tenderinfo.project.utils.CacheUtils.ACache;
import com.zyl_android.tenderinfo.project.utils.DownloadUtil;

import butterknife.BindView;
import butterknife.OnClick;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAsk extends BaseFragement {

    @BindView(R.id.customService)
    LinearLayout customService;
    @BindView(R.id.experts)
    LinearLayout experts;
    @BindView(R.id.tenderBook)
    LinearLayout tenderBook;
    @BindView(R.id.tenderHelp)
    LinearLayout tenderHelp;

    @Override
    protected int getFragementHomeLayout() {
        return R.layout.fragment_ask;
    }

    @Override
    protected void initView() {
        title_textView.setText("比比驿站");
//        title_backImage.setVisibility(View.GONE);
        ACache aCache=ACache.get(getActivity());
        title_backImage.setImageBitmap(aCache.getAsBitmap("banner1"));
    }

    @Override
    protected void initData() {
        //以下为练习litepal所写
        UserTable userTable = new UserTable();
        userTable.setAddress("太原大马村");
        userTable.setCompanyName("山西比比");
        userTable.setUserId(21);
        userTable.setUserName("张亚龙");
        userTable.save();
    }
    @OnClick({R.id.customService, R.id.experts, R.id.tenderBook, R.id.tenderHelp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.customService:
                break;
            case R.id.experts:
                startActivity(new Intent(getActivity(),ExpertsTalkActivity.class));
                break;
            case R.id.tenderBook:
                startDownLoad();
                break;
            case R.id.tenderHelp:
                startActivity(new Intent(getActivity(), TenderHelpActivity.class));
                break;
        }
    }

    private void startDownLoad() {
        DownloadUtil downloadUtil=DownloadUtil.get();
        final DialogUtils dialogUtils=new DialogUtils();
        dialogUtils.diloagShow(getActivity(),R.layout.progress_dialog);
        View view=dialogUtils.getView();
        final ProgressBar progressBar=(ProgressBar)view.findViewById(R.id.progressProBar);
        final TextView textView=(TextView)view.findViewById(R.id.progressText);
        downloadUtil.download(getActivity(),"http://ucenter.utb2013.com/uploads/apk/cn.com.zhwts.apk", Environment.getDataDirectory() + "apk", new DownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess() {
                toast("下载成功");
                dialogUtils.dialogDismiss();
            }

            @Override
            public void onDownloading(int progress) {
                Log.i("下载进度",progress+"");
                progressBar.setProgress(progress);
                textView.setText(progress+"");

            }

            @Override
            public void onDownloadFailed(String error) {
                Log.i("下载失败",error);
            }
        });
    }
}
