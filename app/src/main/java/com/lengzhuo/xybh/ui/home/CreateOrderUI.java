package com.lengzhuo.xybh.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.adapter.home.CreateOrderGoodsListAdapter;
import com.lengzhuo.xybh.beans.AddressBean;
import com.lengzhuo.xybh.beans.CreateOrderGoodsBean;
import com.lengzhuo.xybh.network.CommonCallBack;
import com.lengzhuo.xybh.ui.BaseUI;
import com.lengzhuo.xybh.ui.mine.PaymentMethodActivity;
import com.lengzhuo.xybh.ui.mine.ShoppingCartActivity;
import com.lengzhuo.xybh.utils.NetworkUtils;
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

    @ViewInject(R.id.common_title_back)
    private RelativeLayout common_title_back;

    @ViewInject(R.id.ll_selected_address)
    private LinearLayout ll_selected_address;

    @ViewInject(R.id.rv_good_list)
    private RecyclerView rv_good_list;

    @ViewInject(R.id.tv_name)
    private TextView tv_name;

    @ViewInject(R.id.tv_tel)
    private TextView tv_tel;

    @ViewInject(R.id.tv_address)
    private TextView tv_address;

    @ViewInject(R.id.rl_right)
    private RelativeLayout rl_right;

    @ViewInject(R.id.bt_pay)
    private Button bt_pay;
    private AddressBean mAddressBean;
    public CreateOrderP mCreateOrderP;
    public String mGoods;
    private String mOrderNo;
    private int mAddressId;
    private boolean isFirst;
    public List<CreateOrderGoodsBean> mGoodsDataBean;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        EventBus.getDefault().register(this);
        leftVisible(R.mipmap.back);
        setImgTitle(R.mipmap.home_logo);
        rightVisible(R.mipmap.home_cart);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void prepareData() {

        mAddressBean = getIntent().getParcelableExtra("addressBean");
        mGoodsDataBean =getIntent().getParcelableExtra("goodsDataBean");
        mGoods = getIntent().getStringExtra("goods");

        rv_good_list.setLayoutManager(new LinearLayoutManager(this));
        CreateOrderGoodsListAdapter createOrderGoodsListAdapter = new CreateOrderGoodsListAdapter(R.layout.item_create_order_goods, mGoodsDataBean);
        rv_good_list.setAdapter(createOrderGoodsListAdapter);

        mCreateOrderP = new CreateOrderP(this);

        if (mAddressBean != null) {
            initShowAddressText(mAddressBean);
        } else {
            if (!isFirst) {
                getAddressList();
            }
        }

        createOrder();
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
                //获取到默认地址
                for (AddressBean datum : data) {
                    if (1 == datum.getIsDefault()) {
                        mAddressBean = datum;
                        mAddressId = datum.getAddressId();
                        initShowAddressText(datum);
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

    public static void start(Context context, AddressBean addressBean,ArrayList<CreateOrderGoodsBean> orderGoodsBeanList) {
        Intent starter = new Intent(context, CreateOrderUI.class);
        starter.putExtra("addressBean", addressBean);
        starter.putExtra("goods", JSON.toJSONString(orderGoodsBeanList));
        starter.putParcelableArrayListExtra("goodsDataBean",  orderGoodsBeanList);
        context.startActivity(starter);
    }

    @Event(value = {
            R.id.common_title_back,
            R.id.ll_selected_address,
            R.id.rl_right,
            R.id.bt_pay
    }, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.common_title_back:
                back();
                break;
            case R.id.ll_selected_address:
                //选择地址
                AddressSelectedUi.start(view.getContext());
                break;
            case R.id.rl_right:
                //购物车
                ShoppingCartActivity.toActivity(view.getContext());
                break;
            case R.id.bt_pay:
                PaymentMethodActivity.toActivity(view.getContext(), mOrderNo);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void createSuccess(String orderNo) {
        this.mOrderNo = orderNo;
    }

    @Override
    public void createField() {

    }

    @Subscribe
    public void addressEvent(AddressEvent addressEvent) {
        if (addressEvent.getEventType() == AddressEvent.SELECTED_ADDESS) {
            AddressBean data = (AddressBean) addressEvent.getData();
            initShowAddressText(data);
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
}
