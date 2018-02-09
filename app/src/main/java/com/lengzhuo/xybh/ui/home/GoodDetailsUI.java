package com.lengzhuo.xybh.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.adapter.home.GoodTableAdapter;
import com.lengzhuo.xybh.beans.AddressBean;
import com.lengzhuo.xybh.beans.GoodDetailsBean;
import com.lengzhuo.xybh.ui.BaseUI;
import com.lengzhuo.xybh.ui.home.productDetial.GoodCommentFragment;
import com.lengzhuo.xybh.ui.home.productDetial.GoodDetailFragment;
import com.lengzhuo.xybh.ui.home.productDetial.GoodFragment;
import com.lengzhuo.xybh.ui.mine.ShoppingCartActivity;
import com.lengzhuo.xybh.utils.ToastUtils;
import com.lengzhuo.xybh.utils.UserManager;
import com.lengzhuo.xybh.utils.evntBusBean.GoodDetailsEvent;
import com.lengzhuo.xybh.views.AutoMagicIndicator;
import com.zhy.autolayout.utils.AutoUtils;

import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yjyvi
 * @date 2018/1/31
 * 商品详情
 */
@ContentView(R.layout.activity_good_details)
public class GoodDetailsUI extends BaseUI implements CollectionP.CollectionListener, AddCartP.AddCartListener, GoodDetailP.GoodsDetailsListener {

    @ViewInject(R.id.vp_content)
    private ViewPager vp_content;

    @ViewInject(R.id.ami_type)
    private AutoMagicIndicator ami_type;

    @ViewInject(R.id.ll_collection)
    private LinearLayout ll_collection;

    public static String[] mTitles = {"商品", "详情", "评论"};
    private List<Fragment> mFragmentLists = new ArrayList<>();
    private String mGoodsId;
    private String mShopId;
    public CollectionP mCollectionP;
    private String userId;
    private String isCollection;
    private String operation;
    public AddCartP mAddCartP;
    private GoodDetailP mProductDetailP;
    private GoodDetailsBean.DataBean mGoodDetailsBean;
    public AddressBean mAddressData;
    private int mType = 1;

    @Override
    protected void back() {
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void setControlBasis() {

        EventBus.getDefault().register(this);

        mCollectionP = new CollectionP(this);

        mProductDetailP = new GoodDetailP(getActivity(), this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);

        mGoodsId = getIntent().getStringExtra("goodsId");
        mShopId = getIntent().getStringExtra("shopId");

        mProductDetailP.setProductDetailsData(mGoodsId);

    }

    @Override
    protected void prepareData() {

        mGoodsId = getIntent().getStringExtra("goodsId");
        mShopId = getIntent().getStringExtra("shopId");
        mType = getIntent().getIntExtra("type", 0);

        String webUrl = getResources().getString(R.string.service_host_address)
                .concat(getResources().getString(R.string.detailHtm))
                .concat(".do?c=")
                .concat(UserManager.getUser().getC()).concat("&goodsId=").concat(mGoodsId);

        mProductDetailP.setProductDetailsData(mGoodsId);

        mFragmentLists.add(GoodFragment.newInstance());
        mFragmentLists.add(GoodDetailFragment.newInstance(webUrl));
        mFragmentLists.add(GoodCommentFragment.newInstance(mGoodsId));

        vp_content.setAdapter(new GoodTableAdapter(getSupportFragmentManager(), mFragmentLists));
        vp_content.setOffscreenPageLimit(3);
        vp_content.setCurrentItem(mType);
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

    public static void start(Context context, String goodsId, String shopId, int type) {
        Intent starter = new Intent(context, GoodDetailsUI.class);
        starter.putExtra("goodsId", goodsId);
        starter.putExtra("shopId", shopId);
        starter.putExtra("type", type);
        context.startActivity(starter);
    }

    @Event(value = {
            R.id.iv_back,
            R.id.iv_cart,
            R.id.ll_shop,
            R.id.tv_login,
            R.id.ll_collection,
            R.id.tv_join_cart,
            R.id.tv_pay
    }, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                back();
                break;
            case R.id.iv_cart:
                //购物车
                if (isLoginClick()) return;
                ShoppingCartActivity.toActivity(view.getContext());
                break;
            case R.id.ll_shop:
                ShopDetailUI.start(view.getContext(), mShopId);
                break;
            case R.id.ll_collection:
                if (isLoginClick()) return;

                if (!ll_collection.isSelected()) {
                    ll_collection.setSelected(true);
                    operation = "1";
                } else {
                    ll_collection.setSelected(false);
                    operation = "2";
                }
                mCollectionP.setCollection(operation, mGoodsId, "1");
                break;
            case R.id.tv_join_cart:
                if (isLoginClick()) return;
                EventBus.getDefault().post(new GoodDetailsEvent().setEventType(GoodDetailsEvent.SELECTED_STYLE_ADD_CART));
                break;
            case R.id.tv_pay:
                if (isLoginClick()) return;
                EventBus.getDefault().post(new GoodDetailsEvent().setEventType(GoodDetailsEvent.SELECTED_STYLE));
                break;
            default:
                break;
        }
    }


    @Override
    public void collectionSuccess() {

    }

    @Override
    public void collectionField() {

    }

    @Override
    public void addCartSuccess() {
        ToastUtils.showToast("添加成功");
    }

    @Override
    public void addCartField() {

    }

    @Override
    public void goodsData(GoodDetailsBean.DataBean dataBean) {

        this.mGoodDetailsBean = dataBean;

        //收藏按扭回显
        if (1 == dataBean.getIsCollection()) {
            ll_collection.setSelected(true);
        } else {
            ll_collection.setSelected(false);
        }

        //传递商品数据
        EventBus.getDefault().post(new GoodDetailsEvent().setEventType(GoodDetailsEvent.GOOD_DATA).setData(dataBean));

    }

    @Override
    public void requestGoodsDataField() {

    }

    @Subscribe
    public void goodsEvent(GoodDetailsEvent goodDetailsEvent) {
    }

}
