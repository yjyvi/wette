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
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_bind_phone_number)
public class BindPhoneNumberActivity extends BaseUI {
    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("绑定手机号");
    }

    @Override
    protected void prepareData() {

    }
    
    public static void toActivity(Context context) {
        Intent intent = new Intent(context, BindPhoneNumberActivity.class);
        context.startActivity(intent);
    }
}
