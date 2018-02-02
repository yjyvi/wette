package com.risenb.wette.ui.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.risenb.wette.R;
import com.risenb.wette.beans.MessageBean;
import com.risenb.wette.ui.BaseUI;
import com.risenb.wette.ui.mine.multitype.MessageItemViewBinder;
import com.risenb.wette.utils.PaddingItemDecoration;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/02
 *     desc   : 消息
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_message)
public class MessageActivity extends BaseUI {

    @ViewInject(R.id.rv_message)
    private RecyclerView rv_message;

    private Items mItems;

    private MultiTypeAdapter mAdapter;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.register(MessageBean.class,new MessageItemViewBinder());
        rv_message.addItemDecoration(new PaddingItemDecoration());
        rv_message.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setItems(mItems);
        rv_message.setAdapter(mAdapter);
    }

    @Override
    protected void prepareData() {
        for (int i = 0; i < 10; i++) {
            mItems.add(new MessageBean());
        }
        mAdapter.notifyDataSetChanged();
    }
}
