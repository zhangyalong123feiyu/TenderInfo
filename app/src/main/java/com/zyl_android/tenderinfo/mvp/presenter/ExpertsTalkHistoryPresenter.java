package com.zyl_android.tenderinfo.mvp.presenter;

import android.app.Activity;

import com.google.gson.Gson;
import com.zyl_android.tenderinfo.mvp.model.ExpertsTalkHistoryActivityModel;
import com.zyl_android.tenderinfo.mvp.view.ExpertsTalkHistoryActivityView;
import com.zyl_android.tenderinfo.project.bean.ExpertsAskAnswerResultBean;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by bibinet on 2017-12-15.
 */

public class ExpertsTalkHistoryPresenter {
    private ExpertsTalkHistoryActivityView expertsTalkHistoryActivityView;
    private ExpertsTalkHistoryActivityModel expertsTalkHistoryActivityModel;

    public ExpertsTalkHistoryPresenter(ExpertsTalkHistoryActivityView expertsTalkHistoryActivityView) {
        this.expertsTalkHistoryActivityView = expertsTalkHistoryActivityView;
        this.expertsTalkHistoryActivityModel=new ExpertsTalkHistoryActivityModel();
    }

    public void getHistoryData(final Activity activity, String userId, int pageNum){
        expertsTalkHistoryActivityModel.getExptertsTalkHistory(userId, pageNum, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        expertsTalkHistoryActivityView.onLodaTalkHistoryFailed(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string=response.body().string();
                Gson gson=new Gson();
                final ExpertsAskAnswerResultBean expertsAnswerInfo = gson.fromJson(string, ExpertsAskAnswerResultBean.class);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        expertsTalkHistoryActivityView.onLodaTalkHistorySucess(expertsAnswerInfo.getItems());
                    }
                });
            }
        });
    }
}
