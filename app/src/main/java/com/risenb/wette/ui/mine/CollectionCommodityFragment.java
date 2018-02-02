package com.risenb.wette.ui.mine;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.risenb.wette.R;
import com.risenb.wette.adapter.home.ProductListAdapter;
import com.risenb.wette.ui.LazyLoadFragment;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/02
 *     desc   : 收藏商品
 *     version: 1.0
 * </pre>
 */
public class CollectionCommodityFragment extends LazyLoadFragment {

    @ViewInject(R.id.rv_collection_commodity)
    private RecyclerView rv_collection_commodity;

    private ProductListAdapter mAdapter;

    private List<String> mData = new ArrayList();

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_collection_commodity,container,false);
    }

    @Override
    protected void setControlBasis() {
        mAdapter = new ProductListAdapter(R.layout.item_product_list,mData);
        rv_collection_commodity.setLayoutManager(new GridLayoutManager(getContext(),2));
        rv_collection_commodity.setAdapter(mAdapter);
    }

    @Override
    protected void prepareData() {
        for (int i = 0; i < 20; i++) {
            mData.add("xxxx");
        }
    }
}
