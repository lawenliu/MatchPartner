package com.care.matchpartner;

import android.app.Application;

import com.care.core.Constants;
import com.care.core.SharedDataManager;
import com.flurry.android.FlurryAgent;

/**
 * Created by wliu37 on 7/30/2016.
 */
public class MatchPartnerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Init Flurry
        FlurryAgent.init(this, Constants.FlurryApplicationKey);

        // Init shared data
        SharedDataManager.getInstance().initialize(this);
    }
}
