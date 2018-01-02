package com.custom.view.gallerydemo;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by apple on 2017/12/1.
 */

public class ScaleVieapagerTransform implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.75f;


    @Override
    public void transformPage(View view, float position) {

/**           关键是对position的理解:下面的数据来自于三个View的切换
 1.如果当前的这个视图,静止不动在这里,
 那么当前的视图position是0 ，
 他的上一个视图position是-1
 他的下一个视图position是1
 2.此时我们把当前的视图，手指向右滑动，显示出上一个视图
 那么当前的视图position是0  ---->  1
 他的上一个视图position是-1 ---->  0
 他的下一个视图position是1  ---->  2
 2.此时我们把当前的视图，手指左滑动，显示出下一个视图
 那么当前的视图position是0  ----> -1
 他的上一个视图position是-1 ----> -2
 他的下一个视图position是1  ----> 0
 我们着重考虑-1--0--1或者1--0----1这两种区间的变换，
 超出-1（-1----2）和1（1---2）的变换，视觉上是看不见的，一般也是把他们做复位处理
 */
//              打印log
        Log.e("www", view.getTag() + "transformPage: " + position);
        int pageWidth = view.getWidth();

        if (position <= -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
//                        这个视图已经跑到了屏幕的最左侧，可能是2、3、4、直到无穷大
            view.setAlpha(1.0f);
//            view.setBackgroundColor(Color.BLACK);

            view.setScaleX(0.75f);
            view.setScaleY(0.75f);

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
//                        此时的情景是：当前视图的左侧视图 在往右侧滑动(-1--->0)
//                  或者 ： 此时的情景是：当前视图 在往左侧滑动(0--->-1)
//            view.setAlpha(1 + position);
//            view.setTranslationX(0);
//            view.setScaleX(0.5f);
//            view.setScaleY(0.5f);
            view.setAlpha(1.0f);

            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

//            view.setBackgroundColor(Color.BLUE);
        } else if (position <= 1) { // (0,1]
            // Fade the page out.
//                        此时的情景是：当前视图的右侧视图 在往左侧滑动（1--->0）
//                  或者 ： 此时的情景是：当前视图 在往右侧滑动(0--->1)

//            view.setAlpha(1 - position);//这句话表明:当前视图的透明度由1变为0；当前视图的右侧视图透明度由0变为1

            // Counteract the default slide transition
//            view.setTranslationX(pageWidth * -position);
//            view.setBackgroundColor(Color.YELLOW);

            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
            view.setAlpha(1.0f);


        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
//           这个视图已经跑到了屏幕的最右侧，可能是-2、-3、-4、直到负无穷大
//            view.setBackgroundColor(Color.GREEN);

            view.setAlpha(1.0f);


            view.setScaleX(0.75f);
            view.setScaleY(0.75f);
        }

    }

}