package com.exper.nova.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.exper.nova.R;

/**
 * Created by Rory on 2017/6/15.
 */

public class RoToolsBar extends RelativeLayout implements View.OnClickListener{

    Context mContext;

    ImageView mBackBtn;
    ImageView mMenuBtn;
    ImageView mLogoBtn;

    public RoToolsBar(Context context) {
        this(context , null);
    }

    public RoToolsBar(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public RoToolsBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initViews();
    }

    private void initViews() {
        LayoutInflater.from(mContext).inflate(R.layout.toolsbar, this);
        mBackBtn = (ImageView) findViewById(R.id.btn_back);
        mMenuBtn = (ImageView) findViewById(R.id.btn_menu);
        mLogoBtn = (ImageView) findViewById(R.id.btn_logo);

        mBackBtn.setOnClickListener(this);
        mLogoBtn.setOnClickListener(this);
        mMenuBtn.setOnClickListener(this);
    }

    public void setBackImageVisible(int visible) {
        mBackBtn.setVisibility(visible);
    }

    public void setLogo(int id) {
        mLogoBtn.setImageResource(id);
    }

    public void setMenu(int id) {
        mMenuBtn.setImageResource(id);
    }

    public void setLogoImageVisible(int visible) {
        mLogoBtn.setVisibility(visible);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                listener.onBackPress();
                break;
            case R.id.btn_menu:
                listener.onMenuPress();
                break;
            case R.id.btn_logo:
                listener.onLogoPress();
                break;
        }
    }

    public void setOnBtnClickListener(onBtnClickListener listener) {
        this.listener = listener;
    }

    onBtnClickListener listener;

    public interface onBtnClickListener{
        void onBackPress();

        void onMenuPress();

        void onLogoPress();
    }

}
