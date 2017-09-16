package com.example.drewthoennes.split;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class JoinActivity extends AppCompatActivity {

    Button backButton;
    Button joinGroupButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        getIntent();

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
           public void onClick(View view) {
               Intent intent = new Intent();
               setResult(200, intent);
               finish();
           }
        });

        joinGroupButton = (Button) findViewById(R.id.joinGroupButton);
        joinGroupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Add user to group and increment number of devices
                // Send device's dimensions
            }
        });
    }
}
