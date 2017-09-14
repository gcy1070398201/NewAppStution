package com.example.min.newappstution.car;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by min on 2017/9/13.
 */
public class ShopCarData implements Serializable {
    private String shopName;
    private int num=0;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ShopCarData(String shopName, int num) {
        this.shopName = shopName;
        this.num = num;
    }

    public static List<ShopCarData> getShopInfo(){
        List<ShopCarData> info=new ArrayList<>();
        for (int i=0;i<15;i++){
            info.add(new ShopCarData("text试试"+i,i));
        }
        return info;
    }
}
