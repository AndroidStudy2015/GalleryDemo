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

    public RecAdapter(Context context, int recyclerViewWidth) {
        mContext = context;
        mRecyclerViewWidth = recyclerViewWidth;


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
    public void onBindViewHolder(VH holder, int position) {
        holder.iv.setImageResource(imageIdArray[position]);
        ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) holder.itemRoot.getLayoutParams();
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
