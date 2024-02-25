package com.ken.videocalldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zegocloud.uikit.ZegoUIKit;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class MainActivity extends AppCompatActivity {

    Button startBtn;
    EditText userIdText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.start_btn);
        userIdText = findViewById(R.id.user_id_text);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = userIdText.getText().toString().trim();
                if (userID.isEmpty()) {
                    return;
                }
                startService(userID);
                Intent intent = new Intent(MainActivity.this, CallActivity.class);
                intent.putExtra("user_id", userID);
                startActivity(intent);

            }
        });

    }

    void startService(String userID)
    {
        Application application = getApplication(); // Android's application context
        long appID = 0;   // yourAppID
        String appSign = "";  // yourAppSign
//        String userID = ""; // yourUserID, userID should only contain numbers, English characters, and '_'.
        String userName = userID;   // yourUserName

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();

        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userID, userName,callInvitationConfig);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}