package com.example.min.newappstution.utile;

import android.content.Context;
import android.content.Intent;

/**
 * Created by min on 2017/9/12.
 */
public class IntentUtile {
    public static void goAC(Context context,Class<?>cl){
        Intent intent=new Intent(context,cl);
        context.startActivity(intent);
    }
}
