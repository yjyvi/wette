package com.risenb.wette.ui.mine;

import com.risenb.wette.R;
import com.risenb.wette.ui.BaseUI;

import org.xutils.view.annotation.ContentView;

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
}
