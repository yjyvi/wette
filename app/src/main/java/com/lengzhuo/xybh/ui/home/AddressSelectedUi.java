package com.lengzhuo.xybh.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.AddressBean;
import com.lengzhuo.xybh.network.CommonCallBack;
import com.lengzhuo.xybh.ui.BaseUI;
import com.lengzhuo.xybh.ui.BaseViewHolder;
import com.lengzhuo.xybh.ui.mine.EditAddressActivity;
import com.lengzhuo.xybh.utils.NetworkUtils;
import com.lengzhuo.xybh.utils.ToastUtils;
import com.lengzhuo.xybh.utils.evntBusBean.AddressEvent;
import com.lengzhuo.xybh.utils.evntBusBean.BaseEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/2/9.
 */
@ContentView(R.layout.activity_address_list)
public class AddressSelectedUI extends BaseUI {

    @ViewInject(R.id.rv_address_list)
    private RecyclerView rv_address_list;

    @ViewInject(R.id.tv_add_address)
    private TextView tv_add_address;

    @ViewInject(R.id.ll_empty_view)
    private LinearLayout ll_empty_view;

    public SelectedAddressAdapter mSelectedAddressAdapter;

    @Override
    protected void back() {
        EventBus.getDefault().post(new AddressEvent().setEventType(AddressEvent.SELECTED_ADDESS));
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("我的地址");
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void prepareData() {

        getAddressList();
        rv_address_list.setLayoutManager(new LinearLayoutManager(this));
        mSelectedAddressAdapter = new SelectedAddressAdapter(R.layout.item_address_list, null);
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


    /**
     * 获取地址列表
     */
    private void getAddressList() {
        NetworkUtils.getNetworkUtils().getAddressList(new CommonCallBack<List<AddressBean>>() {
            @Override
            protected void onSuccess(List<AddressBean> data) {
                mSelectedAddressAdapter.setNewData(data);
                showEmptyView(data, ll_empty_view);
            }
        });
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, AddressSelectedUI.class);
        context.startActivity(starter);
    }

    @Event(value = {R.id.tv_add_address}, type = View.OnClickListener.class)
    private void onClick(View view) {
        if (view.getId() == R.id.tv_add_address) {
            EditAddressActivity.toAddAddressActivity(this);
        }
    }


    /**
     * 地址选择Adapter
     */
    private class SelectedAddressAdapter extends BaseQuickAdapter<AddressBean, BaseViewHolder> {
        private ImageView lastView;

        public SelectedAddressAdapter(int layoutResId, @Nullable List<AddressBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder holder, final AddressBean item) {
            holder.<TextView>getView(R.id.tv_addressee).setText(String.format(getResources().getString(R.string.default_name), item.getAddressee()));
            holder.<TextView>getView(R.id.tv_phone_number).setText(String.format(getResources().getString(R.string.default_tel), item.getTelephone()));
            holder.<TextView>getView(R.id.tv_address).setText(String.format(getResources().getString(R.string.default_address), item.getAddress()));
            holder.<TextView>getView(R.id.tv_postal_code).setText(String.format("邮政编码：%1$s", item.getPostalcode()));
            final ImageView ivIsDefault = holder.getView(R.id.iv_is_default);


            if (item.getIsDefault() == 0) {
                ivIsDefault.setImageResource(R.drawable.address_list_unselected);
            } else {
                lastView = ivIsDefault;
                ivIsDefault.setImageResource(R.drawable.address_list_selected);
            }

            ivIsDefault.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!view.isSelected()) {

                        NetworkUtils.getNetworkUtils().updateAddress(String.valueOf(item.getAddressId()), "0", new CommonCallBack<String>() {
                            @Override
                            protected void onSuccess(String data) {
                                ToastUtils.showToast("设置成功");
                                EventBus.getDefault().post(new BaseEvent<AddressBean>().setEventType(2).setData(item));
                            }
                        });

                        //后面需要增加修改默认地址的
                        lastView.setImageResource(R.drawable.address_list_unselected);
                        ivIsDefault.setImageResource(R.drawable.address_list_selected);
                        lastView = (ImageView) view;
                    }
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == 1) {
            getAddressList();
        }
    }

    @Subscribe
    public void addressEvent(AddressEvent addressEvent) {
        if (addressEvent.getEventType() == 2) {
            getAddressList();
        }
    }
}
