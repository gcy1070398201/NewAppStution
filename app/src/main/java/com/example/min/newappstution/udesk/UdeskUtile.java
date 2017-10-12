package com.example.min.newappstution.udesk;

import android.content.Context;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import cn.jpush.android.api.JPushInterface;
import cn.udesk.PreferenceHelper;
import cn.udesk.UdeskConst;
import cn.udesk.UdeskSDKManager;
import cn.udesk.model.UdeskCommodityItem;

/**
 * Created by min on 2017/9/19.
 */
public class UdeskUtile {
    //替换成你们注册生成的域名
    public static String UDESK_DOMAIN = "fafa0815.udesk.cn";
    //替换成你们生成应用产生的appid
    public static String AppId = "ce62227f1e457419";
    // 替换成你们在后台生成的密钥
    public static String UDESK_SECRETKEY = "6866fb3fcd9dedbbcfb4cc4c8753151d";

    /**
     * 设置用户信息
     * @param infoMode 用户信息组合
     */
    public static void setUserInfo(Context context,UserInfoMode infoMode){
        UdeskSDKManager.getInstance().initApiKey(context.getApplicationContext(),UDESK_DOMAIN,UDESK_SECRETKEY,AppId);
        String sdkToken="";
        sdkToken=PreferenceHelper.readString(context, "init_base_name", "sdktoken");
        if (TextUtils.isEmpty(sdkToken)) {
            sdkToken = UUID.randomUUID().toString();
        }
        Map<String, String> info = new HashMap<String, String>();
        info.put(UdeskConst.UdeskUserInfo.USER_SDK_TOKEN, sdkToken);
        info.put(UdeskConst.UdeskUserInfo.NICK_NAME, infoMode.getName());
        info.put(UdeskConst.UdeskUserInfo.CELLPHONE,infoMode.getPhone());
        info.put(UdeskConst.UdeskUserInfo.DESCRIPTION,infoMode.getDESCRIPTION());

        PreferenceHelper.write(context,"init_base_name","sdktoken",  sdkToken);
        UdeskSDKManager.getInstance().setUserInfo(context.getApplicationContext(), sdkToken, info);
        String rid = JPushInterface.getRegistrationID(context.getApplicationContext());
        UdeskSDKManager.getInstance().setRegisterId(context,rid);
        UdeskSDKManager.getInstance().entryChat(context);
    }
    /**
     * 发送商品信息
     * @param item 商品信息组合
     */
    public static void createCommodity(Context context, UdeskCommodityItem item) {
        UdeskSDKManager.getInstance().setCommodity(item);
        UdeskSDKManager.getInstance().entryChat(context);
    }

    public static void createCommodityText(Context context, String item){

        UdeskSDKManager.getInstance().setCommodityText(item);
        UdeskSDKManager.getInstance().entryChat(context);
    }
}
