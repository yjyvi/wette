package com.lengzhuo.xybh.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.RadioGroup;

import com.lengzhuo.xybh.R;
import com.lengzhuo.xybh.adapter.home.HomeTableViewPagerAdapter;
import com.lengzhuo.xybh.ui.classify.ClassifyFragment;
import com.lengzhuo.xybh.ui.home.HomeFragment;
import com.lengzhuo.xybh.ui.mine.MineFragment;
import com.lengzhuo.xybh.views.MyViewPager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

/**
 * @author yjyvi
 * @date 2017/10/22
 * 主切换页面
 */
@ContentView(R.layout.activity_table_home)
public class HomeTableUI extends BaseUI {

    @ViewInject(R.id.tab_pager)
    private MyViewPager tab_pager;

    @ViewInject(R.id.rg_home_table_groups)
    private RadioGroup rg_home_table_groups;


    private static final String CHANGE_HOME_TABLE = "change_home_table";
    ArrayList<Fragment> mFragments = new ArrayList<>();


    public static void startHomeTableView(Context activity, int tab) {
        Intent intent = new Intent(activity, HomeTableUI.class);
        intent.putExtra(CHANGE_HOME_TABLE, tab);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }

    @Override
    protected void back() {
        exit();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void setControlBasis() {
//        StatusBarUtils.transparencyBar(this);
    }


    @Override
    protected void prepareData() {
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(ClassifyFragment.newInstance());
        mFragments.add(MineFragment.newInstance());

        HomeTableViewPagerAdapter homeTableViewPagerAdapter = new HomeTableViewPagerAdapter(getSupportFragmentManager(), mFragments);
        tab_pager.setAdapter(homeTableViewPagerAdapter);
        tab_pager.setOffscreenPageLimit(2);
        tab_pager.setCurrentItem(0);
        tab_pager.setNoScroll(true);
        //切换
        rg_home_table_groups.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_home:
                        changeItem(0);
                        break;
                    case R.id.rb_type:
                        changeItem(1);
                        break;
                    case R.id.rb_mine:
                        changeItem(2);
                        break;
                    default:
                        changeItem(0);
                        break;
                }
            }
        });


    }

    private void changeItem(int position) {
        tab_pager.setCurrentItem(position);
    }


}
