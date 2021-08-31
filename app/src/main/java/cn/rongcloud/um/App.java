package cn.rongcloud.um;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;


import com.umeng.commonsdk.UMConfigure;

import org.android.agoo.huawei.HuaWeiRegister;
import org.android.agoo.xiaomi.MiPushRegistar;

import io.rong.imkit.RongIM;
import io.rong.push.RongPushClient;
import io.rong.push.pushconfig.PushConfig;

public class App extends MultiDexApplication {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public void onCreate() {
        super.onCreate();

//        友盟推送初始化
        //日志开关
        UMConfigure.setLogEnabled(true);
        //预初始化
        PushHelper.preInit(this);
        //初始化
        PushHelper.init(getApplicationContext());
//        友盟推送初始化 结束

//        融云推送
        PushConfig config = new PushConfig.Builder()
                .enableHWPush(true)
                .enableVivoPush(true)
                .enableMeiZuPush("143576","c306e9e3de484148a781e63d666ec565")
                .enableMiPush("2882303761520021123","5202002195123")
                .enableOppoPush("2310a3b186e2469c82f8e3f3e5b90e61","6ea97421accd42e7839d8af410daf2dd")
                .build();
        RongPushClient.setPushConfig(config);
        RongIM.init(this,"8luwapkv86del");


    }
}
