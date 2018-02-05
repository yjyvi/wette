package com.risenb.wette.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;

import com.risenb.wette.R;
import com.risenb.wette.beans.AddressBean;
import com.risenb.wette.ui.BaseUI;
import com.risenb.wette.utils.ToastUtils;
import com.risenb.wette.utils.Utils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/03
 *     desc   : 编辑 or 添加 收获地址
 *     version: 1.0
 * </pre>
 */
@ContentView(R.layout.activity_edit_address)
public class EditAddressActivity extends BaseUI {

    @ViewInject(R.id.et_addressee)
    private EditText et_addressee;

    @ViewInject(R.id.et_phone_number)
    private EditText et_phone_number;

    @ViewInject(R.id.et_address)
    private EditText et_address;

    @ViewInject(R.id.et_postal_code)
    private EditText et_postal_code;

    private AddressBean mAddressBean;

    private SparseArray<Pair<String, String>> mSelectCityArray = new SparseArray<>(3);

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        mAddressBean = getIntent().getParcelableExtra("addressBean");
        setTitle(mAddressBean == null ? "添加收货地址" : "修改收货地址");
    }

    @Override
    protected void prepareData() {

    }

    @Event(value = R.id.tv_continue, type = View.OnClickListener.class)
    private void onClick(View view) {
        if (validate()) addOrUpdateAddress();

    }

    private void addOrUpdateAddress() {

    }

    private boolean validate() {
        boolean result = Utils.isEmpty(new String[]{"请填写联系人", "请填写手机号", "请填写详细地址", "请填写邮政编码"}, et_addressee, et_phone_number, et_address, et_postal_code);
        if (mSelectCityArray.size() < 3) {
            result = false;
            ToastUtils.showToast("请选择所在区域");
        }
        return result;
    }

    public static void toActivity(Context context, AddressBean addressBean) {
        Intent intent = new Intent(context, EditAddressActivity.class);
        if (addressBean != null) intent.putExtra("addressBean", addressBean);
        context.startActivity(intent);
    }

    public static void toAddAddressActivity(Context context) {
        toActivity(context, null);
    }

    public static void toUpdateAddressActivity(Context context, AddressBean addressBean) {
        toActivity(context, addressBean);
    }
}
