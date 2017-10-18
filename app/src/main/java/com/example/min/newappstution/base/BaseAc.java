package com.example.min.newappstution.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.min.newappstution.R;
import com.gcy.library.title.AppTitleView;

import butterknife.ButterKnife;

/**
 * Created by min on 2017/10/12.
 */
public abstract class BaseAc extends AppCompatActivity implements View.OnClickListener,
        AppTitleView.OnLeftButtonClickListener,AppTitleView.OnRightButtonClickListener {
    public Context context;
    private View mAppTitleView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        isShowTitle();
        getContentViewLayout(setViewLayout());
        ButterKnife.bind(this);
        initView();
        initData(getIntent());
    }
    /**
     * 判断是否显示title布局
     * 设置是否显示导航栏，默认显示，不需要则复写方法
     * @param viewID
     */
    private void getContentViewLayout(@Nullable int viewID) {
        //false 不显示 true 显示
        View view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.base_layout, null);
        LinearLayout lyTitleView=view.findViewById(R.id.ly_title);
        mAppTitleView=view.findViewById(R.id.AppTitle);
        if (isShowTitle()){
            lyTitleView.setVisibility(View.VISIBLE);
        }else{
            lyTitleView.setVisibility(View.INVISIBLE);
        }
        LinearLayout linearLayout=view.findViewById(R.id.addView);
        linearLayout.addView(LayoutInflater.from(context).inflate(viewID,null));
        setContentView(view);
    }

    /**
     * 获取Title 对象设置值
     * @return
     */
    public AppTitleView getTitleView() {
        return (AppTitleView) mAppTitleView;
    }
    /**
     * 是否显示title  false 不显示 true 显示
     * @return
     */
    public abstract boolean isShowTitle();

    /**
     * 组件初始化
     */
    public abstract void initView();

    /**
     * 赋值
     */
    public abstract void initData(@Nullable Intent getIntent);
    /**
     * 布局 ID
     * @return
     */
    public abstract int setViewLayout();


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onLeftButtonClick(View v) {

    }

    @Override
    public void OnRightButtonClick(View v) {

    }
}
