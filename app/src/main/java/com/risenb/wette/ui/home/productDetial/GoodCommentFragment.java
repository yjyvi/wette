package com.risenb.wette.ui.home.productDetial;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.risenb.wette.R;
import com.risenb.wette.adapter.home.GoodCommentAdapter;
import com.risenb.wette.ui.LazyLoadFragment;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by yjyvi on 2018/1/31.
 */

public class GoodCommentFragment extends LazyLoadFragment {

    @ViewInject(R.id.rv_goods_comment)
    private RecyclerView rv_goods_comment;


    private ArrayList<String> mLeftData;
    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater=inflater;
        view=inflater.inflate(R.layout.fragment_product_comment,container,false);
    }

    @Override
    protected void setControlBasis() {

    }

    @Override
    protected void prepareData() {
        testData();
        rv_goods_comment.setLayoutManager(new LinearLayoutManager(getActivity()));
        GoodCommentAdapter productCommentAdapter = new GoodCommentAdapter(R.layout.item_good_comment,mLeftData);
        rv_goods_comment.setAdapter(productCommentAdapter);
        rv_goods_comment.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

    }

    private void testData() {
        mLeftData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mLeftData.add("评论" + i);
        }
    }

    /**
     * 初始化实例
     *
     * @return
     */
    public static GoodCommentFragment newInstance() {
        Bundle bundle = new Bundle();
        GoodCommentFragment goodCommentFragment = new GoodCommentFragment();
        goodCommentFragment.setArguments(bundle);
        return goodCommentFragment;
    }
}
