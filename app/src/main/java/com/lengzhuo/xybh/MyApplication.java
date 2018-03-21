package com.lengzhuo.xybh;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.squareup.leakcanary.LeakCanary;
import com.zhy.autolayout.config.AutoLayoutConifg;

import org.xutils.x;

/**
 * @author yjyvi
 */
public class MyApplication extends MultiDexApplication {

    /**
     * 全局context
     */
    public static Context applicationContext;


    @Override
    public void onCreate() {
        super.onCreate();

        //-----------初始化applicationContext----------------
        applicationContext = getApplicationContext();


        //-----------初始化xUtils----------------
        x.Ext.init(this);
        //输出debug日志，开启会影响性能
        x.Ext.setDebug(false);


        //-----------百分比布局初始化----------------
        AutoLayoutConifg.getInstance().useDeviceSize();

        //-----------内存泄漏检查工具初始化----------------
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        //-----------全局捕获异常日志----------------
//        if (!BuildConfig.DEBUG) {
//            CrashHandler crashHandler = CrashHandler.getInstance();
//            crashHandler.init(getApplicationContext());
//        }
    }

}
