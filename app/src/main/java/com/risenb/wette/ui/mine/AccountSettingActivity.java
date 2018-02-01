package com.risenb.wette.ui.mine;

import com.risenb.wette.R;
import com.risenb.wette.ui.BaseUI;

import org.xutils.view.annotation.ContentView;

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
}
