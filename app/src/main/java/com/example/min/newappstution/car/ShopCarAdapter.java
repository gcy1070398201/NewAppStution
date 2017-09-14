package com.example.min.newappstution.car;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.min.newappstution.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by min on 2017/9/13.
 */
public class ShopCarAdapter extends RecyclerView.Adapter<ShopCarAdapter.RecyclerViewHood> {
    private Context context;
    private List<ShopCarData> mList;
    private LayoutInflater mLayoutInflater;
    public ShopCarAdapter(Context context,List<ShopCarData> mList) {
        this.context=context;
        this.mList=mList;
        this.mLayoutInflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHood onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHood m=new RecyclerViewHood(mLayoutInflater.inflate(R.layout.item_layout,parent,false));
        return m;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHood holder, int position) {
        ShopCarData mShopCarData=mList.get(position);
        holder.tvName.setText(mShopCarData.getShopName());
        holder.edNum.setText(mShopCarData.getNum()+"");
        holder.remove.setTag(mShopCarData);
        holder.add.setTag(mShopCarData);
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,((ShopCarData)view.getTag()).getShopName(),
                        Toast.LENGTH_LONG).show();
            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,((ShopCarData)view.getTag()).getShopName(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class RecyclerViewHood extends RecyclerView.ViewHolder{
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.remove)
        ImageView remove;
        @BindView(R.id.edNum)
        EditText edNum;
        @BindView(R.id.add)
        ImageView add;

        public RecyclerViewHood(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
