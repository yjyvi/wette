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
 *     desc   : 绑定手机号
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_bind_phone_number)
public class BindPhoneNumberActivity extends BaseUI {

    private String mOpenId;

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
        setTitle("绑定手机号");
        mOpenId = getIntent().getStringExtra("openId");
    }

    @Override
    protected void prepareData() {

    }

    @Event(value = R.id.tv_finish,type = View.OnClickListener.class)
    public void onClick(View view){
        if(Utils.isEmpty(new String[]{"请输入手机号","请输入密码"},et_phone_number,et_password)){
            String phoneNumber = Utils.getText(et_phone_number);
            String password = Utils.getText(et_password);
            NetworkUtils.getNetworkUtils().bindPhoneNumber(phoneNumber, password,mOpenId, new CommonCallBack<User>() {
                @Override
                protected void onSuccess(User data) {
                    ToastUtils.showToast("绑定成功!");
                    UserManager.saveUser(data);
                    finish();
                }
            });
        }
    }
    
    public static void toActivity(Context context,String openId) {
        Intent intent = new Intent(context, BindPhoneNumberActivity.class);
        intent.putExtra("openId",openId);
        context.startActivity(intent);
    }
}
