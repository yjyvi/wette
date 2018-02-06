package com.risenb.wette.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.risenb.wette.R;
import com.risenb.wette.adapter.home.GoodListAdapter;
import com.risenb.wette.beans.GoodsListBean;
import com.risenb.wette.ui.BaseUI;
import com.risenb.wette.utils.ToastUtils;
import com.risenb.wette.views.refreshlayout.MyRefreshLayout;
import com.risenb.wette.views.refreshlayout.MyRefreshLayoutListener;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yjyvi
 * @date 2018/2/1
 * 店铺界面
 */
@ContentView(R.layout.activity_shop)
public class ShopDetailUI extends BaseUI implements GoodsListP.GoodsListListener, MyRefreshLayoutListener {

    @ViewInject(R.id.rv_refresh)
    private MyRefreshLayout refreshLayout;

    @ViewInject(R.id.rv_goods_list)
    private RecyclerView rv_goods_list;

    @ViewInject(R.id.title_back)
    private RelativeLayout title_back;

    private ArrayList<String> mLeftData;
    private GoodsListP mGoodsListP;
    private int page = 1;
    private int limit = 10;
    public GoodListAdapter mProductListAdapter;
    private List<GoodsListBean.DataBean> mGoodsList;
    public String mShopId;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        refreshLayout.setMyRefreshLayoutListener(this);

        mShopId = getIntent().getStringExtra("shopId");
    }


    @Override
    protected void prepareData() {

        mGoodsListP = new GoodsListP(getActivity(), this);
        mGoodsListP.setGoodsList(0, page, limit);

        GridLayoutManager layout = new GridLayoutManager(this, 2);
        layout.setAutoMeasureEnabled(true);
        rv_goods_list.setLayoutManager(layout);
        mProductListAdapter = new GoodListAdapter(R.layout.item_good_list, mGoodsList);
        rv_goods_list.setAdapter(mProductListAdapter);
    }

    public static void start(Context context, String shopId) {
        Intent starter = new Intent(context, ShopDetailUI.class);
        starter.putExtra("shopId", shopId);
        context.startActivity(starter);
    }

    @Override
    public void resultGoodsListData(GoodsListBean result) {
        mGoodsList = result.getData();

        refreshLayout.loadMoreComplete();
        refreshLayout.refreshComplete();

        if (1 == page) {
            mProductListAdapter.setNewData(mGoodsList);
        } else {
            if (result.getData().size() > 0) {
                mProductListAdapter.addData(result.getData());
            }else {
                ToastUtils.showToast("没有更多数据了");
            }
        }
    }

    @Override
    public void goodsListField() {
        refreshLayout.loadMoreComplete();
        refreshLayout.refreshComplete();
    }

    @Override
    public void onRefresh(View view) {
        page = 1;
        mGoodsListP.setGoodsList(0, page, limit);
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        mGoodsListP.setGoodsList(0, page, limit);
    }
}
