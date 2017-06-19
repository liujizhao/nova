package com.exper.nova.fragment;

import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.exper.nova.MainActivity;
import com.exper.nova.R;
import com.exper.nova.base.BaseFragment;
import com.exper.nova.widget.VerticalPageSeekBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liujizhao on 2017/6/18.
 */

public class BianJiaoSeeFragment extends BaseFragment{

    @BindView(R.id.background)
    ImageView mBackGround;

    @BindView(R.id.flag)
    ImageView mFlagView;

    @BindView(R.id.seek)
    VerticalPageSeekBar mSeekView;

    private float x1 = 0;
    private float x2 = 0;
    private int mCurrentPosition;

    @Override
    protected int attachLayoutRes() {
        return R.layout.bianjiao_see_fragment;
    }

    @Override
    protected void initViews() {
        mBackGround.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //继承了Activity的onTouchEvent方法，直接监听点击事件
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    //当手指按下的时候
                    x1 = motionEvent.getX();
                }
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    //当手指离开的时候
                    x2 = motionEvent.getX();
                    if(x2 - x1 > 100 && mCurrentPosition == 1) {
                        mCurrentPosition = 0;
                        mBackGround.setImageResource(R.drawable.bianjiao_bg_1);
                        mFlagView.setImageResource(R.drawable.flag_bg1);
                        mSeekView.setProgress(0);
                    }else if (x2 - x1 < 100 && mCurrentPosition == 0) {
                        mCurrentPosition = 1;
                        mBackGround.setImageResource(R.drawable.bianjiao_bg_2);
                        mFlagView.setImageResource(R.drawable.flag_bg2);
                        mSeekView.setProgress(0);
                    }
                }
                return true;
            }
        });

        mSeekView.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Matrix imageMatrix = new Matrix();
                imageMatrix.postScale(1 + i / 1000f,1 + i / 1000f,540,960);
                mBackGround.setImageMatrix(imageMatrix);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    protected void updateViews() {

    }

    @OnClick({R.id.btn_back,R.id.btn_menu,R.id.to_duibi})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                ((MainActivity)getActivity()).clearAllFragment();
                break;
            case R.id.btn_menu:
                ((MainActivity)getActivity()).openDrawer();
                break;
            case R.id.to_duibi:
                ((MainActivity)getActivity()).replaceFragment(R.id.content_frame,new BianJiaoDuiBiFragment(),"duibi");
                break;
        }
    }
}
