package com.zyl_android.tenderinfo.project.application;

import android.os.Environment;

import java.io.File;

/**
 * Created by bibinet on 2017-11-24.
 */

public class CachePath {
    public static String MAIN_PATH= Environment.getExternalStorageDirectory().getAbsolutePath()+"/TenderInfo";
    //更新安装包
    public static String PACKGE_PATH=MAIN_PATH+"/DownloadPackage";
    //图片缓存地址
    public static String IMAGE_PATH=MAIN_PATH+"/CacePic";
    //HTTP缓存
    public static String HTTPCACHE_PATH=MAIN_PATH+"/HttpCache";
    public static void createDirectoy(){
        if (!new File(PACKGE_PATH).exists()) {
        			new File(PACKGE_PATH).mkdirs();
        		}
        if (!new File(IMAGE_PATH).exists()) {
        			new File(IMAGE_PATH).mkdirs();
        		}
        if (!new File(HTTPCACHE_PATH).exists()) {
                    new File(HTTPCACHE_PATH).mkdirs();
               }
    }
}
