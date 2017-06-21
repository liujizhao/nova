package com.exper.nova.fragment;

import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.exper.nova.MainActivity;
import com.exper.nova.R;
import com.exper.nova.base.BaseFragment;
import com.sprylab.android.widget.TextureVideoView;

import java.io.InputStream;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liujizhao on 2017/6/18.
 */

public class DuiJiaoFragment extends BaseFragment {

    @BindView(R.id.videoView)
    TextureVideoView mVideoView;

    @BindView(R.id.holderView)
    ImageView mHolderView;

    @BindView(R.id.toolBar)
    View mToolBar;

    @BindView(R.id.videoBoard)
    View mVideoBoard;

    @BindView(R.id.boyTouch)
    ImageButton boyTouchView;

    @BindView(R.id.girlTouch)
    ImageButton girlTouchView;

    @BindView(R.id.boyTouch_circle)
    ImageView boyTouchViewCircle;

    @BindView(R.id.girlTouch_circle)
    ImageView girlTouchViewCircle;

    @BindView(R.id.part2)
    View mPart2View;

    @BindView(R.id.currentSize)
    TextView mCurrentSize;

    @BindView(R.id.seekBar)
    SeekBar mSeekBar;

    @BindView(R.id.holderView2)
    ImageView mPart2HolderView;

    @BindView(R.id.part3View)
    View mPart3View;

    private int mCurrent;

    private DecimalFormat df = new DecimalFormat("######0.00");


    ClassLoader assets = null;
    //把assets文件夹中的图片名压到一个字符串数组里面
    String[] pics = {"00000.png","00001.png","00002.png","00003.png","00004.png","00005.png","00006.png","00007.png","00008.png","00009.png",
            "00010.png","00011.png","00012.png","00013.png","00014.png","00015.png","00016.png","00017.png","00018.png","00019.png",
            "00020.png","00021.png","00022.png","00023.png","00024.png","00025.png","00026.png","00027.png","00028.png","00029.png"};

    @Override
    protected int attachLayoutRes() {
        return R.layout.duijiao_fragment;
    }

    @Override
    protected void initViews() {
        mVideoView.setVideoURI( Uri.parse(getVideoPath()));
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mHolderView.setBackgroundResource(R.drawable.duijiao_last_frame);
                mVideoBoard.setVisibility(View.VISIBLE);
                mToolBar.setVisibility(View.GONE);
                mVideoView.setVisibility(View.GONE);
            }
        });
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(mCurrent == 0){
                    loadPic(girlTouchViewCircle,i);
                }else {
                    loadPic(boyTouchViewCircle,i);
                }
                double v = i * 16 / 29.00;
                mCurrentSize.setText(String.valueOf(df.format(v)));

                float i1 = i / 29f;
                mPart2HolderView.setAlpha(1f - i1);
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
        mVideoView.start();
        //获取assetsManager对象
        assets = getActivity().getClassLoader();
    }

    private String getVideoPath() {
        return "android.resource://" + getActivity().getPackageName() + "/" + R.raw.xianpaizhao;
    }

    @OnClick({R.id.btn_back,R.id.btn_menu,R.id.guangView,R.id.part2Back,R.id.boyTouch,
            R.id.girlTouch,R.id.part2Save,R.id.part2Save2,R.id.back_home,R.id.again})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_home:
            case R.id.part2Back:
            case R.id.btn_back:
                ((MainActivity)getActivity()).clearAllFragment();
                break;
            case R.id.btn_menu:
                ((MainActivity)getActivity()).openDrawer();
                break;
            case R.id.guangView:
                mVideoBoard.setVisibility(View.GONE);
                mToolBar.setVisibility(View.VISIBLE);
                boyTouchView.setVisibility(View.VISIBLE);
                AnimationDrawable animationDrawable = (AnimationDrawable) boyTouchView.getBackground();
                animationDrawable.start();
                girlTouchView.setVisibility(View.VISIBLE);
                AnimationDrawable animationDrawable1 = (AnimationDrawable) girlTouchView.getBackground();
                animationDrawable1.start();
                break;
            case R.id.boyTouch:
                mCurrent = 1;
                mToolBar.setVisibility(View.GONE);
                mPart2View.setVisibility(View.VISIBLE);
                mPart2HolderView.setBackgroundResource(R.drawable.boy);
                mHolderView.setBackgroundResource(R.drawable.qingxi);

                boyTouchView.setVisibility(View.GONE);
                boyTouchViewCircle.setVisibility(View.VISIBLE);

                girlTouchViewCircle.setVisibility(View.GONE);
                girlTouchView.setVisibility(View.VISIBLE);


                mCurrentSize.setText("0.00");
                mSeekBar.setProgress(0);

                mPart2HolderView.setAlpha(100);

                loadPic(boyTouchViewCircle,0);
                break;
            case R.id.girlTouch:
                mCurrent = 0;
                mToolBar.setVisibility(View.GONE);
                mPart2View.setVisibility(View.VISIBLE);
                mPart2HolderView.setBackgroundResource(R.drawable.duijiao_last_frame);
                mHolderView.setBackgroundResource(R.drawable.qingxi);

                boyTouchView.setVisibility(View.VISIBLE);
                boyTouchViewCircle.setVisibility(View.GONE);

                girlTouchView.setVisibility(View.GONE);
                girlTouchViewCircle.setVisibility(View.VISIBLE);

                mCurrentSize.setText("0.00");
                mSeekBar.setProgress(0);

                mPart2HolderView.setAlpha(100);

                loadPic(girlTouchViewCircle,0);
                break;
            case R.id.part2Save2:
            case R.id.part2Save:
                mPart2View.setVisibility(View.GONE);
                mToolBar.setVisibility(View.VISIBLE);
                boyTouchView.setVisibility(View.GONE);
                girlTouchView.setVisibility(View.GONE);
                boyTouchViewCircle.setVisibility(View.GONE);
                girlTouchViewCircle.setVisibility(View.GONE);
                if(mCurrent == 0){
                    mHolderView.setBackgroundResource(R.drawable.girl_save);
                }else {
                    mHolderView.setBackgroundResource(R.drawable.boy_save);
                }
                mPart3View.setVisibility(View.VISIBLE);
                break;
            case R.id.again:
                mHolderView.setBackgroundResource(R.drawable.duijiao_last_frame);
                mPart3View.setVisibility(View.GONE);
                mVideoView.setVisibility(View.VISIBLE);
                mVideoView.start();
                break;
        }
    }

    //加载图片
    private void loadPic(ImageView currentView,int currentImg)
    {
        //定义流，用于加载assets中的图片
        InputStream assetFile = assets.getResourceAsStream("assets/" + pics[pics.length - 1 - currentImg]);
        //把流转化为位图添加到ImageView控件上
        currentView.setImageBitmap(BitmapFactory.decodeStream(assetFile));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mVideoView != null){
            mVideoView.suspend();
        }
    }
}
