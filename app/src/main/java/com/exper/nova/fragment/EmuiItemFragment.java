package com.exper.nova.fragment;

import android.net.Uri;
import android.widget.ImageView;

import com.exper.nova.R;
import com.exper.nova.base.BaseFragment;
import com.exper.nova.widget.TextureVideoView;

import butterknife.BindView;

/**
 * Created by liujizhao on 2017/6/18.
 */

public class EmuiItemFragment extends BaseFragment {

    @BindView(R.id.holderView)
    ImageView mHolderView;

    @BindView(R.id.videoView)
    TextureVideoView mVideoView;

    private int mDrawableId;
    private int mVideoId;

    public EmuiItemFragment(){

    }

    public EmuiItemFragment(int drawableId,int videoId){
        super();
        mDrawableId = drawableId;
        mVideoId = videoId;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.emui_fragment_item;
    }

    @Override
    protected void initViews() {
        mVideoView.setDataSource(getActivity(),Uri.parse(getVideoPath()));
        mHolderView.setBackgroundResource(mDrawableId);
    }

    public void prepare(final boolean play){
        mVideoView.setListener(new TextureVideoView.MediaPlayerListener() {
            @Override
            public void onVideoPrepared() {
                if(play) {
                    mVideoView.play();
                }
            }

            @Override
            public void onVideoEnd() {
                mVideoView.play();
            }
        });
    }

    public void startVideo(){
        if(mVideoView != null) {
            mVideoView.play();
        }
    }

    public void resetVideo(){
        if(mVideoView != null) {
            mVideoView.stop();
        }
    }

    public void suspends(){
        if(mVideoView != null) {
            mVideoView.stop();
        }
    }

    @Override
    protected void updateViews() {
        prepare(false);
    }

    private String getVideoPath() {
        return "android.resource://" + getActivity().getPackageName() + "/" + mVideoId;
    }
}
