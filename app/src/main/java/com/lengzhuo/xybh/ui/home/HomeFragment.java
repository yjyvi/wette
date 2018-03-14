package com.lengzhuo.xybh.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.adapter.home.HomeAdapter;
import com.lengzhuo.xybh.beans.GoodsListBean;
import com.lengzhuo.xybh.beans.HomeBean;
import com.lengzhuo.xybh.ui.LazyLoadFragment;
import com.lengzhuo.xybh.ui.mine.ShoppingCartActivity;
import com.lengzhuo.xybh.ui.mytest.SearchResultActivity;
import com.lengzhuo.xybh.utils.KeyboardUtils;
import com.lengzhuo.xybh.utils.ToastUtils;
import com.lengzhuo.xybh.views.refreshlayout.MyRefreshLayout;
import com.lengzhuo.xybh.views.refreshlayout.MyRefreshLayoutListener;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * @author yjyvi
 * @date 2018/1/30
 * 首页
 */

public class HomeFragment extends LazyLoadFragment implements HomeP.HomeListener, GoodsListP.GoodsListListener, MyRefreshLayoutListener {

    @ViewInject(R.id.rv_refresh)
    private MyRefreshLayout refreshLayout;

    @ViewInject(R.id.rv_home)
    private RecyclerView rv_home;

    @ViewInject(R.id.rl_title_search)
    private RelativeLayout rl_title_search;

    @ViewInject(R.id.rl_right)
    private RelativeLayout rl_cart;

    @ViewInject(R.id.ll_empty_view)
    private LinearLayout ll_empty_view;

    @ViewInject(R.id.et_search)
    private EditText et_search;
    public HomeP mHomeP;
    public GoodsListP mGoodsListP;
    private int page = 1;
    private int limit = 10;
    public HomeAdapter mHomeAdapter;
    private List<GoodsListBean.DataBean> goodsListBean;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    protected void setControlBasis() {
        //跳转购物车
        rl_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLoginClick()) {
                    return;
                }
                ShoppingCartActivity.toActivity(view.getContext());
            }
        });

        refreshLayout.setMyRefreshLayoutListener(this);

        mHomeP = new HomeP(getActivity(), this);
        mGoodsListP = new GoodsListP(this);
        mHomeP.setHomeData();
        mGoodsListP.setGoodsList(0, page, limit);
    }

    @Override
    protected void prepareData() {
        rv_home.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeAdapter = new HomeAdapter();
        rv_home.setAdapter(mHomeAdapter);

        rl_title_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                et_search.setVisibility(View.VISIBLE);
                rl_title_search.setVisibility(View.INVISIBLE);
                et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if (TextUtils.isEmpty(textView.getText().toString().trim())) {
                            ToastUtils.showToast("请输入搜索关键字");
                        } else {
                            SearchResultActivity.start(view.getContext(), et_search.getText().toString().trim());
                            et_search.setVisibility(View.GONE);
                            et_search.setText("");
                            rl_title_search.setVisibility(View.VISIBLE);
                        }
                        return true;
                    }
                });
            }
        });

        rv_home.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerView.SCROLL_STATE_DRAGGING == newState) {
                    //改变EditText的布局显示
                    et_search.setText("");
                    et_search.setVisibility(View.GONE);
                    rl_title_search.setVisibility(View.VISIBLE);
                    KeyboardUtils.hideKeyBoard(view.getContext(), view);
                }
            }
        });

    }


    /**
     * 初始化实例
     *
     * @return
     */
    public static HomeFragment newInstance() {
        Bundle bundle = new Bundle();
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    public void homeDataSuccess(HomeBean.DataBean result) {
        refreshLayout.refreshComplete();
        mHomeAdapter.setHomeDataBean(result);
        mHomeAdapter.notifyDataSetChanged();
    }

    @Override
    public void requestField() {
        refreshLayout.refreshComplete();
    }

    @Override
    public void resultGoodsListData(GoodsListBean result) {
        refreshLayout.loadMoreComplete();
        refreshLayout.refreshComplete();
        if (page == 1) {
            this.goodsListBean = result.getData();
            mHomeAdapter.setGoodsListBean(result.getData());
            mHomeAdapter.notifyDataSetChanged();
            showEmptyView(result.getData(),ll_empty_view);
        } else {
            if (result.getData().size() > 0) {
                goodsListBean.addAll(result.getData());
                mHomeAdapter.setGoodsListBean(goodsListBean);
                mHomeAdapter.notifyDataSetChanged();
            } else {
//                ToastUtils.showToast("没有更多数据了");
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
        mHomeP.setHomeData();
        mGoodsListP.setGoodsList(0, page, limit);
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        mGoodsListP.setGoodsList(0, page, limit);
    }
}
