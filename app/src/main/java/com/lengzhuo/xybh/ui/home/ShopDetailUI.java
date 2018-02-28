package com.lengzhuo.xybh.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.adapter.home.GoodListAdapter;
import com.lengzhuo.xybh.beans.GoodsListBean;
import com.lengzhuo.xybh.beans.ShopDetailBean;
import com.lengzhuo.xybh.ui.BaseUI;
import com.lengzhuo.xybh.ui.mine.ShoppingCartActivity;
import com.lengzhuo.xybh.utils.GlideImgUtils;
import com.lengzhuo.xybh.utils.ToastUtils;
import com.lengzhuo.xybh.views.refreshlayout.MyRefreshLayout;
import com.lengzhuo.xybh.views.refreshlayout.MyRefreshLayoutListener;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * @author yjyvi
 * @date 2018/2/1
 * 店铺界面
 */
@ContentView(R.layout.activity_shop)
public class ShopDetailUI extends BaseUI implements GoodsListP.GoodsListListener, MyRefreshLayoutListener, ShopDetailP.ShopDataListener, CollectionP.CollectionListener {

    @ViewInject(R.id.rv_refresh)
    private MyRefreshLayout refreshLayout;

    @ViewInject(R.id.rv_goods_list)
    private RecyclerView rv_goods_list;

    @ViewInject(R.id.iv_shop_img)
    private ImageView iv_shop_img;

    @ViewInject(R.id.tv_shop_name)
    private TextView tv_shop_name;

    @ViewInject(R.id.iv_is_collection)
    private ImageView iv_is_collection;

    @ViewInject(R.id.ll_empty_view)
    private LinearLayout ll_empty_view;

    private GoodsListP mGoodsListP;
    private int page = 1;
    private int limit = 10;
    public GoodListAdapter mProductListAdapter;
    private List<GoodsListBean.DataBean> mGoodsList;
    public String mShopId;
    public ShopDetailP mShopDetailP;
    public CollectionP mCollectionP;
    private int isCollection;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {

        refreshLayout.setMyRefreshLayoutListener(this);

        mShopId = getIntent().getStringExtra("shopId");

        mGoodsListP = new GoodsListP(this);
        mShopDetailP = new ShopDetailP(this);
        mCollectionP = new CollectionP(this);
    }


    @Override
    protected void prepareData() {


        mShopDetailP.setShopData(mShopId, page, limit);
        mGoodsListP.setGoodsList(0, mShopId, page, limit);

        GridLayoutManager layout = new GridLayoutManager(this, 2);
        layout.setAutoMeasureEnabled(true);
        rv_goods_list.setLayoutManager(layout);
        mProductListAdapter = new GoodListAdapter(R.layout.item_good_list, mGoodsList);
        rv_goods_list.setAdapter(mProductListAdapter);

        mProductListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mGoodsList != null) {
                    GoodDetailsUI.start(view.getContext(), String.valueOf(mGoodsList.get(position).getGoodsId()), String.valueOf(mGoodsList.get(position).getShopId()));
                }

            }
        });
    }

    public static void start(Context context, String shopId) {
        Intent starter = new Intent(context, ShopDetailUI.class);
        starter.putExtra("shopId", shopId);
        context.startActivity(starter);
    }


    @Event(value = {R.id.rl_title_back, R.id.iv_is_collection, R.id.rl_search, R.id.rl_cart}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_title_back:
                back();
                break;
            case R.id.iv_is_collection:

                if (isLoginClick()) {
                    return;
                }

                if (!iv_is_collection.isSelected()) {
                    iv_is_collection.setSelected(true);
                    isCollection = 1;
                } else {
                    isCollection = 2;
                    iv_is_collection.setSelected(false);
                }
                mCollectionP.setCollection(isCollection, mShopId, "2");
                break;
            case R.id.rl_search:
                SearchResultUI.start(view.getContext(), "");
                break;
            case R.id.rl_cart:

                if (isLoginClick()) {
                    return;
                }

                ShoppingCartActivity.toActivity(view.getContext());
                break;
            default:
                break;
        }
    }

    @Override
    public void resultGoodsListData(GoodsListBean result) {

        mGoodsList = result.getData();

        refreshLayout.loadMoreComplete();
        refreshLayout.refreshComplete();

        if (1 == page) {
            showEmptyView(result.getData(), ll_empty_view);
            mProductListAdapter.setNewData(result.getData());
        } else {
            if (result.getData().size() > 0) {
                mProductListAdapter.addData(result.getData());
            } else {
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
        mGoodsListP.setGoodsList(0, mShopId, page, limit);
        mShopDetailP.setShopData(mShopId, page, limit);
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        mGoodsListP.setGoodsList(0,  mShopId,page, limit);
    }

    @Override
    public void getDataSuccess(ShopDetailBean.DataBean data) {
        refreshLayout.refreshComplete();
        if (data != null) {
            //回显
            if (data.getIsCollection() == 1) {
                iv_is_collection.setSelected(true);
            } else {
                iv_is_collection.setSelected(false);
            }

            GlideImgUtils.loadImg(this, data.getLogo(), iv_shop_img);
            tv_shop_name.setText(data.getShopName());
        }
    }

    @Override
    public void getDataField() {
        refreshLayout.refreshComplete();
    }

    @Override
    public void collectionSuccess() {
        ToastUtils.showToast("收藏成功");
    }

    @Override
    public void collectionField() {
        ToastUtils.showToast("收藏失败");
    }
}
