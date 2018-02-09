package com.lengzhuo.xybh.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.lengzhuo.xybh.R;

/**
 * Created by yjyvi on 2017/9/27.
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
        Drawable imgDrawable = ContextCompat.getDrawable(context, R.mipmap.no_img);
        Drawable loadDrawable = ContextCompat.getDrawable(context, R.color.gray_text);

        GlideApp.with(context)
                .load(IconUtils.getPicUrl(context, url))
//                .placeholder(loadDrawable)
//                .error(imgDrawable)
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
        Drawable imgDrawable = ContextCompat.getDrawable(context, R.mipmap.no_img);
        Drawable loadDrawable = ContextCompat.getDrawable(context, R.color.gray_text);

        GlideApp.with(context)
                .load(resourceId)
                .placeholder(loadDrawable)
                .error(imgDrawable)
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
        Drawable imgDrawable = ContextCompat.getDrawable(context, R.mipmap.no_img);
        Drawable loadDrawable = ContextCompat.getDrawable(context, R.color.gray_text);

        GlideApp.with(context)
                .load(localFilePath)
                .placeholder(loadDrawable)
                .error(imgDrawable)
                .into(imageView);
    }

}
