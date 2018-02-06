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
import com.risenb.wette.utils.UserManager;
import com.risenb.wette.utils.Utils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/02
 *     desc   : 修改昵称
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_modify_nick_name)
public class ModifyNickNameActivity extends BaseUI {

    @ViewInject(R.id.et_nick_name)
    private EditText et_nick_name;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("修改昵称");
    }

    @Override
    protected void prepareData() {

    }

    @Event(value = R.id.tv_finish, type = View.OnClickListener.class)
    private void onClic(View view) {
        if (Utils.isEmpty(new String[]{"请输入昵称"}, et_nick_name)) {
            final String nickName = Utils.getText(et_nick_name);
            NetworkUtils.getNetworkUtils().updateNickName(nickName, new CommonCallBack<String>() {
                @Override
                protected void onSuccess(String data) {
                    UserManager.updateNickName(nickName);
                    ToastUtils.showToast("昵称修改成功");
                    finish();
                }
            });
        }
    }

    public static void toActivity(Context context) {
        Intent intent = new Intent(context, ModifyNickNameActivity.class);
        context.startActivity(intent);
    }

}
