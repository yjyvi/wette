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
 *     time   : 2018/02/01
 *     desc   : 账户设置
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_account_setting)
public class AccountSettingActivity extends BaseUI {
    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("账户设置");
    }

    @Override
    protected void prepareData() {

    }

    @Event(value = {R.id.tv_modify_account_setting},type = View.OnClickListener.class)
    private void onClick(View view){
        switch (view.getId()){
            case R.id.tv_modify_account_setting:
                PersonalInformationActivity.toActivity(view.getContext());
                break;
        }
    }

    public static void toActivity(Context context) {
        Intent intent = new Intent(context, AccountSettingActivity.class);
        context.startActivity(intent);
    }
    
}
