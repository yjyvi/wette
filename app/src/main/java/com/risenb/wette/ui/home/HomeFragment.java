package com.risenb.wette.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.risenb.wette.R;
import com.risenb.wette.adapter.home.HomeAdapter;
import com.risenb.wette.ui.LazyLoadFragment;
import com.risenb.wette.utils.ToastUtils;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by yjyvi on 2018/1/30.
 */

public class HomeFragment extends LazyLoadFragment {

    @ViewInject(R.id.rv_home)
    private RecyclerView rv_home;

    @ViewInject(R.id.rl_title_search)
    private RelativeLayout rl_title_search;

    @ViewInject(R.id.et_search)
    private EditText et_search;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    protected void setControlBasis() {

    }

    @Override
    protected void prepareData() {
        rv_home.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_home.setAdapter(new HomeAdapter());

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
                            SearchResultUI.start(view.getContext(),et_search.getText().toString().trim());
                            et_search.setText("");
                            et_search.setVisibility(View.GONE);
                            rl_title_search.setVisibility(View.VISIBLE);
                        }
                        return true;
                    }
                });


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
}
