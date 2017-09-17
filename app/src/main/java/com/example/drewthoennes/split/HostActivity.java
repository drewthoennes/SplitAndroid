package com.example.drewthoennes.split;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class HostActivity extends AppCompatActivity {

    Button backButton;
    Button startButton;
    Socket socket;
    String roomCode;
    Boolean host = true;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_host);

        getIntent();

        try {
            socket = IO.socket("http://elnardu.me");
        } catch(Exception exception) {
            Log.e("Error", exception.getMessage()); // Temporary, shoud be removed in final release
            //System.exit(0);
        }
        socket.connect();

        socket.on("roomCreated", new Emitter.Listener() {
            public void call(Object... args) {
                socket.close();
                String data = (String)args[0];
                roomCode = data;
                Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                intent.putExtra("roomCode", roomCode);
                intent.putExtra("host", host);
                startActivity(intent);
            }
        }).on(Socket.EVENT_ERROR, new Emitter.Listener() {
            public void call(Object... args) {
                Exception err = (Exception)args[0];
                Log.e("Error", err.getMessage());
            }
        });

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(100, intent);
                finish();
            }
        });

        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                socket.emit("createRoom");
                startButton.setEnabled(false);
            }
        });
    }
}