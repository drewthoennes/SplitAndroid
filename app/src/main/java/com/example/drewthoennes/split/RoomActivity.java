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

/**
 * Created by drewthoennes on 9/16/17.
 */

public class RoomActivity extends AppCompatActivity {

    Button endButton;
    TextView hostAccessCode;
    private Socket socket;
    {
        try {
            socket = IO.socket("localhost:8080");
        } catch(Exception exception) {
            Log.e("Error", exception.getMessage());
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        socket.connect();

        hostAccessCode = (TextView) findViewById(R.id.hostAccessCode);
        hostAccessCode.setText(createAccessCode());

        //hostAccessCode.setText(getIntent().getStringExtra("Var"));

        endButton = (Button) findViewById(R.id.endButton);
        endButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LandingActivity.class);
                startActivity(intent);
            }
        });
    }

    public String createAccessCode() {
        // Query for unused access codes
        return "ROFL";
    }

}
