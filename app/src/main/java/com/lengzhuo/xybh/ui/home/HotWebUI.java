package com.lengzhuo.xybh.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.ui.BaseUI;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;


/**
 * Created by yjyvi on 2017/10/8.
 */

@ContentView(R.layout.activity_hot_web)
public class HotWebUI extends BaseUI {
    @ViewInject(R.id.common_title_back)
    private RelativeLayout common_title_back;

    @ViewInject(R.id.wb_content)
    private WebView wb_content;

    @ViewInject(R.id.pb_loading)
    private ProgressBar pb_loading;


    private String title;

    public static void starter(Context activity, String urlPath, String title) {
        Intent intent = new Intent(activity, HotWebUI.class);
        intent.putExtra("url", urlPath);
        intent.putExtra("title", title);
        activity.startActivity(intent);
    }

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        leftVisible(R.mipmap.back);
        title = getIntent().getStringExtra("title");
        setTitle(title);
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void prepareData() {

        final String url = getIntent().getStringExtra("url");

        common_title_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                back();
            }
        });

        WebSettings webSettings = wb_content.getSettings();
        //设置WebView支持JavaScript
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        wb_content.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                if (newProgress == 100) {
                    //加载完网页进度条消失
                    pb_loading.setVisibility(View.GONE);
                } else {
                    //开始加载网页时显示进度条
                    pb_loading.setVisibility(View.VISIBLE);
                    //设置进度值
                    pb_loading.setProgress(newProgress);
                }
            }
        });

        wb_content.addJavascriptInterface(new JsInterface(this), "android");

        wb_content.loadUrl(url);

    }

    private class JsInterface {
        private Context mContext;

        private JsInterface(Context context) {
            this.mContext = context;
        }

        //在js中调用javascript:window.android.goodsDetail(obj);便会触发此方法。
        @JavascriptInterface
        public void goodsDetail(String name) {
            GoodDetailsUI.start(mContext, String.valueOf(name));
        }
    }

}
