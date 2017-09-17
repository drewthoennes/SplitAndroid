package com.example.drewthoennes.split;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import org.json.*;

import java.io.IOException;

import static com.example.drewthoennes.split.R.layout.activity_room;


/**
 * Created by drewthoennes on 9/16/17.
 */

public class RoomActivity extends AppCompatActivity {

    Button endButton;
    Button startVideoButton;
    TextView hostAccessCode;
    Socket socket;
    String roomCode; // Implement this
    String userId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(activity_room);

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        final int screenHeight = displayMetrics.heightPixels;
        final int screenWidth = displayMetrics.widthPixels;
        final int xDpi = (int) displayMetrics.xdpi;
        final int yDpi = (int) displayMetrics.ydpi;

        if(getIntent().getStringExtra("roomCode").toString() != null) {
            roomCode = getIntent().getStringExtra("roomCode").toString();
        }
        else {
            roomCode = "";
        }

        try {
            socket = IO.socket("http://elnardu.me/" + roomCode);
        } catch(Exception exception) {
            Log.e("Error", exception.getMessage()); // Temporary, shoud be removed in final release
            //System.exit(0);
        }
        socket.connect();

        JSONObject settings = new JSONObject();
        try {
            settings.put("width", screenWidth);
            settings.put("height", screenHeight);
            settings.put("xdpi", xDpi);
            settings.put("ydpi", yDpi);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        socket.emit("join", settings);

        socket.on("joinAccepted", new Emitter.Listener() {
            public void call(Object... args) {
                String data = (String) args[0];
                userId = data;
            }
        }).on(Socket.EVENT_ERROR, new Emitter.Listener() {
            public void call(Object... args) {
                Exception err = (Exception) args[0];
                Log.e("Error", err.getMessage());
            }
        });

        hostAccessCode = (TextView) findViewById(R.id.hostAccessCode);
        hostAccessCode.setText(roomCode);

        endButton = (Button) findViewById(R.id.endButton);
        endButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                socket.close();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        startVideoButton = (Button) findViewById(R.id.startVideoButton);
        startVideoButton.setOnClickListener(new View.OnClickListener() {
           public void onClick(View view) {
               socket.emit("prepare");
               Intent intent = new Intent(getApplicationContext(), VideoActivity.class);
               intent.putExtra("userId", userId);
               startActivity(intent);
           }
        });
    }
}
