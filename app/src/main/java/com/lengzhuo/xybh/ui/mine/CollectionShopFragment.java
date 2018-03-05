package com.lengzhuo.xybh.ui.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.CollectionBean;
import com.lengzhuo.xybh.network.CommonCallBack;
import com.lengzhuo.xybh.ui.LazyLoadFragment;
import com.lengzhuo.xybh.ui.mine.multitype.collection.ShopItemViewBinder;
import com.lengzhuo.xybh.utils.NetworkUtils;
import com.lengzhuo.xybh.utils.PaddingItemDecoration;
import com.lengzhuo.xybh.utils.Utils;
import com.lengzhuo.xybh.views.refreshlayout.MyRefreshLayout;
import com.lengzhuo.xybh.views.refreshlayout.MyRefreshLayoutListener;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/02
 *     desc   : 收藏店铺
 *     version: 1.0
 * </pre>
 */
public class CollectionShopFragment extends LazyLoadFragment implements MyRefreshLayoutListener {

    @ViewInject(R.id.rv_collection_shop)
    private RecyclerView rv_collection_shop;

    @ViewInject(R.id.rl_collection_shop)
    private MyRefreshLayout rl_collection_shop;

    @ViewInject(R.id.fl_empty_data)
    private FrameLayout fl_empty_data;

    private Items mItems;

    private MultiTypeAdapter mAdapter;

    private int mPageIndex = 1;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_collection_shop,container,false);
    }

    @Override
    protected void setControlBasis() {
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.register(CollectionBean.class,new ShopItemViewBinder());
        rv_collection_shop.addItemDecoration(new PaddingItemDecoration());
        rv_collection_shop.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter.setItems(mItems);
        rv_collection_shop.setAdapter(mAdapter);
        rl_collection_shop.setMyRefreshLayoutListener(this);
    }


    @Override
    protected void prepareData() {
        getCollectionShopList();
    }

    @Override
    public void onRefresh(View view) {
        mPageIndex = 1;
        mItems.clear();
        rl_collection_shop.setIsLoadingMoreEnabled(true);
        getCollectionShopList();
    }

    @Override
    public void onLoadMore(View view) {
        mPageIndex++;
        getCollectionShopList();
    }

    private void getCollectionShopList(){
        NetworkUtils.getNetworkUtils().getCollectionShopList(String.valueOf(mPageIndex), new CommonCallBack<List<CollectionBean>>() {
            @Override
            protected void onSuccess(List<CollectionBean> data) {
                if (Utils.isShowEmptyLayout(mPageIndex,data, rl_collection_shop, fl_empty_data)) return;
                if(data.size() < 10)
                    rl_collection_shop.setIsLoadingMoreEnabled(false);
                rl_collection_shop.refreshComplete();
                rl_collection_shop.loadMoreComplete();
                mItems.addAll(data);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

}
