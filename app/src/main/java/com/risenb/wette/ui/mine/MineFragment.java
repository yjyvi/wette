package com.risenb.wette.ui.mine;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.risenb.wette.R;
import com.risenb.wette.ui.LazyLoadFragment;
import com.risenb.wette.utils.GlideApp;
import com.risenb.wette.utils.ToastUtils;
import com.risenb.wette.utils.UserManager;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by yjyvi on 2018/1/30.
 */

public class MineFragment extends LazyLoadFragment {

    @ViewInject(R.id.iv_avatar)
    ImageView iv_avatar;

    @ViewInject(R.id.tv_nick_name)
    TextView tv_nick_name;

    @ViewInject(R.id.sv_mine)
    ScrollView sv_mine;

    AlertDialog mExitLoginDialog;

    @Override
    protected void loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        isLogin();
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
            R.id.ll_order,
            R.id.tv_exit_login
    }, type = View.OnClickListener.class)
    private void onClick(View view) {

        if (view.getId() != R.id.ll_login_or_register && !UserManager.isLogin()) {
            ToastUtils.showToast("请先登录！");
            return;
        }

        switch (view.getId()) {
            case R.id.ll_login_or_register:
                if (!UserManager.isLogin())
                    LoginActivity.toActivity(view.getContext());
                break;
            case R.id.ll_account_setting:
                AccountSettingActivity.toActivity(view.getContext());
                break;
            case R.id.fl_feedback:
                FeedbackActivity.toActivity(view.getContext());
                break;
            case R.id.tv_collection:
                CollectionActivity.toActivity(view.getContext(), CollectionActivity.TYPE_ALL);
                break;
            case R.id.ll_order:
                OrderActivity.toActivity(view.getContext(), OrderActivity.ORDER_STATE_ALL);
                break;
            case R.id.bt_waiting_pay://待支付
                OrderActivity.toActivity(view.getContext(), OrderActivity.ORDER_STATE_WAITING_PAY);
                break;
            case R.id.bt_waiting_evaluation://待评价
                OrderActivity.toActivity(view.getContext(), OrderActivity.ORDER_STATE_WAITING_EVA);
                break;
            case R.id.bt_sending_good://发货中
                OrderActivity.toActivity(view.getContext(), OrderActivity.ORDER_STATE_SENDING_GOODS);
                break;
            case R.id.bt_canceled_order://已取消
                OrderActivity.toActivity(view.getContext(), OrderActivity.ORDER_STATE_CANCELED_ORDER);
                break;
            case R.id.tv_exit_login:
                if(mExitLoginDialog == null) {
                    mExitLoginDialog = new AlertDialog.Builder(getContext())
                            .setTitle("提示")
                            .setMessage("确定要退出登录？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    UserManager.clearUser();
                                    isLogin();
                                    sv_mine.smoothScrollTo(0,0);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();
                }
                mExitLoginDialog.show();
                break;
        }
    }

    private void isLogin() {
        if (UserManager.isLogin()) {
            GlideApp.with(this).load(UserManager.getUser().getHeadImg()).loadAvatar().into(iv_avatar);
            tv_nick_name.setText(UserManager.getUser().getNickname());
        } else {
            iv_avatar.setImageResource(R.drawable.mine_no_login_head_image);
            tv_nick_name.setText("请登录/注册");
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
