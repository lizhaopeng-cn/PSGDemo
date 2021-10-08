package com.yostar.appv2;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class MetaUtils {
    public static String getMetaDataFromApp(Activity activity, String key) {
        String value = "";
        try {
            ApplicationInfo appInfo = activity.getPackageManager().getApplicationInfo(activity.getPackageName(),
                    PackageManager.GET_META_DATA);
            value = appInfo.metaData.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void setMetaDataFromApp(Activity activity, String key, String value) {
        try {
            ApplicationInfo appInfo = activity.getPackageManager().getApplicationInfo(activity.getPackageName(),
                    PackageManager.GET_META_DATA);
            Bundle bundle = appInfo.metaData;
            bundle.putString(key, value);
            appInfo.metaData = bundle;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
