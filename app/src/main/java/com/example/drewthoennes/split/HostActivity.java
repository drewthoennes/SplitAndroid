package com.example.drewthoennes.split;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HostActivity extends AppCompatActivity {

    private Button backButton;
    private Button startButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        getIntent();

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
                Bundle bundle = new Bundle();
                //bundle.putParcelable("Socket", (Parcelable) socket);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
