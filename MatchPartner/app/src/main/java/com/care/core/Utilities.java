package com.care.core;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.care.matchpartner.R;

import java.lang.reflect.Field;
import java.util.Locale;

/**
 * Created by wliu37 on 7/30/2016.
 */
public class Utilities {
    public static void launchAppStoreDetail(Context context) {
        if (context != null) {
            try {
                String link = String.format(Locale.getDefault(), Constants.LinkMarketPackageFormat,
                        context.getPackageName());
                Intent browserIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(link));
                context.startActivity(browserIntent);
            } catch (Exception e) {
                Toast.makeText(context, context.getString(R.string.message_no_store), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
