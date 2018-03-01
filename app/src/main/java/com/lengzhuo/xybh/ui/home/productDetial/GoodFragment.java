package com.lengzhuo.xybh.ui.home.productDetial;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.AddressBean;
import com.lengzhuo.xybh.beans.BannerBean;
import com.lengzhuo.xybh.beans.CreateOrderGoodsBean;
import com.lengzhuo.xybh.beans.GoodDetailsBean;
import com.lengzhuo.xybh.beans.GoodSkuBean;
import com.lengzhuo.xybh.network.CommonCallBack;
import com.lengzhuo.xybh.pop.PopUtils;
import com.lengzhuo.xybh.ui.LazyLoadFragment;
import com.lengzhuo.xybh.ui.home.AddCartP;
import com.lengzhuo.xybh.ui.home.AddressSelectedUi;
import com.lengzhuo.xybh.ui.home.CreateOrderUI;
import com.lengzhuo.xybh.ui.home.GoodsSkuP;
import com.lengzhuo.xybh.utils.NetworkUtils;
import com.lengzhuo.xybh.utils.ToastUtils;
import com.lengzhuo.xybh.utils.UserManager;
import com.lengzhuo.xybh.utils.evntBusBean.AddressEvent;
import com.lengzhuo.xybh.utils.evntBusBean.GoodDetailsEvent;
import com.lengzhuo.xybh.views.arl.AutoRollLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author yjyvi
 * @date 2018/1/31
 * 商品属性
 */

public class GoodFragment extends LazyLoadFragment implements GoodsSkuP.GoodsSkuListener, AddCartP.AddCartListener {


    //样式选择
    @ViewInject(R.id.iv_selected_style)
    private ImageView iv_selected_style;

    //选择地址
    @ViewInject(R.id.ll_selected_address)
    private LinearLayout ll_selected_address;
    //选择地址线
    @ViewInject(R.id.iv_address_line)
    private View iv_address_line;

    @ViewInject(R.id.tv_goods_num)
    private TextView tv_goods_num;

    @ViewInject(R.id.tv_name)
    private TextView tv_name;

    @ViewInject(R.id.tv_tel)
    private TextView tv_tel;

    @ViewInject(R.id.tv_address)
    private TextView tv_address;

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
    private AddressBean mAddressBean;
    private GoodDetailsBean.DataBean mDataBean;
    private GoodsSkuP mGoodsSkuP;
    private boolean isAddCart;
    private int mAddressId;
    private AddCartP mAddCartP;
    private static boolean isFirst = false;
    private String mSkuContent;

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
    public void onResume() {
        super.onResume();
//        if (isFirst) {
//            ll_selected_address.setVisibility(View.VISIBLE);
//            iv_address_line.setVisibility(View.VISIBLE);
//            getAddressList();
//        }

    }

