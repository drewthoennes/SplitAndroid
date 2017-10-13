package com.example.drewthoennes.split;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class HomeActivity extends AppCompatActivity {

    private com.beardedhen.androidbootstrap.BootstrapButton hostButton;
    private com.beardedhen.androidbootstrap.BootstrapButton joinButton;

    private FontManager fontManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.right_slide_in, R.anim.right_slide_out);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        Typeface iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        fontManager.markAsIconContainer(findViewById(R.id.settings_icon), iconFont);
        //fontManager.markAsIconContainer(findViewById(R.id.upload_icon), iconFont);

        hostButton = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.hostButton);
        hostButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent hostIntent = new Intent(getApplicationContext(), HostActivity.class);
                startActivity(hostIntent);
            }
        });
        //hostButton.setTextColor(Color.parseColor("#369bb1"));

        joinButton = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.joinButton);
        joinButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent joinIntent = new Intent(getApplicationContext(), JoinActivity.class);
                //startActivityForResult(joinIntent, 200);
                startActivity(joinIntent);
            }
        });
        //joinButton.setTextColor(Color.parseColor("#369bb1"));
    }

}
