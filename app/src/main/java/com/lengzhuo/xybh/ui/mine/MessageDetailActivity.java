package com.lengzhuo.xybh.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.MessageBean;
import com.lengzhuo.xybh.ui.BaseUI;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/06
 *     desc   : 消息详情
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_message_detail)
public class MessageDetailActivity extends BaseUI {

    @ViewInject(R.id.tv_title)
    TextView tv_title;

    @ViewInject(R.id.tv_create_time)
    TextView tv_create_time;

    @ViewInject(R.id.tv_content)
    TextView tv_content;

    MessageBean mMessageBean;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("消息详情");
        mMessageBean = getIntent().getParcelableExtra("message");
    }

    @Override
    protected void prepareData() {
        tv_title.setText(mMessageBean.getTitle());
        tv_create_time.setText(mMessageBean.getCreateTime());
        tv_content.setText(mMessageBean.getContent());
    }

    public static void toActivity(Context context,MessageBean messageBean) {
        Intent intent = new Intent(context, MessageDetailActivity.class);
        intent.putExtra("message",messageBean);
        context.startActivity(intent);
    }
}
