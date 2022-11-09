package com.example.checkable1;

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
import android.widget.ImageButton;

import com.example.checkable1.hospital.SearchHospitalActivity;
import com.example.checkable1.imgprocessing.DetectorActivity;

public class MainActivity extends AppCompatActivity {

    //TAG for Log
    private final String TAG = "TAG_MainActivity";

    //Layout
    ImageButton scanButton;
    ImageButton mapButton;
    ImageButton guideButton;

    //request
    private boolean isAccessFineLocation = false;
    private boolean isAccessCoarseLocation = false;
    private boolean isPermission = false;
    private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;
    private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;
    private AlertDialog alertDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Checkable1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanButton = (ImageButton) findViewById(R.id.button_home_scan);
        mapButton = (ImageButton) findViewById(R.id.button_home_map);
        guideButton = (ImageButton) findViewById(R.id.button_home_guide);

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
                if (!isPermission) {
                    callPermission();
                } else {
                    Intent intent = new Intent(getApplicationContext(), SearchHospitalActivity.class);
                    startActivity(intent);
                }
            }
        });

        guideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "clicked GuideButton");
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