package cn.rongcloud.um.umeng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.umeng.message.UmengNotifyClickActivity;

import org.android.agoo.common.AgooConstants;

import cn.rongcloud.um.R;

public class MfrMessageActivity extends UmengNotifyClickActivity {
    private static final String TAG = "MfrMessageActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mfr_message);
    }
    @Override
    public void onMessage(Intent intent) {
        super.onMessage(intent);
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Log.d(TAG, "bundle: " + bundle);
        }
        String body = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
        Log.d(TAG, "body: " + body);
        if (!TextUtils.isEmpty(body)) {
            runOnUiThread(() -> ((TextView) findViewById(R.id.tv)).setText(body));
        }
    }
}