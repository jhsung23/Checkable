package com.example.checkable1.result;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.checkable1.MainActivity;
import com.example.checkable1.R;

public class NormalResultActivity extends AppCompatActivity {

    //TAG for Log
    private final String TAG = "TAG_NormalResultActivity";

    //Layout
    TextView confidenceTextView;
    Button goHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_result);

        //view setting
        confidenceTextView = (TextView) findViewById(R.id.text_normal_confidence);
        goHomeButton = (Button) findViewById(R.id.button_normal_intent);

        //image detecting 결과값 -> Textview
        Float resultConfidence = getIntent().getFloatExtra("confidence", 0);
        int confidenceInt = (int) (resultConfidence * 100);
        if (resultConfidence >= 0.7) {
            confidenceTextView.setText("정확도 높음" + "(" + confidenceInt + "%)");
        } else if (resultConfidence >= 0.6) {
            confidenceTextView.setText("정확도 보통" + "(" + confidenceInt + "%)");
        } else if (resultConfidence >= 0.5) {
            confidenceTextView.setText("정확도 낮음" + "(" + confidenceInt + "%)");
        }

        //홈 화면으로 이동
        goHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}