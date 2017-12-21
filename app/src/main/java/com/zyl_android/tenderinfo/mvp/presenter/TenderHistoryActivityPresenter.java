package com.zyl_android.tenderinfo.mvp.presenter;

import android.app.Activity;

import com.google.gson.Gson;
import com.zyl_android.tenderinfo.mvp.model.TenderHelpHistoryActivityModel;
import com.zyl_android.tenderinfo.mvp.view.TenderHelpHistoryActivityView;
import com.zyl_android.tenderinfo.project.bean.DeleteHistoryBean;
import com.zyl_android.tenderinfo.project.bean.HelpTenderHistoryReusltBean;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by bibinet on 2017-12-18.
 */

public class TenderHistoryActivityPresenter {
    private TenderHelpHistoryActivityModel tenderHelpHistoryActivityModel;
    private TenderHelpHistoryActivityView tenderHelpHistoryActivityView;

    public TenderHistoryActivityPresenter(TenderHelpHistoryActivityView tenderHelpHistoryActivityView) {
        this.tenderHelpHistoryActivityView = tenderHelpHistoryActivityView;
        this.tenderHelpHistoryActivityModel=new TenderHelpHistoryActivityModel();
    }
    public void getTenderHistoryData(final Activity activity, String customerId, int pageNumb){
        tenderHelpHistoryActivityModel.getTenderderHistoryData(customerId, pageNumb, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tenderHelpHistoryActivityView.onLoadTenderHistoryDataFailed(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                Gson gson=new Gson();
                final HelpTenderHistoryReusltBean helpHistoryInfo = gson.fromJson(response.body().string(), HelpTenderHistoryReusltBean.class);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tenderHelpHistoryActivityView.onLoadTenderHistoryDataSucess(helpHistoryInfo.getItem());
                    }
                });
            }
        });
    }
    public void deleteHistoryData(final Activity activity, String objectid){
        tenderHelpHistoryActivityModel.deleteHistoryData(objectid, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tenderHelpHistoryActivityView.onDeleteHistoryFailed(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson=new Gson();
                final DeleteHistoryBean deleteInfo = gson.fromJson(response.body().string(), DeleteHistoryBean.class);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tenderHelpHistoryActivityView.onDeleteHistorySucess(deleteInfo);
                    }
                });
            }
        });

    }
}
