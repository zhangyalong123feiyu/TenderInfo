package com.zyl_android.tenderinfo.project.utils;

import android.content.Context;
import android.os.Environment;

import com.zyl_android.tenderinfo.project.application.TenderApplication;

/**
 * Created by bibinet on 2017-11-7.
 */

public class CacheUtil {
    /**
     * 获取app缓存路径
     * @param
     * @return
     */
    public static String getCachePath( ){
        String cachePath ;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            //外部存储可用
            cachePath = TenderApplication.context.getExternalCacheDir().getPath() ;
        }else {
            //外部存储不可用
            cachePath = TenderApplication.context.getCacheDir().getPath() ;
        }
        return cachePath ;
    }
}
