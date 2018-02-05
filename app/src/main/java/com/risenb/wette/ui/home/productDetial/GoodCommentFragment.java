package com.risenb.wette.ui.home.productDetial;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.risenb.wette.R;
import com.risenb.wette.adapter.home.GoodCommentAdapter;
import com.risenb.wette.beans.GoodCommentBean;
import com.risenb.wette.ui.LazyLoadFragment;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjyvi on 2018/1/31.
 */

public class GoodCommentFragment extends LazyLoadFragment implements GoodCommentP.GoodCommentListener {

    @ViewInject(R.id.rv_goods_comment)
    private RecyclerView rv_goods_comment;


    private ArrayList<String> mLeftData;
    public String mGoodsId;
    public GoodCommentP mGoodCommentP;
    private int page=1;
    private int limit=10;
    private List<GoodCommentBean.DataBean> mDataBean;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_product_comment, container, false);
    }

    @Override
    protected void setControlBasis() {

    }

    @Override
    protected void prepareData() {
        mGoodsId = getArguments().getString("goodsId");

        mGoodCommentP = new GoodCommentP(getActivity(),this);
        mGoodCommentP.setGoodComment(mGoodsId,page,limit);

        rv_goods_comment.setLayoutManager(new LinearLayoutManager(getActivity()));
        GoodCommentAdapter productCommentAdapter = new GoodCommentAdapter(R.layout.item_good_comment, mDataBean);
        rv_goods_comment.setAdapter(productCommentAdapter);
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
    public void goodCommentData(List<GoodCommentBean.DataBean> dataBean) {
        this.mDataBean=dataBean;
    }
}
