package com.custom.view.gallerydemo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ClipViewPager extends ViewPager {
    public ClipViewPager(Context context) {
        super(context);
    }

    public ClipViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
//            根据手指触摸点，得到你所触摸的View
            View view = viewoOfClickOnScreen(ev);
            if (view != null) {
//                根据你所触摸的View，得到你所触摸的View的位置
                int index = indexOfChild(view);
                if (getCurrentItem() != index) {
//                    跳转到这个View
                    setCurrentItem(indexOfChild(view));
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 找到屏幕上显示的所有子条目，得到他们在屏幕中的坐标，与手指触摸点比对，返回你所点到的View
     * @param ev
     * @return
     */
    private View viewoOfClickOnScreen(MotionEvent ev) {
        int childCount = getChildCount();

        int[] location = new int[2];


        for (int i = 0; i < childCount; i++) {
            //        遍历每一个ViewPager的子View
            View v = getChildAt(i);
//            得到子View在屏幕上的坐标值，存在location中
            v.getLocationOnScreen(location);
//          注意：这个X坐标为，View的最左侧坐标
            int minX = location[0];
            int minY = getTop();
//              得到view的最右侧坐标
            int maxX = location[0] + v.getWidth();
            int maxY = getBottom();
//          手指触摸的坐标
            float x = ev.getX();
            float y = ev.getY();
//          手指触摸坐标，在哪一个子View的范围之内，那么就返回这个View
            if ((x > minX && x < maxX) && (y > minY && y < maxY)) {
                return v;
            }
        }
        return null;
    }
}

