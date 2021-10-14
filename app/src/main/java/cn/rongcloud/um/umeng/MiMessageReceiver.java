package cn.rongcloud.um.umeng;

import android.app.Notification;
import android.content.Context;

import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import org.android.agoo.control.NotifManager;

import java.util.List;

import io.rong.push.PushManager;
import io.rong.push.PushType;
import io.rong.push.common.RLog;
import io.rong.push.core.PushUtils;
import io.rong.push.notification.PushNotificationMessage;

public class MiMessageReceiver extends PushMessageReceiver {
    private static final String TAG = io.rong.push.platform.mi.MiMessageReceiver.class.getSimpleName();

    public MiMessageReceiver() {
    }

    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage message) {
        RLog.v(TAG, "onReceivePassThroughMessage is called. " + message.toString());
    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage message) {
        RLog.v(TAG, "onNotificationMessageClicked is called. " + message.toString());
        PushNotificationMessage pushNotificationMessage = PushUtils.transformToPushMessage(message.getContent());
        PushManager.getInstance().onNotificationMessageClicked(context, PushType.XIAOMI, pushNotificationMessage);
    }

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage message) {
        RLog.v(TAG, "onNotificationMessageArrived is called. " + message.toString());
        PushNotificationMessage pushNotificationMessage = PushUtils.transformToPushMessage(message.getContent());
        if (pushNotificationMessage != null) {
            PushManager.getInstance().onNotificationMessageArrived(context, PushType.XIAOMI, pushNotificationMessage);
        }

    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage message) {
        RLog.v(TAG, "onCommandResult is called. " + message.toString());
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
        String command = message.getCommand();
        List<String> arguments = message.getCommandArguments();
        String cmdArg1 = arguments != null && arguments.size() > 0 ? (String) arguments.get(0) : null;
        String cmdArg2 = arguments != null && arguments.size() > 1 ? (String) arguments.get(1) : null;
        RLog.d(TAG, "onReceiveRegisterResult. cmdArg1: " + cmdArg1 + "; cmdArg2:" + cmdArg2);
        if ("register".equals(command)) {
            if (message.getResultCode() == 0L) {
                PushManager.getInstance().onReceiveToken(context, PushType.XIAOMI, cmdArg1);
                NotifManager notifManager=new NotifManager();
                notifManager.init(context.getApplicationContext());
                notifManager.reportThirdPushToken(cmdArg1,"MI_TOKEN");
            } else {
                PushManager.getInstance().onErrorResponse(context, PushType.XIAOMI, "request_token", message.getResultCode());
            }
        }

    }
}