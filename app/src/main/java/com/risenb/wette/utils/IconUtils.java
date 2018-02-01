package com.risenb.wette.utils;


import android.content.Context;

import com.risenb.wette.R;


/**
 * Created by Administrator on 2017/6/6.
 */

public class IconUtils {

    public static String getPicUrl(Context context, String url){
        if(url!=null){
            if (url.startsWith("http")) {
                return url;
            } else {
                return context.getResources().getString(R.string.service_host_address) + url;
            }
        }else {
            return url;
        }
    }
}
