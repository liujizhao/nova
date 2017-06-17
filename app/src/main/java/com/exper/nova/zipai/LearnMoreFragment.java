package com.exper.nova.zipai;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.hardware.Camera;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.exper.nova.MainActivity;
import com.exper.nova.R;
import com.exper.nova.base.BaseFragment;
import com.exper.nova.viewpager.ViewPagerAdapter;
import com.exper.nova.widget.PreviewVideoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Blank on 2017/6/16.
 */

public class LearnMoreFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @BindView(R.id.videoView)
    PreviewVideoView mVideoView;

    @BindView(R.id.placeholder)
    ImageView mPlaceHolder;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.indicatorLayout)
    LinearLayout mIndicatorLayout;

    @BindView(R.id.viewPagerLayout)
    View pagerLayout;

    @BindView(R.id.location_view)
    View locationView;

    @BindView(R.id.des_view)
    ImageView mDesView;

    private ViewPagerAdapter mViewPagerAdapter;
    private List<View> mViews = new ArrayList<>();
    private int[] mImageIds = new int[]{
            R.drawable.learn_more_pic_1,R.drawable.learn_more_pic_2,R.drawable.learn_more_pic_3,R.drawable.learn_more_pic_4
    };
    private ImageView[] mTips;

    @Override
    protected int attachLayoutRes() {
        return R.layout.learn_more_fragment;
    }

    @Override
    protected void initViews() {
        mVideoView.setVideoURI(Uri.parse(getVideoPath()));
        mPlaceHolder.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPlaceHolder.setVisibility(View.GONE);
                mVideoView.start();
            }
        },500);
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mPlaceHolder.setBackgroundResource(R.drawable.learn_more_small);
                mPlaceHolder.setVisibility(View.VISIBLE);
                setAnim(mPlaceHolder);
                pagerLayout.setVisibility(View.VISIBLE);
            }
        });

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

    private void initImageViews() {
        mViews.clear();
        for (int ids : mImageIds){
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setImageResource(ids);
            mViews.add(imageView);
        }
    }

    @Override
    protected void updateViews() {
        if (!mVideoView.isPlaying()) {
            mVideoView.start();
        }
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setCurrentItem(mViews.size() * 100);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.suspend();
        }
    }

    private String getVideoPath() {
        return "android.resource://" + getActivity().getPackageName() + "/" + R.raw.renxiangzipai;
    }

    @OnClick({R.id.btn_back,R.id.btn_menu,R.id.back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back:
            case R.id.btn_back:
                ((MainActivity)getActivity()).clearAllFragment();
                break;
            case R.id.btn_menu:
                ((MainActivity)getActivity()).openDrawer();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int current = position % mViews.size();
        setImageBackground(current);
        if(current == 0){
            mDesView.setImageResource(R.drawable.learn_more_txt_1);
        }else if(current == 1){
            mDesView.setImageResource(R.drawable.learn_more_txt_2);
        }else if(current == 2){
            mDesView.setImageResource(R.drawable.learn_more_txt_3);
        }else if(current == 3){
            mDesView.setImageResource(R.drawable.learn_more_txt_4);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

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

    private void setAnim(final View v) {

        Rect rect = new Rect();
        locationView.getGlobalVisibleRect(rect);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 462.0f / 1080.0f, 1.0f, 776.0f / 1920.0f,Animation.RELATIVE_TO_PARENT, 130 / 1080.0f, Animation.RELATIVE_TO_PARENT, 1000 / 1920.0f);
        scaleAnimation.setInterpolator(new AccelerateInterpolator());
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setRepeatCount(0);

        AnimationSet set = new AnimationSet(false);
        set.setFillAfter(false);
        set.addAnimation(scaleAnimation);
        set.setDuration(1000);// 动画的执行时间
        v.startAnimation(set);
        // 动画监听事件
        set.setAnimationListener(new Animation.AnimationListener() {
            // 动画的开始
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            // 动画的结束
            @Override
            public void onAnimationEnd(Animation animation) {
                mPlaceHolder.setVisibility(View.GONE);
            }
        });

    }

}
