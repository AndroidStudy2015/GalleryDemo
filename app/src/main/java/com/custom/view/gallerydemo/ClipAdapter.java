package com.custom.view.gallerydemo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by apple on 2018/1/2.
 */

public class ClipAdapter extends PagerAdapter {

    // 准备要显示的图片资源
    private int[] imageIdArray = { R.drawable.iv1, R.drawable.iv2,
            R.drawable.iv3, R.drawable.iv4 ,R.drawable.iv1, R.drawable.iv2,
            R.drawable.iv3, R.drawable.iv4 };

    private Context context;

    public ClipAdapter(Context context) {
        this.context = context;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getCount() {
        return imageIdArray.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View
                .inflate(context, R.layout.viewpager_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv);
        imageView.setImageResource(imageIdArray[position]);
        // ★★★这句话很重要！！！别忘了写！！！
        ((ViewPager) container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        // ★★★这句话很重要！！！别忘了写！！！
        ((ViewPager) container).removeView((View) object);
    }
}

