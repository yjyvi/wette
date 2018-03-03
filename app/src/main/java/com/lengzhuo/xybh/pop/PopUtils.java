package com.lengzhuo.xybh.pop;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.GoodDetailsBean;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by yjyvi on 2017/9/21.
 */

public class PopUtils {

    /**
     * 返回的结果
     */

    public static Map<Integer, String> valueList = new TreeMap();
    public static String valueId = "";
    private static GoodsSelectedStyleListener mClickListener;
    private static Map<Integer, Integer> lastAttrId = new HashMap<>();
    private static Map<Integer, String> AttrName = new HashMap<>();

    public static void showGoodsStyle2(final boolean isOne, final Activity context, View contentView, final GoodDetailsBean.DataBean dataBean, final GoodsSelectedStyleListener clickListener) {

        mClickListener = clickListener;


        View layoutView = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.fragment_selected_style, null);
        final PopupWindow popupWindow = new PopupWindow(layoutView, AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT);

        RecyclerView rv_style = (RecyclerView) layoutView.findViewById(R.id.rv_style);

        LinearLayoutManager layout = new LinearLayoutManager(context);
        layout.setAutoMeasureEnabled(true);

        rv_style.setLayoutManager(layout);
        GoodStyleAdapter goodStyleAdapter = new GoodStyleAdapter(R.layout.item_selected_style, dataBean.getAttrList(), lastAttrId, new GoodStyleAdapter.ValueAttrListener() {
            @Override
            public void valueResult(int parentAttrId, int attrId, String result) {
                valueList.put(parentAttrId, result);
                lastAttrId.put(parentAttrId, attrId);
                if (isOne) {
                    reData();
                }
            }
        });


        rv_style.setAdapter(goodStyleAdapter);
        popupWindow.setTouchable(true);
        showPoPNormal(context, contentView, popupWindow, Gravity.BOTTOM);

    }


    /**
     * 准备数据
     */
    private static void reData() {
        for (Map.Entry<Integer, String> integerStringEntry : valueList.entrySet()) {
            integerStringEntry.getValue();
            integerStringEntry.getKey();
            valueId = valueId + ";" + valueList.get(integerStringEntry.getKey());
        }

        if (valueId.length() > 1) {
            mClickListener.selectedResult("[" + valueId.substring(1, valueId.length()) + "]");
            valueList.clear();
            valueId = "";
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
                reData();
            }
        });
    }

    /**
     * 清除数据
     */
    public static void clearData() {
        lastAttrId.clear();
        AttrName.clear();
    }

    /**
     * 默认弹窗的回调
     */
    public interface NormalNotifyPopListener {
        void commitClick();
    }

    public interface GoodsSelectedStyleListener {
        void selectedResult(String result);
    }


}
