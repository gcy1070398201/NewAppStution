package com.example.min.newappstution.updateui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonRecyclerAdapter;
import com.example.min.newappstution.R;
import com.example.min.newappstution.utile.IntentUtile;
import com.gcy.library.status.StatusUtils;
import com.gcy.library.title.AppTitleView;

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
    CommonRecyclerAdapter commonRecyclerAdapter;
    private List<String>stringList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_ac_layout);
        ButterKnife.bind(this);
        StatusUtils.setFullToStatusBar(this);
        AppTitle.initViewsVisible(false,true,false,false);
        AppTitle.setAppTitle("基本设置");
        rcy.setLayoutManager(new LinearLayoutManager(this));
        stringList=TextInfoMode.getInfo();
        commonRecyclerAdapter=new CommonRecyclerAdapter<String>(this,R.layout.item_up_layout,stringList) {
            @Override
            public void onUpdate(BaseAdapterHelper helper, String item, int position) {
                TextView textView=helper.getView(R.id.itemName);
                textView.setText(item);
            }
        };
        rcy.setAdapter(commonRecyclerAdapter);
        commonRecyclerAdapter.setOnItemClickListener(new CommonRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
                if (stringList.get(position).equals("纯滚动模式")){
                    IntentUtile.goAC(TextAc.this,TextPureScrollAc.class);
                }else if (stringList.get(position).equals("列表滑动到指定的位置")){
                    IntentUtile.goAC(TextAc.this,TextScrToPosAc.class);
                }else if (stringList.get(position).equals("下拉模式")) {
                    IntentUtile.goAC(TextAc.this,TextUpScrollAc.class);
                }else if (stringList.get(position).equals("上拉模式")) {
                    IntentUtile.goAC(TextAc.this,TextDownScrollAc.class);
                }else if (stringList.get(position).equals("上拉and下拉模式")) {
                    IntentUtile.goAC(TextAc.this,TextUpDownScrollAc.class);
                }else{
                        Toast.makeText(TextAc.this,stringList.get(position),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
