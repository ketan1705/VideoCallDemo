package com.ken.videocalldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class CallActivity extends AppCompatActivity {

    EditText userIdText;
    TextView heyUserTextView;
    ZegoSendCallInvitationButton voiceCallBtn, videoCallBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);


        userIdText = findViewById(R.id.user_id_text);
        heyUserTextView = findViewById(R.id.hey_user_text_view);

        voiceCallBtn = findViewById(R.id.voice_call_btn);
        videoCallBtn = findViewById(R.id.video_call_btn);

        String userId = getIntent().getStringExtra("user_id");
        heyUserTextView.setText(userId);

        userIdText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String targetUserId = userIdText.getText().toString();
                setVoiceCall(targetUserId);
                setVideoCall(targetUserId);
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    void setVoiceCall(String targetUserId)
    {
        voiceCallBtn.setIsVideoCall(false);
        voiceCallBtn.setResourceID("zego_uikit_call"); // Please fill in the resource ID name that has been configured in the ZEGOCLOUD's console here.
        voiceCallBtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserId)));
    }
    void setVideoCall(String targetUserId)
    {
        videoCallBtn.setIsVideoCall(true);
        videoCallBtn.setResourceID("zego_uikit_call"); // Please fill in the resource ID name that has been configured in the ZEGOCLOUD's console here.
        videoCallBtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserId)));
    }
}