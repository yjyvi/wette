package com.risenb.wette.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.risenb.wette.R;
import com.risenb.wette.adapter.home.ProductListAdapter;
import com.risenb.wette.beans.GoodsListBean;
import com.risenb.wette.ui.BaseUI;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjyvi on 2018/1/31.
 */

@ContentView(R.layout.activity_product_list)
public class ProductListUI extends BaseUI implements GoodsListP.GoodsListListener {

    @ViewInject(R.id.rv_product_list)
    private RecyclerView rv_product_list;

    @ViewInject(R.id.common_title_back)
    private RelativeLayout common_title_back;

    public ProductListAdapter mProductListAdapter;
    private ArrayList<String > mLeftData;
    public GoodsListP mGoodsListP;
    private int page=1;
    private int limit=10;
    private List<GoodsListBean.DataBean> mGoodsList;

    @Override
    protected void back() {
        finish();
    }

    private void testData() {
        mLeftData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mLeftData.add("商品列表名称" + i);
        }
    }

    @Override
    protected void setControlBasis() {
        testData();
        setTitle("商品列表");
        leftVisible(R.mipmap.back);
        rightVisible(R.mipmap.home_cart);


    }

    @Override
    protected void prepareData() {

        mGoodsListP = new GoodsListP(this,this);
        mGoodsListP.setGoodsList(0,page,limit);

        rv_product_list.setLayoutManager(new GridLayoutManager(this, 2));
        mProductListAdapter = new ProductListAdapter(R.layout.item_product_list, mGoodsList);
        rv_product_list.setAdapter(mProductListAdapter);
        mProductListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProductDetailsUI.start(view.getContext());
            }
        });

        common_title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ProductListUI.class);
        context.startActivity(starter);
    }

    @Override
    public void resultGoodsListData(GoodsListBean result) {
        mGoodsList = result.getData() ;
        mProductListAdapter.setNewData(mGoodsList);
    }
}
