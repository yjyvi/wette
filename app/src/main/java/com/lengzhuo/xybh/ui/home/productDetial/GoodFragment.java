package com.lengzhuo.xybh.ui.home.productDetial;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.BannerBean;
import com.lengzhuo.xybh.beans.CreateOrderGoodsBean;
import com.lengzhuo.xybh.beans.GoodDetailsBean;
import com.lengzhuo.xybh.beans.GoodSkuBean;
import com.lengzhuo.xybh.pop.PopUtils;
import com.lengzhuo.xybh.ui.LazyLoadFragment;
import com.lengzhuo.xybh.ui.home.AddCartP;
import com.lengzhuo.xybh.ui.home.CreateOrderUI;
import com.lengzhuo.xybh.ui.home.GoodsSkuP;
import com.lengzhuo.xybh.utils.PlaceholderUtils;
import com.lengzhuo.xybh.utils.ToastUtils;
import com.lengzhuo.xybh.utils.evntBusBean.GoodDetailsEvent;
import com.lengzhuo.xybh.views.arl.AutoRollLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;


/**
 * @author yjyvi
 * @date 2018/1/31
 * 商品属性
 */

public class GoodFragment extends LazyLoadFragment implements GoodsSkuP.GoodsSkuListener, AddCartP.AddCartListener {

    @ViewInject(R.id.iv_selected_style)
    private ImageView iv_selected_style;

    @ViewInject(R.id.tv_goods_num)
    private TextView tv_goods_num;

    @ViewInject(R.id.tv_good_name)
    private TextView tv_good_name;

    @ViewInject(R.id.tv_good_price)
    private TextView tv_good_price;

    @ViewInject(R.id.tv_current_page)
    private TextView tv_current_page;

    @ViewInject(R.id.tv_total_page)
    private TextView tv_total_page;

    @ViewInject(R.id.vp_item_banner)
    private AutoRollLayout vp_item_banner;

    @ViewInject(R.id.tv_style)
    private TextView tv_style;


