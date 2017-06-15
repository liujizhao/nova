package com.exper.nova;

import android.os.Build;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.exper.nova.base.BaseActivity;
import com.exper.nova.home.HDSelfFragment;
import com.exper.nova.home.ParamFragment;
import com.exper.nova.util.ToastUtils;
import com.exper.nova.util.Tools;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.splash_img)
    ImageView splashView;

    @BindView(R.id.self_hd_btn)
    ImageView selfHDBtn;

    @BindView(R.id.param_btn)
    ImageView paramBtn;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.nav_view)
    View mNavLayout;

    private long mExitTime = 0;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        ToastUtils.init(this);
        _initDrawerLayout(mDrawerLayout);
    }

    @Override
    protected void updateViews() {
        splashView.postDelayed(new Runnable() {
            @Override
            public void run() {
                splashView.setVisibility(View.GONE);
                selfHDBtn.setVisibility(View.VISIBLE);
                paramBtn.setVisibility(View.VISIBLE);
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        },3000);
    }

    @OnClick({R.id.self_hd_btn,R.id.param_btn,R.id.text_1,R.id.text_2,R.id.text_3,R.id.text_4})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.self_hd_btn:
                replaceFragment(R.id.content_frame, new HDSelfFragment(),"self");
                break;
            case R.id.param_btn:
                replaceFragment(R.id.content_frame, new ParamFragment(),"param");
                break;
        }
    }

    @OnClick({R.id.text_1,R.id.text_2,R.id.text_3,R.id.text_4})
    public void drawerClick(View view){
        view.post(new Runnable() {
            @Override
            public void run() {
                closeDrawer();
            }
        });
        switch (view.getId()){
            case R.id.text_1:
                replaceFragment(R.id.content_frame, new HDSelfFragment(), "self");
                break;
            case R.id.text_2:
                replaceFragment(R.id.content_frame, new ParamFragment(), "param");
                break;
            case R.id.text_3:
                replaceFragment(R.id.content_frame, new HDSelfFragment(), "self");
                break;
            case R.id.text_4:
                replaceFragment(R.id.content_frame, new ParamFragment(), "param");
                break;
        }
    }

    private void _initDrawerLayout(DrawerLayout drawerLayout) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            //将侧边栏顶部延伸至status bar
            drawerLayout.setFitsSystemWindows(true);
            //将主页面顶部延伸至status bar
            drawerLayout.setClipToPadding(false);
        }
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        ViewGroup.LayoutParams layoutParams = mNavLayout.getLayoutParams();
        layoutParams.width = Tools.getWindowWidth(this) / 2;
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
        mDrawerLayout.openDrawer(GravityCompat.END);
    }

    public void closeDrawer(){
        mDrawerLayout.closeDrawer(GravityCompat.END);
    }
}
