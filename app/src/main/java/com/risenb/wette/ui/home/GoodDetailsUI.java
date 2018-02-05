package com.risenb.wette.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.risenb.wette.R;
import com.risenb.wette.adapter.home.GoodTableAdapter;
import com.risenb.wette.ui.BaseUI;
import com.risenb.wette.ui.home.productDetial.GoodCommentFragment;
import com.risenb.wette.ui.home.productDetial.GoodDetailFragment;
import com.risenb.wette.ui.home.productDetial.GoodFragment;
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
@ContentView(R.layout.activity_good_details)
public class GoodDetailsUI extends BaseUI implements View.OnClickListener, CollectionP.CollectionListener {

    @ViewInject(R.id.vp_content)
    private ViewPager vp_content;

    @ViewInject(R.id.ami_type)
    private AutoMagicIndicator ami_type;

    @ViewInject(R.id.iv_back)
    private ImageView iv_back;

    @ViewInject(R.id.iv_cart)
    private ImageView iv_cart;

    @ViewInject(R.id.ll_shop)
    private LinearLayout ll_shop;

    @ViewInject(R.id.ll_collection)
    private LinearLayout ll_collection;

    @ViewInject(R.id.tv_join_cart)
    private TextView tv_join_cart;

    @ViewInject(R.id.tv_pay)
    private TextView tv_pay;


    public static String[] mTitles = {"商品", "详情", "评论"};
    private List<Fragment> mFragmentLists = new ArrayList<>();
    private String mGoodsId;
    private String mShopId;
    public CollectionP mCollectionP;
    private String userId;
    private String isCollection;
    private String operation;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        iv_back.setOnClickListener(this);
        iv_cart.setOnClickListener(this);
        ll_shop.setOnClickListener(this);
        ll_collection.setOnClickListener(this);

        mCollectionP = new CollectionP(this, this);
    }

    @Override
    protected void prepareData() {
        mGoodsId = getIntent().getStringExtra("goodsId");
        mShopId = getIntent().getStringExtra("shopId");


        mFragmentLists.add(GoodFragment.newInstance(mGoodsId));
        mFragmentLists.add(GoodDetailFragment.newInstance());
        mFragmentLists.add(GoodCommentFragment.newInstance(mGoodsId));

        vp_content.setAdapter(new GoodTableAdapter(getSupportFragmentManager(), mFragmentLists));
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
                colorTransitionPagerTitleView.setTextSize(18);

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
                indicator.setLineHeight(4);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setYOffset(AutoUtils.getPercentHeightSize(2));
                return indicator;
            }

        });

        ami_type.setNavigator(commonNavigator);
        ViewPagerHelper.bind(ami_type, vp_content);

    }


    public static void start(Context context, String goodsId, String shopId) {
        Intent starter = new Intent(context, GoodDetailsUI.class);
        starter.putExtra("goodsId", goodsId);
        starter.putExtra("shopId", shopId);
        context.startActivity(starter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                back();
                break;
            case R.id.iv_cart:
                //购物车
                break;
            case R.id.ll_shop:
                ShopDetailUI.start(view.getContext(), mShopId);
                break;
            case R.id.ll_collection:
                if (!ll_collection.isSelected()) {
                    ll_collection.setSelected(true);
                    operation="1";
                }else {
                    ll_collection.setSelected(false);
                    operation="2";
                }
                userId = "42ca9e5498c0dea784faaddad7ebc8d2";
                isCollection = "-1";
                mCollectionP.setCollection(userId, operation, isCollection, mGoodsId, "1");
                break;
            default:
                break;
        }
    }


    @Override
    public void collectionResult() {

    }
}
