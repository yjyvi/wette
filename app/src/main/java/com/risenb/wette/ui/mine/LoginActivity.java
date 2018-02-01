package com.risenb.wette.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.risenb.wette.R;
import com.risenb.wette.ui.BaseUI;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/01/31
 *     desc   : 登录
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseUI {

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        rightVisible("注册");
        setTitle("登录");
    }

    @Override
    protected void prepareData() {

    }

    @Event(value = {R.id.rl_right_title, R.id.tv_get_back_pwd, R.id.iv_wechat_login}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            //注册
            case R.id.rl_right_title:
                RegisterActivity.toActivity(view.getContext());
                break;
            //找回密码
            case R.id.tv_get_back_pwd:
                GetBackPwdActivity.toActivity(view.getContext());
                break;
            //微信登录
            case R.id.iv_wechat_login:
                BindPhoneNumberActivity.toActivity(view.getContext());
                break;
        }
    }

    public static void toActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

}
