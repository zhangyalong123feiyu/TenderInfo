package com.zyl_android.tenderinfo.project.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by bibinet on 2017-11-4.
 */

public class TenderApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }

}
