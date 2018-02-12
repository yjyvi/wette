package yjyvi.lib.arl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoViewActivity extends AutoLayoutActivity {

    private HackyViewPager mViewPager;
    private ArrayList<String> mPicses;
    private int mPosition;
    private LinearLayout mDotContainer;
    private PhotoViewAttacher mAttacher;
    public static final String PHOTOVIEW_POSITION = "photoView_position";
    private PhotoView mPhotoView;
    private int photoPosition;
    private MyAdapter mAdapter;
    public static final String PHOTO_POSITION = "photo_position";
    private String sType;
    private RelativeLayout rl_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        Intent intent = getIntent();
        mPosition = intent.getIntExtra(PHOTO_POSITION, 0);
        mPicses = intent.getStringArrayListExtra("pics");
        sType = intent.getStringExtra("scaleType");

        mDotContainer = ((LinearLayout) findViewById(R.id.photo_view_dot_container));
        addDots();

        mViewPager = ((HackyViewPager) findViewById(R.id.hackyViewPager));
        mAdapter = new MyAdapter();
        mViewPager.setAdapter(mAdapter);
        //设置页面改变监听
        mViewPager.addOnPageChangeListener(onPageChageListener);
        mViewPager.setCurrentItem(mPosition);
    }


    public static void start(Context context, ArrayList<String> pics) {
        Intent starter = new Intent(context, PhotoViewActivity.class);
        starter.putExtra("pics", pics);
        context.startActivity(starter);
    }

    private ViewPager.OnPageChangeListener onPageChageListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < mAdapter.getCount(); i++) {
                mDotContainer.getChildAt(i).setEnabled(i != position);
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mDotContainer.getChildAt(mPosition).setEnabled(false);
    }

    private void addDots() {
        mDotContainer.removeAllViews();
        if (mPicses == null || mPicses.isEmpty()) {
            return;
        }
        int dotWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        for (String picUrl : mPicses) {
            View dot = new View(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dotWidth, dotWidth);
            lp.setMargins(0, 0, dotWidth, 0);
            dot.setLayoutParams(lp);
            dot.setBackgroundResource(R.drawable.arl_dot_selector);
            mDotContainer.addView(dot);
        }
    }

    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPicses == null ? 0 : mPicses.size();
        }

        @SuppressLint("ResourceType")
        @Override
        public View instantiateItem(ViewGroup container, final int position) {
            View view = View.inflate(PhotoViewActivity.this, R.layout.item_photo_view, null);
            mPhotoView = (PhotoView) view.findViewById(R.id.photo);

            Glide.with(container.getContext())
                    .load(mPicses.get(position))
                    .into(mPhotoView);

            if (!TextUtils.isEmpty(sType)) {
                mPhotoView.setPadding(300, 300, 300, 300);
                mPhotoView.setBackgroundResource(Color.TRANSPARENT);
            }

            //设置图片显示为居中填满
            mPhotoView.setScaleType(ImageView.ScaleType.FIT_CENTER);

            photoPosition = position;

            //给图片增加点击事件
            mPhotoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float v, float v1) {
                    finish();
                }

            });

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
