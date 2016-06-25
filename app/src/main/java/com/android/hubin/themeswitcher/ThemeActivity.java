package com.android.hubin.themeswitcher;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.android.hubin.themeswitcher.colorUi.util.ColorUiUtil;
import com.android.hubin.themeswitcher.colorUi.util.SharedPreferencesMgr;
import com.android.hubin.themeswitcher.colorUi.widget.ColorButton;
import com.android.hubin.themeswitcher.colorUi.widget.ColorListView;

import java.util.ArrayList;


public class ThemeActivity extends BaseActivity {

    ColorButton btn;
    ColorListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        listView = (ColorListView) findViewById(R.id.list_view);
        ArrayList data = new ArrayList();
        for (int i = 0; i < 10; i++) {
            data.add("next page" + i);
        }
        ThemeAdapter adapter = new ThemeAdapter(this, data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(ThemeActivity.this, SecondActivity.class));
            }
        });
        btn = (ColorButton) findViewById(R.id.btn_switch);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SharedPreferencesMgr.getInt("theme", 0) == 1) {
                    SharedPreferencesMgr.setInt("theme", 0);
                    setTheme(R.style.theme_1);
                } else {
                    SharedPreferencesMgr.setInt("theme", 1);
                    setTheme(R.style.theme_2);
                }
                final View rootView = getWindow().getDecorView();
                if (Build.VERSION.SDK_INT >= 14) {
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
    }
}
