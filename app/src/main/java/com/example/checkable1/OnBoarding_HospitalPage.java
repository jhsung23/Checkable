package com.example.checkable1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class OnBoarding_HospitalPage extends AppCompatActivity {

    Button previousButton;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_hospital_page);

        previousButton = (Button) findViewById(R.id.button_hospital_previous);
        nextButton = (Button) findViewById(R.id.button_hospital_next);

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OnBoarding_HelpPage.class);
                startActivity(intent);
                Log.d("TAG", "intent");
            }
        });


    }
}