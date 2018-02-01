package com.risenb.wette.pop;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.risenb.wette.R;
import com.risenb.wette.utils.ToastUtils;
import com.risenb.wette.views.AutoFlowLayout;
import com.zhy.autolayout.AutoLinearLayout;


/**
 * Created by yjyvi on 2017/9/21.
 */

public class PopUtils {

    /**
     * 返回的结果
     */
    public static String selectedColorStyleResult = "";
    public static String selectedSizeStyleResult = "";

    public static void showGoodsStyle(final Activity context, View contentView, String colorContent, String sizeContent, final GoodsSelectedStyleListener clickListener) {

        View layoutView = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.pop_selected_style, null);
        final PopupWindow popupWindow = new PopupWindow(layoutView, AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT);

        AutoFlowLayout alf_color_type = (AutoFlowLayout) layoutView.findViewById(R.id.alf_color_type);
        AutoFlowLayout alf_size = (AutoFlowLayout) layoutView.findViewById(R.id.alf_size);
        TextView tv_commit = (TextView) layoutView.findViewById(R.id.tv_commit);

        showItems(alf_color_type, context, colorContent, 1);
        showItems(alf_size, context, sizeContent, 2);


        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.selectedResult(selectedColorStyleResult + selectedSizeStyleResult);
                popupWindow.dismiss();
            }
        });

        showPoPNormal(context, contentView, popupWindow, Gravity.BOTTOM);
    }


    /**
     * 显示流式布局文字
     *
     * @param autoFlowLayout
     * @param context
     * @param contents
     * @return
     */
    private static void showItems(final AutoFlowLayout autoFlowLayout, final Activity context, String contents, final int type) {
        autoFlowLayout.removeAllViews();
        String pressTexts = contents.substring(0, contents.length());
        final String[] split = pressTexts.split(",");
        for (int i = 0; i < split.length; i++) {
            final TextView tag = (TextView) LayoutInflater.from(context).inflate(R.layout.item_goos_sytle, autoFlowLayout, false);
            final String text = split[i];
            tag.setText(text);
            autoFlowLayout.addView(tag);
            tag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!tag.isSelected()) {
                        //单选操作，遍历后设置为未选中状态
                        for (int i1 = 0; i1 < autoFlowLayout.getChildCount(); i1++) {
                            autoFlowLayout.getChildAt(i1).setSelected(false);
                        }
                        tag.setSelected(true);
                        String content = tag.getText().toString().trim();
                        ToastUtils.showToast(content);
                        if (1 == type) {
                            selectedColorStyleResult = content;
                        } else {
                            selectedSizeStyleResult = content;
                        }
                    } else {
                        tag.setSelected(false);
                    }
                }
            });
        }
    }

    /**
     * 默认提示弹窗
     *
     * @param context
     * @param contentView
     */
    public static void showNotify(final Activity context, View contentView, String msg, String commitContent, final NormalNotifyPopListener clickListener) {

        View layoutView = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.item_normal_notify, null);
        final PopupWindow popupWindow = new PopupWindow(layoutView, AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT);
        TextView tv_msg = (TextView) layoutView.findViewById(R.id.tv_msg);
        TextView tv_commit = (TextView) layoutView.findViewById(R.id.tv_commit);

        tv_msg.setText(msg);
        tv_commit.setText(commitContent);

        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.commitClick();
                popupWindow.dismiss();
            }
        });

        showPoPNormal(context, contentView, popupWindow, Gravity.CENTER);
    }

    /**
     * 改变背景颜色
     *
     * @param bgAlpha
     * @param activity
     */
    private static void backgroundAlpha(float bgAlpha, Activity activity) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        activity.getWindow().setAttributes(lp);
    }


    /**
     * 公共参数
     *
     * @param context
     * @param contentView
     * @param popupWindow
     */
    public static void showPoPNormal(final Activity context, View contentView, PopupWindow popupWindow, int location) {
        //设置SelectPicPopupWindow弹出窗体可点击
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置SelectPicPopupWindow弹出窗体动画效果
        backgroundAlpha(0.4F, context);
        popupWindow.showAtLocation(contentView, location, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f, context);
            }
        });
    }


    /**
     * 默认弹窗的回调
     */
    public interface NormalNotifyPopListener {
        void commitClick();
    }

    public interface GoodsSelectedStyleListener {
        void selectedResult(String reslut);
    }


}
