package com.care.matchpartner;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.care.adapter.GameAdapter;
import com.care.core.Constants;
import com.care.core.SharedDataManager;
import com.care.core.Utilities;
import com.flurry.android.FlurryAgent;
import com.wandoujia.ads.sdk.Ads;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayerMenu = null;
    private BaseAdapter mGameAdapter = null;
    private GridView mGameGridView = null;

    private RelativeLayout mMainActivity = null;
    private ImageView mMenuImageView = null;

    private PopupWindow mPopupMenu = null;
    private TextView mBkPickTextView = null;
    private TextView mToolbarTextView = null;
    private TextView mRateUsTextView = null;

    private RelativeLayout mAdBannerLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainActivity = (RelativeLayout) findViewById(R.id.id_main_activity);
        mMenuImageView = (ImageView) findViewById(R.id.id_btn_menu);
        mGameGridView = (GridView) findViewById(R.id.id_game_gridview);

        // Flurry Initialize
        mAdBannerLayout = (RelativeLayout) findViewById(R.id.id_ad_banner);

        mMediaPlayerMenu = MediaPlayer.create(this, R.raw.btn_sound_1);
        mMediaPlayerMenu.setLooping(false);

        initBackground();
        initPopupMenu(this);
        initializeAds();
        initializeGame();
    }

    private void initializeGame() {


        mGameAdapter = new GameAdapter(this);
        mGameGridView.setAdapter(mGameAdapter);
    }

    private void initializeAds() {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    Ads.init(MainActivity.this, Constants.WANDOUJIA_APP_ID, Constants.WANDOUJIA_SECRET_KEY);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean success) {
                if (success) {
                    Ads.preLoad(Constants.WANDOUJIA_BANNER_ID, Ads.AdFormat.banner);
                    View bannerView = Ads.createBannerView(MainActivity.this, Constants.WANDOUJIA_BANNER_ID);
                    mAdBannerLayout.addView(bannerView, new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));

                }
            }
        }.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();

        initBackground();
        FlurryAgent.onStartSession(this);
    }

    @Override
    public void onStop() {
        FlurryAgent.onEndSession(this);

        super.onStop();
    }


    private void initBackground() {
        int indexBK = SharedDataManager.getInstance().getCurrentBackgroundIndex();
        mMainActivity.setBackgroundResource(Utilities.getResId("bk" + (indexBK + 1), R.drawable.class));
    }

    private void initPopupMenu(final Context context) {
        View menuContent = View.inflate(this, R.layout.menu_popup, null);
        mBkPickTextView = (TextView)menuContent.findViewById(R.id.menu_color_screen);
        mToolbarTextView = (TextView)menuContent.findViewById(R.id.menu_tool_bar);
        mRateUsTextView = (TextView)menuContent.findViewById(R.id.menu_rate_us);

        mPopupMenu = new PopupWindow(menuContent, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupMenu.setFocusable(true);
        mPopupMenu.setOutsideTouchable(true);
        mPopupMenu.setBackgroundDrawable(new ColorDrawable());
        mPopupMenu.update();

        mMenuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAndDismissPopupMenu();
                mMediaPlayerMenu.start();
            }
        });
        mBkPickTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAndDismissPopupMenu();
                Intent intent = new Intent(context, BkImageSelectActivity.class);
                startActivity(intent);
            }
        });
        mToolbarTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mRateUsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAndDismissPopupMenu();
                Utilities.launchAppStoreDetail(context);
            }
        });
    }


    private void showAndDismissPopupMenu() {
        if(mPopupMenu.isShowing()) {
            mPopupMenu.dismiss();
        } else {
            mPopupMenu.showAsDropDown(mMenuImageView);
        }
    }
}
