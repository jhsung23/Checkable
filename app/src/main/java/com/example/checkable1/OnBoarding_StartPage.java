package com.example.checkable1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class OnBoarding_StartPage extends AppCompatActivity {

    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Checkable1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_start_page);

        nextButton = (Button) findViewById(R.id.button_start_next);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OnBoarding_Scan1Page.class);
                startActivity(intent);
                Log.d("TAG", "intent");
            }
        });
    }
}