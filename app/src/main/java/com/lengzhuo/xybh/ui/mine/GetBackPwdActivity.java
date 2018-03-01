package com.lengzhuo.xybh.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.network.CommonCallBack;
import com.lengzhuo.xybh.ui.BaseUI;
import com.lengzhuo.xybh.utils.NetworkUtils;
import com.lengzhuo.xybh.utils.ToastUtils;
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
 *     desc   : 找回密码
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_get_back_pwd)
public class GetBackPwdActivity extends BaseUI {

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
        setTitle("找回密码");
        mHandler = new ValidateHandler(tv_validate_code);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.onDestroy();
    }

    @Override
    protected void prepareData() {

    }

    private void getBackPwd() {
        String phoneNumber = Utils.getText(et_phone_number);
        String validateCode = Utils.getText(et_validate_code);
        String password = Utils.getText(et_password);
        NetworkUtils.getNetworkUtils().getBackPassword(phoneNumber, validateCode, password, new CommonCallBack<String>() {
            @Override
            protected void onSuccess(String data) {
                ToastUtils.showToast("密码修改成功");
                finish();
            }
        });
    }

    private boolean validate() {
        boolean result = Utils.isEmpty(new String[]{"请输入手机号", "请输入验证码", "请输入密码", "请输入确认密码"}, et_phone_number, et_validate_code, et_password, et_confirm_password);
        if (!et_confirm_password.getText().toString().trim().equals(et_password.getText().toString().trim())) {
            ToastUtils.showToast("确认密码和密码不一致");
            result = false;
        }
        return result;
    }

    private void sendValidateCode() {
        String phoneNumber = Utils.getText(et_phone_number);
        NetworkUtils.getNetworkUtils().sendValidateCode(phoneNumber, "2", new CommonCallBack<String>() {
            @Override
            protected void onSuccess(String data) {
                ToastUtils.showToast("验证码发送成功");
                mHandler.startCountdown();
            }
        });
    }

    @Event(value = {R.id.tv_get_back_pwd, R.id.tv_validate_code}, type = View.OnClickListener.class)
    private void onClick(View view) {
        if (view.getId() == R.id.tv_validate_code) {
            if (TextUtils.isEmpty(et_phone_number.getText().toString().trim())) {
                ToastUtils.showToast("请输入手机号");
                return;
            }
            sendValidateCode();
        } else if (validate()) getBackPwd();

    }
    
    public static void toActivity(Context context) {
        Intent intent = new Intent(context, GetBackPwdActivity.class);
        context.startActivity(intent);
    }
}
