package com.exper.nova.fragment;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.exper.nova.MainActivity;
import com.exper.nova.R;
import com.exper.nova.base.BaseFragment;
import com.exper.nova.widget.TextureVideoView;

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
    ImageView boyTouchView;

    @BindView(R.id.girlTouch)
    ImageView girlTouchView;

    @BindView(R.id.part2)
    View mPart2View;

    @BindView(R.id.holderView2)
    ImageView mPart2HolderView;

    @BindView(R.id.part3View)
    View mPart3View;

    private int mCurrent;

    @Override
    protected int attachLayoutRes() {
        return R.layout.duijiao_fragment;
    }

    @Override
    protected void initViews() {
        mVideoView.setDataSource(getActivity(), Uri.parse(getVideoPath()));
        mVideoView.setListener(new TextureVideoView.MediaPlayerListener() {
            @Override
            public void onVideoPrepared() {

            }

            @Override
            public void onVideoEnd() {
                mHolderView.setBackgroundResource(R.drawable.duijiao_last_frame);
                mVideoBoard.setVisibility(View.VISIBLE);
                mToolBar.setVisibility(View.GONE);
                mVideoView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void updateViews() {
        mVideoView.play();
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
                girlTouchView.setVisibility(View.VISIBLE);
                break;
            case R.id.boyTouch:
                mCurrent = 1;
                mToolBar.setVisibility(View.GONE);
                mPart2View.setVisibility(View.VISIBLE);
                mPart2HolderView.setBackgroundResource(R.drawable.boy);
                mHolderView.setBackgroundResource(R.drawable.qingxi);
                break;
            case R.id.girlTouch:
                mCurrent = 0;
                mToolBar.setVisibility(View.GONE);
                mPart2View.setVisibility(View.VISIBLE);
                mPart2HolderView.setBackgroundResource(R.drawable.duijiao_last_frame);
                mHolderView.setBackgroundResource(R.drawable.qingxi);
                break;
            case R.id.part2Save2:
            case R.id.part2Save:
                mPart2View.setVisibility(View.GONE);
                mToolBar.setVisibility(View.VISIBLE);
                boyTouchView.setVisibility(View.GONE);
                girlTouchView.setVisibility(View.GONE);
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
                mVideoView.play();
                break;
        }
    }
}
