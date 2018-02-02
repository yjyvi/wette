package com.risenb.wette.ui.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.risenb.wette.R;
import com.risenb.wette.beans.CollectionShopBean;
import com.risenb.wette.ui.LazyLoadFragment;
import com.risenb.wette.ui.mine.multitype.CollectionShopItemViewBinder;
import com.risenb.wette.utils.PaddingItemDecoration;

import org.xutils.view.annotation.ViewInject;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/02
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class CollectionShopFragment extends LazyLoadFragment {

    @ViewInject(R.id.rv_collection_shop)
    private RecyclerView rv_collection_shop;

    private Items mItems;

    private MultiTypeAdapter mAdapter;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_collection_shop,container,false);
    }

    @Override
    protected void setControlBasis() {
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.register(CollectionShopBean.class,new CollectionShopItemViewBinder());
        rv_collection_shop.addItemDecoration(new PaddingItemDecoration());
        rv_collection_shop.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter.setItems(mItems);
        rv_collection_shop.setAdapter(mAdapter);
    }

    @Override
    protected void prepareData() {
        for (int i = 0; i < 10; i++) {
            mItems.add(new CollectionShopBean());
        }
        mAdapter.notifyDataSetChanged();
    }
}
