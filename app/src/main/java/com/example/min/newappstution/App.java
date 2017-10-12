package com.example.min.newappstution;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import cn.jpush.android.api.JPushInterface;
import cn.udesk.config.UdeskConfig;

/**
 * Created by min on 2017/9/19.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
        UdeskConfig.udeskTitlebarBgResId = R.color.udesk_titlebar_bg2;
        UdeskConfig.udeskTitlebarTextLeftRightResId = R.color.udesk_color_navi_text2;
        UdeskConfig.udeskIMRightTextColorResId = R.color.udesk_color_im_text_right2;
        UdeskConfig.udeskIMLeftTextColorResId = R.color.udesk_color_im_text_left2;
        UdeskConfig.udeskIMAgentNickNameColorResId = R.color.udesk_color_im_left_nickname2;
        UdeskConfig.udeskIMTimeTextColorResId = R.color.udesk_color_im_time_text2;
        UdeskConfig.udeskIMTipTextColorResId = R.color.udesk_color_im_tip_text2;
        UdeskConfig.udeskbackArrowIconResId = R.drawable.udesk_titlebar_back;

        UdeskConfig.udeskCommityBgResId = R.color.udesk_color_im_commondity_bg2;
        UdeskConfig.udeskCommityTitleColorResId = R.color.udesk_color_im_commondity_title2;
        UdeskConfig.udeskCommitysubtitleColorResId = R.color.udesk_color_im_commondity_subtitle2;
        UdeskConfig.udeskCommityLinkColorResId = R.color.udesk_color_im_commondity_title2;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
