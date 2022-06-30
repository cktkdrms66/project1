package com.example.proejct1.util;

import android.content.Context;

import com.example.proejct1.R;
import com.example.proejct1.model.Sex;
import com.example.proejct1.model.University;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class util {
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
}
