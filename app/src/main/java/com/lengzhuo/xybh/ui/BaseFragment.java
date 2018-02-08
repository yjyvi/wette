package com.lengzhuo.xybh.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lengzhuo.xybh.MyApplication;
import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.ui.mine.LoginActivity;
import com.lengzhuo.xybh.utils.ToastUtils;
import com.lengzhuo.xybh.utils.UserManager;

import org.xutils.x;

/**
 * 描述：自定义Fragment
 *
 * @author wangjian
 */
public abstract class BaseFragment extends Fragment {
    /**
     * 视图
     */
    protected View view;
    protected LayoutInflater inflater;
    protected MyApplication application = null;

    /**
     * 描述：创建
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 描述：加载视图
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loadViewLayout(inflater, container);
        x.view().inject(this, view);
        setControlBasis();
        prepareData();
        return view;
    }


    protected View findViewById(int id) {
        return view.findViewById(id);
    }

    /**
     * 描述：加载视图
     */
    protected abstract void loadViewLayout(LayoutInflater inflater, ViewGroup container);

    /**
     * 描述：设置控件基础
     */
    protected abstract void setControlBasis();

    /**
     * 描述：准备数据
     */
    protected abstract void prepareData();


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

    /**
     * 是否登录
     * @return
     */
    public boolean isLoginClick() {
        if (!UserManager.isLogin()) {
            ToastUtils.showToast(getResources().getString(R.string.login_hint));
            LoginActivity.toActivity(getActivity());
            return true;
        }
        return false;
    }
}
