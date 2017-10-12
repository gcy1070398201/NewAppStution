package com.example.min.newappstution;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by min on 2017/9/26.
 */
public abstract class BaseAc extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData(getIntent().getExtras());
    }

    protected abstract void initView();
    protected abstract void initData(@Nullable Bundle bundle);

}
