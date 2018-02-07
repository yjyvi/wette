package com.risenb.wette.ui.home.productDetial;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.risenb.wette.R;
import com.risenb.wette.adapter.home.GoodCommentAdapter;
import com.risenb.wette.beans.GoodCommentListBean;
import com.risenb.wette.ui.LazyLoadFragment;
import com.risenb.wette.utils.ToastUtils;
import com.risenb.wette.views.refreshlayout.MyRefreshLayout;
import com.risenb.wette.views.refreshlayout.MyRefreshLayoutListener;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by yjyvi on 2018/1/31.
 */

public class GoodCommentFragment extends LazyLoadFragment implements GoodCommentListP.GoodCommentListener, MyRefreshLayoutListener {


    @ViewInject(R.id.rv_refresh)
    private MyRefreshLayout refreshLayout;

    @ViewInject(R.id.rv_goods_comment)
    private RecyclerView rv_goods_comment;

    public String mGoodsId;
    public GoodCommentListP mGoodCommentListP;
    private int page = 1;
    private int limit = 10;
    private List<GoodCommentListBean.DataBean> mDataBean;
    public GoodCommentAdapter mProductCommentAdapter;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_product_comment, container, false);
    }

    @Override
    protected void setControlBasis() {
        refreshLayout.setMyRefreshLayoutListener(this);
    }

    @Override
    protected void prepareData() {
        mGoodsId = getArguments().getString("goodsId");

        mGoodCommentListP = new GoodCommentListP(getActivity(), this);
        mGoodCommentListP.setGoodComment(mGoodsId, page, limit);

        rv_goods_comment.setLayoutManager(new LinearLayoutManager(getActivity()));
        mProductCommentAdapter = new GoodCommentAdapter(R.layout.item_good_comment, mDataBean);
        rv_goods_comment.setAdapter(mProductCommentAdapter);
        rv_goods_comment.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

    }

    /**
     * 初始化实例
     *
     * @return
     */
    public static GoodCommentFragment newInstance(String goodsId) {
        Bundle bundle = new Bundle();
        GoodCommentFragment goodCommentFragment = new GoodCommentFragment();
        bundle.putString("goodsId", goodsId);
        goodCommentFragment.setArguments(bundle);
        return goodCommentFragment;
    }

    @Override
    public void goodCommentData(List<GoodCommentListBean.DataBean> dataBean) {
        refreshLayout.refreshComplete();
        refreshLayout.loadMoreComplete();
        this.mDataBean = dataBean;

        if (1==page) {
            mProductCommentAdapter.setNewData(dataBean);
        }else {
            if (dataBean.size()>0) {
                mProductCommentAdapter.addData(dataBean);
            }else {
                ToastUtils.showToast("没有更多数据了");
            }
        }
    }

    @Override
    public void getCommentDataField() {
        refreshLayout.refreshComplete();
        refreshLayout.loadMoreComplete();
    }

    @Override
    public void onRefresh(View view) {
        page = 1;
        mGoodCommentListP.setGoodComment(mGoodsId, page, limit);
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        mGoodCommentListP.setGoodComment(mGoodsId, page, limit);
    }
}
