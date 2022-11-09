package com.example.checkable1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OnBoarding_HospitalPage extends AppCompatActivity {

    Button previousButton;
    //Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_hospital_page);

        previousButton = (Button) findViewById(R.id.button_hospital_previous);
//        nextButton=(Button)findViewById(R.id.nextButtonName);

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // "다시 볼 수 있습니다" 페이지로 이동시키기
    }
}