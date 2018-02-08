package com.lengzhuo.xybh.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lengzhuo.xybh.MyApplication;
import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.utils.ToastUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.x;

import java.lang.reflect.Field;

/**
 * 描述：自定义Activity
 *
 * @author wangjian
 */
public abstract class BaseUI extends AutoLayoutActivity {
    private long exitTime = 0;
    protected MyApplication application;
    /**
     * 描述：返回
     */
    protected abstract void back();

    /**
     * 描述：设置控件基础
     */
    protected abstract void setControlBasis();

    /**
     * 描述：准备数据
     */
    protected abstract void prepareData();



    /**
     * 描述：创建
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(getActivity());
        View back = findViewById(R.id.common_title_back);
        if(back != null){
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    back();
                }
            });
        }
        //沉浸式布局
        setTranslucentStatus();
        setControlBasis();
        prepareData();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected FragmentActivity getActivity() {
        return BaseUI.this;
    }


    /**
     * 设置透明状态栏
     */
    protected void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

            try {
                Class decorViewClazz = Class.forName("com.android.internal.policy.DecorView");
                Field field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor");
                field.setAccessible(true);
                //改为透明
                field.setInt(getWindow().getDecorView(), Color.TRANSPARENT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 描述：退出程序
     */
    protected void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtils.showToast("再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    /**
     * 描述：设置标题
     *
     * @param text 标题
     */
    protected void setTitle(String text) {
        TextView tv_title = (TextView) findViewById(R.id.title);
        if (tv_title != null) {
            tv_title.setVisibility(View.VISIBLE);
            tv_title.setText(text);
        }
    }

    protected void setImgTitle(int drawable) {
        RelativeLayout ll_title = (RelativeLayout) findViewById(R.id.common_title);
        if (ll_title != null) {
            ll_title.setVisibility(View.VISIBLE);
        }
        ImageView iv_title = (ImageView) findViewById(R.id.iv_title);
        if (iv_title != null) {
            iv_title.setVisibility(View.VISIBLE);
            iv_title.setImageResource(drawable);
        }
    }

    /**
     * 描述:隐藏返回按钮
     */
    protected void backGone() {
        RelativeLayout back = (RelativeLayout) findViewById(R.id.common_title_back);
        if (back != null) {
            back.setVisibility(View.GONE);
        }
    }


    /**
     * 描述:显示右菜单全部
     */
    protected void rightVisible(String title, int drawable) {
        rightVisible(title);
        rightVisible(drawable);
    }

    /**
     * 描述:显示左菜单图片
     */
    protected void leftVisible(int drawable) {
        RelativeLayout ll_left = (RelativeLayout) findViewById(R.id.common_title_back);
        if (ll_left != null) {
            ll_left.setVisibility(View.VISIBLE);
        }
        ImageView iv_left = (ImageView) findViewById(R.id.iv_back);
        if (iv_left != null) {
            iv_left.setVisibility(View.VISIBLE);
            iv_left.setImageResource(drawable);
        }
    }

    /**
     * 描述:显示右菜单文字
     */
    protected void rightVisible(String title) {
        RelativeLayout rl_right = (RelativeLayout) findViewById(R.id.rl_right_title);
        if (rl_right != null) {
            rl_right.setVisibility(View.VISIBLE);
        }
        TextView tv_right = (TextView) findViewById(R.id.iv_right_title);
        if (tv_right != null) {
            tv_right.setVisibility(View.VISIBLE);
            tv_right.setText(title);
        }
    }

    /**
     * 描述:显示右菜单图片
     */
    protected void rightVisible(int drawable) {
        RelativeLayout rl_right = (RelativeLayout) findViewById(R.id.rl_right);
        if (rl_right != null) {
            rl_right.setVisibility(View.VISIBLE);
        }
        ImageView iv_right = (ImageView) findViewById(R.id.iv_right);
        if (iv_right != null) {
            iv_right.setVisibility(View.VISIBLE);
            iv_right.setImageResource(drawable);
        }
    }
}
