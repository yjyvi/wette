package com.lengzhuo.xybh.pop;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.GoodDetailsBean;
import com.lengzhuo.xybh.ui.BaseViewHolder;
import com.lengzhuo.xybh.views.AutoFlowLayout;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author yjyvi
 * @data 2018/2/7.
 */

class GoodStyleAdapter extends BaseQuickAdapter<GoodDetailsBean.DataBean.AttrListBeanX, BaseViewHolder> {
    private Map<Integer, Integer> mLastAttrIds;
    private List<GoodDetailsBean.DataBean.AttrListBeanX> mDataBean;
    private ValueAttrListener mValueAttrListener;
    private Map<Integer, View> mAttrList = new TreeMap<>();

    public GoodStyleAdapter(int layout, List<GoodDetailsBean.DataBean.AttrListBeanX> dataBean, Map<Integer, Integer> lastAttrIds, ValueAttrListener valueAttrListener) {
        super(layout, dataBean);
        this.mValueAttrListener = valueAttrListener;
        this.mDataBean = dataBean;
        this.mLastAttrIds = lastAttrIds;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final GoodDetailsBean.DataBean.AttrListBeanX item) {
        final AutoFlowLayout ll_attr_content = helper.getView(R.id.alf_color_type);

        for (int position = 0; position < item.getAttrList().size(); position++) {
            TextView textView = (TextView) LayoutInflater.from(helper.itemView.getContext()).inflate(R.layout.item_goos_sytle, ll_attr_content, false);
            textView.setText(item.getAttrList().get(position).getAttrName());

            //默认选中第一个
            if (mLastAttrIds.size() == 0 && position == 0) {
                textView.setSelected(true);
            }else {
                //回显上一次记录的控件为选中状态
                for (Map.Entry<Integer, Integer> integerStringEntry : mLastAttrIds.entrySet()) {
                    if (item.getAttrList().get(position).getAttrId() == integerStringEntry.getValue()) {
                        textView.setSelected(true);
                    }
                }
            }

            ll_attr_content.addView(textView);

            final int finalPosition = position;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedChildView(item, ll_attr_content, view, finalPosition);
                }
            });


        }

        helper.setText(R.id.tv_type_name, item.getAttrName());

        if (helper.getAdapterPosition() == mDataBean.size() - 1) {
            helper.getView(R.id.v_line).setVisibility(View.GONE);
        }

    }


    /**
     * 点击事件的属性记录
     * @param item
     * @param linearLayout
     * @param textView
     * @param position
     */
    private void selectedChildView(GoodDetailsBean.DataBean.AttrListBeanX item, AutoFlowLayout linearLayout, View textView, int position) {
        if (!textView.isSelected()) {

            //关闭所有按扭
            int itemCount = linearLayout.getChildCount();
            for (int i = 0; i < itemCount; i++) {
                View viewByPosition = linearLayout.getChildAt(i);
                if (viewByPosition != null) {
                    viewByPosition.setSelected(false);
                }
            }


            if (mAttrList.size() > 0) {
                for (Map.Entry<Integer, View> integerViewEntry : mAttrList.entrySet()) {
                    Integer key = integerViewEntry.getKey();
                    View value = integerViewEntry.getValue();
                    if (item.getAttrId() == key) {
                        value.setSelected(false);
                        mAttrList.remove(key);
                        break;
                    }
                }
            }

            mAttrList.put(item.getAttrId(), textView);
            textView.setSelected(true);

            if (item.getAttrList() != null && item.getAttrList().size() > 0) {
                mValueAttrListener.valueResult(item.getAttrId(), item.getAttrList().get(position).getAttrId(),item.getAttrId() + ":" + String.valueOf(item.getAttrList().get(position).getAttrId()));
            }
        }
    }


    public interface ValueAttrListener {
        void valueResult(int parentAttrId, int attrId, String result);

    }

}
