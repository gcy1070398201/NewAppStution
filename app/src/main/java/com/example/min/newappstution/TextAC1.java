package com.example.min.newappstution;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.gcy.library.status.StatusUtils;
import com.gcy.library.status.StatusView;
import com.gcy.library.title.AppTitleView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by min on 2017/9/12.
 */
public class TextAC1 extends AppCompatActivity {
    @BindView(R.id.statusView)
    StatusView statusView;
    @BindView(R.id.nslView)
    NestedScrollView nslView;
    @BindView(R.id.header)
    View header;
//    @BindView(R.id.ly_title)
//    LinearLayout ly_title;
    @BindView(R.id.AppTitle)
    AppTitleView mAppTitleView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_1_layout);
        ButterKnife.bind(this);
//        if (StatusUtils.setStatusBarDarkFont(this, true)){
//            statusView.setBackgroundColor(Color.GREEN);
//        }else{
//            statusView.setBackgroundColor(Color.BLACK);
//        }
//        StatusUtils.setFullToStatusBar(this);  // StatusBar
        StatusUtils.setFullToStatusBar(this);
        mAppTitleView.initViewsVisible(true,true,false,false);
        mAppTitleView.setLeftIcon(R.mipmap.back);
        nslView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.e("y","Y_  "+scrollY);
                int height=header.getHeight();
                int scrollDistance = Math.min(scrollY, height);
                int statusAlpha = (int) ((float) scrollDistance / (float) height * 255F);
                setAnyBarAlpha(statusAlpha);
            }
        });
        setAnyBarAlpha(0);
    }
    private void setAnyBarAlpha(int alpha) {
        statusView.getBackground().mutate().setAlpha(alpha);
//        ly_title.getBackground().mutate().setAlpha(alpha);
    }
}
