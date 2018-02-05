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

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjyvi on 2018/2/1.
 */
@ContentView(R.layout.activity_shop)
public class ShopUI extends BaseUI implements GoodsListP.GoodsListListener {

    @ViewInject(R.id.rv_goods_list)
    private RecyclerView rv_goods_list;

    @ViewInject(R.id.title_back)
    private RelativeLayout title_back;

    private ArrayList<String> mLeftData;
    private GoodsListP mGoodsListP;
    private int page=1;
    private int limit=10;
    public GoodListAdapter mProductListAdapter;
    private List<GoodsListBean.DataBean> mGoodsList;
    public String mShopId;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
//        testData();
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
        mGoodsListP = new GoodsListP(getActivity(),this);
        mGoodsListP.setGoodsList(0,page,limit);
    }

    private void testData() {
        mLeftData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mLeftData.add("商品列表名称" + i);
        }
    }

    @Override
    protected void prepareData() {
        mShopId = getIntent().getStringExtra("shopId");

        GridLayoutManager layout = new GridLayoutManager(this, 2);
        layout.setAutoMeasureEnabled(true);
        rv_goods_list.setLayoutManager(layout);
        mProductListAdapter = new GoodListAdapter(R.layout.item_good_list, mGoodsList);
        rv_goods_list.setAdapter(mProductListAdapter);
    }

    public static void start(Context context,String shopId) {
        Intent starter = new Intent(context, ShopUI.class);
        starter.putExtra("shopId",shopId);
        context.startActivity(starter);
    }

    @Override
    public void resultGoodsListData(GoodsListBean result) {
        mGoodsList = result.getData() ;
        mProductListAdapter.setNewData(mGoodsList);
    }
}
