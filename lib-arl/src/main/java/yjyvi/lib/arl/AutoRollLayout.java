package yjyvi.lib.arl;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhy.autolayout.AutoFrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * perfromClick View的方法
 * 1 在View的onTouchEvent方法中的 up类型判断 中  被触发
 * 2 它的实现中会检查有无点击监听，如果有点击监听，就触发点击监听的onClick方法，传入自己
 * 3 用途：模拟点击
 */
public class AutoRollLayout extends AutoFrameLayout {

    private Context context;
    private ViewPager mViewPager;
    private TextView mTextView;
    private LinearLayout mDotContainer;
    private OnItemClickListener mOnItemClickListener;
    private GestureDetector mGestureDetector;
    private static final int DOT_RADIUS = 8;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public AutoRollLayout(Context context) {

        this(context, null);
    }

    public AutoRollLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoRollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }


    /**
     * 用户手指在触摸屏幕
     */
    boolean mIsTouching = false;

    private void init() {
        View.inflate(getContext(), R.layout.arl_layout_arl, this);
        mViewPager = (ViewPager) findViewById(R.id.arl_arl_vp);
        mTextView = (TextView) findViewById(R.id.arl_arl_tv);
        mDotContainer = (LinearLayout) findViewById(R.id.arl_arl_dot_container);
        mViewPager.setOnPageChangeListener(mPageListener);
        mGestureDetector = new GestureDetector(getContext(), mOnGestureListener);
        mViewPager.setOnTouchListener(mOnTouchListener);

    }

    private GestureDetector.OnGestureListener mOnGestureListener = new GestureDetector.OnGestureListener() {
        // 手指按下
        @Override
        public boolean onDown(MotionEvent e) {
            Log.d("OnGestureListener", "onDown");
            return false;
        }

        // 按下后一小会 没动
        @Override
        public void onShowPress(MotionEvent e) {
            Log.d("OnGestureListener", "onShowPress");
        }

        // 单击
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d("OnGestureListener", "onSingleTapUp");
//            if (mOnItemClickListener != null) {
//                mOnItemClickListener.onItemClick(mViewPager.getCurrentItem());
//            }

            return true;
        }

        // 手指在移动
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d("OnGestureListener", "onScroll");
            return false;
        }

        // 长按
        @Override
        public void onLongPress(MotionEvent e) {
            Log.d("OnGestureListener", "onLongPress");

        }

        // 手指离开屏幕还有速度
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("OnGestureListener", "onFling");
            Log.d("OnGestureListener", e1.getRawX()+"");
            mCurrentItemListener.currentItemPosition(mViewPager.getCurrentItem());
//            autoRoll = true;
            return true;
        }
    };

    private OnTouchListener mOnTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mGestureDetector.onTouchEvent(event);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mIsTouching = true;
                    if (autoRoll) {
                        handler.removeCallbacks(mGonNextPageRunnable);
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                    break;
                case MotionEvent.ACTION_UP:
                    mIsTouching = false;
                    if (autoRoll) {
                        handler.postDelayed(mGonNextPageRunnable, 2000);
                    }
                    break;
                default:

            }
            return false;
        }
    };

    static Handler handler = new Handler();
    List<? extends String> items;

    public <T extends String> void setItems(List<T> items) {
        this.items = items;
        mViewPager.setAdapter(mAdapter);
//        监听
        mTextView.setText(null);
        // 点
//        addDots();
        if (mPageListener != null) {
            mPageListener.onPageSelected(0);
        }
    }

    boolean autoRoll = false;

    public void setAutoRoll(boolean autoRoll) {
        if (this.autoRoll == autoRoll) {
            return;
        }
        this.autoRoll = autoRoll;

        if (autoRoll) {
            handler.postDelayed(mGonNextPageRunnable, 2000);
        } else {
            handler.removeCallbacks(mGonNextPageRunnable);
        }
    }

    private void addDots() {
        if (mDotContainer == null) {
            return;
        }
        mDotContainer.removeAllViews();
        if (items == null || items.isEmpty()) {
            return;
        }
        int dotWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DOT_RADIUS, getResources().getDisplayMetrics());
        for (String item : items) {
            View dot = new View(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dotWidth, dotWidth);
            lp.setMargins(0, 0, dotWidth, 0);


            dot.setBackgroundResource(R.drawable.arl_dot_selector);
            dot.setLayoutParams(lp);
//            mDotContainer.addView(dot);
//            dot.setOnClickListener(mGoPageListener);
        }
    }

    private boolean isPhotoPre = false;

    public void setPhotoPre(boolean photoPre) {
        isPhotoPre = photoPre;
    }

    private PagerAdapter mAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return items == null ? 0 : items.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        List<ImageView> cache = new ArrayList<>();

        @Override
        public Object instantiateItem(final ViewGroup container, final int position) {
            if (cache.isEmpty()) {
                ImageView imageView = new ImageView(container.getContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                cache.add(imageView);
            }
            ImageView imageView = cache.remove(0);
            // 用Picasso imageView 显示图片
            String imagePath = items.get(position);
            if (!imagePath.startsWith("http")) {
//                imagePath = Url.ip + imagePath;

            }
            Glide.with(container.getContext())
                    .load(imagePath)
                    .into(imageView);


            if (isPhotoPre) {
                imageView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setAutoRoll(false);
                        PhotoViewActivity.start(getContext(), (ArrayList<String>) items);
                    }
                });
            }

            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            cache.add((ImageView) object);
        }
    };

    private ViewPager.OnPageChangeListener mPageListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            if (items == null || items.isEmpty()) {
                return;
            }

            if (mAdapter != null) {
                for (int i = 0; i < mAdapter.getCount(); i++) {
                    if (mDotContainer != null) {
                        View childAt = mDotContainer.getChildAt(i);
                        if (childAt != null) {
                            childAt.setEnabled(i != position);
                        }
                    }
                }
            }
        }
    };

    private OnClickListener mGoPageListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mViewPager != null) {
                mViewPager.setCurrentItem(mDotContainer.indexOfChild(v));
            }

        }
    };

    /*
   套路 防止间隔执行的任务 多次执行的标准写法
     */

    private Runnable mGonNextPageRunnable = new Runnable() {
        @Override
        public void run() {
            //！ 只移除为执行的任务，不会影响已经执行的
            // 如果队列中有多个 ==  ，会移除多个
            // 如果队列中没有 ==  ，没有任何影响
            handler.removeCallbacks(this);
            if (!mIsTouching) {
                goNextPage();

            }
            handler.postDelayed(this, 2000);
        }
    };

    boolean fromLeftToRight = true;

    private void goNextPage() {
        // ViewPager的setCurrentItem 方法中进行了 容错处理，接受负值和超出count的数值，
        if (mViewPager == null) {
            return;
        }
        if (mAdapter.getCount() <= 1) {
            return;
        }

        int currentIndex = mViewPager.getCurrentItem();
        if (currentIndex == 0) {
            fromLeftToRight = true;
        } else if (currentIndex == mAdapter.getCount() - 1) {
            fromLeftToRight = false;
        }
        int targetIndex = 0;
        if (fromLeftToRight) {
            targetIndex = currentIndex + 1;
        } else {
            targetIndex = currentIndex - 1;
        }
        mCurrentItemListener.currentItemPosition(targetIndex);

        mViewPager.setCurrentItem(targetIndex);
    }

    private CurrentItemListener mCurrentItemListener;

    public void setCurrentItemListener(CurrentItemListener currentItemListener) {
        mCurrentItemListener = currentItemListener;
    }

    public interface CurrentItemListener {
        void currentItemPosition(int position);
    }


}
