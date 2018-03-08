package com.lengzhuo.xybh.ui.mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.ui.LazyLoadFragment;
import com.lengzhuo.xybh.utils.GlideApp;
import com.lengzhuo.xybh.utils.ToastUtils;
import com.lengzhuo.xybh.utils.UserManager;

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

    @ViewInject(R.id.iv_message)
    ImageView iv_message;

    @ViewInject(R.id.tv_exit_login)
    TextView tv_exit_login;

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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void setControlBasis() {
//        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
//        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
//        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        //设置状态栏颜色
//        getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
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
            R.id.tv_nick_name,
            R.id.iv_avatar,
            R.id.ll_account_setting,
            R.id.fl_feedback,
            R.id.tv_collection,
            R.id.ll_order,
            R.id.tv_exit_login,
            R.id.iv_message,
            R.id.iv_shopping_cart,
            R.id.fl_collection_commodity,
            R.id.fl_collection_shop,
            R.id.fl_customer_service
    }, type = View.OnClickListener.class)
    private void onClick(View view) {

        //反馈
        if (view.getId() == R.id.fl_feedback) {
            FeedbackActivity.toActivity(view.getContext());
            return;
        }

        //拨打客服电话
        if (view.getId() == R.id.fl_customer_service) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:15117934180"));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return;
        }

        if (view.getId() != R.id.tv_nick_name && !UserManager.isLogin()) {
            ToastUtils.showToast("请先登录！");
            return;
        }



        switch (view.getId()) {
            case R.id.iv_avatar:
            case R.id.tv_nick_name:
                if (isLoginClick())
                break;
            case R.id.ll_account_setting:
                AccountSettingActivity.toActivity(view.getContext());
                break;
            case R.id.tv_collection:
                CollectionActivity.toActivity(view.getContext(), CollectionActivity.TYPE_ALL);
                break;
            case R.id.fl_collection_commodity:
                CollectionActivity.toActivity(view.getContext(), CollectionActivity.TYPE_GOODS);
                break;
            case R.id.fl_collection_shop:
                CollectionActivity.toActivity(view.getContext(), CollectionActivity.TYPE_SHOPS);
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
            case R.id.iv_message:
                MessageActivity.toActivity(view.getContext());
                break;
            case R.id.iv_shopping_cart:
                ShoppingCartActivity.toActivity(view.getContext());
                break;
            case R.id.tv_exit_login:
                if (mExitLoginDialog == null) {
                    mExitLoginDialog = new AlertDialog.Builder(getContext())
                            .setTitle("提示")
                            .setMessage("确定要退出登录？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    UserManager.clearUser();
                                    isLogin();
                                    sv_mine.smoothScrollTo(0, 0);
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
            default:
                break;
        }
    }


    private void isLogin() {
        if (UserManager.isLogin()) {
            GlideApp.with(this).load(UserManager.getUser().getHeadImg()).loadAvatar().into(iv_avatar);
            tv_nick_name.setText(UserManager.getUser().getNickname());
            iv_message.setVisibility(View.VISIBLE);
            tv_exit_login.setVisibility(View.VISIBLE);

        } else {
            iv_avatar.setImageResource(R.drawable.mine_no_login_head_image);
            tv_nick_name.setText("请登录/注册");
            iv_message.setVisibility(View.GONE);
            tv_exit_login.setVisibility(View.GONE);
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
