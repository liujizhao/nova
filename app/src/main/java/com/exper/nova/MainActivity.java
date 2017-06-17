package com.exper.nova;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.exper.nova.base.BaseActivity;
import com.exper.nova.zipai.HDSelfFragment;
import com.exper.nova.home.ParamFragment;
import com.exper.nova.util.ToastUtils;
import com.exper.nova.widget.RoToolsBar;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements RoToolsBar.onBtnClickListener {

    @BindView(R.id.splash_img)
    ImageView splashView;

    @BindView(R.id.self_hd_btn)
    ImageView selfHDBtn;

    @BindView(R.id.home_double_curl)
    ImageView mHomeDouble;

    @BindView(R.id.home_fashion)
    ImageView mHomeFashion;

    @BindView(R.id.home_emui)
    ImageView mHomeEmui;

    @BindView(R.id.ro_tools_bar)
    RoToolsBar mRoToolsBar;

    @BindView(R.id.logo_image)
    ImageView mLogoImageTxt;

    @BindView(R.id.param_btn)
    ImageView paramBtn;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.nav_view)
    View mNavLayout;

    @BindView(R.id.home_sub_menu_layout)
    LinearLayout mSubMenuLayout;

    @BindView(R.id.animation_view_1)
    ImageView animationView1;

    @BindView(R.id.animation_view_2)
    ImageView animationView2;

    @BindView(R.id.animation_view_3)
    ImageView animationView3;

    @BindView(R.id.animation_view_4)
    ImageView animationView4;

    @BindView(R.id.mainze_view)
    ImageView mianZeView;

    @BindView(R.id.btn_layout)
    View btnLayout;

    private long mExitTime = 0;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 10000:
                    Animation animation4 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_1);
                    animation4.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            mHandler.sendEmptyMessageDelayed(10001,200);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    animationView4.setAlpha(1.0f);
                    animationView4.startAnimation(animation4);
                    break;
                case 10001:
                    Animation animation3 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_1);
                    animation3.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            mHandler.sendEmptyMessageDelayed(10002,200);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    animationView3.setAlpha(1.0f);
                    animationView3.startAnimation(animation3);
                    break;
                case 10002:
                    Animation animation2 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_1);
                    animation2.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            mHandler.sendEmptyMessageDelayed(10003,100);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    animationView2.setAlpha(1.0f);
                    animationView2.startAnimation(animation2);
                    break;
                case 10003:
                    Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_2);
                    animationView1.setAlpha(1.0f);
                    animation1.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Animation animation12 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alpha_anim);
                            selfHDBtn.setAlpha(1.0f);
                            selfHDBtn.startAnimation(animation12);

                            mHomeDouble.setAlpha(1.0f);
                            mHomeDouble.startAnimation(animation12);

                            mHomeFashion.setAlpha(1.0f);
                            mHomeFashion.startAnimation(animation12);

                            mHomeEmui.setAlpha(1.0f);
                            mHomeEmui.startAnimation(animation12);

                            mRoToolsBar.setAlpha(1.0f);
                            mRoToolsBar.startAnimation(animation12);

                            mLogoImageTxt.setAlpha(1.0f);
                            mLogoImageTxt.startAnimation(animation12);

                            paramBtn.setAlpha(1.0f);
                            paramBtn.startAnimation(animation12);

                            mianZeView.setAlpha(1.0f);
                            mianZeView.startAnimation(animation12);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    animationView1.startAnimation(animation1);
                    break;
            }
        }
    };

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        ToastUtils.init(this);
        ViewGroup.LayoutParams layoutParams = mNavLayout.getLayoutParams();
        layoutParams.width = 779;
    }


    @Override
    protected void onResume() {
        super.onResume();
        mRoToolsBar.setOnBtnClickListener(this);
        mRoToolsBar.setLogo(R.drawable.home_title_logo);
        mRoToolsBar.setMenu(R.drawable.home_title_menu);
        mRoToolsBar.setBackImageVisible(View.GONE);
        mRoToolsBar.setLogoImageVisible(View.VISIBLE);
    }

    @Override
    protected void updateViews() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                splashView.setVisibility(View.GONE);
                mHandler.sendEmptyMessage(10000);
            }
        },3000);
    }

    @OnClick({R.id.self_hd_btn,R.id.param_btn , R.id.home_double_curl})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.self_hd_btn:
                replaceFragment(R.id.content_frame, new HDSelfFragment(),"self");
                break;
            case R.id.param_btn:
                replaceFragment(R.id.content_frame, new ParamFragment(),"param");
                break;
            case R.id.home_double_curl:
                showSubMenu();
                break;
        }
    }

    boolean isSubMenuShown = false;

    private void showSubMenu() {
        if(!isSubMenuShown) {


            selfHDBtn.setVisibility(View.INVISIBLE);
            mHomeEmui.setVisibility(View.INVISIBLE);
            mHomeFashion.setVisibility(View.INVISIBLE);

            selfHDBtn.setAlpha(0.0f);
            mHomeEmui.setAlpha(0.0f);
            mHomeFashion.setAlpha(0.0f);

            mSubMenuLayout.setVisibility(View.VISIBLE);
            isSubMenuShown = true;
        }else{
            selfHDBtn.setVisibility(View.VISIBLE);
            mHomeEmui.setVisibility(View.VISIBLE);
            mHomeFashion.setVisibility(View.VISIBLE);

            selfHDBtn.setAlpha(1.0f);
            mHomeEmui.setAlpha(1.0f);
            mHomeFashion.setAlpha(1.0f);

            mSubMenuLayout.setVisibility(View.GONE);
            isSubMenuShown = false;
        }
    }

    @OnClick({R.id.menu_item_1,R.id.menu_item_2,R.id.menu_item_3,R.id.menu_item_4,R.id.menu_item_5,R.id.menu_close})
    public void drawerClick(View view){
        view.post(new Runnable() {
            @Override
            public void run() {
                closeDrawer();
            }
        });
        switch (view.getId()){
            case R.id.menu_item_1:
                replaceFragment(R.id.content_frame, new HDSelfFragment(), "self");
                break;
            case R.id.menu_item_2:
                ToastUtils.showToast("光学变焦");
                break;
            case R.id.menu_item_3:
                ToastUtils.showToast("先拍照 后对焦");
                break;
            case R.id.menu_item_4:
                ToastUtils.showToast("时尚设计");
                break;
            case R.id.menu_item_5:
                ToastUtils.showToast("特色功能");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        // 获取堆栈里有几个
        final int stackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
            mDrawerLayout.closeDrawer(GravityCompat.END);
        } else if (stackEntryCount == 0) {
            // 如果剩一个说明在主页，提示按两次退出app
            _exit();
        }else{
            super.onBackPressed();
        }
    }

    private void _exit() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    public void openDrawer(){
        if(!mDrawerLayout.isDrawerOpen(GravityCompat.END))
            mDrawerLayout.openDrawer(GravityCompat.END);
    }

    public void closeDrawer(){
        if(mDrawerLayout.isDrawerOpen(GravityCompat.END))
            mDrawerLayout.closeDrawer(GravityCompat.END);
    }

    @Override
    public void onBackPress() {

    }

    @Override
    public void onMenuPress() {
        openDrawer();
    }

    @Override
    public void onLogoPress() {

    }
}
