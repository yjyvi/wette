package com.lengzhuo.xybh.ui.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
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

    //分享
    @ViewInject(R.id.rl_right)
    private RelativeLayout rl_right;

    private String con = "&UserInfo_Id=";
    //表单的数据信息
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    // 表单的结果回调
    private final static int FILECHOOSER_RESULTCODE = 1;
    private Uri imageUri;
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void prepareData() {

        final String url = getIntent().getStringExtra("url");

        common_title_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                back();
            }
        });

        WebSettings settings = wb_content.getSettings();
        settings.setSupportZoom(false);
        //设置是否使用WebView推荐使用的窗口
        settings.setUseWideViewPort(true);

        settings.setJavaScriptEnabled(true);
        wb_content.addJavascriptInterface(new JsOperation(), "goodsDetail()");

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

        settings.setDefaultTextEncodingName("utf-8");
        wb_content.loadUrl(url);
        wb_content.evaluateJavascript("javascript:goodsDetail()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                Log.d("HotWebUI", "result=" + s);
            }
        });
    }


    private class JsOperation {
        @JavascriptInterface
        public void goodsDetail(final String content) {
            Log.d("HotWebUI", content);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    GoodDetailsUI.start(getActivity(),);
                }
            });
        }
    }
}
