package com.example.checkable1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.checkable1.hospital.SearchHospitalActivity;

public class MainActivity extends AppCompatActivity {

    //TAG for Log
    private final String TAG = "TAG_MainActivity";

    //Layout
    ImageButton scanButton;
    ImageButton mapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Checkable1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanButton = (ImageButton) findViewById(R.id.button_home_scan);
        mapButton = (ImageButton) findViewById(R.id.button_home_map);

        //scanButton 클릭 시 스캔 카메라 화면 띄우기
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DetectorActivity.class);
                startActivity(intent);
                Log.d(TAG, "intent");
            }
        });

        //mapButton 클릭 시 병원 정보 화면(SearchHospitalActivity) 띄우기
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchHospitalActivity.class);
                startActivity(intent);
            }
        });

    }
}