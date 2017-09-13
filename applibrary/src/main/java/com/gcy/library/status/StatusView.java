package com.gcy.library.status;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;

import com.gcy.library.R;

import java.lang.reflect.Field;

/**
 * Created by gcy on 2017/8/1.
 */
public class StatusView extends View {

    private static boolean isInitialize = false;
    private static int statusBarHeight = 0;
    private static int screenWidth = 0;

    private static void initialize(View view) {
        if (isInitialize) return;
        isInitialize = true;

        Context context = view.getContext();
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;

        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Field field = clazz.getField("status_bar_height");
            int x = Integer.parseInt(field.get(clazz.newInstance()).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Throwable e) {
            Rect outRect = new Rect();
            View decorView = getParentView(view);
            decorView.getWindowVisibleDisplayFrame(outRect);
            statusBarHeight = outRect.top;
        }
    }

    private static View getParentView(View view) {
        ViewParent viewParent = view.getParent();
        if (viewParent != null) {
            return getParentView((View) viewParent);
        }
        return view;
    }

    public StatusView(Context context) {
        this(context, null, 0);
    }

    public StatusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initialize(this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StatusView);
        int fitsViewId = typedArray.getInt(R.styleable.StatusView_fitsView, -1);
        typedArray.recycle();

        if (fitsViewId != -1) {
            View parent = getParentView(this);
            View fitsView = parent.findViewById(fitsViewId);

            if (fitsView != null &&
                    Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP &&
                    Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH)
                fitsView.setFitsSystemWindows(true);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setMeasuredDimension(screenWidth, statusBarHeight);
        } else {
            setMeasuredDimension(0, 0);
            setVisibility(View.GONE);
        }
    }
}
