package com.care.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.care.matchpartner.ImageItemView;

/**
 * Created by wliu37 on 7/30/2016.
 */
public class ImageAdapter extends BaseAdapter {

    private int mNumberItems = 0;
    private Context mContext;

    public ImageAdapter(Context context) {
        super();

        mContext = context;
        mNumberItems = ImageItemView.getThumbNumber();
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageItemView itemView = (ImageItemView)convertView;

        if (itemView == null || itemView.getCurrentIndex() != position) {
            itemView = new ImageItemView(mContext);
            itemView.setArguments(position);
        }

        return itemView;
    }

    @Override
    public int getCount() {
        return mNumberItems;
    }
}
