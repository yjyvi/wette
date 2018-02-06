package com.risenb.wette.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.risenb.wette.R;
import com.risenb.wette.adapter.home.GoodTableAdapter;
import com.risenb.wette.beans.AddressBean;
import com.risenb.wette.beans.CreateOrderGoodsBean;
import com.risenb.wette.beans.GoodDetailsBean;
import com.risenb.wette.network.CommonCallBack;
import com.risenb.wette.ui.BaseUI;
import com.risenb.wette.ui.home.productDetial.GoodCommentFragment;
import com.risenb.wette.ui.home.productDetial.GoodDetailFragment;
import com.risenb.wette.ui.home.productDetial.GoodFragment;
import com.risenb.wette.ui.mine.LoginActivity;
import com.risenb.wette.ui.mine.ShoppingCartActivity;
import com.risenb.wette.utils.NetworkUtils;
import com.risenb.wette.utils.ToastUtils;
import com.risenb.wette.utils.UserManager;
import com.risenb.wette.utils.evntBusBean.GoodDetailsEvent;
import com.risenb.wette.views.AutoMagicIndicator;
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
public class GoodDetailsUI extends BaseUI implements CollectionP.CollectionListener, AddCartP.AddCartListener, GoodDetailP.GoodsDetailsListener, GoodsSkuP.GoodsSkuListener {

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
    private String mAmount = "1";
    private String mAddressId = "1";
    public AddressBean mAddressData;
    public GoodsSkuP mGoodsSkuP;
    public String mSkuId = "1";

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
        mAddCartP = new AddCartP(this);
        mProductDetailP = new GoodDetailP(getActivity(), this);
        mGoodsSkuP = new GoodsSkuP(this);
    }


    @Override
    protected void prepareData() {

        getAddressList();
        mGoodsId = getIntent().getStringExtra("goodsId");
        mShopId = getIntent().getStringExtra("shopId");

        mProductDetailP.setProductDetailsData(mGoodsId);

        mFragmentLists.add(GoodFragment.newInstance());
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


    private void getAddressList() {
        NetworkUtils.getNetworkUtils().getAddressList(new CommonCallBack<List<AddressBean>>() {


            @Override
            protected void onSuccess(List<AddressBean> data) {
                //获取到默认地址
                for (AddressBean datum : data) {
                    if (1 == datum.getIsDefault()) {
                        mAddressData = datum;
                        mAddressId = String.valueOf(datum.getAddressId());
                        EventBus.getDefault().post(new GoodDetailsEvent().setEventType(GoodDetailsEvent.DEFAULT_ADDRESS).setData(datum));
                    }
                }
            }
        });
    }


    public static void start(Context context, String goodsId, String shopId) {
        Intent starter = new Intent(context, GoodDetailsUI.class);
        starter.putExtra("goodsId", goodsId);
        starter.putExtra("shopId", shopId);
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
                ShoppingCartActivity.toActivity(view.getContext());
                break;
            case R.id.ll_shop:
                ShopDetailUI.start(view.getContext(), mShopId);
                break;
            case R.id.ll_collection:
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

//                getSku();

                if (mGoodDetailsBean != null) {
//                    mSkuId = mGoodDetailsBean.getSkuId();
                    mSkuId = "1";
                    mAddCartP.setAddCart(
                            String.valueOf(mGoodDetailsBean.getShopId()),
                            String.valueOf(mGoodDetailsBean.getGoodsId()),
                            mSkuId, mAddressId, mAmount
                    );
                }
                break;
            case R.id.tv_pay:

                if (mGoodDetailsBean == null) {
                    return;
                }

                if (UserManager.isLogin()) {
                    CreateOrderGoodsBean createOrderGoodsBean = new CreateOrderGoodsBean();
                    createOrderGoodsBean.setGoodsId(String.valueOf(mGoodDetailsBean.getGoodsId()));
                    createOrderGoodsBean.setGoodsAmount(mAmount);
                    createOrderGoodsBean.setShopId(String.valueOf(mGoodDetailsBean.getShopId()));
                    createOrderGoodsBean.setSkuId(mSkuId);

                    String goods = JSON.toJSONString(createOrderGoodsBean);
                    CreateOrderUI.start(view.getContext(), mAddressData, goods);
                } else {
                    ToastUtils.showToast("未登录，请求登录");
                    LoginActivity.toActivity(view.getContext());
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void collectionResult() {
        ToastUtils.showToast("收藏成功");
    }

    @Override
    public void collectionField() {

    }


    @Subscribe
    public void goodsEvent(GoodDetailsEvent goodDetailsEvent) {
        //购买的商品数量
        if (GoodDetailsEvent.GOOD_NUM == goodDetailsEvent.getEventType()) {
            mAmount = (String) goodDetailsEvent.getData();
        }
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

    @Override
    public void requestSkuSuccess() {

    }

    @Override
    public void requestSkuField() {

    }

    public String getSku() {
        String skuId = "1";

        String properties = String.valueOf(mGoodDetailsBean.getAttrList().get(0).getAttrList().get(0).getAttrId());
        mGoodsSkuP.setGoodsSku(mGoodsId, properties);


        return skuId;
    }
}
