package com.risenb.wette;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.zhy.autolayout.config.AutoLayoutConifg;

import org.xutils.x;

/**
 * @author yjyvi
 */
public class MyApplication extends MultiDexApplication {
    /**
     * 根目录
     */
    public static String path;

    /**
     * 全局context
     */
    public static Context applicationContext;


    @Override
    public void onCreate() {
        super.onCreate();

        //-----------初始化applicationContext----------start------
        applicationContext = getApplicationContext();

        //-----------初始化xUtils----------start------
        //
        x.Ext.init(this);
        //输出debug日志，开启会影响性能
        x.Ext.setDebug(false);
        //创建数据库
        //-----------初始化xUtils----------end------

        //-----------初始化applicationContext----------end------
        //-----------百分比布局初始化----------start------
        AutoLayoutConifg.getInstance().useDeviceSize();
        //-----------百分比布局初始化----------end------

        //-----------全局捕获异常日志---------start-------
//        if (!BuildConfig.DEBUG) {
//            CrashHandler crashHandler = CrashHandler.getInstance();
//            crashHandler.init(getApplicationContext());
//        }
        //-----------全局捕获异常日志----------end------
    }

}
