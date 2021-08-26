package cn.rongcloud.um;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import io.rong.imkit.RongIM;
import io.rong.imkit.utils.RouteUtils;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        JSONObject rc = null;
        if (extras!=null) {
            try {
                rc = new JSONObject(extras.getString("rc"));
                Log.d("TAG", "main: get data" + rc);
                Conversation.ConversationType conversationType =
                        Conversation.ConversationType.setValue(rc.getInt("conversationType"));
                String targetId = rc.getString("fromUserId");
                String content = rc.getString("content");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        connectRong();


    }

    private void connectRong() {
//        push1 token
        RongIM.connect("aim2qLnFE2AscjJSxkJVcXvou7YHIsVKR1kxmw0fW+k=@9tsf.cn.rongnav.com;9tsf.cn.rongcfg.com", new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String s) {
                Toast.makeText(MainActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
                RouteUtils.routeToConversationListActivity(MainActivity.this, "会话列表");
            }

            @Override
            public void onError(RongIMClient.ConnectionErrorCode connectionErrorCode) {

            }

            @Override
            public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus databaseOpenStatus) {

            }
        });
    }
}