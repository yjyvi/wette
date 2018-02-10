package com.lengzhuo.xybh.ui.mine;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.MessageBean;
import com.lengzhuo.xybh.network.CommonCallBack;
import com.lengzhuo.xybh.ui.BaseUI;
import com.lengzhuo.xybh.ui.mine.multitype.MessageItemViewBinder;
import com.lengzhuo.xybh.utils.NetworkUtils;
import com.lengzhuo.xybh.utils.PaddingItemDecoration;
import com.lengzhuo.xybh.utils.ToastUtils;
import com.lengzhuo.xybh.utils.Utils;
import com.lengzhuo.xybh.utils.evntBusBean.BaseEvent;
import com.lengzhuo.xybh.views.refreshlayout.MyRefreshLayout;
import com.lengzhuo.xybh.views.refreshlayout.MyRefreshLayoutListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
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


    @ViewInject(R.id.fl_empty_data)
    private FrameLayout fl_empty_data;

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
                if (Utils.isShowEmptyLayout(data, rl_message, fl_empty_data)) return;
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
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onMessageEvent(final BaseEvent<MessageBean> event) {
        new AlertDialog.Builder(this)
                .setMessage("确定要删除?")
                .setTitle("提示")
                .setPositiveButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        NetworkUtils.getNetworkUtils().deleteMessage(event.getData().getMessageId(), new CommonCallBack<String>() {
                            @Override
                            protected void onSuccess(String data) {
                                ToastUtils.showToast("删除成功!");
                                mItems.remove(event.getData());
                                mAdapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        });
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
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
