package com.example.min.newappstution.utile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by min on 2017/9/12.
 */
public class IntentUtile {
    public static void goAC(Context context,Class<?>cl){
        Intent intent=new Intent(context,cl);
        context.startActivity(intent);
    }
    public static void goToActivity(Context context,Class<?> clazz){
        Intent intent=new Intent();
        intent.setClass(context, clazz);
        context.startActivity(intent);
    }
    public static void goToActivity(Context context,Class<?> clazz,Bundle bundle){
        Intent intent=new Intent();
        intent.setClass(context, clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    /**
     * 启动一个activity
     * @param cls     目标类
     */
    public static void goToActivity(Context context,Class<?> cls, String key, Object data) {
        Intent intent = new Intent();
        String type = data.getClass().getSimpleName();
        if ("Integer".equals(type)) {
            intent.putExtra(key, (Integer) data);
        } else if ("Boolean".equals(type)) {
            intent.putExtra(key, (Boolean) data);
        } else if ("String".equals(type)) {
            intent.putExtra(key, (String) data);
        } else if ("Float".equals(type)) {
            intent.putExtra(key, (Float) data);
        } else if ("Long".equals(type)) {
            intent.putExtra(key, (Long) data);
        }
        intent.setClass(context,cls);
        context.startActivity(intent);
    }
}
