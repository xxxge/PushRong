package cn.rongcloud.um;

import android.content.Context;
import android.util.Log;

import io.rong.push.PushType;
import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

public class PushMessageNew extends PushMessageReceiver {
    @Override
    public boolean onNotificationMessageClicked(Context context, PushType pushType, PushNotificationMessage notificationMessage) {
        Log.e("TAG", "onNotificationMessageClicked: "+notificationMessage );
        return super.onNotificationMessageClicked(context, pushType, notificationMessage);
    }
}
