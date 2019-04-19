package com.example.jay.packageandroidproject.util;

import android.app.Activity;
import android.content.Context;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.tools.PictureFileUtils;

public class CameraUtil {
    public static void openPicture4All(Activity activity){
        PictureSelector.create(activity).openGallery(PictureMimeType.ofAll())
                .maxSelectNum(8)
                .minSelectNum(1)
                .imageSpanCount(4)
                .sizeMultiplier(0.5f)
                .previewImage(true)
                .previewVideo(true)
                .isCamera(false)
                .forResult(PictureConfig.TYPE_ALL);
    }

    public static void takeSinglePhoto(Activity activity){
        PictureSelector.create(activity).openCamera(PictureMimeType.ofImage())
                .imageFormat(PictureMimeType.PNG)
                .forResult(PictureConfig.TYPE_CAMERA);
    }

    public static void takeVideo(Activity activity){
        PictureSelector.create(activity).openCamera(PictureMimeType.ofVideo())
                .recordVideoSecond(10)
                .forResult(PictureConfig.TYPE_VIDEO);
    }

    public static void open4Audio(Activity activity){
        PictureSelector.create(activity).openGallery(PictureMimeType.ofAudio())
                .forResult(PictureConfig.TYPE_AUDIO);
    }

    public static void clearPictureCache(Context mCtx){
        //包括裁剪和压缩后的缓存，要在上传成功后调用，注意：需要系统sd卡权限
        PictureFileUtils.deleteCacheDirFile(mCtx);
    }
}
