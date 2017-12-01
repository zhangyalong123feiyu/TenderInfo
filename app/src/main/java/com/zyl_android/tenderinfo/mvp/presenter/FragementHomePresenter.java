package com.zyl_android.tenderinfo.mvp.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.zyl_android.tenderinfo.mvp.base.basepresenter.BasePresenter;
import com.zyl_android.tenderinfo.mvp.model.FragementHomeModel;
import com.zyl_android.tenderinfo.mvp.view.FragmentHomeView;
import com.zyl_android.tenderinfo.project.application.CachePath;
import com.zyl_android.tenderinfo.project.bean.BannerBean;
import com.zyl_android.tenderinfo.project.bean.HomeFiveProjectBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by bibinet on 2017-11-6.
 */

public class FragementHomePresenter extends BasePresenter {
    private FragmentHomeView fragmentHomeView;
    private FragementHomeModel fragementHomeModel;
    public FragementHomePresenter(FragmentHomeView fragmentHomeView){
        this.fragmentHomeView=fragmentHomeView;
        this.fragementHomeModel=new FragementHomeModel();
    }
    public void getBannerData(){
        Subscription subscription=fragementHomeModel.getBannerData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new Observer<BannerBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        fragmentHomeView.onGetBannerFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(BannerBean bannerBean) {
                        fragmentHomeView.onGetBannerSucess(bannerBean.getItem());
                    }
                }
        );
        addSubScription(subscription);
    }
    public void getHomeProjectData(String pageNum,String location){
        Subscription subscription=fragementHomeModel.getFragementHomeData(pageNum,location).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<HomeFiveProjectBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            fragmentHomeView.onGetHomeDataFailed(e.getMessage());
            }

            @Override
            public void onNext(HomeFiveProjectBean homeFiveProjectBean) {
            fragmentHomeView.onGetHomeDataSucess(homeFiveProjectBean.getItems());
            }
        });
        addSubScription(subscription);
    }
    public void getHomeTenderData(String pageNum,String location){
        Subscription subscription=fragementHomeModel.getFragementHomeTenderData(pageNum,location).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<HomeFiveProjectBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            fragmentHomeView.onGetHomeTenderFailed(e.getMessage());
            }

            @Override
            public void onNext(HomeFiveProjectBean homeFiveProjectBean) {
            fragmentHomeView.onGetHomeTenderSucess(homeFiveProjectBean.getItems());
            }
        });
        addSubScription(subscription);
    }
    public void getHomeBuyProjectInfo(String pageNum,String location){
        Subscription subscription=fragementHomeModel.getFragementHomeBuyData(pageNum,location).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<HomeFiveProjectBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                fragmentHomeView.onGetHomeBuyFailed(e.getMessage());
            }

            @Override
            public void onNext(HomeFiveProjectBean homeFiveProjectBean) {
                fragmentHomeView.onGetHomeBuySucess(homeFiveProjectBean.getItems());
            }
        });
        addSubScription(subscription);
    }
    public void downLoadImage(String url){
        fragementHomeModel.downLoadImage(url).subscribeOn(Schedulers.io()).map(new Func1<ResponseBody, Bitmap>() {
            @Override
            public Bitmap call(ResponseBody response) {
                if (DownloadImage(response)) {
                    Bitmap bitmap = BitmapFactory.decodeFile(CachePath.IMAGE_PATH + File.separator + "AndroidTutorialPoint.png");
                    return bitmap;//返回一个bitmap对象
                		}
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                    fragmentHomeView.onDownLoadImageSucess(bitmap);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        fragmentHomeView.onGetHomeDataFailed(throwable.getMessage());
                    }
                }
        );
    }
    private boolean DownloadImage(ResponseBody body) {
        try {
            InputStream in = null;
            FileOutputStream out = null;

            try {
                in = body.byteStream();
                out = new FileOutputStream(CachePath.IMAGE_PATH + File.separator + "AndroidTutorialPoint.jpg");
                int c;

                while ((c = in.read()) != -1) {
                    out.write(c);
                }
            }
            catch (IOException e) {
                return false;
            }
            finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
