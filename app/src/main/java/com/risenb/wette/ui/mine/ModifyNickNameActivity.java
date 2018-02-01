package com.risenb.wette.ui.mine;

import android.content.Context;
import android.content.Intent;

import com.risenb.wette.R;
import com.risenb.wette.ui.BaseUI;

import org.xutils.view.annotation.ContentView;

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
    
    public static void toActivity(Context context) {
        Intent intent = new Intent(context, ModifyNickNameActivity.class);
        context.startActivity(intent);
    }
    
}
