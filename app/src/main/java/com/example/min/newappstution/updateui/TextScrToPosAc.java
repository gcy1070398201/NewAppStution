package com.example.min.newappstution.updateui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonRecyclerAdapter;
import com.example.min.newappstution.R;
import com.gcy.library.status.StatusUtils;
import com.gcy.library.title.AppTitleView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by min on 2017/9/21.
 */
public class TextScrToPosAc extends AppCompatActivity {
    @BindView(R.id.rcy)
    RecyclerView rcy;
    @BindView(R.id.AppTitle)
    AppTitleView mAppTitleView;
    private CommonRecyclerAdapter commonRecyclerAdapter;
    private CommonRecyclerAdapter commonRecyclerAdapter2;
    private List<String> stringList;
    private List<String> stringList2;
    private boolean move = false;
    LinearLayoutManager linearLayoutManager;
    LinearLayoutManager linearLayoutManager2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.up_src_pos_layout);
        ButterKnife.bind(this);
        StatusUtils.setFullToStatusBar(this);
        mAppTitleView.initViewsVisible(false,true,false,false);
        mAppTitleView.setAppTitle("列表滑动到指定的位置");
        initRecyclerView();

    }

    private void initRecyclerView() {
        stringList=TextInfoMode.getInfoOneListView();
        stringList2=TextInfoMode.getInfoTwoListView();
        linearLayoutManager=new LinearLayoutManager(TextScrToPosAc.this);
        rcy.setLayoutManager(linearLayoutManager);
        commonRecyclerAdapter=new CommonRecyclerAdapter<String>(TextScrToPosAc.this,R.layout.item_up_layout,
                stringList) {
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
                move(position);
            }
        });
        moveToPosition(linearLayoutManager,rcy,2);
    }
    private void smoothMoveToPosition(LinearLayoutManager mLinearLayoutManager,RecyclerView mRecyclerView,int n) {

        int firstItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mLinearLayoutManager.findLastVisibleItemPosition();
        if (n <= firstItem ){
            mRecyclerView.smoothScrollToPosition(n);
        }else if ( n <= lastItem ){
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        }else{
            mRecyclerView.smoothScrollToPosition(n);
        }

    }

    private void moveToPosition(LinearLayoutManager mLinearLayoutManager,RecyclerView mRecyclerView,int n) {

        int firstItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mLinearLayoutManager.findLastVisibleItemPosition();
        if (n <= firstItem ){
            mRecyclerView.scrollToPosition(n);
        }else if ( n <= lastItem ){
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        }else{
            mRecyclerView.scrollToPosition(n);
        }
    }
    private void move(int n){
        rcy.stopScroll();
        smoothMoveToPosition(linearLayoutManager,rcy,n);
    }
}
