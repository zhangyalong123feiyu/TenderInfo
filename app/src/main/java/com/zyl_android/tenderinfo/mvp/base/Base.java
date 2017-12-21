package com.zyl_android.tenderinfo.mvp.base;

import com.google.gson.Gson;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by bibinet on 2017-12-19.
 */

public class Base {
    public static class BaseModel{
        protected final Request request;
        protected final OkHttpClient okHttpClient;

        public BaseModel(String url) {
            okHttpClient=new OkHttpClient();
            request=new Request.Builder().url(url).build();
        }
    }

    public static class BasePresneter{
        protected final Gson gson;
        public BasePresneter() {
            gson=new Gson();
        }
    }
    public static class BaseView<T>{
       public void onLoadSucess(T t){};
        public void onLoadSucess(List<T> tList){};
        public  void onLoadFailed(String msg){};

    }
}
