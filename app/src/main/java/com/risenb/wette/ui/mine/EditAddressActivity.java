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
 *     time   : 2018/02/03
 *     desc   : 编辑 or 添加 收获地址
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_edit_address)
public class EditAddressActivity extends BaseUI {
    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("输出新的配送地址");
    }

    @Override
    protected void prepareData() {

    }
    
    public static void toActivity(Context context) {
        Intent intent = new Intent(context, EditAddressActivity.class);
        context.startActivity(intent);
    }
    
}
