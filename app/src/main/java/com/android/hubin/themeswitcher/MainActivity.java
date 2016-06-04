package com.android.hubin.themeswitcher;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.hubin.themeswitcher.R;
import com.android.hubin.themeswitcher.colorUi.util.ColorUiUtil;
import com.android.hubin.themeswitcher.colorUi.util.SharedPreferencesMgr;
import com.android.hubin.themeswitcher.colorUi.widget.ColorButton;


public class MainActivity extends BaseActivity {

    ColorButton btn,btn_next;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (ColorButton)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SharedPreferencesMgr.getInt("theme", 0) == 1) {
                    SharedPreferencesMgr.setInt("theme", 0);
                    setTheme(R.style.theme_1);
                } else {
                    SharedPreferencesMgr.setInt("theme", 1);
                    setTheme(R.style.theme_2);
                }
                final View rootView = getWindow().getDecorView();
                if(Build.VERSION.SDK_INT >= 14) {
                    rootView.setDrawingCacheEnabled(true);
                    rootView.buildDrawingCache(true);
                    final Bitmap localBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
                    rootView.setDrawingCacheEnabled(false);
                    if (null != localBitmap && rootView instanceof ViewGroup) {
                        final View localView2 = new View(getApplicationContext());
                        localView2.setBackgroundDrawable(new BitmapDrawable(getResources(), localBitmap));
                        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        ((ViewGroup) rootView).addView(localView2, params);
                        localView2.animate().alpha(0).setDuration(400).setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                ColorUiUtil.changeTheme(rootView, getTheme());
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                ((ViewGroup) rootView).removeView(localView2);
                                localBitmap.recycle();
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        }).start();
                    }
                } else {
                    ColorUiUtil.changeTheme(rootView, getTheme());
                }
            }
        });
        btn_next = (ColorButton)findViewById(R.id.btn_2);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }
}
