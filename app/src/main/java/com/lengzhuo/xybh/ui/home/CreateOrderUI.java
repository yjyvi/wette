package com.lengzhuo.xybh.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.adapter.home.CreateOrderGoodsListAdapter;
import com.lengzhuo.xybh.beans.AddressBean;
import com.lengzhuo.xybh.beans.CreateOrderGoodsBean;
import com.lengzhuo.xybh.network.CommonCallBack;
import com.lengzhuo.xybh.ui.BaseUI;
import com.lengzhuo.xybh.ui.mine.PaymentMethodActivity;
import com.lengzhuo.xybh.utils.NetworkUtils;
import com.lengzhuo.xybh.utils.PlaceholderUtils;
import com.lengzhuo.xybh.utils.ToastUtils;
import com.lengzhuo.xybh.utils.UserManager;
import com.lengzhuo.xybh.utils.evntBusBean.AddressEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yjyvi
 * @date 2018/2/1
 * 预览订单界面
 */
@ContentView(R.layout.activity_pay_order)
public class CreateOrderUI extends BaseUI implements CreateOrderP.CreateOrderListener {


    @ViewInject(R.id.rv_good_list)
    private RecyclerView rv_good_list;

    @ViewInject(R.id.tv_name)
    private TextView tv_name;

    @ViewInject(R.id.tv_tel)
    private TextView tv_tel;

    @ViewInject(R.id.tv_address)
    private TextView tv_address;

    @ViewInject(R.id.tv_money)
    private TextView tv_money;

    @ViewInject(R.id.tv_add_address)
    private TextView tv_add_address;

    @ViewInject(R.id.ll_selected_address)
    private LinearLayout ll_selected_address;

    private AddressBean mAddressBean;
    public CreateOrderP mCreateOrderP;
    public String mGoods;
    private String mOrderNo;
    private int mAddressId;
    private boolean isFirst;
    public List<CreateOrderGoodsBean> mGoodsDataBean;

    public static String sAddressBean = "addressBean";
    public static String mGoodsDataBeanStr = "goodsDataBean";

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        EventBus.getDefault().register(this);
        leftVisible(R.mipmap.back);
        setTitle("订单确认");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void prepareData() {

        mAddressBean = getIntent().getParcelableExtra(sAddressBean);
        mGoodsDataBean = getIntent().getParcelableArrayListExtra(mGoodsDataBeanStr);

        rv_good_list.setLayoutManager(new LinearLayoutManager(this));
        CreateOrderGoodsListAdapter createOrderGoodsListAdapter = new CreateOrderGoodsListAdapter(R.layout.item_create_order_goods, mGoodsDataBean);
        rv_good_list.setAdapter(createOrderGoodsListAdapter);

        showTotalMoney();

        mCreateOrderP = new CreateOrderP(this);

        if (mAddressBean != null) {
            initShowAddressText(mAddressBean);
        } else {
            if (!isFirst) {
                getAddressList();
            }
        }


    }


    /**
     * 获取地址
     */
    private void getAddressList() {
        isFirst = true;
        if (!UserManager.isLogin()) {
            return;
        }
        NetworkUtils.getNetworkUtils().getAddressList(new CommonCallBack<List<AddressBean>>() {
            @Override
            protected void onSuccess(List<AddressBean> data) {

                if (data.size() == 0) {
                    tv_add_address.setVisibility(View.VISIBLE);
                    ll_selected_address.setVisibility(View.GONE);
                } else {
                    ll_selected_address.setVisibility(View.VISIBLE);
                    tv_add_address.setVisibility(View.GONE);
                    //获取到默认地址
                    for (AddressBean datum : data) {
                        if (1 == datum.getIsDefault()) {
                            mAddressBean = datum;
                            mAddressId = datum.getAddressId();
                            initShowAddressText(datum);
                        }
                    }
                }
            }
        });
    }

    /**
     * 生成订单
     */
    private void createOrder() {
        mCreateOrderP.setCreateOrder(mGoods, String.valueOf(mAddressId));
    }

    /**
     * 跳转界面
     *
     * @param context
     * @param addressBean
     * @param goods
     */
    public static void start(Context context, AddressBean addressBean, String goods) {
        Intent starter = new Intent(context, CreateOrderUI.class);
        starter.putExtra("addressBean", addressBean);
        starter.putExtra("goods", goods);
        context.startActivity(starter);
    }

    public static void start(Context context, AddressBean addressBean, ArrayList<CreateOrderGoodsBean> orderGoodsBeanList) {
        Intent starter = new Intent(context, CreateOrderUI.class);

        starter.putExtra(sAddressBean, addressBean);
        starter.putParcelableArrayListExtra(mGoodsDataBeanStr, orderGoodsBeanList);
        context.startActivity(starter);
    }

    @Event(value = {
            R.id.common_title_back,
            R.id.ll_selected_address,
            R.id.tv_add_address,
            R.id.bt_pay
    }, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.common_title_back:
                back();
                break;
            case R.id.ll_selected_address:
            case R.id.tv_add_address:
                //选择地址
                AddressSelectedUi.start(view.getContext());
                break;
            case R.id.bt_pay:
                if (mAddressId == 0) {
                    ToastUtils.showToast("请选择地址");
                    return;
                }
                mGoods = JSON.toJSONString(mGoodsDataBean);
                createOrder();
                break;
            default:
                break;
        }
    }


    @Override
    public void createSuccess(String orderNo) {
        this.mOrderNo = orderNo;
        PaymentMethodActivity.toActivity(this, mOrderNo);
        finish();
    }

    @Override
    public void createField(String msg) {
        ToastUtils.showToast(msg);
    }

    @Subscribe
    public void addressEvent(AddressEvent addressEvent) {
        if (addressEvent.getEventType() == AddressEvent.SELECTED_ADDESS) {

            AddressBean data = (AddressBean) addressEvent.getData();
            if (data != null) {
                ll_selected_address.setVisibility(View.VISIBLE);
                tv_add_address.setVisibility(View.GONE);
                initShowAddressText(data);
            } else {
                getAddressList();
            }
        }
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


    /**
     * 显示总金额
     */
    private void showTotalMoney() {
        double maxMoney = 0.0;
        for (CreateOrderGoodsBean createOrderGoodsBean : mGoodsDataBean) {
            maxMoney += (createOrderGoodsBean.getGoodsPrice() * Integer.parseInt(createOrderGoodsBean.getGoodsAmount()));
        }
        tv_money.setText(PlaceholderUtils.pricePlaceholder(maxMoney));
    }
}
