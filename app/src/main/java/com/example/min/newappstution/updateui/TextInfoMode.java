package com.example.min.newappstution.updateui;

import com.example.min.newappstution.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by min on 2017/9/15.
 */
public class TextInfoMode {
    public static List<String> getInfo(){
        List<String> stringList=new ArrayList<>();
        stringList.add("纯滚动模式");
        stringList.add("下拉模式");
        stringList.add("上拉模式");
        stringList.add("上拉and下拉模式");
        stringList.add("自定义刷新头部");
        stringList.add("自定义刷新底部");
        stringList.add("列表滑动到指定的位置");
        return stringList;
    }
    public static List<String> getInfoListView(){
        List<String> stringList=new ArrayList<>();
        for (int i=0;i<15;i++){
            stringList.add(""+R.string.app_name);
        }
        return stringList;
    }

    public static List<String> getInfoOneListView(){
        List<String> stringList=new ArrayList<>();
        for (int i=0;i<15;i++){
            stringList.add("上面"+R.string.app_name+i+"楼");
        }
        return stringList;
    }
    public static List<String> getInfoTwoListView(){
        List<String> stringList=new ArrayList<>();
        for (int i=0;i<15;i++){
            stringList.add("下面"+R.string.app_name+i+"楼");
        }
        return stringList;
    }
}
