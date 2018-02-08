package com.lengzhuo.xybh.ui.home.productDetial;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.ui.LazyLoadFragment;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by yjyvi on 2018/1/31.
 */

public class GoodDetailFragment extends LazyLoadFragment {

    @ViewInject(R.id.web_content)
    private WebView wb_content;

    @ViewInject(R.id.pb_loading)
    private ProgressBar pb_loading;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater=inflater;
        view=inflater.inflate(R.layout.fragment_good_detial,container,false);
    }

    @Override
    protected void setControlBasis() {

    }

    @Override
    protected void prepareData() {

        String webDataUrl = getArguments().getString("webDataUrl");

        WebSettings settings = wb_content.getSettings();
        settings.setSupportZoom(false);

        settings.setJavaScriptEnabled(true);
        wb_content.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
        settings.setDefaultTextEncodingName("utf-8");
        wb_content.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                if(newProgress==100){
                    //加载完网页进度条消失
                    pb_loading.setVisibility(View.GONE);
                }
                else{
                    //开始加载网页时显示进度条
                    pb_loading.setVisibility(View.VISIBLE);
                    //设置进度值
                    pb_loading.setProgress(newProgress);
                }
            }
        });


        if (TextUtils.isEmpty(webDataUrl)) {
            webDataUrl="http://www.baidu.com";
        }
        wb_content.loadUrl(webDataUrl);

    }



    /**
     * 初始化实例
     *
     * @return
     */
    public static GoodDetailFragment newInstance(String webDataUrl) {
        Bundle bundle = new Bundle();
        GoodDetailFragment goodDetailFragment = new GoodDetailFragment();
        bundle.putString("webDataUrl", webDataUrl);
        goodDetailFragment.setArguments(bundle);
        return goodDetailFragment;
    }
}
