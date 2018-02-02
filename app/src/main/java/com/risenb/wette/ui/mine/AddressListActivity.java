package com.risenb.wette.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.risenb.wette.R;
import com.risenb.wette.beans.AddressBean;
import com.risenb.wette.ui.BaseUI;
import com.risenb.wette.ui.mine.multitype.AddressItemViewBinder;
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
 *     desc   : 收货地址
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_address_list)
public class AddressListActivity extends BaseUI {


    @ViewInject(R.id.rv_address_list)
    private RecyclerView rv_address_list;

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
        mAdapter.register(AddressBean.class,new AddressItemViewBinder());
        rv_address_list.addItemDecoration(new PaddingItemDecoration());
        rv_address_list.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setItems(mItems);
        rv_address_list.setAdapter(mAdapter);
    }

    @Override
    protected void prepareData() {
        for (int i = 0; i < 10; i++) {
            mItems.add(new AddressBean());
        }
        mAdapter.notifyDataSetChanged();
    }
    
    public static void toActivity(Context context) {
        Intent intent = new Intent(context, AddressListActivity.class);
        context.startActivity(intent);
    }
    
}
