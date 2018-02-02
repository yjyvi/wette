package com.risenb.wette.ui.classify;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.risenb.wette.R;
import com.risenb.wette.adapter.type.GoodsListAdapter;
import com.risenb.wette.ui.LazyLoadFragment;
import com.zhy.autolayout.AutoLinearLayout;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjyvi on 2018/2/1.
 */

public class TwoClassifyFragment extends LazyLoadFragment {

    String TAG = "TwoClassifyFragment";
//    @ViewInject(R.id.rv_category)
//    private RecyclerView rv_category;

    @ViewInject(R.id.ll_frg_main)
    private LinearLayout llayout_main;

    @ViewInject(R.id.tv_frg_main)
    private TextView tv;

    private AutoLinearLayout.LayoutParams lp_gd = null;
    private AutoLinearLayout.LayoutParams lp_tv = null;
    private ArrayList<Category> itemList = null;
    private GDAdapter adapter = null;
    private ArrayList<String> mRightData;
    public GoodsListAdapter mGoodsListAdapter;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_two_type_list, container, false);
    }

    @Override
    protected void setControlBasis() {
        updateTitle();

        //模拟数据
        for (int i = 0; i < 3; i++) {
            setData();
        }

    }

    @Override
    protected void prepareData() {

    }

    protected void updateTitle() {
        if (getArguments() != null) {
            updateTitle(getArguments().getString("name"));
        }
    }

    protected void updateTitle(String title) {
        if (tv != null) {
            tv.setText(title);
//            if (mGoodsListAdapter != null) {
//                mGoodsListAdapter.notifyDataSetChanged();
//            }
        }
    }

    private void setData() {
        rightData();
        if (itemList == null) {
            itemList = new ArrayList<>();
            for (int i = 1; i < 11; i++) {
                itemList.add(new Category("选项 " + i, "" + i));
            }
        }

        //高度60dp+行距8dp = 68dp
        int heightUnit = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 68, getResources().getDisplayMetrics());
        int height;

        //计算Gridview总高度
        if (itemList.size() % 3 == 0) {
            height = (itemList.size() / 3 + 2) * heightUnit;
        } else {
            height = (itemList.size() / 3 + 1) * heightUnit;
        }

        if (lp_gd == null)
            lp_gd = new AutoLinearLayout.LayoutParams(AutoLinearLayout.LayoutParams.MATCH_PARENT, height);

        if (lp_tv == null)
            lp_tv = new AutoLinearLayout.LayoutParams(AutoLinearLayout.LayoutParams.MATCH_PARENT
                    , (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP
                    , 30, getResources().getDisplayMetrics()));

        TextView tv_title = new TextView(getActivity());
        tv_title.setLayoutParams(lp_tv);
        tv_title.setText("组一");
        llayout_main.addView(tv_title);


//        RecyclerView recyclerView = new RecyclerView(getActivity());
//        rv_category.setLayoutManager(new GridLayoutManager(getActivity(), 3));
//        mGoodsListAdapter = new GoodsListAdapter(R.layout.item_goods_list, itemList);
//        mGoodsListAdapter.addHeaderView(tv_title);
//        rv_category.setAdapter(mGoodsListAdapter);
        GridView gridView = new GridView(getActivity());
        gridView.setNumColumns(3);
        gridView.setVerticalSpacing(9);
        gridView.setLayoutParams(lp_gd);
//
        adapter = new GDAdapter(getActivity(), itemList, R.color.gray);
        gridView.setAdapter(adapter);
        llayout_main.addView(gridView);
    }

    private void rightData() {
        mRightData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mRightData.add("二级分类" + i);
        }
    }


    public static class GDAdapter extends BaseAdapter {
        Context context;
        List<Category> results;
        int imageId;
        ViewHolder holder = null;

        public GDAdapter(Context context, List<Category> results, int imageId) {
            this.context = context;
            this.results = results;
            this.imageId = imageId;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return results.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return results.get(position);
        }


        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Category c = (Category) getItem(position);

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_goods_list, null);
                holder.tv = (TextView) convertView.findViewById(R.id.tv_good_name);
                holder.imv = (ImageView) convertView.findViewById(R.id.iv_good_img);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            convertView.setTag(holder);

            holder.tv.setText(c.getName());
            holder.imv.setImageResource(imageId);

            return convertView;
        }

        class ViewHolder {
            TextView tv;
            ImageView imv;
        }
    }

    /**
     * 选项对象
     */
    public static class Category {
        private String name;
        private String id;

        Category(String name, String id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return this.name;
        }
    }
}
