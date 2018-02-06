package com.risenb.wette.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.risenb.wette.R;
import com.risenb.wette.network.CommonCallBack;
import com.risenb.wette.ui.BaseUI;
import com.risenb.wette.utils.NetworkUtils;
import com.risenb.wette.utils.ToastUtils;
import com.risenb.wette.utils.Utils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/02
 *     desc   : 修改密码
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_modify_pwd)
public class ModifyPwdActivity extends BaseUI {

    @ViewInject(R.id.et_password)
    private EditText et_password;

    @ViewInject(R.id.et_password_new)
    private EditText et_password_new;

    @ViewInject(R.id.et_confirm_password_new)
    private EditText et_confirm_password_new;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("修改密码");
    }

    @Override
    protected void prepareData() {

    }

    @Event(value = {R.id.tv_finish}, type = View.OnClickListener.class)
    private void onClick(View view) {
        if (Utils.isEmpty(new String[]{"请输入旧密码", "请输入新密码", "请输入确认新密码"})) {
            String password = Utils.getText(et_password);
            String passwordNew = Utils.getText(et_password_new);
            if (!passwordNew.equals(Utils.getText(et_confirm_password_new))) {
                ToastUtils.showToast("新密码和确认密码不一致");
                return;
            }
            NetworkUtils.getNetworkUtils().updatePassword(password, passwordNew, new CommonCallBack<String>() {
                @Override
                protected void onSuccess(String data) {
                    ToastUtils.showToast("密码修改成功");
                    finish();
                }
            });
        }
    }

    public static void toActivity(Context context) {
        Intent intent = new Intent(context, ModifyPwdActivity.class);
        context.startActivity(intent);
    }

}
