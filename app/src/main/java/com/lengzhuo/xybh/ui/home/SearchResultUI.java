package com.lengzhuo.xybh.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.adapter.home.SearchGoodsListAdapter;
import com.lengzhuo.xybh.beans.SearchBean;
import com.lengzhuo.xybh.ui.BaseUI;
import com.lengzhuo.xybh.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yjyvi
 * @date 2018/2/1
 * 搜索结果界面
 */
@ContentView(R.layout.activity_search_result)
public class SearchResultUI extends BaseUI implements SearchP.SearchGoodsListener {

    @ViewInject(R.id.rv_search_list)
    private RecyclerView rv_search_list;

    @ViewInject(R.id.common_title_back)
    private RelativeLayout title_back;

    @ViewInject(R.id.tv_num)
    private TextView tv_num;

    @ViewInject(R.id.et_search)
    private EditText et_search;

    private ArrayList<String> mLeftData;
    public SearchP mSearchP;
    public SearchGoodsListAdapter mSearchGoodsListAdapter;
    public List<SearchBean.DataBean.GoodsListBean> mGoodsList;
    private int page = 1;
    private int limit = 10;
    public String mSearchContent;


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

        mSearchP = new SearchP(this);


        Intent intent = getIntent();
        mSearchContent = intent.getStringExtra("searchContent");
        if (!TextUtils.isEmpty(mSearchContent)) {
            mSearchP.setSearchData(mSearchContent, page, limit);
        }

        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (TextUtils.isEmpty(textView.getText().toString().trim())) {
                    ToastUtils.showToast("请输入搜索关键字");
                } else {
                    //搜索接口

                    mSearchP.setSearchData(mSearchContent, page, limit);
                    et_search.setText("");
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
        Intent starter = new Intent(context, SearchResultUI.class);
        starter.putExtra("searchContent", searchContent);
        context.startActivity(starter);
    }

    @Override
    public void searchData(SearchBean.DataBean searchBean) {
        mGoodsList = searchBean.getGoodsList();
        mSearchGoodsListAdapter.setNewData(mGoodsList);
        tv_num.setText(String.valueOf(searchBean.getGoodsList().size() + "个结果"));
    }

    @Override
    public void searchField() {

    }
}
