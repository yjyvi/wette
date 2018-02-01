package com.risenb.wette.ui;

import com.risenb.wette.CommonConstant;
import com.risenb.wette.R;
import com.risenb.wette.utils.SPUtils;

import org.xutils.view.annotation.ContentView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 *
 * @author wen
 * @date 2016/12/1
 * 欢迎界面
 */
@ContentView(R.layout.activity_splash)
public class SplashUI extends BaseUI {
    @Override
    protected void back() {

    }

    @Override
    protected void setControlBasis() {
            intoApp();
    }

    @Override
    protected void prepareData() {
    }

    public void intoApp() {


        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //do something
                if (SPUtils.getBooleanToken(getApplicationContext(), CommonConstant.Common
                        .FIRST_LAUNCHER)) {
                    HomeTableUI.startHomeTableView(SplashUI.this, 0);
                } else {
                    GuideUI.startGuideActivity(SplashUI.this);
                }
                finish();
            }
        },0,2, TimeUnit.SECONDS);

//        Timer timer = new Timer();
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                if (SPUtils.getBooleanToken(getApplicationContext(), CommonConstant.Common
//                        .FIRST_LAUNCHER)) {
//                    HomeTableUI.startHomeTableView(SplashUI.this, 0);
//                } else {
//                    GuideUI.startGuideActivity(SplashUI.this);
//                }
//                finish();
//            }
//        };
//        timer.schedule(task, 2000);


    }



}
