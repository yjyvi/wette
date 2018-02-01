package com.risenb.wette.adapter.type;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.risenb.wette.R;
import com.risenb.wette.ui.BaseViewHolder;
import com.risenb.wette.ui.home.ProductListUI;
import com.risenb.wette.utils.ToastUtils;

import java.util.List;

/**
 * Created by yjyvi on 2018/1/31.
 */

public class ClassifyRightAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private List<String> data;

    public ClassifyRightAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.data = data;
    }


    @Override
    protected void convert(com.risenb.wette.ui.BaseViewHolder helper, String item) {
        RecyclerView rv_goods = helper.getView(R.id.rv_goods);
        GridLayoutManager layout = new GridLayoutManager(helper.itemView.getContext(), 3);
        layout.setAutoMeasureEnabled(true);
        rv_goods.setLayoutManager(layout);
        GoodsListAdapter goodsListAdapter = new GoodsListAdapter(R.layout.item_goods_list, data);
        goodsListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showToast(String.valueOf(position));
                ProductListUI.start(view.getContext());
            }
        });
        rv_goods.setAdapter(goodsListAdapter);

    }
}
