package com.lengzhuo.xybh.utils;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.RequestOptions;
import com.lengzhuo.xybh.R;


/**
 * <pre>
 *     author : linzheng
 *     e-mail : 1007687534@qq.com
 *     time   : 2017/09/21
 *     desc   : Glide 扩展
 *     version: 1.0
 * </pre>
 */
@GlideExtension
public class MyAppExtension {

    private MyAppExtension() {
    }

    @GlideOption
    public static void loadAvatar(RequestOptions options) {
        options
                .error(R.drawable.mine_no_login_head_image)
                .placeholder(R.drawable.mine_no_login_head_image)
                .circleCrop();
    }

}
