package com.care.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.care.matchpartner.GameItemView;
import com.care.matchpartner.R;

import java.util.Dictionary;
import java.util.HashMap;

public class GameAdapter extends BaseAdapter {

    private static HashMap<Integer, Integer> bkColors = new HashMap<>();
    private static HashMap<Integer, Integer> partners = new HashMap<>();
    private static HashMap<Integer, View> partnerViews = new HashMap<>();

    private Integer[] mThumbIds = {
            R.drawable.p11, 0, 0, 0, 0,
            R.drawable.p32, 0, 0, 0, R.drawable.p12,
            0, 0, R.drawable.p31, 0, R.drawable.p41,
            0, 0, 0, R.drawable.p42, R.drawable.p21,
            0, 0, 0, 0, R.drawable.p22,
    };

    private Context mContext;
    private int mRoadOn;

    public GameAdapter(Context context) {
        super();

        mContext = context;
        initBKColors();
        initPartners();
        initPartnerViews();
        initThumbs();
        mRoadOn = (int)partners.keySet().toArray()[0];
    }

    private void initBKColors() {
        bkColors.put(R.drawable.p11, 0xFF00FF00);
        bkColors.put(R.drawable.p12, 0xFF00FF00);
        bkColors.put(R.drawable.p21, 0xFF0000FF);
        bkColors.put(R.drawable.p22, 0xFF0000FF);
        bkColors.put(R.drawable.p31, 0xFFFF0000);
        bkColors.put(R.drawable.p32, 0xFFFF0000);
        bkColors.put(R.drawable.p41, 0xFFFFFFFF);
        bkColors.put(R.drawable.p42, 0xFFFFFFFF);
    }

    private void initPartners() {
        partners.put(R.drawable.p11, R.drawable.p12);
        partners.put(R.drawable.p12, R.drawable.p11);
        partners.put(R.drawable.p21, R.drawable.p22);
        partners.put(R.drawable.p22, R.drawable.p21);
        partners.put(R.drawable.p31, R.drawable.p32);
        partners.put(R.drawable.p32, R.drawable.p31);
        partners.put(R.drawable.p41, R.drawable.p42);
        partners.put(R.drawable.p42, R.drawable.p41);
    }

    private void initPartnerViews() {
        partnerViews.put(R.drawable.p11, null);
        partnerViews.put(R.drawable.p12, null);
        partnerViews.put(R.drawable.p21, null);
        partnerViews.put(R.drawable.p22, null);
        partnerViews.put(R.drawable.p31, null);
        partnerViews.put(R.drawable.p32, null);
        partnerViews.put(R.drawable.p41, null);
        partnerViews.put(R.drawable.p42, null);
    }

    private void initThumbs() {
        //mThumbIds = null;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        GameItemView itemView = (GameItemView)convertView;

        if (itemView == null || itemView.getCurrentIndex() != position) {
            itemView = new GameItemView(mContext);
            itemView.setArguments(position, mThumbIds[position]);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (partners.keySet().contains(mThumbIds[position])) {
                        mRoadOn = mThumbIds[position];
                        view.setBackgroundColor(bkColors.get(mRoadOn));
                        partnerViews.get(mThumbIds[position]).setBackgroundColor(bkColors.get(mRoadOn));
                    }

                    view.setBackgroundColor(bkColors.get(mRoadOn));
                }
            });

            if (partners.keySet().contains(mThumbIds[position])) {
                partnerViews.put(partners.get(mThumbIds[position]), itemView);
            }
        }

        return itemView;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }
}