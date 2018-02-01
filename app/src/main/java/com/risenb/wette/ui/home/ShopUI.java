package com.risenb.wette.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.risenb.wette.R;
import com.risenb.wette.adapter.home.ProductListAdapter;
import com.risenb.wette.ui.BaseUI;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by yjyvi on 2018/2/1.
 */
@ContentView(R.layout.activity_shop)
public class ShopUI extends BaseUI {

    @ViewInject(R.id.rv_goods_list)
    private RecyclerView rv_goods_list;

    @ViewInject(R.id.title_back)
    private RelativeLayout title_back;

    private ArrayList<String> mLeftData;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        testData();
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
    }

    private void testData() {
        mLeftData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mLeftData.add("商品列表名称" + i);
        }
    }

    @Override
    protected void prepareData() {
        GridLayoutManager layout = new GridLayoutManager(this, 2);
        layout.setAutoMeasureEnabled(true);
        rv_goods_list.setLayoutManager(layout);
        rv_goods_list.setAdapter(new ProductListAdapter(R.layout.item_product_list, mLeftData));
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ShopUI.class);
        context.startActivity(starter);
    }
}
