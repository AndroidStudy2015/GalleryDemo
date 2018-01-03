package com.custom.view.gallerydemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by apple on 2018/1/2.
 */

class RecAdapter extends RecyclerView.Adapter<RecAdapter.VH> {
    private int mRecyclerViewWidth;
    private Context mContext;
    private RecyclerView mRecyclerView;

    public RecAdapter(Context context, int recyclerViewWidth, RecyclerView recyclerview) {
        mContext = context;
        mRecyclerViewWidth = recyclerViewWidth;
        mRecyclerView = recyclerview;

    }

    // 准备要显示的图片资源
    private int[] imageIdArray = {R.drawable.iv1, R.drawable.iv2,
            R.drawable.iv3, R.drawable.iv4, R.drawable.iv1, R.drawable.iv2,
            R.drawable.iv3, R.drawable.iv4, R.drawable.iv1, R.drawable.iv2,
            R.drawable.iv3, R.drawable.iv4, R.drawable.iv1, R.drawable.iv2,
            R.drawable.iv3, R.drawable.iv4, R.drawable.iv1, R.drawable.iv2,
            R.drawable.iv3, R.drawable.iv4, R.drawable.iv1, R.drawable.iv2,
            R.drawable.iv3, R.drawable.iv4, R.drawable.iv1, R.drawable.iv2,
            R.drawable.iv3, R.drawable.iv4, R.drawable.iv1, R.drawable.iv2,
            R.drawable.iv3, R.drawable.iv4, R.drawable.iv1, R.drawable.iv2,
            R.drawable.iv3, R.drawable.iv4, R.drawable.iv1, R.drawable.iv2,
            R.drawable.iv3, R.drawable.iv4, R.drawable.iv1, R.drawable.iv2,
            R.drawable.iv3, R.drawable.iv4, R.drawable.iv1, R.drawable.iv2,
            R.drawable.iv3, R.drawable.iv4};


    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(mContext);
        View view = from.inflate(R.layout.viewpager_item, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(final VH holder, final int position) {
        holder.iv.setImageResource(imageIdArray[position]);
        final ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) holder.itemRoot.getLayoutParams();
        // 为了居中， 第一个条目leftMagrin、最后一个条目的rightMargin是（recyclerView宽度减去一个条目的宽度）/2
        int margin = (mRecyclerViewWidth - p.width) / 2;
        if (position == 0) {
            p.leftMargin = margin;
            p.rightMargin = 0;
            holder.itemRoot.setLayoutParams(p);
        } else if (position == imageIdArray.length - 1) {
            p.leftMargin = 0;
            p.rightMargin = margin;
            holder.itemRoot.setLayoutParams(p);
        } else {
            p.leftMargin = 0;
            p.rightMargin = 0;
            holder.itemRoot.setLayoutParams(p);

        }


        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] location = new int[2];
                v.getLocationOnScreen(location);
                int currentX = location[0];
                int currentCenterX = (int) (currentX + p.width / 2 * 0.8f);//因为除了中间外的其他条目是被缩放为0.8的状态
                int recyclerViewCenterX = mRecyclerViewWidth / 2;
                int offX = currentCenterX - recyclerViewCenterX;

                if (Math.abs(offX) >p.width / 2 * 0.21f) {//因为已经居中的Item，已经被放大到比例1了
                    mRecyclerView.smoothScrollBy(offX, 0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageIdArray.length;
    }


    class VH extends RecyclerView.ViewHolder {

        private RelativeLayout itemRoot;
        private ImageView iv;

        public VH(View itemView) {
            super(itemView);
            itemRoot = itemView.findViewById(R.id.item_root);

            iv = itemView.findViewById(R.id.iv);
        }
    }

}
