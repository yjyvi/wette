package com.lengzhuo.xybh.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lengzhuo.xybh.MyApplication;
import com.lengzhuo.xybh.beans.User;

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

    public static void updateNickName(String nickName){
        sUser.setNickname(nickName);
        SPUtils.put(MyApplication.applicationContext, "user_json", sUser.toString());
    }

    public static void updatePhoneNumber(String phoneNumber){
        sUser.setPhone(phoneNumber);
        SPUtils.put(MyApplication.applicationContext, "user_json", sUser.toString());
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
