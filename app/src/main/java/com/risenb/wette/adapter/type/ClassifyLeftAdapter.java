package com.risenb.wette.adapter.type;

import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.risenb.wette.R;
import com.risenb.wette.ui.BaseViewHolder;

import java.util.List;

/**
 * Created by yjyvi on 2018/1/31.
 */

public class ClassifyLeftAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public ClassifyLeftAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(final BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name, item);
        LinearLayout ll_type = helper.getView(R.id.ll_type);
//        ll_type.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                View v_line = helper.getView(R.id.v_line);
//                if (!v_line.isSelected()) {
//                    v_line.setSelected(true);
//                }else {
//                    v_line.setSelected(false);
//                }
//            }
//        });
    }
}
