package com.risenb.wette.views;

import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by yjyvi on 2017/6/14.
 */

public class WebViewUtils {

    /**
     * 初始化webView
     */
    public static void initWebView(WebView web) {
        WebSettings settings = web.getSettings();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }

        //去除WebView的焦点事件
        web.setFocusableInTouchMode(false);

        web.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        //去掉超连接事件
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }



        });

        //取消长按复制事件
        web.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });


        settings.setSupportZoom(false);

        settings.setJavaScriptEnabled(false);

        settings.setDefaultTextEncodingName("utf-8");


    }

    /**
     * 添加HTML标签样式
     *
     * @param bodyHTML
     * @return
     */
    public static String getHtmlData(String bodyHTML) {

        String textFont = "body{" +
                "font-size: 14.0;" +
                "font-family:myWebFont;" +
                "color: 000000; " +
                "line-height:2.0;}";
        String css = "@font-face {font-family:myWebFont; src: url(PingFang Light_0.ttf)}";
        String head = "<head><style>img{max-width: 100%; width:auto; height: auto;}" + textFont + css + "</style></head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }




}
