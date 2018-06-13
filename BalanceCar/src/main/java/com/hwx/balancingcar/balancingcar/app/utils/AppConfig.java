package com.hwx.balancingcar.balancingcar.app.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v4.content.SharedPreferencesCompat;

import java.io.File;

/**
 * Created in Nov 8, 2015 7:48:11 PM.
 *
 * @author bj
 */
public class AppConfig {

    private static AppConfig appConfig;

    private SharedPreferences preferences;

    /**
     * App根目录.
     */
    public String APP_PATH_ROOT;

    private AppConfig(Context application) {
        preferences = application.getSharedPreferences("HWXSharedPreferences_balancing", Context.MODE_PRIVATE);
        APP_PATH_ROOT = getRootPath(application).getAbsolutePath() + File.separator + "Aerlang";
    }
    /**
     * 得到SD卡根目录.
     */
    public static File getRootPath(Context context) {
        if (sdCardIsAvailable()) {
            return Environment.getExternalStorageDirectory(); // 取得sdcard文件路径
        } else {
            return context.getFilesDir();
        }
    }

    /**
     * SD卡是否可用.
     */
    public static boolean sdCardIsAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = new File(Environment.getExternalStorageDirectory().getPath());
            return sd.canWrite();
        } else
            return false;
    }

    public static AppConfig getInstance(Context application) {
        if (appConfig == null)
            appConfig = new AppConfig(application);
        return appConfig;
    }
    public void putInt(String key, int value) {
        //preferences.edit().putInt(key, value).commit();
        SharedPreferencesCompat.EditorCompat.getInstance().apply(preferences.edit().putInt(key, value));
    }

    public int getInt(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }

    public void putString(String key, String value) {
        //preferences.edit().putString(key, value).commit();
        SharedPreferencesCompat.EditorCompat.getInstance().apply(preferences.edit().putString(key, value));
    }

    public String getString(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    public void putBoolean(String key, boolean value) {
        //preferences.edit().putBoolean(key, value).commit();
        SharedPreferencesCompat.EditorCompat.getInstance().apply(preferences.edit().putBoolean(key, value));
    }

    public boolean getBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    public void putLong(String key, long value) {
        //preferences.edit().putLong(key, value).commit();
        SharedPreferencesCompat.EditorCompat.getInstance().apply(preferences.edit().putLong(key, value));
    }

    public long getLong(String key, long defValue) {
        return preferences.getLong(key, defValue);
    }

    public void putFloat(String key, float value) {
        preferences.edit().putFloat(key, value).commit();
    }

    public float getFloat(String key, float defValue) {
        return preferences.getFloat(key, defValue);
    }
}