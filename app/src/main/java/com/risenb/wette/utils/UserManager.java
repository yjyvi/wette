package com.risenb.wette.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.risenb.wette.MyApplication;
import com.risenb.wette.beans.User;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/05
 *     desc   : 用户管理类
 *     version: 1.0
 * </pre>
 */
public class UserManager {

    private static User sUser = null;

    public static User getUser() {
        if (sUser == null) {
            String userJson = SPUtils.getString(MyApplication.applicationContext, "user_json");
            if (!TextUtils.isEmpty(userJson))
                sUser = JSON.parseObject(userJson, User.class);
        }
        return sUser;
    }

    public static void clearUser(){
        SPUtils.put(MyApplication.applicationContext, "user_json","");
        sUser = null;
    }

    public static void saveUser(User user) {
        SPUtils.put(MyApplication.applicationContext, "user_json", user.toString());
        sUser = user;
    }

    public static boolean isLogin() {
        return getUser() != null;
    }

}