    @Override
    protected void prepareData() {
        mGoodsSkuP = new GoodsSkuP(this);
        mAddCartP = new AddCartP(this);
//        getAddressList();
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
            R.id.ll_selected_address,
            R.id.iv_reduce,
            R.id.iv_add
    }, type = View.OnClickListener.class)
    private void onClick(final View view) {
        switch (view.getId()) {
            case R.id.iv_selected_style:
                stylePop();
                break;
            case R.id.ll_selected_address:
                if (isLoginClick()) return;
                isFirst = false;
                //选择地址
                AddressSelectedUi.start(view.getContext());
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
        PopUtils.showGoodsStyle2(getActivity(), iv_selected_style, mDataBean, new PopUtils.GoodsSelectedStyleListener() {
            @Override
            public void selectedResult(String result) {
                mSkuContent = result;
            }

            @Override
            public void selectedName(Map<Integer, String> result) {
                //TODO  此处规格显示需要优化*******************
                StringBuilder colorName;
                StringBuilder sizeName;
                if (mDataBean.getAttrList().size() > 0) {
                    colorName = new StringBuilder("规格  " + mDataBean.getAttrList().get(0).getAttrName() + ":");
                } else {
                    return;
                }

                if (mDataBean.getAttrList().size() > 1) {
                    sizeName = new StringBuilder(";" + mDataBean.getAttrList().get(1).getAttrName() + ":");
                } else {
                    sizeName = new StringBuilder();
                }

                for (Map.Entry<Integer, String> integerStringEntry : result.entrySet()) {
                    if (integerStringEntry.getKey() == 1) {
                        colorName.append(integerStringEntry.getValue());
                    } else if (integerStringEntry.getKey() == 4) {
                        sizeName.append(integerStringEntry.getValue());
                    } else {
                        colorName.append(integerStringEntry.getValue());
                    }
                }

                colorName.append(sizeName);
                tv_style.setText(colorName.toString());
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
                    tv_good_price.setText("¥" + mDataBean.getPrice());


                    showInitStyle();
                    initBannerData();


                    if (!TextUtils.isEmpty(mDataBean.getSkuStock())) {
                        goodsNumMax = Integer.parseInt(mDataBean.getSkuStock());
                    }
                }
                break;
            case GoodDetailsEvent.SELECTED_STYLE:
                isAddCart = false;
                mGoodsSkuP.setGoodsSku(String.valueOf(mDataBean.getGoodsId()), mSkuContent);
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
     * 显示规格默认第1个
     */
    private void showInitStyle() {
        List<GoodDetailsBean.DataBean.AttrListBeanX> attrList = mDataBean.getAttrList();
        StringBuilder colorName;
        StringBuilder sizeName;
        mSkuContent = "[";
        if (attrList != null && attrList.size() > 0) {
            List<GoodDetailsBean.DataBean.AttrListBeanX.AttrListBean> attrList1 = attrList.get(0).getAttrList();
            colorName = new StringBuilder("规格  " + attrList.get(0).getAttrName() + ":");
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
            mSkuContent = mSkuContent.substring(0, mSkuContent.length() - 1);
        }

        mSkuContent += "]";
        tv_style.setText(colorName.toString());
    }


    /**
     * 地址列表的EvenBus事件
     *
     * @param addressEvent
     */
    @Subscribe
    public void addressEvent(AddressEvent addressEvent) {
//        if (addressEvent.getEventType() == AddressEvent.SELECTED_ADDESS) {
//            AddressBean data = (AddressBean) addressEvent.getData();
//            initShowAddressText(data);
//        }
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
                    tv_current_page.setText(String.valueOf(position+1));
                }
            });


        }
    }


    /**
     * 获取地址列表
     */
    private void getAddressList() {
        isFirst = true;
        if (!UserManager.isLogin()) {
            ll_selected_address.setVisibility(View.GONE);
            iv_address_line.setVisibility(View.GONE);
            return;
        }
        NetworkUtils.getNetworkUtils().getAddressList(new CommonCallBack<List<AddressBean>>() {
            @Override
            protected void onSuccess(List<AddressBean> data) {
                //获取到默认地址
                for (AddressBean datum : data) {
                    if (1 == datum.getIsDefault()) {
                        initShowAddressText(datum);
                    }
                }
            }
        });
    }

    /**
     * 显示地址信息
     *
     * @param datum
     */
    private void initShowAddressText(AddressBean datum) {
        mAddressBean = datum;
        mAddressId = datum.getAddressId();
        tv_name.setText(String.format(getResources().getString(R.string.default_name), datum.getAddressee()));
        tv_tel.setText(String.format(getResources().getString(R.string.default_tel), datum.getTelephone()));
        tv_address.setText(String.format(getResources().getString(R.string.default_address), datum.getProvinceName() + datum.getCityName() + datum.getAreaName() + datum.getAddress()));
    }

    @Override
    public void requestSkuSuccess(GoodSkuBean.DataBean data) {
        if (data == null) {
            return;
        }

        if (isLoginClick()) return;

        if (isAddCart) {
            if (mDataBean != null) {
                mAddCartP.setAddCart(
                        String.valueOf(mDataBean.getShopId()),
                        String.valueOf(mDataBean.getGoodsId()),
                        String.valueOf(data.getSkuId()),
                        String.valueOf(mAddressId),
                        tv_goods_num.getText().toString().trim()
                );
            }
        } else {
            ArrayList<CreateOrderGoodsBean> orderGoodsBeanList = new ArrayList<>();

            CreateOrderGoodsBean createOrderGoodsBean = new CreateOrderGoodsBean();
            createOrderGoodsBean.setGoodsId(String.valueOf(mDataBean.getGoodsId()));
            createOrderGoodsBean.setGoodsAmount(tv_goods_num.getText().toString().trim());
            createOrderGoodsBean.setShopId(String.valueOf(mDataBean.getShopId()));
            createOrderGoodsBean.setSkuId(String.valueOf(data.getSkuId()));
            createOrderGoodsBean.setGoodsImageUrl(mDataBean.getCover());
            createOrderGoodsBean.setGoodsTitle(mDataBean.getGoodsName());
            createOrderGoodsBean.setGoodsPrice(String.valueOf(mDataBean.getPrice()));
            createOrderGoodsBean.setGoodsSkuContent(data.getPropertiesName());
            orderGoodsBeanList.add(createOrderGoodsBean);
            CreateOrderUI.start(getActivity(), mAddressBean, orderGoodsBeanList);
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
