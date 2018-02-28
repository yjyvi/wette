package com.lengzhuo.xybh.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.User;
import com.lengzhuo.xybh.network.CommonCallBack;
import com.lengzhuo.xybh.ui.BaseUI;
import com.lengzhuo.xybh.utils.NetworkUtils;
import com.lengzhuo.xybh.utils.ToastUtils;
import com.lengzhuo.xybh.utils.UserManager;
import com.lengzhuo.xybh.utils.Utils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

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

    @ViewInject(R.id.et_phone_number)
    EditText et_phone_number;

    @ViewInject(R.id.et_password)
    EditText et_password;


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

    @Event(value = {
            R.id.rl_right_title,
            R.id.tv_get_back_pwd,
            R.id.iv_wechat_login,
            R.id.tv_login
    }, type = View.OnClickListener.class)
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
            case R.id.tv_login:
                if (validate()) login();
                break;
        }
    }

    public static void toActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    private boolean validate() {
        return Utils.isEmpty(new String[]{"请输入手机号", "请输入密码"}, et_phone_number, et_password);
    }

    private void login() {
        String phoneNumber = et_phone_number.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        NetworkUtils.getNetworkUtils().login("1", phoneNumber, password, new CommonCallBack<User>() {
            @Override
            protected void onSuccess(User data) {
                ToastUtils.showToast("登录成功");
                UserManager.saveUser(data);

                finish();
            }
        });
    }

}
