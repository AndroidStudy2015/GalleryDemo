package com.custom.view.gallerydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
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

        mRecAdapter = new RecAdapter(this);
        mRecyclerview.setAdapter(mRecAdapter);
        mLl1 = findViewById(R.id.ll1);
        final LinearSnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(mRecyclerview);
        mRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {


                    View snapView = helper.findSnapView(manager);
//                    snapView.setScaleX(1.0f);
//                    snapView.setScaleY(1.0f);
//                    snapView.setScaleX(0.75f);
//                    snapView.setScaleY(0.75f);
                }

                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    int childCount = mRecyclerview.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View childAt = mRecyclerview.getChildAt(i);
//                        childAt.setScaleX(0.75f);
//                        childAt.setScaleY(0.75f);

                    }


                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                int childCount = mRecyclerview.getChildCount();
                Log.e("ccc", childCount + "");

                int[] location = new int[2];
                for (int i = 0; i < childCount; i++) {
                    View v = mRecyclerview.getChildAt(i);
                    v.getLocationOnScreen(location);

                    int minX = location[0];
                    int minY = mRecyclerview.getTop();

                    int maxX = location[0] + v.getWidth();
                    int maxY = mRecyclerview.getBottom();

                    int recyclerViewCenterX = mRecyclerview.getLeft()+(mRecyclerview.getRight() - mRecyclerview.getLeft()) / 2;
                    int centerX= location[0] + v.getWidth()/2;


//                    int abs = Math.abs(recyclerViewCenterX - centerX);
                    int dis = centerX-recyclerViewCenterX  ;
                    float percent = Math.abs(dis *0.17f/ 350.0f);
                    double scale = 1-percent;
//if (scale<0.9){
//    scale=0.9;
//}
                    v.setScaleX((float) (scale));
                    v.setScaleY((float) (scale));

                    Log.e("qwe",recyclerViewCenterX+"///"+centerX+"///"+scale+"///"+percent+"///"+i);
                    Log.e("qwe","-----");

                }
                Log.e("qwe","====================");

            }

        });
        mLl1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mRecyclerview.dispatchTouchEvent(event);
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
