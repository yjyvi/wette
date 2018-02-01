package com.risenb.wette.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.risenb.wette.R;
import com.risenb.wette.adapter.home.ProductTableAdapter;
import com.risenb.wette.ui.BaseUI;
import com.risenb.wette.ui.home.productDetial.ProductCommentFragment;
import com.risenb.wette.ui.home.productDetial.ProductDetialFragment;
import com.risenb.wette.ui.home.productDetial.ProductFragment;
import com.risenb.wette.views.AutoMagicIndicator;
import com.zhy.autolayout.utils.AutoUtils;

import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yjyvi
 * @date 2018/1/31
 */
@ContentView(R.layout.activity_product_details)
public class PreductDetailsUI extends BaseUI implements View.OnClickListener {

    @ViewInject(R.id.vp_content)
    private ViewPager vp_content;

    @ViewInject(R.id.ami_type)
    private AutoMagicIndicator ami_type;

    @ViewInject(R.id.iv_back)
    private ImageView iv_back;

    @ViewInject(R.id.iv_cart)
    private ImageView iv_cart;


    public static String[] mTitles = {"商品", "详情", "评论"};
    private List<Fragment> mFragmentLists = new ArrayList<>();

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        iv_back.setOnClickListener(this);
        iv_cart.setOnClickListener(this);
    }

    @Override
    protected void prepareData() {

        mFragmentLists.add(ProductFragment.newInstance());
        mFragmentLists.add(ProductDetialFragment.newInstance());
        mFragmentLists.add(ProductCommentFragment.newInstance());

        vp_content.setAdapter(new ProductTableAdapter(getSupportFragmentManager(), mFragmentLists));
        vp_content.setOffscreenPageLimit(3);
        vp_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.BLACK);
                colorTransitionPagerTitleView.setSelectedColor(context.getResources().getColor(R.color.normal_color));
                colorTransitionPagerTitleView.setTextSize(15);

                colorTransitionPagerTitleView.setText(mTitles[index]);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vp_content.setCurrentItem(index);
                    }
                });

                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(context.getResources().getColor(R.color.normal_color));
                indicator.setLineHeight(3);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setYOffset(AutoUtils.getPercentHeightSize(1));
                return indicator;
            }

        });

        ami_type.setNavigator(commonNavigator);
        ViewPagerHelper.bind(ami_type, vp_content);

    }


    public static void start(Context context) {
        Intent starter = new Intent(context, PreductDetailsUI.class);
        context.startActivity(starter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                back();
                break;
            case R.id.iv_cart:

                break;
            default:
                break;
        }
    }
}
