package com.lengzhuo.xybh.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lengzhuo.xybh.R;
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
 *     time   : 2018/02/02
 *     desc   : 意见反馈
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_feedback)
public class FeedbackActivity extends BaseUI implements TextWatcher {

    @ViewInject(R.id.et_content)
    private EditText et_content;

    @ViewInject(R.id.tv_hint)
    private TextView tv_hint;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("意见反馈");
        et_content.addTextChangedListener(this);
    }

    @Override
    protected void prepareData() {
    }

    @Event(value = {R.id.tv_commit}, type = View.OnClickListener.class)
    private void onClick(View view) {
        String content = Utils.getText(et_content);
        if (TextUtils.isEmpty(content)) {
            ToastUtils.showToast("请输入您要反馈的内容");
            return;
        }

        if (!UserManager.isLogin()) {
            ToastUtils.showToast("请先登录！");
            LoginActivity.toActivity(view.getContext());
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        tv_hint.setText("还可以输入(" + (200 - s.length()) + "字");
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
