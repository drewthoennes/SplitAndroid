package com.example.drewthoennes.split;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class JoinActivity extends AppCompatActivity {

    RelativeLayout activity_join_top;
    RelativeLayout titleBar;
    TextView timesIcon;
    TextView settingsIcon;
    com.beardedhen.androidbootstrap.BootstrapButton joinGroupButton;
    TextView joinAccessCodeLabel;
    com.beardedhen.androidbootstrap.BootstrapEditText joinAccessCode;
    EditText firstEditText;

    Typeface iconFont;
    FontManager fontManager;

    boolean host = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.left_slide_in, R.anim.left_slide_out);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_join);

        getIntent();

        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        fontManager.markAsIconContainer(findViewById(R.id.times_icon), iconFont);
        fontManager.markAsIconContainer(findViewById(R.id.settings_icon), iconFont);

        activity_join_top = (RelativeLayout) findViewById(R.id.activity_join_top);
        activity_join_top.setBackgroundColor(Color.parseColor("#1477d5"));

        titleBar = (RelativeLayout) findViewById(R.id.titleBar);
        titleBar.setBackgroundColor(Color.parseColor("#282828"));

        timesIcon = (TextView) findViewById(R.id.times_icon);
        timesIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Intent intent = new Intent();
                //setResult(200, intent);
                Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        });

        settingsIcon = (TextView) findViewById(R.id.settings_icon);
        settingsIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            }
        });

        // Label above edit text
        joinAccessCodeLabel = (TextView) findViewById(R.id.joinAccessCodeLabel);
        joinAccessCodeLabel.setTextColor(Color.WHITE);
        joinAccessCodeLabel.setHintTextColor(Color.WHITE);
        joinAccessCodeLabel.setCursorVisible(false);
        joinAccessCodeLabel.requestFocus();

        // Edit text for access code
        joinAccessCode = (com.beardedhen.androidbootstrap.BootstrapEditText) findViewById(R.id.joinAccessCode);
        joinAccessCode.setRounded(true);
        // Only allows the join button to be pressed when the length is equal to four
        joinAccessCode.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(joinAccessCode.getText().toString().length() == 4) {
                    joinGroupButton.setEnabled(true);
                }
                else {
                    joinGroupButton.setEnabled(false);
                }
            }
            public void afterTextChanged(Editable editable) {}
        });

        // Button at bottom of activity
        joinGroupButton = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.joinGroupButton);
        joinGroupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Add user to group and increment number of devices
                Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                intent.putExtra("roomCode", joinAccessCode.getText().toString());
                intent.putExtra("host", host);
                startActivity(intent);
            }
        });

        // Show keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}