package com.lengzhuo.xybh.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.User;
import com.lengzhuo.xybh.network.CommonCallBack;
import com.lengzhuo.xybh.ui.BaseUI;
import com.lengzhuo.xybh.ui.HomeTableUI;
import com.lengzhuo.xybh.utils.NetworkUtils;
import com.lengzhuo.xybh.utils.ToastUtils;
import com.lengzhuo.xybh.utils.UserManager;
import com.lengzhuo.xybh.utils.Utils;
import com.lengzhuo.xybh.utils.ValidateHandler;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

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

    @ViewInject(R.id.et_nick_name)
    private EditText et_nick_name;

    @ViewInject(R.id.et_phone_number)
    private EditText et_phone_number;

    @ViewInject(R.id.et_validate_code)
    private EditText et_validate_code;

    @ViewInject(R.id.et_password)
    private EditText et_password;

    @ViewInject(R.id.et_confirm_password)
    private EditText et_confirm_password;

    @ViewInject(R.id.tv_validate_code)
    private TextView tv_validate_code;

    ValidateHandler mHandler;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("注册");
        rightVisible("登录");
        mHandler = new ValidateHandler(tv_validate_code);
    }

    @Override
    protected void prepareData() {

    }

    private boolean validate() {
        boolean result = Utils.isEmpty(new String[]{"请输入昵称", "请输入手机号", "请输入验证码", "请输入密码", "请输入确认密码"}, et_nick_name, et_phone_number, et_validate_code, et_password, et_confirm_password);
        if (!et_confirm_password.getText().toString().trim().equals(et_password.getText().toString().trim())) {
            ToastUtils.showToast("确认密码和密码不一致");
            result = false;
        }
        return result;
    }

    private void register() {
        String nickName = Utils.getText(et_nick_name);
        String phoneNumber = Utils.getText(et_phone_number);
        String validateCode = Utils.getText(et_validate_code);
        String password = Utils.getText(et_password);
        NetworkUtils.getNetworkUtils().register("1", phoneNumber, password, validateCode, nickName, new CommonCallBack<User>() {
            @Override
            protected void onSuccess(User data) {
                ToastUtils.showToast("注册成功");
                UserManager.saveUser(data);
                startActivity(new Intent(RegisterActivity.this, HomeTableUI.class));
            }
        });
    }

    private void sendValidateCode() {
        String phoneNumber = Utils.getText(et_phone_number);
        NetworkUtils.getNetworkUtils().sendValidateCode(phoneNumber, "1", new CommonCallBack<String>() {
            @Override
            protected void onSuccess(String data) {
                ToastUtils.showToast("验证码发送成功");
                mHandler.startCountdown();
            }
        });
    }

    @Event(value = {R.id.tv_register, R.id.tv_validate_code}, type = View.OnClickListener.class)
    private void onClick(View view) {
        if (view.getId() == R.id.tv_validate_code) {
            if (TextUtils.isEmpty(et_phone_number.getText().toString().trim())) {
                ToastUtils.showToast("请输入手机号");
                return;
            }
            sendValidateCode();
        } else if (validate()) register();

    }

    public static void toActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.onDestroy();
    }
}
