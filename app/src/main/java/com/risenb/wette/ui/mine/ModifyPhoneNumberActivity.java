package com.risenb.wette.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.risenb.wette.R;
import com.risenb.wette.network.CommonCallBack;
import com.risenb.wette.ui.BaseUI;
import com.risenb.wette.utils.NetworkUtils;
import com.risenb.wette.utils.ToastUtils;
import com.risenb.wette.utils.UserManager;
import com.risenb.wette.utils.Utils;
import com.risenb.wette.utils.ValidateHandler;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

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


    @ViewInject(R.id.tv_validate_code)
    private TextView tv_validate_code;

    @ViewInject(R.id.et_validate_code)
    private EditText et_validate_code;

    @ViewInject(R.id.et_phone_number)
    private EditText et_phone_number;

    ValidateHandler mHandler;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        mHandler = new ValidateHandler(tv_validate_code);
        et_phone_number.setText(UserManager.getUser().getPhone());
    }

    @Override
    protected void prepareData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.onDestory();
    }

    @Event(value = {R.id.tv_validate_code,R.id.tv_finish},type = View.OnClickListener.class)
    private void onClick(View view){
        if(view.getId() == R.id.tv_finish){
            if(Utils.isEmpty(new String[]{"请填写手机号","请填写验证码"})){
               final String phoneNumber = Utils.getText(et_phone_number);
               String validateCode = Utils.getText(et_validate_code);
                NetworkUtils.getNetworkUtils().updatePhoneNumber(phoneNumber, validateCode, new CommonCallBack<String>() {
                    @Override
                    protected void onSuccess(String data) {
                        UserManager.updatePhoneNumber(phoneNumber);
                        ToastUtils.showToast("手机号修改成功");
                        finish();
                    }
                });
            }
        }else{
            mHandler.startCountdown();
        }
    }
    
    public static void toActivity(Context context) {
        Intent intent = new Intent(context, ModifyPhoneNumberActivity.class);
        context.startActivity(intent);
    }
}
