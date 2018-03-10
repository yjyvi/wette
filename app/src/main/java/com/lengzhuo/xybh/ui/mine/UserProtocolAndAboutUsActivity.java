package com.lengzhuo.xybh.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebView;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.network.OKHttpManager;
import com.lengzhuo.xybh.ui.BaseUI;
import com.lengzhuo.xybh.utils.NetworkUtils;
import com.lengzhuo.xybh.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.IOException;

import okhttp3.Call;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/03/10
 *     desc   : 用户协议 和 关于我们
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_user_protocol_and_about_us)
public class UserProtocolAndAboutUsActivity extends BaseUI {

    private int mType;

    @ViewInject(R.id.wv_content)
    WebView wv_content;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle(mType == 1 ? "注册协议" : "关于我们");
        mType = getIntent().getIntExtra("type", 1);
        NetworkUtils.getNetworkUtils().getUserProtocolOrAboutUs(mType, new OKHttpManager.StringCallBack() {
            @Override
            public void requestFailure(Call call, IOException e) {
                ToastUtils.showToast("数据获取失败");
                finish();
            }

            @Override
            public void requestSuccess(String result) {
                wv_content.loadData(result, "text/html; charset=UTF-8", null);
            }
        });
    }

    @Override
    protected void prepareData() {

    }

    public static void toActivity(Context context, int type) {
        Intent intent = new Intent(context, UserProtocolAndAboutUsActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    public static void toUserProcotolActivity(Context context) {
        toActivity(context, 1);
    }

    public static void toAboutUsActivity(Context context) {
        toActivity(context, 0);
    }

}
