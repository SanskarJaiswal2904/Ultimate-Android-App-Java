package com.example.easygolive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.livestreaming.ZegoUIKitPrebuiltLiveStreamingConfig;
import com.zegocloud.uikit.prebuilt.livestreaming.ZegoUIKitPrebuiltLiveStreamingFragment;

public class LiveActivity extends AppCompatActivity {

    String liveId, nameId, userId;
    boolean isHost;
    TextView LiveIdText;
    ImageView shareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);

        LiveIdText = findViewById(R.id.live_id_textview);
        shareBtn = findViewById(R.id.share_btn);

        liveId = getIntent().getStringExtra("liveId");
        userId = getIntent().getStringExtra("user_ID");
        nameId = getIntent().getStringExtra("name");
        isHost = getIntent().getBooleanExtra("host",false);

        LiveIdText.setText("LIVE ID : "+liveId);

        addFragment();

        shareBtn.setOnClickListener(view -> { // to share meeting id
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT,"Join my Live in Easy GoLive \n Live ID : "+ liveId); // the text to share
            startActivity(Intent.createChooser(intent,"Share via"));
        });
    }
    public void addFragment() {
        long appID = Constants.AppId;
        String appSign = Constants.AppSign;
        String userID = userId;
        String userName = nameId;

        boolean isHost = getIntent().getBooleanExtra("host", false);
        String liveID = getIntent().getStringExtra("liveID");

        ZegoUIKitPrebuiltLiveStreamingConfig config;
        if (isHost) {
            config = ZegoUIKitPrebuiltLiveStreamingConfig.host();
        } else {
            config = ZegoUIKitPrebuiltLiveStreamingConfig.audience();
        }
        ZegoUIKitPrebuiltLiveStreamingFragment fragment = ZegoUIKitPrebuiltLiveStreamingFragment.newInstance(
                appID, appSign, userID, userName,liveID,config);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNow();
    }
}