package com.risenb.wette.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.risenb.wette.R;
import com.risenb.wette.beans.order.OrderListBean;
import com.risenb.wette.ui.BaseUI;
import com.risenb.wette.ui.mine.multitype.OrderItemViewBinder;
import com.risenb.wette.utils.PaddingItemDecoration;
import com.risenb.wette.utils.ToastUtils;
import com.risenb.wette.views.refreshlayout.MyRefreshLayout;
import com.risenb.wette.views.refreshlayout.MyRefreshLayoutListener;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/02
 *     desc   : 我的订单
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_order)
public class OrderActivity extends BaseUI implements MyRefreshLayoutListener, MyOrderP.Listener {
    public static final String KEY_ORDER_STATE = "ORDER_STATE";
    public static final int ORDER_STATE_WAITING_PAY = 1;//等待支付
    public static final int ORDER_STATE_SENDING_GOODS = 3;//发货中
    public static final int ORDER_STATE_WAITING_EVA = 4;//待评价
    public static final int ORDER_STATE_CANCELED_ORDER = 2;//去掉订单
    public static final int ORDER_STATE_COMPLETE = 4;
    public static final int ORDER_STATE_ALL = -1;//全部
    private int orderState;

    private Items mItems;

    private MultiTypeAdapter mAdapter;

    @ViewInject(R.id.rv_refresh)
    private MyRefreshLayout refreshLayout;
    @ViewInject(R.id.rv_order)
    private RecyclerView rv_order;
    private int page = 1;
    private MyOrderP myOrderP;
    List<OrderListBean.DataBean> orderList = new ArrayList<>();
    private boolean isRefresh;
    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("我的订单");
        orderState = getIntent().getIntExtra(KEY_ORDER_STATE,ORDER_STATE_ALL);
        mItems = new Items();
        mAdapter = new MultiTypeAdapter();
        mAdapter.setItems(mItems);
        mAdapter.register(OrderListBean.DataBean.class,new OrderItemViewBinder());
        rv_order.addItemDecoration(new PaddingItemDecoration().setDividerHeight(12).setColor("#f5f5f5"));
        rv_order.setLayoutManager(new LinearLayoutManager(this));
        rv_order.setAdapter(mAdapter);
        refreshLayout.setMyRefreshLayoutListener(this);
    }

    @Override
    protected void prepareData() {
        mItems.addAll(orderList);
        myOrderP = new MyOrderP(this);
        //刷新加载数据
        refreshLayout.beginRefreshing();



    }

    /**
     *
     * @param context
     * @param orderState 订单状态
     */
    public static void toActivity(Context context,int orderState) {
        Intent intent = new Intent(context, OrderActivity.class);
        intent.putExtra(KEY_ORDER_STATE,orderState);
        context.startActivity(intent);
    }

    @Override
    public void onRefresh(View view) {
        isRefresh = true;
        myOrderP.loadList(orderState,1,10);
    }

    @Override
    public void onLoadMore(View view) {
        //判断条目数 如果条目加载完全 禁止上拉加载 延时2000 更友好
        if (myOrderP.getTotal()<=orderList.size()){
            rv_order.postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.loadMoreComplete();
                    refreshLayout.setIsLoadingMoreEnabled(false);
                }
            },2000);
            ToastUtils.showToast(getString(R.string.no_more_data));
            return;
        }
        isRefresh = false;
        myOrderP.loadList(orderState,page,10);


    }

    @Override
    public void loadListSuccess(List<OrderListBean.DataBean> orderList) {
        refreshLayout.refreshComplete();
        refreshLayout.loadMoreComplete();
        if (isRefresh){
            this.orderList = orderList;
            mItems.clear();
            mItems.addAll(orderList);
            page=2;
        }
        else {
            this.orderList.addAll(orderList);
            page++;
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadListError(String msg) {
        refreshLayout.refreshComplete();
        refreshLayout.loadMoreComplete();
    }
}
