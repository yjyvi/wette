package com.risenb.wette.ui.home.productDetial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.risenb.wette.R;
import com.risenb.wette.ui.LazyLoadFragment;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by yjyvi on 2018/1/31.
 */

public class ProductDetialFragment extends LazyLoadFragment {

    @ViewInject(R.id.web_content)
    private WebView wb_content;

    @ViewInject(R.id.pb_loading)
    private ProgressBar pb_loading;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater=inflater;
        view=inflater.inflate(R.layout.fragment_product_detial,container,false);
    }

    @Override
    protected void setControlBasis() {

    }

    @Override
    protected void prepareData() {

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


        wb_content.loadUrl("http://www.baidu.com");

    }



    /**
     * 初始化实例
     *
     * @return
     */
    public static ProductDetialFragment newInstance() {
        Bundle bundle = new Bundle();
        ProductDetialFragment productFragment = new ProductDetialFragment();
        productFragment.setArguments(bundle);
        return productFragment;
    }
}
