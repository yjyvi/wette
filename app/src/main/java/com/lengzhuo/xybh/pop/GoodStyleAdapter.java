package com.lengzhuo.xybh.pop;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.GoodDetailsBean;
import com.lengzhuo.xybh.ui.BaseViewHolder;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author yjyvi
 * @data 2018/2/7.
 */

class GoodStyleAdapter extends BaseQuickAdapter<GoodDetailsBean.DataBean.AttrListBeanX, BaseViewHolder> {
    private List<GoodDetailsBean.DataBean.AttrListBeanX> mDataBean;
    private ValueAttrListener mValueAttrListener;
    private Map<Integer, View> mAttrList = new TreeMap<>();

    public GoodStyleAdapter(int layout, List<GoodDetailsBean.DataBean.AttrListBeanX> dataBean, ValueAttrListener valueAttrListener) {
        super(layout, dataBean);
        this.mValueAttrListener = valueAttrListener;
        this.mDataBean = dataBean;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final GoodDetailsBean.DataBean.AttrListBeanX item) {
        if (item != null) {
            RecyclerView rv_attrList = helper.getView(R.id.rv_attrList);
            rv_attrList.setLayoutManager(new LinearLayoutManager(helper.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            GoodsAttrListAdapter goodsAttrListAdapter = new GoodsAttrListAdapter(R.layout.item_goos_sytle, item.getAttrList());
            rv_attrList.setAdapter(goodsAttrListAdapter);

            helper.setText(R.id.tv_type_name, item.getAttrName());

            if (helper.getAdapterPosition() == mDataBean.size() - 1) {
                helper.getView(R.id.v_line).setVisibility(View.GONE);
            }

            goodsAttrListAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if (!view.isSelected()) {
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

                        mAttrList.put(item.getAttrId(), view);
                        view.setSelected(true);
                        if (item.getAttrList() != null && item.getAttrList().size() > 0) {
                            mValueAttrListener.valueResult(item.getAttrId(), item.getAttrId() + ":" + String.valueOf(item.getAttrList().get(position).getAttrId()));
                        }
                    }
                }
            });
        }

    }


    public interface ValueAttrListener {
        void valueResult(int id, String result);
    }


    private class GoodsAttrListAdapter extends BaseQuickAdapter<GoodDetailsBean.DataBean.AttrListBeanX.AttrListBean, BaseViewHolder> {
        public GoodsAttrListAdapter(int item_goos_sytle, List<GoodDetailsBean.DataBean.AttrListBeanX.AttrListBean> attrList) {
            super(item_goos_sytle, attrList);
        }

        @Override
        protected void convert(BaseViewHolder helper, GoodDetailsBean.DataBean.AttrListBeanX.AttrListBean item) {
            helper.setText(R.id.tv_content, item.getAttrName());
        }
    }
}
