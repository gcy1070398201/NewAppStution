package com.example.min.newappstution.updateui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonRecyclerAdapter;
import com.example.min.newappstution.R;
import com.gcy.library.status.StatusUtils;
import com.gcy.library.title.AppTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by min on 2017/9/14.
 */
public class TextAc extends AppCompatActivity {

    @BindView(R.id.AppTitle)
    AppTitleView AppTitle;
    @BindView(R.id.rcy)
    RecyclerView rcy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_ac_layout);
        ButterKnife.bind(this);
        StatusUtils.setFullToStatusBar(this);
        AppTitle.initViewsVisible(false,true,false,false);
        AppTitle.setAppTitle("基本设置");
        rcy.setLayoutManager(new LinearLayoutManager(this));
        rcy.setAdapter(new CommonRecyclerAdapter<String>(this,R.layout.item_up_layout,getInfo()) {
            @Override
            public void onUpdate(BaseAdapterHelper helper, String item, int position) {
                TextView textView=helper.getView(R.id.itemName);
                textView.setText(item);
            }
        });
    }



    private List<String> getInfo(){
        List<String> stringList=new ArrayList<>();
        stringList.add("纯滚动模式");
        stringList.add("下拉模式");
        stringList.add("上拉模式");
        stringList.add("上拉and下拉模式");
        stringList.add("自定义刷新头部");
        stringList.add("自定义刷新底部");
        return stringList;
    }

}
