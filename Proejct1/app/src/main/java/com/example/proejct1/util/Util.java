package com.example.proejct1.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.proejct1.R;
import com.example.proejct1.model.Sex;
import com.example.proejct1.model.University;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Util {

    public static SharedPreferences pref;

    public static String getStrWithHashTag(String value) {
        return "#" + value + " ";
    }

    public static String getStrWithHashTag(Sex value) {
        return "#" + value.korName() + " ";
    }

    public static String getStrWithHashTag(int value) {
        return "#" + value + " ";
    }

    public static String getStrWithHashTag(University value) {
        return "#" + value.korName() + " ";
    }

    public static String readRawTxt(Context context, int rawId) {
        String data = null;
        InputStream inputStream = context.getResources().openRawResource(rawId);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1) {
                //if(i == 64) {byteArrayOutputStream.write('\n');byteArrayOutputStream.write('\n');}
                //else
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            data = byteArrayOutputStream.toString("UTF-8");

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void saveData(Activity activity, String key, String value) {
        if (pref == null) {
            pref = activity.getPreferences(Activity.MODE_PRIVATE);
        }
        pref.edit().putString(key, value).commit();
    }

    public static void saveData(Activity activity, String key, int value) {
        if (pref == null) {
            pref = activity.getPreferences(Activity.MODE_PRIVATE);
        }
        pref.edit().putInt(key, value).commit();
    }

    public static String getData(Activity activity, String key, String defaultValue) {
        if (pref == null) {
            pref = activity.getPreferences(Activity.MODE_PRIVATE);
        }
        return pref.getString(key, defaultValue);
    }

    public static int getData(Activity activity, String key, int defaultValue) {
        if (pref == null) {
            pref = activity.getPreferences(Activity.MODE_PRIVATE);
        }
        return pref.getInt(key, defaultValue);
    }
}
