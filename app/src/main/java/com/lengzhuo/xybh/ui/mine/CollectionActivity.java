package com.lengzhuo.xybh.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.ui.BaseUI;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/02
 *     desc   : 我的收藏
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_collection)
public class CollectionActivity extends BaseUI implements ViewPager.OnPageChangeListener {

    @ViewInject(R.id.vp_collection)
    private ViewPager vp_collection;

    @ViewInject(R.id.tv_commodity_selected)
    private TextView tv_commodity_selected;

    @ViewInject(R.id.tv_shop_selected)
    private TextView tv_shop_selected;

    private static final String KEY_TYPE = "TYPE";
    public static final int TYPE_GOODS = 0;
    public static final int TYPE_SHOPS = 1;
    public static final int TYPE_ALL = 2;
    private int mType;
    private Fragment[] mFragments = {new CollectionCommodityFragment(), new CollectionShopFragment()};

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        mType = getIntent().getIntExtra(KEY_TYPE, 0);
        setTitle("我的收藏");
        getIntent().getIntExtra(KEY_TYPE, TYPE_ALL);
        vp_collection.addOnPageChangeListener(this);
        vp_collection.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
        switch (mType) {
            case TYPE_GOODS:
                vp_collection.setCurrentItem(0);
                break;
            case TYPE_SHOPS:
                vp_collection.setCurrentItem(1);
                break;
            case TYPE_ALL:
                vp_collection.setCurrentItem(0);
                break;
        }
    }

    @Override
    protected void prepareData() {

    }

    @Event(value = {R.id.fl_commodity, R.id.fl_shop}, type = View.OnClickListener.class)
    private void onClick(View view) {
        changeTab(view.getId() == R.id.fl_commodity ? 0 : 1);
    }

    public static void toActivity(Context context, int type) {
        Intent intent = new Intent(context, CollectionActivity.class);
        intent.putExtra(KEY_TYPE, type);
        context.startActivity(intent);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        changeTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void changeTab(int position) {
        if (position == 0) {
            tv_commodity_selected.setTextColor(Color.parseColor("#ee4617"));
            tv_shop_selected.setTextColor(Color.parseColor("#666666"));
            findViewById(R.id.v_shop_selected).setVisibility(View.GONE);
            findViewById(R.id.v_commodity_selected).setVisibility(View.VISIBLE);
            vp_collection.setCurrentItem(position);
        } else {
            tv_commodity_selected.setTextColor(Color.parseColor("#666666"));
            tv_shop_selected.setTextColor(Color.parseColor("#ee4617"));
            findViewById(R.id.v_shop_selected).setVisibility(View.VISIBLE);
            findViewById(R.id.v_commodity_selected).setVisibility(View.GONE);
        }
    }
}
