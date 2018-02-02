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
 *     time   : 2018/02/02
 *     desc   : 修改手机号
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_modify_phone_number)
public class ModifyPhoneNumberActivity extends BaseUI {
    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {

    }

    @Override
    protected void prepareData() {

    }
    
    public static void toActivity(Context context) {
        Intent intent = new Intent(context, ModifyPhoneNumberActivity.class);
        context.startActivity(intent);
    }
}
