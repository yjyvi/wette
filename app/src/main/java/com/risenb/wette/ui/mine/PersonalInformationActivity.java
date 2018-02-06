package com.risenb.wette.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.risenb.wette.R;
import com.risenb.wette.ui.BaseUI;
import com.risenb.wette.utils.UserManager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/02
 *     desc   : 个人信息
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_personal_information)
public class PersonalInformationActivity extends BaseUI {

    @ViewInject(R.id.tv_nick_name)
    private TextView tv_nick_name;

    @ViewInject(R.id.tv_phone_number)
    private TextView tv_phone_number;

    @Override
    protected void onResume() {
        super.onResume();
        tv_nick_name.setText(UserManager.getUser().getNickname());
        tv_phone_number.setText(UserManager.getUser().getPhone());
    }

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("个人信息");
    }

    @Override
    protected void prepareData() {

    }

    @Event(value = {R.id.ll_modify_nick_name, R.id.ll_modify_phone_number, R.id.ll_modify_pwd}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_modify_nick_name:
                ModifyNickNameActivity.toActivity(view.getContext());
                break;
            case R.id.ll_modify_phone_number:
                ModifyPhoneNumberActivity.toActivity(view.getContext());
                break;
            case R.id.ll_modify_pwd:
                ModifyPwdActivity.toActivity(view.getContext());
                break;
        }
    }

    public static void toActivity(Context context) {
        Intent intent = new Intent(context, PersonalInformationActivity.class);
        context.startActivity(intent);
    }
}
