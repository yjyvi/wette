package com.risenb.wette.ui.mine;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.risenb.wette.R;
import com.risenb.wette.adapter.home.ProductListAdapter;
import com.risenb.wette.beans.GoodsListBean;
import com.risenb.wette.ui.LazyLoadFragment;
import com.risenb.wette.ui.home.GoodsListP;

import org.xutils.view.annotation.ViewInject;

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
public class CollectionCommodityFragment extends LazyLoadFragment implements GoodsListP.GoodsListListener {

    @ViewInject(R.id.rv_collection_commodity)
    private RecyclerView rv_collection_commodity;

    private ProductListAdapter mProductListAdapter;
    private GoodsListP mGoodsListP;
    private int page=1;
    private int limit=10;
    private List<GoodsListBean.DataBean> mGoodsList;


    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_collection_commodity,container,false);
    }

    @Override
    protected void setControlBasis() {
        mGoodsListP = new GoodsListP(getActivity(),this);
        mGoodsListP.setGoodsList(0,page,limit);
    }

    @Override
    protected void prepareData() {

        rv_collection_commodity.setLayoutManager(new GridLayoutManager(getContext(),2));
        mProductListAdapter = new ProductListAdapter(R.layout.item_product_list,mGoodsList);
        rv_collection_commodity.setAdapter(mProductListAdapter);
    }

    @Override
    public void resultGoodsListData(GoodsListBean result) {
        mGoodsList = result.getData() ;
        mProductListAdapter.setNewData(mGoodsList);
    }
}
