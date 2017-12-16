package com.zyl_android.tenderinfo.project.api;


import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by bibinet on 2017-12-12.
 */

public interface DownLoadApi {
    @Streaming
    @GET
    Observable<ResponseBody> downLoadApk(@Url String url);

}
