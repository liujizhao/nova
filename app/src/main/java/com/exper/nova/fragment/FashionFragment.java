package com.exper.nova.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.exper.nova.MainActivity;
import com.exper.nova.R;
import com.exper.nova.base.BaseFragment;
import com.exper.nova.viewpager.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liujizhao on 2017/6/18.
 */

public class FashionFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.indicatorLayout)
    LinearLayout mIndicatorLayout;

    private int mCurrentPosition;
    private ViewPagerAdapter mViewPagerAdapter;
    private List<View> mViews = new ArrayList<>();
    private int[] mImageIds = new int[]{
            R.drawable.fashion_bg1,R.drawable.fashion_bg2,R.drawable.fashion_bg3,R.drawable.fashion_bg4,R.drawable.fashion_bg5,R.drawable.fashion_bg6,R.drawable.fashion_bg7
    };
    private ImageView[] mTips;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fashion_fragment;
    }

    @Override
    protected void initViews() {
        mTips = new ImageView[mImageIds.length];
        mIndicatorLayout.removeAllViews();
        for(int i = 0; i< mTips.length; i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(10,10));
            mTips[i] = imageView;
            if(i == 0){
                mTips[i].setBackgroundResource(R.drawable.selected);
            }else{
                mTips[i].setBackgroundResource(R.drawable.normal);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            mIndicatorLayout.addView(imageView, layoutParams);
        }

        initImageViews();
        mViewPagerAdapter = new ViewPagerAdapter(mViews);
    }

    @Override
    protected void updateViews() {
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
        mCurrentPosition = mViews.size() * 100;
        mViewPager.setCurrentItem(mCurrentPosition);
    }

    private void initImageViews() {
        mViews.clear();
        for (int ids : mImageIds){
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setBackgroundResource(ids);
            mViews.add(imageView);
        }
    }

    private void setImageBackground(int selectItems){
        for(int i = 0; i< mTips.length; i++){
            if(i == selectItems){
                mTips[i].setBackgroundResource(R.drawable.selected);
            }else{
                mTips[i].setBackgroundResource(R.drawable.normal);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurrentPosition = position;
        setImageBackground(mCurrentPosition % mViews.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick({R.id.btn_back,R.id.btn_menu})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                getActivity().onBackPressed();
                break;
            case R.id.btn_menu:
                ((MainActivity)getActivity()).openDrawer();
                break;
        }
    }
}
