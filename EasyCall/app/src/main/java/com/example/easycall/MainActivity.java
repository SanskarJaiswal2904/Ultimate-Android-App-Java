package com.example.easycall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class MainActivity extends AppCompatActivity {
    EditText userName;
    Button start_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.userName);
        start_btn = findViewById(R.id.startBtn);

        start_btn.setOnClickListener(view -> {
            String userId = userName.getText().toString().trim();
            if (userId.isEmpty()){
                Toast.makeText(getApplicationContext(),"Enter User Name",Toast.LENGTH_SHORT).show();
                return;
            }
            startService(userId);
            Intent intent = new Intent(MainActivity.this,CallActivity.class);
            intent.putExtra("userId",userId);
            startActivity(intent);
        });
    }


    void startService(String userID){
        Application application = getApplication(); // Android's application context
        long appID = 772647388;   // yourAppID
        String appSign = "f24a0be3ea4a45d6f0caf1da1c9990ef19a741f0f0658015295e8ce24bfe55bf";  // yourAppSign

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