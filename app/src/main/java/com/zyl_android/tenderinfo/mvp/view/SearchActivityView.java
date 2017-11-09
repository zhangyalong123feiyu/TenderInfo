package com.zyl_android.tenderinfo.mvp.view;

import com.zyl_android.tenderinfo.project.bean.HotWordsBean;

import java.util.List;

/**
 * Created by bibinet on 2017-11-9.
 */

public interface SearchActivityView {
    void onGetHotWordsSucess(List<String> hotMsg);
    void onGetHotWordsFailed(String msg);
}
