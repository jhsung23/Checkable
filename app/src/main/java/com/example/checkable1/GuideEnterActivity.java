package com.example.checkable1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class GuideEnterActivity extends AppCompatActivity {

    Button scanInformButton;
    Button hospitalInformButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_enter);

        scanInformButton = (Button) findViewById(R.id.button_guideEnter_scan);
        hospitalInformButton = (Button) findViewById(R.id.button_guideEnter_hospital);

        scanInformButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "clicked scanInformButton");

            }
        });

        hospitalInformButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "clicked hospitalInformButton");

            }
        });
    }
}