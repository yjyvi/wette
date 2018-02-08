package com.lengzhuo.xybh.ui.mine;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Pair;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.beans.AddressBean;
import com.lengzhuo.xybh.beans.RegionBean;
import com.lengzhuo.xybh.network.CommonCallBack;
import com.lengzhuo.xybh.ui.BaseUI;
import com.lengzhuo.xybh.utils.NetworkUtils;
import com.lengzhuo.xybh.utils.ToastUtils;
import com.lengzhuo.xybh.utils.Utils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

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

    @ViewInject(R.id.tv_region)
    private TextView tv_region;

    @ViewInject(R.id.tv_city)
    private TextView tv_city;

    @ViewInject(R.id.tv_area)
    private TextView tv_area;

    private AddressBean mAddressBean;

    //省市区列表
    List<RegionBean> mRegionList = new ArrayList<>();

    SparseArray<List<RegionBean.CityListBean>> mCityList = new SparseArray<>();

    SparseArray<List<RegionBean.CityListBean.AreaListBean>> mAreaList = new SparseArray<>();

    AlertDialog mRegionDialog;

    private SparseArray<Pair<Integer, String>> mSelectCityArray = new SparseArray<>(3);

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        mAddressBean = getIntent().getParcelableExtra("addressBean");
        setTitle(mAddressBean == null ? "添加收货地址" : "修改收货地址");
        if (mAddressBean != null) {
            mSelectCityArray.put(0, new Pair<>(mAddressBean.getProvinceId(), mAddressBean.getProvinceName()));
            mSelectCityArray.put(1, new Pair<>(mAddressBean.getCityId(), mAddressBean.getCityName()));
            mSelectCityArray.put(2, new Pair<>(mAddressBean.getAreaId(), mAddressBean.getAreaName()));
            tv_region.setText(mAddressBean.getProvinceName());
            tv_city.setText(mAddressBean.getCityName());
            tv_area.setText(mAddressBean.getAreaName());
            et_addressee.setText(mAddressBean.getAddressee());
            et_address.setText(mAddressBean.getAddress());
            et_phone_number.setText(mAddressBean.getTelephone());
            et_postal_code.setText(mAddressBean.getPostalcode());
        }
    }

    @Override
    protected void prepareData() {
        getRegionList();
    }

    @Event(value = {R.id.tv_continue, R.id.tv_region, R.id.tv_city, R.id.tv_area}, type = View.OnClickListener.class)
    private void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_region:

                if (!Utils.getText(tv_city).equals("市") || !Utils.getText(tv_area).equals("区")) {
                    mSelectCityArray.clear();
                    tv_city.setText("市");
                    tv_area.setText("区");
                }

                mRegionDialog.show();
                break;
            case R.id.tv_city:

                Pair<Integer, String> selectRegion = mSelectCityArray.get(0);
                if (selectRegion == null) {
                    ToastUtils.showToast("请选择省");
                    return;
                }

                if (!Utils.getText(tv_area).equals("区")) {
                    mSelectCityArray.remove(2);
                    tv_area.setText("区");
                }

                final List<RegionBean.CityListBean> cityList = mCityList.get(selectRegion.first);
                String[] cityArray = new String[cityList.size()];
                for (int i = 0; i < cityList.size(); i++) {
                    cityArray[i] = cityList.get(i).getRegionName();
                }
                new AlertDialog.Builder(this)
                        .setItems(cityArray, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                RegionBean.CityListBean cityBean = cityList.get(which);
                                tv_city.setText(cityBean.getRegionName());
                                mSelectCityArray.put(1, new Pair<>(cityBean.getId(), cityBean.getRegionName()));
                                dialog.dismiss();
                            }
                        })
                        .show();
                break;
            case R.id.tv_area:
                Pair<Integer, String> selectCity = mSelectCityArray.get(1);
                if (selectCity == null) {
                    ToastUtils.showToast("请选择市");
                    return;
                }

                final List<RegionBean.CityListBean.AreaListBean> areaList = mAreaList.get(selectCity.first);
                String[] areaArray = new String[areaList.size()];
                for (int i = 0; i < areaList.size(); i++) {
                    areaArray[i] = areaList.get(i).getRegionName();
                }
                new AlertDialog.Builder(this)
                        .setItems(areaArray, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                RegionBean.CityListBean.AreaListBean areaBean = areaList.get(which);
                                tv_area.setText(areaBean.getRegionName());
                                mSelectCityArray.put(2, new Pair<>(areaBean.getId(), areaBean.getRegionName()));
                                dialog.dismiss();
                            }
                        })
                        .show();
                break;
            default:
                if (validate()) addOrUpdateAddress();
        }


    }

    private void addOrUpdateAddress() {
        String addressee = Utils.getText(et_addressee);
        String phoneNumber = Utils.getText(et_phone_number);
        String address = Utils.getText(et_address);
        String postalCode = Utils.getText(et_postal_code);

        NetworkUtils.getNetworkUtils().updateAddress(
                mAddressBean == null ? "" : String.valueOf(mAddressBean.getAddressId()),
                addressee,
                String.valueOf(mSelectCityArray.get(0).first),
                mSelectCityArray.get(0).second,
                String.valueOf(mSelectCityArray.get(1).first),
                mSelectCityArray.get(1).second,
                String.valueOf(mSelectCityArray.get(2).first),
                mSelectCityArray.get(2).second,
                address,
                postalCode,
                phoneNumber,
                new CommonCallBack<String>() {
                    @Override
                    protected void onSuccess(String data) {
                        if (mAddressBean != null)
                            ToastUtils.showToast("收货地址修改成功");
                        else
                            ToastUtils.showToast("收货地址添加成功");
                        setResult(1);
                        finish();
                    }
                }
        );

    }

    private ProgressDialog mProgressDialog;

    private void getRegionList() {
        mProgressDialog = ProgressDialog.show(this, "提示", "加载中...");
        NetworkUtils.getNetworkUtils().getRegionList(new CommonCallBack<List<RegionBean>>() {
            @Override
            protected void onSuccess(final List<RegionBean> data) {
                initialData(data);
                mProgressDialog.dismiss();
            }

            @Override
            protected void onError(String msg) {
                mProgressDialog.dismiss();
                ToastUtils.showToast("数据加载失败，请稍后再试");
                finish();
            }
        });
    }


    private void initialData(final List<RegionBean> data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (RegionBean region : data) {
                    mRegionList.add(region);
                    int regionId = region.getId();
                    List<RegionBean.CityListBean> cityList = region.getCityList();
                    mCityList.put(regionId, cityList);
                    for (RegionBean.CityListBean city : cityList) {
                        int cityId = city.getId();
                        mAreaList.put(cityId, city.getAreaList());
                    }
                }

                final String[] regionArray = new String[mRegionList.size()];
                for (int i = 0; i < mRegionList.size(); i++) {
                    regionArray[i] = mRegionList.get(i).getRegionName();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRegionDialog = new AlertDialog.Builder(EditAddressActivity.this)
                                .setItems(regionArray, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        RegionBean regionBean = mRegionList.get(which);
                                        tv_region.setText(regionBean.getRegionName());
                                        mSelectCityArray.put(0, new Pair<>(regionBean.getId(), regionBean.getRegionName()));
                                        dialog.dismiss();
                                    }
                                })
                                .create();
                        mProgressDialog.dismiss();
                    }
                });
            }
        }).start();
    }

    private boolean validate() {
        boolean result = Utils.isEmpty(new String[]{"请填写联系人", "请填写手机号", "请填写详细地址", "请填写邮政编码"}, et_addressee, et_phone_number, et_address, et_postal_code);
        if (mSelectCityArray.size() < 3) {
            result = false;
            ToastUtils.showToast("请选择所在区域");
        }
        return result;
    }

    public static void toActivity(Activity context, AddressBean addressBean) {
        Intent intent = new Intent(context, EditAddressActivity.class);
        if (addressBean != null) intent.putExtra("addressBean", addressBean);
        context.startActivityForResult(intent, 2);
    }

    public static void toAddAddressActivity(Activity context) {
        toActivity(context, null);
    }

    public static void toUpdateAddressActivity(Activity context, AddressBean addressBean) {
        toActivity(context, addressBean);
    }
}
