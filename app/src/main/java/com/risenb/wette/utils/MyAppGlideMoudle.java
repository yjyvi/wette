package com.risenb.wette.utils;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * <pre>
 *     author : linzheng
 *     e-mail : 1007687534@qq.com
 *     time   : 2017/08/18
 *     desc   : 此类和 Glide 4 有很大关系，如果出现 GlideApp 找不到类的问题，请 rebuild
 *     version: 1.0
 * </pre>
 */
@GlideModule
public final class MyAppGlideMoudle extends AppGlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
    }

}
