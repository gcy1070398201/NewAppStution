package com.example.min.newappstution.car;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.min.newappstution.R;
import com.gcy.library.keyboard.HideIUtil;
import com.gcy.library.status.StatusUtils;
import com.gcy.library.title.AppTitleView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by min on 2017/9/13.
 */
public class GoShopCarAc extends AppCompatActivity {
    @BindView(R.id.AppTitle)
    AppTitleView mAppTitleView;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private List<ShopCarData> mList;
    private ShopCarAdapter mShopCarAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_layout);
        HideIUtil.wrap(this);
        ButterKnife.bind(this);
        StatusUtils.setFullToStatusBar(this);
        mAppTitleView.initViewsVisible(false, true, false, false);
        mAppTitleView.setAppTitle("购物车");
        mList = ShopCarData.getShopInfo();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mShopCarAdapter = new ShopCarAdapter(this, mList);
        mRecyclerView.setAdapter(mShopCarAdapter);
    }
}
