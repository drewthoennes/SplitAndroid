package com.example.drewthoennes.split;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.socket.client.IO;
import io.socket.client.Socket;
//import io.socket.IOAcknowledge;


/**
 * Created by drewthoennes on 9/16/17.
 */

public class RoomActivity extends AppCompatActivity {

    Button endButton;
    TextView hostAccessCode;
    Socket socket;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        try {
            socket = IO.socket("http://elnardu.me/wsac");
        } catch(Exception exception) {
            Log.e("Error", exception.getMessage()); // Temporary, shoud be removed in final release
            System.exit(0);
        }
        socket.connect();
        socket.emit("echo", "hello");

        hostAccessCode = (TextView) findViewById(R.id.hostAccessCode);
        hostAccessCode.setText(getAccessCode());

        endButton = (Button) findViewById(R.id.endButton);
        endButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                socket.close();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    public String getAccessCode() {
        // Query for unused access codes
        return "ROFL";
    }

//    public void onMessage(String data, IOAcknowledge ack) {
//        System.out.println("Server said " + data);
//    }


}
