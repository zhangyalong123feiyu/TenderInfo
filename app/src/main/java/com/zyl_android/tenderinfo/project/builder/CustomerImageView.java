package com.zyl_android.tenderinfo.project.builder;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by bibinet on 2017-12-7.
 */

public class CustomerImageView extends AppCompatImageView {
    private int startX;
    private int startY;
    public CustomerImageView(Context context) {
        super(context);
    }

    public CustomerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        	switch (event.getAction()) {
        			case MotionEvent.ACTION_DOWN:
        				startX=(int)event.getRawX();
        				startX=(int)event.getRawY();
        				break;
        	        case MotionEvent.ACTION_MOVE:
                        int endX = (int)event.getRawX();
                        int endY = (int)event.getRawY();
                        if (Math.abs(endX-startX)> Math.abs(endY-startY)) {//滚动方向为左右
                                return false;
                        		}else {//滚动方向为上下
                        }

        	            break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
        			default:
        				break;
        			}

        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
