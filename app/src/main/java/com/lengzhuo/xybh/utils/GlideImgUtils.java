package com.lengzhuo.xybh.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by yjyvi on 2017/9/27.
 */

public class GlideImgUtils {

    /**
     * 加载网络图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImg(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(IconUtils.getPicUrl(context, url))
                .into(imageView);
    }


    /**
     * 加载资源文件
     * @param context
     * @param resourceId
     * @param imageView
     */
    public static void loadImg(Context context, int resourceId, ImageView imageView) {
        Glide.with(context)
                .load(resourceId)
                .into(imageView);
    }


    /**
     * 加载本地路径图片
     * @param context
     * @param locaFilePath
     * @param imageView
     */
    public static void loadLocaImg(Context context, String locaFilePath, ImageView imageView) {
        Glide.with(context)
                .load(locaFilePath)
                .into(imageView);
    }

}
