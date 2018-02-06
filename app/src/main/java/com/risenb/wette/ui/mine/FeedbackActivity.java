package com.risenb.wette.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
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
 *     desc   : 意见反馈
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_feedback)
public class FeedbackActivity extends BaseUI {

    @ViewInject(R.id.et_content)
    private EditText et_content;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("意见反馈");
    }

    @Override
    protected void prepareData() {
    }

    @Event(value = {R.id.tv_commit},type = View.OnClickListener.class)
    private void onClick(View view){
        String content = Utils.getText(et_content);
        if(TextUtils.isEmpty(content)){
            ToastUtils.showToast("请输入您要反馈的内容");
            return;
        }
        NetworkUtils.getNetworkUtils().feedback(content, new CommonCallBack<String>() {
            @Override
            protected void onSuccess(String data) {
                ToastUtils.showToast("反馈提交成功");
                finish();
            }
        });
    }
    
    public static void toActivity(Context context) {
        Intent intent = new Intent(context, FeedbackActivity.class);
        context.startActivity(intent);
    }
}
