package com.risenb.wette.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
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
 * @date 2018/1/31
 * 商品列表
 */

@ContentView(R.layout.activity_good_list)
public class GoodListUI extends BaseUI implements GoodsListP.GoodsListListener, MyRefreshLayoutListener {

    @ViewInject(R.id.rv_refresh)
    private MyRefreshLayout refreshLayout;

    @ViewInject(R.id.rv_good_list)
    private RecyclerView rv_good_list;

    @ViewInject(R.id.common_title_back)
    private RelativeLayout common_title_back;

    public GoodListAdapter mGoodListAdapter;
    private ArrayList<String> mLeftData;
    public GoodsListP mGoodsListP;
    private int page = 1;
    private int limit = 10;
    private List<GoodsListBean.DataBean> mGoodsList;

    @Override
    protected void back() {
        finish();
    }


    @Override
    protected void setControlBasis() {
        setTitle("商品列表");
        leftVisible(R.mipmap.back);
        rightVisible(R.mipmap.home_cart);
    }

    @Override
    protected void prepareData() {

        refreshLayout.setMyRefreshLayoutListener(this);

        mGoodsListP = new GoodsListP(this, this);
        mGoodsListP.setGoodsList(0, page, limit);

        rv_good_list.setLayoutManager(new GridLayoutManager(this, 2));
        mGoodListAdapter = new GoodListAdapter(R.layout.item_good_list, mGoodsList);
        rv_good_list.setAdapter(mGoodListAdapter);
        mGoodListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GoodDetailsUI.start(view.getContext(), String.valueOf(mGoodsList.get(position).getGoodsId()), String.valueOf(mGoodsList.get(position).getShopId()));
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
        Intent starter = new Intent(context, GoodListUI.class);
        context.startActivity(starter);
    }

    @Override
    public void resultGoodsListData(GoodsListBean result) {
        mGoodsList = result.getData();
        refreshLayout.refreshComplete();
        refreshLayout.loadMoreComplete();

        if (page == 1) {
            mGoodListAdapter.setNewData(mGoodsList);
        } else {
            if (result.getData().size() > 0) {
                mGoodListAdapter.addData(result.getData());
            }else {
                ToastUtils.showToast("没有更多数据了");
            }
        }
    }

    @Override
    public void goodsListField() {
        refreshLayout.refreshComplete();
        refreshLayout.loadMoreComplete();
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
