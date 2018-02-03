package com.risenb.wette.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.risenb.wette.R;
import com.risenb.wette.beans.OrderBean;
import com.risenb.wette.ui.BaseUI;
import com.risenb.wette.ui.mine.multitype.OrderItemViewBinder;
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
 *     desc   : 我的订单
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_order)
public class OrderActivity extends BaseUI {

    private Items mItems;

    private MultiTypeAdapter mAdapter;

    @ViewInject(R.id.rv_order)
    private RecyclerView rv_order;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("我的订单");
        mItems = new Items();
        mAdapter = new MultiTypeAdapter();
        mAdapter.setItems(mItems);
        mAdapter.register(OrderBean.class,new OrderItemViewBinder());
        rv_order.addItemDecoration(new PaddingItemDecoration().setDividerHeight(12).setColor("#f5f5f5"));
        rv_order.setLayoutManager(new LinearLayoutManager(this));
        rv_order.setAdapter(mAdapter);
    }

    @Override
    protected void prepareData() {
        for (int i = 0; i < 10; i++) {
            mItems.add(new OrderBean());
        }
        mAdapter.notifyDataSetChanged();
    }
    
    public static void toActivity(Context context) {
        Intent intent = new Intent(context, OrderActivity.class);
        context.startActivity(intent);
    }
    
}
