package com.zyl_android.tenderinfo.project.utils;

import android.os.CountDownTimer;

/**
 * Created by bibinet on 2017-11-24.
 * 倒计时类
 */

public class CountDownTimerUtils {
    private static CountDownTimer countDownTimer=null;
    private CounTimeIntevalListioner countTmeinterValListioner;
    public void startCountDownTimer(int taotalTime,int terval){
        if (countDownTimer==null) {
            synchronized (CountDownTimerUtils.class){
                countDownTimer=new CountDownTimer(taotalTime,terval) {
                    @Override
                    public void onTick(long l) {
                        countTmeinterValListioner.onTick(l);
                    }

                    @Override
                    public void onFinish() {
                        countTmeinterValListioner.onFinish();
                    }
                }.start();
            }
        }
    }
    public void setCounTimeIntevalListioner(CounTimeIntevalListioner countTmeinterValListioner){
        this.countTmeinterValListioner=countTmeinterValListioner;
    }
    public interface CounTimeIntevalListioner{
        void onTick(long l);
        void onFinish();
    }
}
