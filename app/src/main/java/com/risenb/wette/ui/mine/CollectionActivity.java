package com.risenb.wette.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.risenb.wette.R;
import com.risenb.wette.ui.BaseUI;

import org.xutils.view.annotation.ContentView;
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
public class CollectionActivity extends BaseUI {

    @ViewInject(R.id.vp_collection)
    private ViewPager vp_collection;
    private static final String KEY_TYPE = "TYPE";
    public static final int TYPE_GOODS = 0;
    public static final int TYPE_SHOPS = 1;
    public static final int TYPE_ALL = 2;
    private int type;
    private Fragment[] mFragments = {new CollectionCommodityFragment(), new CollectionShopFragment()};

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("我的收藏");
        getIntent().getIntExtra(KEY_TYPE,TYPE_ALL);
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
        switch (type){
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
    
    public static void toActivity(Context context,int type) {
        Intent intent = new Intent(context, CollectionActivity.class);
        intent.putExtra(KEY_TYPE,type);
        context.startActivity(intent);
    }
    
}
