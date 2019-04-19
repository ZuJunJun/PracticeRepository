package com.example.jay.packageandroidproject.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionsUtil {
    public static int REQUEST_DANGROUS_PERMISSIONS = 10000;
    private static List<String> mRequestPermissions = new ArrayList<>();
    private static String[] DANGROUS_PERMISSIONS = {Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_PHONE_STATE,Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public interface NonePermissions2Apply{
       void onNonePermissions2Apply();
    }

    public static void checkXPermissions(Activity mCtx,NonePermissions2Apply nonePermissions2Apply) {

        for (int i = 0; i < DANGROUS_PERMISSIONS.length; i++) {
            if (ContextCompat.checkSelfPermission(mCtx, DANGROUS_PERMISSIONS[i]) != PackageManager.PERMISSION_GRANTED) {
                mRequestPermissions.add(DANGROUS_PERMISSIONS[i]);
            }
        }
        if (mRequestPermissions.size()>0) {
            String[] mPermissionsArr = mRequestPermissions.toArray(new String[mRequestPermissions.size()]);
            ActivityCompat.requestPermissions(mCtx, mPermissionsArr, REQUEST_DANGROUS_PERMISSIONS);
        }else {
           nonePermissions2Apply.onNonePermissions2Apply();
        }
    }

    public static void checkSpecificPermissions(Activity mCtx,String[] mPermissionsArr) {
        ActivityCompat.requestPermissions(mCtx, mPermissionsArr, REQUEST_DANGROUS_PERMISSIONS);
    }

}