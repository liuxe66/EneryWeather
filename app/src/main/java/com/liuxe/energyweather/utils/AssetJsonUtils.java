package com.liuxe.energyweather.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 作者：liji on 2017/7/24 06:42
 * 邮箱：lijiwork@sina.com
 * QQ ：275137657
 */

public class AssetJsonUtils {
    
    String cityJsonStr = "";
    
    //读取方法
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
