package com.lengzhuo.xybh.ui.mine;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.CollectionBean;
import com.lengzhuo.xybh.network.CommonCallBack;
import com.lengzhuo.xybh.ui.LazyLoadFragment;
import com.lengzhuo.xybh.ui.mine.multitype.collection.CommodityItemViewBinder;
import com.lengzhuo.xybh.utils.NetworkUtils;
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
 *     desc   : 收藏商品
 *     version: 1.0
 * </pre>
 */
public class CollectionCommodityFragment extends LazyLoadFragment implements MyRefreshLayoutListener {

    @ViewInject(R.id.rv_collection_commodity)
    private RecyclerView rv_collection_commodity;

    @ViewInject(R.id.rl_collection_commodity)
    private MyRefreshLayout rl_collection_commodity;

    @ViewInject(R.id.fl_empty_data)
    private FrameLayout fl_empty_data;

    private int mPageIndex = 1;

    Items mItems;

    MultiTypeAdapter mAdapter;


    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_collection_commodity,container,false);
    }

    @Override
    protected void setControlBasis() {
        mItems = new Items();
        mAdapter = new MultiTypeAdapter();
        mAdapter.register(CollectionBean.class,new CommodityItemViewBinder());
        mAdapter.setItems(mItems);
        rl_collection_commodity.setMyRefreshLayoutListener(this);
        rv_collection_commodity.setLayoutManager(new GridLayoutManager(getContext(),2));
        rv_collection_commodity.setAdapter(mAdapter);
    }

    @Override
    protected void prepareData() {
        getCollectionCommodityList();
    }

    private void getCollectionCommodityList(){
        rl_collection_commodity.refreshComplete();
        rl_collection_commodity.loadMoreComplete();
        NetworkUtils.getNetworkUtils().getCollectionCommodityList(String.valueOf(mPageIndex), new CommonCallBack<List<CollectionBean>>() {
            @Override
            protected void onSuccess(List<CollectionBean> data) {
                if (Utils.isShowEmptyLayout(mPageIndex,data, rl_collection_commodity, fl_empty_data)) return;
                if(data.size()<10)
                    rl_collection_commodity.setIsLoadingMoreEnabled(false);
                mItems.addAll(data);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onRefresh(View view) {
        mPageIndex = 1;
        mItems.clear();
        rl_collection_commodity.setIsLoadingMoreEnabled(true);
        getCollectionCommodityList();
    }

    @Override
    public void onLoadMore(View view) {
        mPageIndex ++;
        getCollectionCommodityList();
    }
}
