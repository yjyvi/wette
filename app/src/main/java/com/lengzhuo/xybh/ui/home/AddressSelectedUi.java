package com.lengzhuo.xybh.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.AddressBean;
import com.lengzhuo.xybh.network.CommonCallBack;
import com.lengzhuo.xybh.ui.BaseUI;
import com.lengzhuo.xybh.ui.BaseViewHolder;
import com.lengzhuo.xybh.utils.NetworkUtils;
import com.lengzhuo.xybh.utils.evntBusBean.AddressEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/2/9.
 */
@ContentView(R.layout.activity_address_list)
public class AddressSelectedUi extends BaseUI {

    @ViewInject(R.id.rv_address_list)
    private RecyclerView rv_address_list;

    @ViewInject(R.id.tv_add_address)
    private TextView tv_add_address;
    private List<AddressBean> mAddressBean;
    public SelectedAddressAdapter mSelectedAddressAdapter;

    @Override
    protected void back() {
        finish();

    }

    @Override
    protected void setControlBasis() {
        setTitle("选择地址");
        EventBus.getDefault().register(this);
        tv_add_address.setVisibility(View.GONE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void prepareData() {

        NetworkUtils.getNetworkUtils().getAddressList(new CommonCallBack<List<AddressBean>>() {
            @Override
            protected void onSuccess(List<AddressBean> data) {
                mSelectedAddressAdapter.setNewData(data);
            }
        });
        rv_address_list.setLayoutManager(new LinearLayoutManager(this));
        mSelectedAddressAdapter = new SelectedAddressAdapter(R.layout.item_address_list, mAddressBean);
        rv_address_list.setAdapter(mSelectedAddressAdapter);

        mSelectedAddressAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AddressBean data = mSelectedAddressAdapter.getData().get(position);
                EventBus.getDefault().post(new AddressEvent().setEventType(AddressEvent.SELECTED_ADDESS).setData(data));
                finish();
            }
        });
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, AddressSelectedUi.class);
        context.startActivity(starter);
    }


    /**
     * 地址选择Adapter
     */
    private class SelectedAddressAdapter extends BaseQuickAdapter<AddressBean, BaseViewHolder> {
        public SelectedAddressAdapter(int layoutResId, @Nullable List<AddressBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder holder, final AddressBean item) {
            holder.<TextView>getView(R.id.tv_addressee).setText("收货人：" + item.getAddressee());
            holder.<TextView>getView(R.id.tv_phone_number).setText("联系方式：" + item.getTelephone());
            holder.<TextView>getView(R.id.tv_address).setText("收货地址：" + item.getAddress());
            holder.<TextView>getView(R.id.tv_postal_code).setText("邮政编码：" + item.getPostalcode());
            holder.<ImageView>getView(R.id.iv_is_default).setImageResource(item.getIsDefault() == 0 ? R.drawable.address_list_unselected : R.drawable.address_list_selected);
        }
    }

    @Subscribe
    public void addressEvent(AddressEvent addressEvent) {

    }
}
