package com.custom.view.gallerydemo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
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
            View view = viewoOfClickOnScreen(ev);
            if (view != null) {
                int index = indexOfChild(view);
                if (getCurrentItem() != index) {
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
        Log.e("ccc",childCount+"");

        int[] location = new int[2];
        for (int i = 0; i < childCount; i++) {
            View v = getChildAt(i);
            v.getLocationOnScreen(location);

            int minX = location[0];
            int minY = getTop();

            int maxX = location[0] + v.getWidth();
            int maxY = getBottom();

            float x = ev.getX();
            float y = ev.getY();

            if ((x > minX && x < maxX) && (y > minY && y < maxY)) {
                return v;
            }
        }
        return null;
    }
}

