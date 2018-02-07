package com.risenb.wette.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.risenb.wette.R;
import com.risenb.wette.beans.MessageBean;
import com.risenb.wette.network.CommonCallBack;
import com.risenb.wette.ui.BaseUI;
import com.risenb.wette.ui.mine.multitype.MessageItemViewBinder;
import com.risenb.wette.utils.NetworkUtils;
import com.risenb.wette.utils.PaddingItemDecoration;
import com.risenb.wette.views.refreshlayout.MyRefreshLayout;
import com.risenb.wette.views.refreshlayout.MyRefreshLayoutListener;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

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
public class MessageActivity extends BaseUI implements MyRefreshLayoutListener {

    @ViewInject(R.id.rv_message)
    private RecyclerView rv_message;

    @ViewInject(R.id.rl_message)
    private MyRefreshLayout rl_message;

    private Items mItems;

    private MultiTypeAdapter mAdapter;

    int mPageIndex = 1;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.register(MessageBean.class, new MessageItemViewBinder());
        rv_message.addItemDecoration(new PaddingItemDecoration());
        rv_message.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setItems(mItems);
        rv_message.setAdapter(mAdapter);
        rl_message.setMyRefreshLayoutListener(this);
    }

    @Override
    protected void prepareData() {
        getMessageList();
    }

    public void getMessageList() {
        NetworkUtils.getNetworkUtils().getMessageList(String.valueOf(mPageIndex), new CommonCallBack<List<MessageBean>>() {
            @Override
            protected void onSuccess(List<MessageBean> data) {
                if (data.size() < 10)
                    rl_message.setIsLoadingMoreEnabled(false);
                rl_message.refreshComplete();
                rl_message.loadMoreComplete();
                mItems.addAll(data);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    public static void toActivity(Context context) {
        Intent intent = new Intent(context, MessageActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onRefresh(View view) {
        mPageIndex = 1;
        mItems.clear();
        rl_message.setIsLoadingMoreEnabled(true);
        getMessageList();
    }

    @Override
    public void onLoadMore(View view) {
        mPageIndex++;
        getMessageList();
    }
}