    /**
     * 商品添加最大数量
     */
    private int goodsNumMax = 100;
    private ArrayList<BannerBean.ResultdataBean> mResultBannerBean;
    private GoodDetailsBean.DataBean mDataBean;
    private GoodsSkuP mGoodsSkuP;
    private boolean isAddCart;
    private AddCartP mAddCartP;
    private String mSkuContent;
    private int mSkuId;
    private String mPropertiesName;
    private double mGoodsPrice;
    /**
     * 是否只有一个属性选择
     */
    private boolean mIsOne;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    protected void setControlBasis() {
        EventBus.getDefault().register(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    protected void prepareData() {
        mGoodsSkuP = new GoodsSkuP(this);
        mAddCartP = new AddCartP(this);
    }


    /**
     * 初始化实例
     *
     * @return
     */
    public static GoodFragment newInstance() {
        Bundle bundle = new Bundle();
        GoodFragment productFragment = new GoodFragment();
        productFragment.setArguments(bundle);
        return productFragment;
    }

    @Event(value = {
            R.id.iv_selected_style,
            R.id.iv_reduce,
            R.id.iv_add
    }, type = View.OnClickListener.class)
    private void onClick(final View view) {
        switch (view.getId()) {
            case R.id.iv_selected_style:
                stylePop();
                break;
            case R.id.iv_reduce:
                numAddOrReduce(false);
                break;
            case R.id.iv_add:
                numAddOrReduce(true);
                break;
            default:
                break;
        }
    }

    /**
     * 选择商品规格样式
     */
    private void stylePop() {
        if (mDataBean == null) {
            return;
        }
        PopUtils.showGoodsStyle2(mIsOne, getActivity(), iv_selected_style, mDataBean, new PopUtils.GoodsSelectedStyleListener() {
            @Override
            public void selectedResult(String result) {
                mSkuContent = result;
                mGoodsSkuP.setGoodsSku(String.valueOf(mDataBean.getGoodsId()), result);
            }
        });
    }


    /**
     * 加减
     *
     * @param isAdd
     */
    private void numAddOrReduce(boolean isAdd) {
        String num = tv_goods_num.getText().toString().trim();
        int i = Integer.parseInt(num);
        if (isAdd && i < goodsNumMax) {
            i++;
        } else if (i > 1) {
            i--;
        }
        tv_goods_num.setText(String.valueOf(i));

        EventBus.getDefault().post(new GoodDetailsEvent().setEventType(GoodDetailsEvent.GOOD_NUM).setData(tv_goods_num.getText().toString().trim()));

    }


    @Subscribe
    public void goodsEvent(GoodDetailsEvent goodDetailsEvent) {
        switch (goodDetailsEvent.getEventType()) {
            case GoodDetailsEvent.GOOD_DATA:
                mDataBean = (GoodDetailsBean.DataBean) goodDetailsEvent.getData();
                if (mDataBean != null) {
                    tv_good_name.setText(mDataBean.getGoodsName());
                    tv_good_price.setText(String.format(getResources().getString(R.string.money_icon), mDataBean.getPrice()));


                    showInitStyle();
                    initBannerData();
                    mGoodsPrice = mDataBean.getPrice();

                    if (!TextUtils.isEmpty(mDataBean.getSkuStock())) {
                        goodsNumMax = Integer.parseInt(mDataBean.getSkuStock());
                    }
                }
                break;
            case GoodDetailsEvent.SELECTED_STYLE:
                isAddCart = false;
                createOrder();
                break;
            case GoodDetailsEvent.SELECTED_STYLE_ADD_CART:
                isAddCart = true;
                mGoodsSkuP.setGoodsSku(String.valueOf(mDataBean.getGoodsId()), mSkuContent);
                break;
            default:
                break;
        }

    }


    /**
     * 创建订单
     */
    private void createOrder() {
        ArrayList<CreateOrderGoodsBean> orderGoodsBeanList = new ArrayList<>();

        CreateOrderGoodsBean createOrderGoodsBean = new CreateOrderGoodsBean();

        createOrderGoodsBean.setGoodsId(String.valueOf(mDataBean.getGoodsId()));
        createOrderGoodsBean.setGoodsAmount(tv_goods_num.getText().toString().trim());
        createOrderGoodsBean.setShopId(String.valueOf(mDataBean.getShopId()));
        createOrderGoodsBean.setSkuId(String.valueOf(mSkuId));
        createOrderGoodsBean.setGoodsImageUrl(mDataBean.getCover());
        createOrderGoodsBean.setGoodsTitle(mDataBean.getGoodsName());
        createOrderGoodsBean.setGoodsPrice(mGoodsPrice);
        createOrderGoodsBean.setGoodsSkuContent(mPropertiesName);
        orderGoodsBeanList.add(createOrderGoodsBean);
        CreateOrderUI.start(getActivity(), null, orderGoodsBeanList);
    }


    /**
     * 显示规格默认第1个
     */
    private void showInitStyle() {
        List<GoodDetailsBean.DataBean.AttrListBeanX> attrList = mDataBean.getAttrList();
        StringBuilder colorName;
        StringBuilder sizeName;
        mSkuContent = "[";
        if (attrList != null && attrList.size() > 0) {
            List<GoodDetailsBean.DataBean.AttrListBeanX.AttrListBean> attrList1 = attrList.get(0).getAttrList();
            colorName = new StringBuilder(PlaceholderUtils.skuPlaceholder(attrList.get(0).getAttrName()) + ":");
            if (attrList1 != null && attrList1.size() > 0) {
                colorName.append(attrList1.get(0).getAttrName());
                mSkuContent += attrList.get(0).getAttrId() + ":" + attrList1.get(0).getAttrId() + ";";
            }
        } else {
            return;
        }


        if (attrList.size() > 1) {
            List<GoodDetailsBean.DataBean.AttrListBeanX.AttrListBean> attrList2 = attrList.get(1).getAttrList();
            sizeName = new StringBuilder(";" + attrList.get(1).getAttrName() + ":");

            if (attrList2 != null && attrList2.size() > 0) {
                sizeName.append(attrList2.get(0).getAttrName());
                mSkuContent += attrList.get(1).getAttrId() + ":" + attrList2.get(0).getAttrId();
            }
            colorName.append(sizeName);
        } else {
            mIsOne = true;
            mSkuContent = mSkuContent.substring(0, mSkuContent.length() - 1);
        }

        mSkuContent += "]";

        mGoodsSkuP.setGoodsSku(String.valueOf(mDataBean.getGoodsId()), mSkuContent);

        tv_style.setText(colorName.toString());
    }


    /**
     * 转换轮播图数据
     */
    private void initBannerData() {
        String carousel = mDataBean.getCarousel();
        if (!TextUtils.isEmpty(carousel)) {
            mResultBannerBean = new ArrayList<>();
            List<String> banners2 = new ArrayList<>();

            String[] banners = carousel.split(",");

            for (int i = 0; i < banners.length; i++) {
                BannerBean.ResultdataBean banner = new BannerBean.ResultdataBean();
                banner.setBanner_Url(banners[i]);
                mResultBannerBean.add(banner);
                banners2.add(banners[i]);
            }
            vp_item_banner.setItems(banners2);
            tv_total_page.setText(String.valueOf("/" + banners2.size()));
            vp_item_banner.setCurrentItemListener(new AutoRollLayout.CurrentItemListener() {
                @Override
                public void currentItemPosition(int position) {
                    tv_current_page.setText(String.valueOf(position + 1));
                }
            });

        }
    }

    @Override
    public void requestSkuSuccess(GoodSkuBean.DataBean data) {
        if (data == null) {
            return;
        }

        if (isAddCart) {

            if (isLoginClick()) return;

            if (mDataBean != null) {
                mAddCartP.setAddCart(
                        String.valueOf(mDataBean.getShopId()),
                        String.valueOf(mDataBean.getGoodsId()),
                        String.valueOf(data.getSkuId()),
                        tv_goods_num.getText().toString().trim()
                );
            }
        } else {
            mSkuId = data.getSkuId();
            mPropertiesName = data.getPropertiesName();
            mGoodsPrice = data.getCostPrice();
            tv_good_price.setText(PlaceholderUtils.pricePlaceholder(data.getCostPrice()));
            goodsNumMax = data.getSkuStock();
            tv_style.setText(PlaceholderUtils.skuPlaceholder(data.getPropertiesName()));
        }

    }

    @Override
    public void requestSkuField() {
        ToastUtils.showToast("查询库存失败");
    }

    @Override
    public void addCartSuccess() {
        ToastUtils.showToast("添加成功");
    }

    @Override
    public void addCartField() {
        ToastUtils.showToast("添加购物车失败");
    }
}
