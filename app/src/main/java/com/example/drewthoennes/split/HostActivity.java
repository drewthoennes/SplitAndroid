package com.example.drewthoennes.split;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class HostActivity extends AppCompatActivity {

    private Button backButton;
    private Button startButton;
    private Socket socket;
    private String roomCode;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        getIntent();

        try {
            socket = IO.socket("http://elnardu.me");
        } catch(Exception exception) {
            Log.e("Error", exception.getMessage()); // Temporary, shoud be removed in final release
            System.exit(0);
        }
        socket.connect();

        socket.emit("createRoom");

        socket.on("roomCreated", new Emitter.Listener() {
            public void call(Object... args) {
                String data = (String)args[0];
                roomCode = data;
            }
        }).on(Socket.EVENT_ERROR, new Emitter.Listener() {
            public void call(Object... args) {
                Exception err = (Exception)args[0];
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
                Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                //intent.putExtra("Var", "My string value");
                //Bundle bundle = new Bundle();
                //bundle.putParcelable("Socket", socket);
                //intent.putExtras(bundle);
                intent.putExtra("roomCode", roomCode);
                startActivity(intent);
            }
        });
    }
}
