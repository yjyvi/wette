package com.lengzhuo.xybh.utils;

import android.content.Context;
import android.widget.ImageView;

/**
 *
 * @author yjyvi
 * @date 2017/9/27
 */

public class GlideImgUtils {


    /**
     * 加载网络图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImg(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .load(IconUtils.getPicUrl(context, url))
                .fitCenter()
                .into(imageView);
    }


    /**
     * 加载资源文件
     *
     * @param context
     * @param resourceId
     * @param imageView
     */
    public static void loadImg(Context context, int resourceId, ImageView imageView) {
        GlideApp.with(context)
                .load(resourceId)
                .fitCenter()
                .into(imageView);
    }


    /**
     * 加载本地路径图片
     *
     * @param context
     * @param localFilePath
     * @param imageView
     */
    public static void loadLocaImg(Context context, String localFilePath, ImageView imageView) {
        GlideApp.with(context)
                .load(localFilePath)
                .fitCenter()
                .into(imageView);
    }

}
