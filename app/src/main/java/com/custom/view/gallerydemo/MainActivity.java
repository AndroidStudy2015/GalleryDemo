package com.custom.view.gallerydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    private ClipViewPager mViewPager;
    private LinearLayout mLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        ClipAdapter adapter = new ClipAdapter(this);

        mViewPager = findViewById(R.id.viewpager);
        mLl = findViewById(R.id.ll);
        mViewPager.setPageMargin(0);//设置间距
//        mViewPager.setPadding(30,30,30,30);//设置间距
        mViewPager.setOffscreenPageLimit(8);//设置缓存数量
        mViewPager.setAdapter(adapter);
        mViewPager.setPageTransformer(false, new ScaleVieapagerTransform());
        mLl.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.dispatchTouchEvent(event);
            }
        });
    }


}
