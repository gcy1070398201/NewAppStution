package com.example.min.newappstution.updateui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonRecyclerAdapter;
import com.example.min.newappstution.R;
import com.gcy.library.status.StatusUtils;
import com.gcy.library.title.AppTitleView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by min on 2017/9/25.
 * 经典上拉
 */
public class TextDownScrollAc extends AppCompatActivity{

    @BindView(R.id.rcy)
    RecyclerView rcy;
    @BindView(R.id.AppTitle)
    AppTitleView mAppTitleView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private CommonRecyclerAdapter commonRecyclerAdapter;
    private List<String> stringList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.down_src_layout);
        ButterKnife.bind(this);
        StatusUtils.setFullToStatusBar(this);
        mAppTitleView.initViewsVisible(false,true,false,false);
        mAppTitleView.setAppTitle("经典上拉模式");
        stringList=TextInfoMode.getInfoListView();
        rcy.setLayoutManager(new LinearLayoutManager(this));
        setInfo(stringList);

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commonRecyclerAdapter.addAll(TextInfoMode.getInfoListView());
                        refreshLayout.finishLoadmore();
                        refreshLayout.setLoadmoreFinished(true);
                    }
                },1500);
            }
        });

    }
    private void setInfo(@Nullable List<String> stringList){
        commonRecyclerAdapter=new CommonRecyclerAdapter<String>(TextDownScrollAc.this,R.layout.item_up_layout,
                stringList) {
            @Override
            public void onUpdate(BaseAdapterHelper helper, String item, int position) {
                TextView textView=helper.getView(R.id.itemName);
                textView.setText(item);
            }
        };
        rcy.setAdapter(commonRecyclerAdapter);
    }

}
