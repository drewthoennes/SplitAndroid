package com.example.drewthoennes.split;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HostActivity extends AppCompatActivity {

    Button backButton;
    TextView hostAccessCode;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        hostAccessCode = (TextView) findViewById(R.id.hostAccessCode);
        hostAccessCode.setText(createAccessCode());

        getIntent();

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(100, intent);
                finish();
            }
        });
    }

    public String createAccessCode() {
        // Check open strings with server
        return "ROFL";
    }
}
