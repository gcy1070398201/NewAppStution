package com.gcy.library.title;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gcy.library.R;


/**
 * Created by gcy on 2017/6/15.
 */
public class AppTitleView extends LinearLayout{
    private OnLeftButtonClickListener mLeftButtonClickListener;
    private OnRightButtonClickListener mRightButtonClickListener;
    private MyViewHolder mViewHolder;
    private View viewAppTitle;

    public AppTitleView(Context context) {
        super(context);
        init();
    }
    public AppTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init()
    {
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        viewAppTitle = inflater.inflate(R.layout.title_layout, null);
        this.addView(viewAppTitle, layoutParams);
        mViewHolder = new MyViewHolder(this);
        mViewHolder.llLeftGoBack.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mLeftButtonClickListener != null)
                {
                    mLeftButtonClickListener.onLeftButtonClick(v);
                }
            }
        });
        mViewHolder.llRight.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mRightButtonClickListener != null)
                {
                    mRightButtonClickListener.OnRightButtonClick(v);
                }
            }
        });
    }

    public void initViewsVisible(boolean isLeftButtonVisile, boolean isCenterTitleVisile, boolean isRightIconVisile, boolean isRightTitleVisile)
    {
        // 左侧返回
        mViewHolder.llLeftGoBack.setVisibility(isLeftButtonVisile ? View.VISIBLE : View.INVISIBLE);

        // 中间标题
        mViewHolder.tvCenterTitle.setVisibility(isCenterTitleVisile ? View.VISIBLE : View.INVISIBLE);

        // 右侧返回图标,文字
        if (!isRightIconVisile && !isRightTitleVisile)
        {
            mViewHolder.llRight.setVisibility(View.INVISIBLE);
        }
        else
        {
            mViewHolder.llRight.setVisibility(View.VISIBLE);
        }
        mViewHolder.ivRightComplete.setVisibility(isRightIconVisile ? View.VISIBLE : View.GONE);
        mViewHolder.tvRightComplete.setVisibility(isRightTitleVisile ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setAppTitle(String title)
    {
        if (!TextUtils.isEmpty(title))
        {
            mViewHolder.tvCenterTitle.setText(title);
        }
    }
    public void setAppTitleColorSize(int size,int color)
    {
        if (size!=0)
            mViewHolder.tvCenterTitle.setTextSize(size);
        if (color!=0)
            mViewHolder.tvCenterTitle.setTextColor(color);
    }
    public void setRightTitleColorSize(int size,int color)
    {
            mViewHolder.tvRightComplete.setTextSize(size);
            mViewHolder.tvRightComplete.setTextColor(color);
    }
    public void setRightTitle(String text)
    {
        if (!TextUtils.isEmpty(text))
        {
            mViewHolder.tvRightComplete.setText(text);
        }
    }

    public void setRightIcon(int sourceID)
    {
        mViewHolder.ivRightComplete.setImageResource(sourceID);
    }
    public void setLeftIcon(int sourceID)
    {
        mViewHolder.leftimage.setImageResource(sourceID);
    }

    public void setLeftOnclick(OnLeftButtonClickListener mOnLeftButtonClickListener)
    {
        if (mOnLeftButtonClickListener != null)
        {
        }
    }

    public void setAppBackground(int color)
    {
        viewAppTitle.setBackgroundColor(color);
    }

    public void setOnLeftButtonClickListener(OnLeftButtonClickListener listen)
    {
        mLeftButtonClickListener = listen;
    }

    public void setOnRightButtonClickListener(OnRightButtonClickListener listen)
    {
        mRightButtonClickListener = listen;
    }

    public static abstract interface OnLeftButtonClickListener
    {
        public abstract void onLeftButtonClick(View v);
    }

    public static abstract interface OnRightButtonClickListener
    {
        public abstract void OnRightButtonClick(View v);
    }

    static class MyViewHolder
    {
        LinearLayout llLeftGoBack;
        ImageView leftimage;
        TextView tvCenterTitle;
        LinearLayout llRight;
        ImageView ivRightComplete;
        TextView tvRightComplete;

        public MyViewHolder(View v)
        {
            llLeftGoBack = (LinearLayout) v.findViewById(R.id.llLeftGoBack);
            tvCenterTitle = (TextView) v.findViewById(R.id.tvCenterTitle);
            leftimage= (ImageView) v.findViewById(R.id.leftimage);
            llRight = (LinearLayout) v.findViewById(R.id.llRight);
            ivRightComplete = (ImageView) v.findViewById(R.id.ivRightComplete);
            tvRightComplete = (TextView) v.findViewById(R.id.tvRightComplete);
        }
    }
}
