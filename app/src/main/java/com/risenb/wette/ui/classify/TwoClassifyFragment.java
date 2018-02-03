package com.risenb.wette.ui.classify;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.risenb.wette.R;
import com.risenb.wette.adapter.type.ClassifyRightAdapter;
import com.risenb.wette.ui.LazyLoadFragment;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by yjyvi on 2018/2/1.
 */

public class TwoClassifyFragment extends LazyLoadFragment {

    String TAG = "TwoClassifyFragment";
    @ViewInject(R.id.rv_category)
    private RecyclerView rv_category;

    private LinearLayout.LayoutParams lp_gd = null;
    private LinearLayout.LayoutParams lp_tv = null;
    private ArrayList<String> mRightData;
    public ClassifyRightAdapter mGoodsListAdapter;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_two_type_list, container, false);
    }

    @Override
    protected void setControlBasis() {
        setData();
    }

    @Override
    protected void prepareData() {

    }

    private void setData() {
        rightData();
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        layout.setAutoMeasureEnabled(true);
        rv_category.setLayoutManager(layout);
        mGoodsListAdapter = new ClassifyRightAdapter(R.layout.item_classify_right, mRightData);
        rv_category.setAdapter(mGoodsListAdapter);

    }

    private void rightData() {
        mRightData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mRightData.add("二级分类" + i);
        }
    }


}
