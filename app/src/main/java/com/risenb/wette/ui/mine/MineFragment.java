package com.risenb.wette.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.risenb.wette.R;
import com.risenb.wette.ui.LazyLoadFragment;

import org.xutils.view.annotation.Event;

/**
 * Created by yjyvi on 2018/1/30.
 */

public class MineFragment extends LazyLoadFragment {
    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    protected void setControlBasis() {

    }

    @Override
    protected void prepareData() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Event(value = {
            R.id.bt_canceled_order,
            R.id.bt_sending_good,
            R.id.bt_waiting_evaluation,
            R.id.bt_waiting_pay,
            R.id.ll_login_or_register,
            R.id.ll_account_setting,
            R.id.fl_feedback,
            R.id.tv_collection,
            R.id.ll_order
    }, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_login_or_register:
                LoginActivity.toActivity(view.getContext());
                break;
            case R.id.ll_account_setting:
                AccountSettingActivity.toActivity(view.getContext());
                break;
            case R.id.fl_feedback:
                FeedbackActivity.toActivity(view.getContext());
                break;
            case R.id.tv_collection:
                CollectionActivity.toActivity(view.getContext(),CollectionActivity.TYPE_ALL);
                break;
            case R.id.ll_order:
                OrderActivity.toActivity(view.getContext(),OrderActivity.ORDER_STATE_ALL);
                break;
            case R.id.bt_waiting_pay://待支付
                OrderActivity.toActivity(view.getContext(),OrderActivity.ORDER_STATE_WAITING_PAY);
                break;
            case R.id.bt_waiting_evaluation://待评价
                OrderActivity.toActivity(view.getContext(),OrderActivity.ORDER_STATE_WAITING_EVA);
                break;
            case R.id.bt_sending_good://发货中
                OrderActivity.toActivity(view.getContext(),OrderActivity.ORDER_STATE_SENDING_GOODS);
                break;
            case R.id.bt_canceled_order://已取消
                OrderActivity.toActivity(view.getContext(),OrderActivity.ORDER_STATE_CANCELED_ORDER);
                break;
        }
    }

    /**
     * 初始化实例
     */
    public static MineFragment newInstance() {
        Bundle bundle = new Bundle();
        MineFragment mineFragment = new MineFragment();
        mineFragment.setArguments(bundle);
        return mineFragment;
    }
}
