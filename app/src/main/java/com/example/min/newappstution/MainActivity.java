package com.example.min.newappstution;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.min.newappstution.car.GoShopCarAc;
import com.example.min.newappstution.udesk.UdeskUtile;
import com.example.min.newappstution.udesk.UserInfoMode;
import com.example.min.newappstution.updateui.TextAc;
import com.example.min.newappstution.utile.IntentUtile;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.udesk.model.UdeskCommodityItem;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.go_T1)
    TextView go_T1;
    @BindView(R.id.go_ShopCar)
    TextView go_ShopCar;
    @BindView(R.id.go_UpdateAc)
    TextView go_UpdateAc;
    @BindView(R.id.openCustomerService)
    TextView openCustomerService;
    @BindView(R.id.seedShopInfo)
    TextView seedShopInfo;
    @BindView(R.id.seedShopTEXT)
    TextView seedShopTEXT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.go_T1,R.id.go_ShopCar,R.id.go_UpdateAc,R.id.openCustomerService,R.id.seedShopInfo,R.id.seedShopTEXT})
    public void viewOnClick(View view){
        switch (view.getId()){
            case R.id.go_T1:
                IntentUtile.goAC(MainActivity.this,TextAC1.class);
                break;
            case R.id.go_ShopCar:
                IntentUtile.goAC(MainActivity.this,GoShopCarAc.class);
                break;
            case R.id.go_UpdateAc:
                IntentUtile.goAC(MainActivity.this,TextAc.class);
                break;
            //客服单聊
            case R.id.openCustomerService:
                UserInfoMode infoMode=new UserInfoMode();
                infoMode.setName("张三丰");
                infoMode.setPhone("13501267714");
                infoMode.setDESCRIPTION("测试数据");
                UdeskUtile.setUserInfo(MainActivity.this,infoMode);
                break;
            //向客服发送商品链接
            case R.id.seedShopInfo:
                UdeskCommodityItem item = new UdeskCommodityItem();
                item.setTitle("木林森男鞋新款2016夏季透气网鞋男士休闲鞋网面韩版懒人蹬潮鞋子");// 商品主标题
                item.setSubTitle("¥ 99.00");//商品副标题
                item.setThumbHttpUrl("https://img.alicdn.com/imgextra/i1/1728293990/TB2ngm0qFXXXXcOXXXXXXXXXXXX_!!1728293990.jpg_430x430q90.jpg");// 左侧图片
                item.setCommodityUrl("https://detail.tmall.com/item.htm?spm=a1z10.3746-b.w4946-14396547293.1.4PUcgZ&id=529634221064&sku_properties=-1:-1");// 商品网络链接
                UdeskUtile.createCommodity(MainActivity.this,item);
                break;
            //向客服发送商品链接
            case R.id.seedShopTEXT:
                UdeskUtile.createCommodityText(MainActivity.this,"1111111111");
                break;
        }
    }


}
