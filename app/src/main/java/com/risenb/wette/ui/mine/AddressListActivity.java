package com.risenb.wette.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.risenb.wette.R;
import com.risenb.wette.beans.AddressBean;
import com.risenb.wette.network.CommonCallBack;
import com.risenb.wette.ui.BaseUI;
import com.risenb.wette.ui.mine.multitype.AddressItemViewBinder;
import com.risenb.wette.utils.NetworkUtils;
import com.risenb.wette.utils.PaddingItemDecoration;
import com.risenb.wette.utils.ToastUtils;
import com.risenb.wette.utils.evntBusBean.BaseEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/02
 *     desc   : 收货地址
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_address_list)
public class AddressListActivity extends BaseUI {


    @ViewInject(R.id.rv_address_list)
    private RecyclerView rv_address_list;

    private Items mItems;

    private MultiTypeAdapter mAdapter;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        EventBus.getDefault().register(this);
        setTitle("收获地址");
        rightVisible(R.mipmap.home_cart);
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.register(AddressBean.class,new AddressItemViewBinder());
        rv_address_list.addItemDecoration(new PaddingItemDecoration());
        rv_address_list.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setItems(mItems);
        rv_address_list.setAdapter(mAdapter);
    }

    @Event(value = {R.id.rl_right,R.id.tv_add_address},type = View.OnClickListener.class)
    private void onClick(View view){
        if(view.getId() == R.id.tv_add_address){
            EditAddressActivity.toAddAddressActivity(this);
        }else{
            ShoppingCartActivity.toActivity(view.getContext());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onMessageEvent(final BaseEvent<AddressBean> msg){
        String isDel = msg.getEventType() == 1 ? "0" : "1";
        NetworkUtils.getNetworkUtils().updateAddress(String.valueOf(msg.getData().getAddressId()), isDel, new CommonCallBack<String>() {
            @Override
            protected void onSuccess(String data) {
                ToastUtils.showToast(msg.getEventType() == 1 ? "设置成功" : "删除成功");
                getAddressList();
            }
        });
    }

    @Override
    protected void prepareData() {
        getAddressList();
    }

    private void getAddressList(){
        NetworkUtils.getNetworkUtils().getAddressList(new CommonCallBack<List<AddressBean>>() {
            @Override
            protected void onSuccess(List<AddressBean> data) {
                mItems.clear();
                mItems.addAll(data);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == 1){
            getAddressList();
        }
    }

    public static void toActivity(Context context) {
        Intent intent = new Intent(context, AddressListActivity.class);
        context.startActivity(intent);
    }
    
}
