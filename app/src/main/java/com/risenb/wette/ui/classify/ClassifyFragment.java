package com.risenb.wette.ui.classify;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.risenb.wette.R;
import com.risenb.wette.adapter.type.ClassifyLeftAdapter;
import com.risenb.wette.adapter.type.ClassifyRightAdapter;
import com.risenb.wette.ui.LazyLoadFragment;
import com.risenb.wette.utils.ToastUtils;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by yjyvi on 2018/1/30.
 */

public class ClassifyFragment extends LazyLoadFragment {

    @ViewInject(R.id.rv_left)
    private RecyclerView rv_left;

    @ViewInject(R.id.rv_right)
    private RecyclerView rv_right;
    public ArrayList<String> mLeftData;
    private ArrayList<String> mRightData;
    public ClassifyRightAdapter mClassifyRightAdapter;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_classify, container, false);
    }

    @Override
    protected void setControlBasis() {
        setTitle("品类");
        leftVisible(R.mipmap.home_search);
        rightVisible(R.mipmap.home_cart);
    }

    @Override
    protected void prepareData() {

        leftData();
        rightData();

        //左侧列表
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        layout.setAutoMeasureEnabled(true);
        rv_left.setLayoutManager(layout);
        ClassifyLeftAdapter classifyLeftAdapter = new ClassifyLeftAdapter(R.layout.item_classify_left, mLeftData);
        rv_left.setAdapter(classifyLeftAdapter);
        rv_left.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        classifyLeftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                scroll(position,adapter,view);
                TextView item = (TextView) adapter.getItem(position);
                item.setSelected(true);
                ToastUtils.showToast(String.valueOf(position));
            }

        });


        //右则分类列表
        LinearLayoutManager layout2 = new LinearLayoutManager(getActivity());
        layout2.setAutoMeasureEnabled(true);
        rv_right.setLayoutManager(layout2);
        mClassifyRightAdapter = new ClassifyRightAdapter(R.layout.item_classify_right, mRightData);
        rv_right.setAdapter(mClassifyRightAdapter);
        rv_right.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

    }


    //可见列表项的数量
    private int visibleCount = 0;
    //上次点击的位置
    private int lastPosition = 0;
    private int ce = 0;
    //实际列表是否超出屏幕
    private boolean isOut = true;

    private void scroll(int position,BaseQuickAdapter adapter, View view) {
        if (visibleCount == 0) {
            visibleCount = rv_left.getChildCount();
            if (visibleCount == mLeftData.size())
                isOut = false;
            else {
                ce = visibleCount / 2;
            }
        }

        RecyclerView.LayoutManager layoutManager  = rv_left.getLayoutManager();

        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;

            //上移
            if (position <= (linearManager.findFirstVisibleItemPosition() + ce)) {
                rv_left.smoothScrollToPosition(position - ce);
            } else {
                //下移
                if ((linearManager.findLastVisibleItemPosition() + ce + 1) <= adapter.getItemCount()) {
                    rv_left.smoothScrollToPosition(position + ce);
                } else {
                    rv_left.smoothScrollToPosition(adapter.getItemCount() - 1);
                }

            }

            lastPosition = position;

//            adapter.setSelected(position);
//            adapter.notifyDataSetChanged();
        }



        //更新右侧标签页的标题
//        fragment.updateTitle("c" + (position + 1));
    }

    private void leftData() {
        mLeftData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mLeftData.add("一级分类" + i);
        }
    }

    private void rightData() {
        mRightData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mRightData.add("二级分类" + i);
        }
    }


    /**
     * 初始化实例
     *
     * @return
     */
    public static ClassifyFragment newInstance() {
        Bundle bundle = new Bundle();
        ClassifyFragment classifyFragment = new ClassifyFragment();
        classifyFragment.setArguments(bundle);
        return classifyFragment;
    }
}
