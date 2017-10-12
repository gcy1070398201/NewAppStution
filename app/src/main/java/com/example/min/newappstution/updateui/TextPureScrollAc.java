package com.example.min.newappstution.updateui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.min.newappstution.R;
import com.gcy.library.status.StatusUtils;
import com.gcy.library.title.AppTitleView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by min on 2017/9/15.
 * RecyclerView 纯滚动模式
 */
public class TextPureScrollAc extends AppCompatActivity{
    @BindView(R.id.AppTitle)
    AppTitleView mAppTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pure_src_layout);
        ButterKnife.bind(this);
        StatusUtils.setFullToStatusBar(this);
        mAppTitle.initViewsVisible(false,true,false,false);
        mAppTitle.setAppTitle("纯滚动模式");
    }
}
