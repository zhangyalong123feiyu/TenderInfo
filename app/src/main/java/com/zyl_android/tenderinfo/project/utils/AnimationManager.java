package com.zyl_android.tenderinfo.project.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Created by Wh on 2017-9-15.
 */

public class AnimationManager {
    public static void topToBottom(View view, int pxDis, int duration){
        Animation animation = new TranslateAnimation(0, 0, -pxDis, 0);
        animation.setDuration(duration);
        view.startAnimation(animation);
    }

    public static void bottomToTop(View view, int pxDis, int duration){
        Animation animation = new TranslateAnimation(0, 0, pxDis, 0);
        animation.setDuration(duration);
        view.startAnimation(animation);
    }

    public static void rightToLeft(View view, int pxDis, int duration){
        Animation animation = new TranslateAnimation(pxDis, 0, 0, 0);
        animation.setDuration(duration);
        view.startAnimation(animation);
    }
}
