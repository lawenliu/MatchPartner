package com.care.matchpartner;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by wliu37 on 7/30/2016.
 */
public class ImageItemView extends LinearLayout {

    public static Integer[] ThumbIds = {
            R.drawable.bk1, R.drawable.bk2,
            R.drawable.bk3, R.drawable.bk4,
            R.drawable.bk5, R.drawable.bk6
    };

    private int mCurrentIndex = 0;

    private ImageView mItemImageView;

    public ImageItemView(Context context) {
        super(context);

        InitView(context);
    }

    public void InitView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_image_item, this);

        mItemImageView = (ImageView) findViewById(R.id.id_bk_image_item);
    }

    public void setArguments(int curIndex) {
        mCurrentIndex = curIndex;
        mItemImageView.setImageResource(ThumbIds[mCurrentIndex]);
    }

    public static int getThumbNumber() {
        return ThumbIds.length;
    }

    public int getCurrentIndex() {
        return mCurrentIndex;
    }
}
