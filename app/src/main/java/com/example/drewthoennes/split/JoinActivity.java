package com.example.drewthoennes.split;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class JoinActivity extends AppCompatActivity {

    Button backButton;
    Button joinGroupButton;
    Socket socket;
    EditText joinAccessCode;
    boolean host = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_join);

        getIntent();

        joinAccessCode = (EditText) findViewById(R.id.joinAccessCode);

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
                Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                intent.putExtra("roomCode", joinAccessCode.getText().toString());
                intent.putExtra("host", host);
                startActivity(intent);
            }
        });
    }
}