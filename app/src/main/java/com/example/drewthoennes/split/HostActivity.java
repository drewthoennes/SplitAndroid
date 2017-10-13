package com.example.drewthoennes.split;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class HostActivity extends AppCompatActivity {

    RelativeLayout activity_host_top;
    RelativeLayout titleBar;
    TextView timesIcon;
    TextView settingsIcon;
    TextView urlLabel;
    com.beardedhen.androidbootstrap.BootstrapButton startButton;
    com.beardedhen.androidbootstrap.BootstrapEditText youtubeText;

    Socket socket;
    String roomCode;
    Boolean host = true;

    Typeface iconFont;
    FontManager fontManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.left_slide_in, R.anim.left_slide_out);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_host);

        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        fontManager.markAsIconContainer(findViewById(R.id.times_icon), iconFont);
        fontManager.markAsIconContainer(findViewById(R.id.settings_icon), iconFont);

        activity_host_top = (RelativeLayout) findViewById(R.id.activity_host_top);
        activity_host_top.setBackgroundColor(Color.parseColor("#1477d5"));

        titleBar = (RelativeLayout) findViewById(R.id.titleBar);
        titleBar.setBackgroundColor(Color.parseColor("#282828"));

        timesIcon = (TextView) findViewById(R.id.times_icon);
        timesIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(homeIntent);
            }
        });

        settingsIcon = (TextView) findViewById(R.id.settings_icon);
        settingsIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            }
        });

        urlLabel = (TextView) findViewById(R.id.urlLabel);
        urlLabel.setTextColor(Color.WHITE);

        getIntent();

        try {
            socket = IO.socket("http://elnardu.me");
            //socket = IO.socket("https://aqueous-reaches-96453.herokuapp.com");
        } catch(Exception exception) {
            Log.e("Error", exception.getMessage()); // Temporary, shoud be removed in final release
            //System.exit(0);
        }
        socket.connect();

        youtubeText = (com.beardedhen.androidbootstrap.BootstrapEditText) findViewById(R.id.youtubeText);

        socket.on("roomCreated", new Emitter.Listener() {
            public void call(Object... args) {
                socket.close();
                String data = (String)args[0];
                roomCode = data;
                Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                intent.putExtra("roomCode", roomCode);
                intent.putExtra("host", host);
                intent.putExtra("link", youtubeText.getText().toString());
                startActivity(intent);
            }
        }).on(Socket.EVENT_ERROR, new Emitter.Listener() {
            public void call(Object... args) {
                Exception err = (Exception)args[0];
                Log.e("Error", err.getMessage());
            }
        });

        startButton = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                socket.emit("createRoom");
                startButton.setEnabled(false);
            }
        });
    }
}