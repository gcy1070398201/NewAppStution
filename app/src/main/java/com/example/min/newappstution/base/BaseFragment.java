package com.example.min.newappstution.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/12.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    /**
     * 贴附的activity
     */
    protected FragmentActivity mActivity;
    /**
     * 根view
     */
    protected View mRootView;
    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;
    /**
     * 是否加载完成
     * 当执行完oncreatview,View的初始化方法后方法后即为true
     */
    protected boolean mIsPrepare;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity=getActivity();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutResId = setLayoutResouceId();
        if (layoutResId>0){
            mRootView = inflater.inflate(setLayoutResouceId(), container, false);
            //解决点击穿透问题
            mRootView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
        }

        initData(getArguments());
        ButterKnife.bind(mRootView);
        initView();
        mIsPrepare = true;
        onLazyLoad();
        setListener();
        return mRootView;
    }
    /**
     * 初始化数据
     * @param arguments 接收到的从其他地方传递过来的参数
     */
    protected abstract void initData(Bundle arguments);
    /**
     * 初始化View
     */
    protected abstract void initView();
    /**
     * 设置监听事件
     * @author 漆可
     * @date 2016-5-26 下午3:59:36
     */
    protected void setListener()
    {

    }
    /**
     * 懒加载，仅当用户可见切view初始化结束后才会执行
     */
    protected void onLazyLoad()
    {

    }
    /**
     * 设置根布局资源id
     * @return
     */
    protected abstract int setLayoutResouceId();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);

        this.mIsVisible = isVisibleToUser;

        if (isVisibleToUser)
        {
            onVisibleToUser();
        }
    }
    /**
     * 用户可见时执行的操作
     * @author 漆可
     * @date 2016-5-26 下午4:09:39
     */
    protected void onVisibleToUser()
    {
        if (mIsPrepare && mIsVisible)
        {
            onLazyLoad();
        }
    }


    @Override
    public void onClick(View view) {

    }
}
