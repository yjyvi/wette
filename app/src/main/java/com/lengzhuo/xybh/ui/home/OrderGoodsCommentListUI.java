package com.lengzhuo.xybh.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.adapter.home.OrderGoodsCommentListAdapter;
import com.lengzhuo.xybh.beans.OrderGoodsBean;
import com.lengzhuo.xybh.ui.BaseUI;
import com.lengzhuo.xybh.utils.evntBusBean.OrderEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/2/8.
 */
@ContentView(R.layout.activity_order_goods_comment_list)
public class OrderGoodsCommentListUI extends BaseUI implements OrderGoodsCommentListP.OrderGoodsListener {

    @ViewInject(R.id.ll_empty_view)
    private LinearLayout ll_empty_view;


    @ViewInject(R.id.rv_order_goods_list)
    private RecyclerView rv_order_goods_list;
    public OrderGoodsCommentListAdapter mOrderGoodsCommentListAdapter;
    public OrderGoodsCommentListP mOrderGoodsCommentListP;
    public String mOrderId;

    @Override
    protected void back() {
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void setControlBasis() {
        EventBus.getDefault().register(this);
        leftVisible(R.mipmap.back);
        setTitle("评价晒单");
        rv_order_goods_list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void prepareData() {
        mOrderGoodsCommentListP = new OrderGoodsCommentListP(this);

        mOrderId = getIntent().getStringExtra("orderId");

        mOrderGoodsCommentListP.setOrderGoodsCommentList(mOrderId);

        mOrderGoodsCommentListAdapter = new OrderGoodsCommentListAdapter(R.layout.item_order_goods_comment_list, null);
        rv_order_goods_list.setAdapter(mOrderGoodsCommentListAdapter);

    }


    public static void start(Context context, String orderId) {
        Intent starter = new Intent(context, OrderGoodsCommentListUI.class);
        starter.putExtra("orderId", orderId);
        context.startActivity(starter);
    }

    @Override
    public void orderGoodsListSuccess(List<OrderGoodsBean.DataBean> dataBeans) {
        mOrderGoodsCommentListAdapter.setNewData(dataBeans);
        showEmptyView(dataBeans,ll_empty_view);
    }

    @Override
    public void orderGoodsListField() {

    }

    @Subscribe
    public void orderEvent(OrderEvent orderEvent) {
        if (OrderEvent.COMMENT_SUCCESS == orderEvent.getEventType()) {
            mOrderId = getIntent().getStringExtra("orderId");
            mOrderGoodsCommentListP.setOrderGoodsCommentList(mOrderId);
        }
    }
}
