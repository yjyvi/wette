package com.lengzhuo.xybh.ui.classify;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.adapter.type.ClassifyLeftAdapter;
import com.lengzhuo.xybh.beans.ClassifyBean;
import com.lengzhuo.xybh.ui.LazyLoadFragment;
import com.lengzhuo.xybh.ui.home.SearchResultUI;
import com.lengzhuo.xybh.ui.mine.ShoppingCartActivity;
import com.lengzhuo.xybh.views.SmoothScrollLayoutManager;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 *
 * @author yjyvi
 * @date 2018/1/30
 * 分类界面
 */

public class ClassifyFragment extends LazyLoadFragment implements ClassifyP.ClassifyListener, View.OnClickListener {

    @ViewInject(R.id.rv_left)
    private RecyclerView rv_left;

    @ViewInject(R.id.common_title_back)
    private RelativeLayout rl_search;

    @ViewInject(R.id.rl_right)
    private RelativeLayout rl_right;

    public ClassifyP mClassifyP;
    public ClassifyLeftAdapter mClassifyLeftAdapter;
    private List<ClassifyBean.DataBean> mClassifyData;

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

        mClassifyP = new ClassifyP(getActivity(), this);
        mClassifyP.setClassifyData();

        rl_search.setOnClickListener(this);
        rl_right.setOnClickListener(this);
    }

    @Override
    protected void prepareData() {

//        leftData();

        //左侧列表
        SmoothScrollLayoutManager layout = new SmoothScrollLayoutManager(getActivity());
        layout.setAutoMeasureEnabled(true);

        rv_left.setLayoutManager(layout);
        mClassifyLeftAdapter = new ClassifyLeftAdapter(R.layout.item_classify_left, mClassifyData);
        rv_left.setAdapter(mClassifyLeftAdapter);
        mClassifyLeftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                scroll(position, adapter, view);
                if (mClassifyData != null && mClassifyData.size() > 0) {
                    initRightData(mClassifyData.get(position).getList());
                }
            }

        });
    }

    private void initRightData(List<ClassifyBean.DataBean.ListBeanX> classifyData) {
        //模拟右侧标签页
        TwoClassifyFragment fragment = TwoClassifyFragment.newInstance(classifyData);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.ll_right_main, fragment, "c0").commit();

    }


    //可见列表项的数量
    private int visibleCount = 0;
    //上次点击的位置
    private int lastPosition = 0;
    private int ce = 0;
    //实际列表是否超出屏幕
    private boolean isOut = true;

    private View lastView;

    private void scroll(int position, BaseQuickAdapter adapter, View view) {

        //改变选中状态
        if (!view.isSelected()) {
            //去除上一次控件的状态
            if (lastView != null) {
                lastView.setSelected(false);
            }
            lastView = view;
            view.setSelected(true);
        }


        if (visibleCount == 0) {
            visibleCount = rv_left.getChildCount();
            if (visibleCount == mClassifyData.size()) {
                isOut = false;
            } else {
                ce = visibleCount / 2;
            }
        }

        RecyclerView.LayoutManager layoutManager = rv_left.getLayoutManager();
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

    @Override
    public void classifyData(List<ClassifyBean.DataBean> dataBean) {
        this.mClassifyData = dataBean;
        mClassifyLeftAdapter.setNewData(dataBean);
        //默认显示第一个分类的数据
        if (dataBean != null && dataBean.size() > 0) {
            initRightData(dataBean.get(0).getList());
        }
    }

    @Override
    public void getDataField() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.common_title_back:
                SearchResultUI.start(view.getContext(), "");
                break;

            case R.id.rl_right:

                if (isLoginClick()) {
                    return;
                }

                ShoppingCartActivity.toActivity(view.getContext());
                break;
            default:
                break;
        }

    }
}
