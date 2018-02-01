package com.risenb.wette.ui.mine;

import android.content.Context;
import android.content.Intent;

import com.risenb.wette.R;
import com.risenb.wette.ui.BaseUI;

import org.xutils.view.annotation.ContentView;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/01/31
 *     desc   : 注册
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseUI {
    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("注册");
        rightVisible("登录");
    }

    @Override
    protected void prepareData() {

    }
    
    public static void toActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }
    
}
