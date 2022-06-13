package com.example.checkable1.result;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.checkable1.R;
import com.example.checkable1.hospital.SearchHospitalActivity;

public class AbnormalResultActivity extends AppCompatActivity {

    //TAG for log
    private final String TAG = "TAG_AbnormalResultActivity";

    //layout
    TextView confidenceTextView;
    Button goSearchHospitalButton;

    //request
    private boolean isAccessFineLocation = false;
    private boolean isAccessCoarseLocation = false;
    private boolean isPermission = false;
    private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;
    private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;
    private AlertDialog alertDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abnormal_result);

        confidenceTextView = (TextView) findViewById(R.id.text_abnormal_confidence);
        goSearchHospitalButton = (Button) findViewById(R.id.button_abnormal_intent);

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

        //병원 정보 화면(SearchHospitalActivity) 띄우기
        goSearchHospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPermission) {
                    callPermission();
                } else {
                    Intent intent = new Intent(getApplicationContext(), SearchHospitalActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    //Permission 설정 method
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_ACCESS_FINE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            isAccessFineLocation = true;
            Log.d(TAG, "fine location");

        } else if (requestCode == PERMISSIONS_ACCESS_COARSE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            isAccessCoarseLocation = true;
            Log.d(TAG, "coarse location");

        } else {
            alertDialog = createDialog();
            alertDialog.show();
        }

        if (isAccessFineLocation || isAccessCoarseLocation) {
            isPermission = true;
            Log.d(TAG, "both of locations");

            Intent intent = new Intent(getApplicationContext(), SearchHospitalActivity.class);
            startActivity(intent);
        }
    }

    private void callPermission() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_ACCESS_FINE_LOCATION);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSIONS_ACCESS_COARSE_LOCATION);
        } else {
            isPermission = true;
        }
    }

    public AlertDialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setMessage("위치 권한과 인터넷 연결이 없으면 이용할 수 없어요.");

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        return builder.create();
    }
}