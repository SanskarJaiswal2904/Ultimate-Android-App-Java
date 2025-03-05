package com.example.easycall;

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

    TextView greetingView;
    EditText receiverName;
    ZegoSendCallInvitationButton video,voice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        greetingView = findViewById(R.id.greeting);
        receiverName = findViewById(R.id.receiverName);
        voice = findViewById(R.id.voiceCallButton);
        video = findViewById(R.id.videoCallButton);

        String userId = getIntent().getStringExtra("userId");
        greetingView.setText("Hey, "+userId);

        receiverName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String targetUserID = receiverName.getText().toString().trim();
                setVoiceCall(targetUserID);
                setVideoCall(targetUserID);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    void setVoiceCall(String targetUserID){
        voice.setIsVideoCall(false);
        voice.setResourceID("zego_uikit_call");
        voice.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID)));
    }
    void setVideoCall(String targetUserID){
        video.setIsVideoCall(true);
        video.setResourceID("zego_uikit_call");
        video.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID)));
    }
}