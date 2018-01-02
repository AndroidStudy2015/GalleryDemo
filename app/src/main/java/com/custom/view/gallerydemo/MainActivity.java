package com.custom.view.gallerydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    private ClipViewPager mViewPager;
    private LinearLayout mLl;
    private RecyclerView mRecyclerview;
    private RecAdapter mRecAdapter;
    private LinearLayout mLl1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        extendsViewPager();
        extendsRecyclerView();
    }

    private void extendsRecyclerView() {
        mRecyclerview = findViewById(R.id.recyclerview);


        final LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        mRecyclerview.setLayoutManager(manager);


        int mRecyclerviewWidth = 0;
        ViewGroup.LayoutParams layoutParams = mRecyclerview.getLayoutParams();
        if (layoutParams.width == -1) {
            mRecyclerviewWidth = DisplayUtils.getScreenWidth(this);
        } else {
            mRecyclerviewWidth = layoutParams.width;
        }
        mRecAdapter = new RecAdapter(this, mRecyclerviewWidth);
        mRecyclerview.setAdapter(mRecAdapter);
        mLl1 = findViewById(R.id.ll1);
        final LinearSnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(mRecyclerview);
        mRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                int childCount = mRecyclerview.getChildCount();
                Log.e("ccc", childCount + "");

                int[] location = new int[2];
                for (int i = 0; i < childCount; i++) {
                    View v = mRecyclerview.getChildAt(i);
                    v.getLocationOnScreen(location);


                    int recyclerViewCenterX = mRecyclerview.getLeft() + mRecyclerview.getWidth() / 2;
                    int itemCenterX = location[0] + v.getWidth() / 2;

//                   ★ 两边的图片缩放比例
                    float scale = 0.9f;
//                     ★某个item中心X坐标距recyclerview中心X坐标的偏移量
                    int offX =  Math.abs(itemCenterX - recyclerViewCenterX);
//                    ★ 在一个item的宽度范围内，item从1缩放至scale，那么改变了（1-scale），从下列公式算出随着offX变化，item的变化缩放百分比

                    float percent =offX * (1 - scale) / v.getWidth();
//                   ★  取反哟
                    float interpretateScale = 1 - percent;


//                    这个if不走的话，得到的是多级渐变模式
                    if (interpretateScale < scale) {
                        interpretateScale = scale;
                    }
                    v.setScaleX((interpretateScale));
                    v.setScaleY((interpretateScale));

                    Log.e("qwe", recyclerViewCenterX + "///" + itemCenterX + "///" + interpretateScale + "///" + percent + "///" + i);
                    Log.e("qwe", "-----");

                }
                Log.e("qwe", "====================");

            }

        });

    }

    private void extendsViewPager() {
        ClipAdapter adapter = new ClipAdapter(this);

        mViewPager = findViewById(R.id.viewpager);
        mLl = findViewById(R.id.ll);
        mViewPager.setPageMargin(15);//设置间距
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
