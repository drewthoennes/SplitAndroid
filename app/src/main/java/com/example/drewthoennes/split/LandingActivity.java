package com.example.drewthoennes.split;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LandingActivity extends AppCompatActivity {

    private Button hostButton;
    private Button joinButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        hostButton = (Button) findViewById(R.id.hostButton);
        hostButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent hostIntent = new Intent(getApplicationContext(), HostActivity.class);
                startActivityForResult(hostIntent, 100);
            }
        });

        joinButton = (Button) findViewById(R.id.joinButton);
        joinButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent joinIntent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivityForResult(joinIntent, 200);
            }
        });

    }
}
