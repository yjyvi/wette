package com.lengzhuo.xybh.pop;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.GoodDetailsBean;
import com.lengzhuo.xybh.utils.ToastUtils;
import com.lengzhuo.xybh.views.AutoFlowLayout;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by yjyvi on 2017/9/21.
 */

public class PopUtils {

    /**
     * 返回的结果
     */
    public static String selectedColorStyleResult = "";
    public static String selectedSizeStyleResult = "";

    public static Map<Integer, String> valueList = new TreeMap();
    public static String valueId = "";
    private static GoodsSelectedStyleListener mClickListener;
    private static Map<Integer, Integer> lastAttrId = new HashMap<>();
    private static Map<Integer, String> AttrName = new HashMap<>();

    public static void showGoodsStyle(final Activity context, View contentView, GoodDetailsBean.DataBean dataBean, final GoodsSelectedStyleListener clickListener) {

        View layoutView = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.pop_selected_style, null);
        final PopupWindow popupWindow = new PopupWindow(layoutView, AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT);

        AutoFlowLayout alf_color_type = (AutoFlowLayout) layoutView.findViewById(R.id.alf_color_type);
        AutoFlowLayout alf_size = (AutoFlowLayout) layoutView.findViewById(R.id.alf_size);
        TextView tv_commit = (TextView) layoutView.findViewById(R.id.tv_commit);


        StringBuilder colors = new StringBuilder();
        StringBuilder sizes = new StringBuilder();
        forData(dataBean, colors, sizes);


        showItems(alf_color_type, context, colors.toString(), 1);
        showItems(alf_size, context, sizes.toString(), 2);


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
     * 遍历数据
     *
     * @param dataBean
     * @param colors
     * @param sizes
     */
    private static void forData(GoodDetailsBean.DataBean dataBean, StringBuilder colors, StringBuilder sizes) {
        List<GoodDetailsBean.DataBean.AttrListBeanX> attrList = dataBean.getAttrList();
        for (int i = 0; i < attrList.size(); i++) {
            if (attrList.get(i) != null && "颜色".equals(attrList.get(i).getAttrName())) {
                List<GoodDetailsBean.DataBean.AttrListBeanX.AttrListBean> attrList1 = attrList.get(i).getAttrList();
                for (int j = 0; j < attrList1.size(); j++) {
                    colors.append(attrList1.get(j).getAttrName() + ",");
                }
            }

            if (attrList.get(i) != null && "尺码".equals(attrList.get(i).getAttrName())) {
                List<GoodDetailsBean.DataBean.AttrListBeanX.AttrListBean> attrList1 = attrList.get(i).getAttrList();
                for (int j = 0; j < attrList1.size(); j++) {
                    sizes.append(attrList1.get(j).getAttrName() + ",");
                }
            }
        }
    }


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
            public void valueResult(int id, String result) {
                valueList.put(id, result);
                if (isOne) {
                    reData();
                }
            }

            @Override
            public void showNameResult(int parentAttrId, int attrId, String name) {
                lastAttrId.put(parentAttrId, attrId);
                AttrName.put(parentAttrId, name);
                clickListener.selectedName(AttrName);
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
     * 显示流式布局文字
     *
     * @param autoFlowLayout
     * @param context
     * @param contents
     * @return
     */
    private static void showItems(final AutoFlowLayout autoFlowLayout, final Activity context, String contents, final int type) {
        autoFlowLayout.removeAllViews();

        if (!TextUtils.isEmpty(contents)) {
            String pressTexts = contents.substring(0, contents.length() - 1);
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

        void selectedName(Map<Integer, String> result);
    }


}
