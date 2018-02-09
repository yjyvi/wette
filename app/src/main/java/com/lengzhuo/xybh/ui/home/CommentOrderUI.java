package com.lengzhuo.xybh.ui.home;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.ui.BaseUI;
import com.lengzhuo.xybh.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * @author yjyvi
 * @date 2018/2/1
 * 评价订单
 */
@ContentView(R.layout.activity_comment_order)
public class CommentOrderUI extends BaseUI implements CommentOrderP.CommentOrderListener {

    @ViewInject(R.id.common_title_back)
    private RelativeLayout common_title_back;

    @ViewInject(R.id.et_content)
    private EditText et_content;

    @ViewInject(R.id.bt_commit)
    private Button bt_commit;
    public CommentOrderP mCommentOrderP;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        leftVisible(R.mipmap.back);
        setTitle("评价");
    }

    @Override
    protected void prepareData() {


        final String goodsId = getIntent().getStringExtra("goodsId");
        final String orderGid = getIntent().getStringExtra("orderGid");

        mCommentOrderP = new CommentOrderP(this);

        common_title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        et_content.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (TextUtils.isEmpty(textView.getText().toString().trim())) {
                    ToastUtils.showToast("请输入评价内容");
                }
                return true;
            }
        });

        bt_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = et_content.getText().toString().trim();
                mCommentOrderP.setCommentOrder(goodsId, orderGid, content);
            }
        });
    }

    public static void start(Context context, String goodsId, String orderGid) {
        Intent starter = new Intent(context, CommentOrderUI.class);
        starter.putExtra("goodsId", goodsId);
        starter.putExtra("orderGid", orderGid);
        context.startActivity(starter);
    }

    @Override
    public void commentSuccess() {
        finish();
    }

    @Override
    public void commentField() {

    }
}
