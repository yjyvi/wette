package com.lengzhuo.xybh.ui.mytest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.adapter.home.SearchGoodsListAdapter;
import com.lengzhuo.xybh.beans.SearchBean;
import com.lengzhuo.xybh.ui.BaseUI;
import com.lengzhuo.xybh.ui.home.GoodDetailsUI;
import com.lengzhuo.xybh.utils.KeyboardUtils;
import com.lengzhuo.xybh.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * @author yjyvi
 * @date 2018/2/1
 * 搜索结果界面
 */
@ContentView(R.layout.activity_search_result)
public class SearchResultActivity extends BaseUI implements SearchView {

    @ViewInject(R.id.rv_search_list)
    private RecyclerView rv_search_list;

    @ViewInject(R.id.common_title_back)
    private RelativeLayout title_back;

    @ViewInject(R.id.tv_num)
    private TextView tv_num;

    @ViewInject(R.id.et_search)
    private EditText et_search;

    @ViewInject(R.id.ll_empty_view)
    private LinearLayout ll_empty_view;

    public SearchGoodsListAdapter mSearchGoodsListAdapter;
    public List<SearchBean.DataBean.GoodsListBean> mGoodsList;
    private int page = 1;
    public String mSearchContent;
    public SearchPresenter mSearchPresenter;
    public ProgressDialog mProgressDialog;


    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        leftVisible(R.mipmap.back);
        setTitle("搜索");
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
    }


    @Override
    protected void prepareData() {


        mSearchPresenter = new SearchPresenter(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在加载...");

        Intent intent = getIntent();
        mSearchContent = intent.getStringExtra("searchContent");

        if (!TextUtils.isEmpty(mSearchContent)) {
            et_search.setText(mSearchContent);
            mSearchPresenter.getSearchResult(mSearchContent);
        }

        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (TextUtils.isEmpty(textView.getText().toString().trim())) {
                    ToastUtils.showToast("请输入搜索关键字");
                } else {
                    //搜索接口
                    mSearchContent = textView.getText().toString().trim();
                    mSearchPresenter.getSearchResult(mSearchContent);
                    KeyboardUtils.hideKeyBoard(getActivity(), textView);
                }
                return true;
            }
        });

        GridLayoutManager layout = new GridLayoutManager(this, 2);
        layout.setAutoMeasureEnabled(true);
        rv_search_list.setLayoutManager(layout);
        mSearchGoodsListAdapter = new SearchGoodsListAdapter(R.layout.item_good_list, mGoodsList);
        rv_search_list.setAdapter(mSearchGoodsListAdapter);
        mSearchGoodsListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GoodDetailsUI.start(view.getContext(),
                        String.valueOf(mGoodsList.get(position).getGoodsId()),
                        String.valueOf(mGoodsList.get(position).getShopId()));
            }
        });
    }

    public static void start(Context context, String searchContent) {
        Intent starter = new Intent(context, SearchResultActivity.class);
        starter.putExtra("searchContent", searchContent);
        context.startActivity(starter);
    }


    @Override
    public void showResult(SearchBean searchBean) {
        mGoodsList = searchBean.getData().getGoodsList();
        mSearchGoodsListAdapter.setNewData(searchBean.getData().getGoodsList());
        showEmptyView(searchBean.getData().getGoodsList(), ll_empty_view);
        if (0 == searchBean.getData().getGoodsList().size()) {
            tv_num.setVisibility(View.GONE);
        } else {
            tv_num.setText(String.valueOf(searchBean.getData().getGoodsList().size() + "个结果"));
        }
    }

    @Override
    public void showProgressDialog() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showError(String errMsg) {
        mProgressDialog.dismiss();
        ToastUtils.showToast(errMsg);
    }
}
