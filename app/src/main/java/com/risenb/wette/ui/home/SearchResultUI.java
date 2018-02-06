package com.risenb.wette.ui.home;

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

import com.risenb.wette.R;
import com.risenb.wette.adapter.home.SearchGoodsListAdapter;
import com.risenb.wette.beans.SearchBean;
import com.risenb.wette.ui.BaseUI;
import com.risenb.wette.ui.home.productDetial.SearchP;
import com.risenb.wette.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yjyvi
 * @date 2018/2/1
 *  搜索结果界面
 */
@ContentView(R.layout.activity_search_result)
public class SearchResultUI extends BaseUI implements SearchP.SearchGoodsListener {

    @ViewInject(R.id.rv_search_list)
    private RecyclerView rv_search_list;

    @ViewInject(R.id.common_title_back)
    private RelativeLayout title_back;

    @ViewInject(R.id.et_search)
    private EditText et_search;

    private ArrayList<String> mLeftData;
    public SearchP mSearchP;
    public SearchGoodsListAdapter mSearchGoodsListAdapter;
    public List<SearchBean.GoodsListBean> mGoodsList;


    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
//        testData();
        leftVisible(R.mipmap.back);
        setTitle("搜索");
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
    }

    private void testData() {
        mLeftData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mLeftData.add("商品列表名称商品列表名称商品列表名称" + i);
        }
    }

    @Override
    protected void prepareData() {

        mSearchP = new SearchP(this);


        Intent intent = getIntent();
        String searchContent = intent.getStringExtra("searchContent");
        if (!TextUtils.isEmpty(searchContent)) {
            et_search.setText(searchContent);
            mSearchP.setSearchData(searchContent);
        }

        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (TextUtils.isEmpty(textView.getText().toString().trim())) {
                    ToastUtils.showToast("请输入搜索关键字");
                } else {
                    //搜索接口
                    et_search.setText("");
                }
                return true;
            }
        });

        GridLayoutManager layout = new GridLayoutManager(this, 2);
        layout.setAutoMeasureEnabled(true);
        rv_search_list.setLayoutManager(layout);
        mSearchGoodsListAdapter = new SearchGoodsListAdapter(R.layout.item_good_list,mGoodsList);
        rv_search_list.setAdapter(mSearchGoodsListAdapter);
    }

    public static void start(Context context, String searchContent) {
        Intent starter = new Intent(context, SearchResultUI.class);
        starter.putExtra("searchContent", searchContent);
        context.startActivity(starter);
    }

    @Override
    public void searchData(SearchBean searchBean) {
        mGoodsList = searchBean.getGoodsList();
        mSearchGoodsListAdapter.setNewData(mGoodsList);
    }
}
