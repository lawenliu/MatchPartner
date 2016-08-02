package com.care.core;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wliu37 on 7/30/2016.
 */
public class SharedDataManager {
    private static SharedDataManager instance = null;

    private SharedPreferences mPrefs = null;
    private SharedPreferences.Editor mEditor = null;

    public static SharedDataManager getInstance() {
        if(instance == null) {
            instance = new SharedDataManager();
        }

        return instance;
    }

    public void initialize(Context context) {
        mPrefs = context.getSharedPreferences(Constants.SharedPreferenceFileName, Context.MODE_PRIVATE);
        mEditor = this.mPrefs.edit();
    }

    public int isAntiMosquitoTurnedOn() {
        if(mPrefs != null) {
            return mPrefs.getInt(Constants.KeyMaxScore, 0);
        }

        return 0;
    }

    public void setMaxScore(int newValue) {
        if(mEditor != null) {
            mEditor.putInt(Constants.KeyMaxScore, newValue);
            mEditor.commit();
        }
    }

    public int getCurrentBackgroundIndex() {
        if(mPrefs != null) {
            return mPrefs.getInt(Constants.KeyCurrentBackgroundIndex, 0);
        }

        return 0;
    }

    public void setCurrentBackgroundIndex(int newValue) {
        if(mEditor != null) {
            mEditor.putInt(Constants.KeyCurrentBackgroundIndex, newValue);
            mEditor.commit();
        }
    }
}
