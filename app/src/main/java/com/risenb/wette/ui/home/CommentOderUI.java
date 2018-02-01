package com.risenb.wette.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.risenb.wette.R;
import com.risenb.wette.ui.BaseUI;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by yjyvi on 2018/2/1.
 */
@ContentView(R.layout.activity_comment_order)
public class CommentOderUI extends BaseUI {

    @ViewInject(R.id.common_title_back)
    private RelativeLayout common_title_back;

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
        common_title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, CommentOderUI.class);
        context.startActivity(starter);
    }
}
