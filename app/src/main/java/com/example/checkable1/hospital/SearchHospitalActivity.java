package com.example.checkable1.hospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.checkable1.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;


public class SearchHospitalActivity extends AppCompatActivity {

    //TAG for Log
    private final String TAG = "TAG_SearchHospital";

    //Open API & XML Parsing
    private final String key = "IKD7B6DKZrMraeoRimEhN5K7inB8YFvuRK7hADTCsUTdhTDKoVepV20J4XE0vs9kA21VeP1UHFXBi0DpLt1Ehw%3D%3D";
    HospitalInfo hospitalInfo;
    ArrayList<HospitalInfo> hospitalArray;

    //GPS
    GpsInfo gpsInfo;

    //Current Location
    Location curLocation;
    private Double curLatitude;
    private Double curLongitude;

    //Layout
    RecyclerView recyclerView;
    HospitalItemAdapter hospitalItemAdapter;
    ProgressBar progressBar;
    private AlertDialog alertDialog = null;

    //call
    private final int PERMISSIONS_CALL_PHONE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hospital);

        progressBar = (ProgressBar) findViewById(R.id.progressBar_searchHospital);
        //화면로드됨
        //1. GPS 실행 (현재위치 받아오기)

        gpsInfo = new GpsInfo(this);
        if (gpsInfo.isGetLocation()) {
            //현재 경도, 위도 값
            curLatitude = gpsInfo.getLatitude();
            curLongitude = gpsInfo.getLongitude();

            Log.d(TAG, "Latitude: " + curLatitude + ", Longitude: " + curLongitude);
            curLocation = new Location("curLocation");
            curLocation.setLatitude(curLatitude);
            curLocation.setLongitude(curLongitude);
        } else {
            //Log.d(TAG, "사용자의 현재 위치를 가져오지 못했습니다.");
            Toast.makeText(this, "위치를 가져오지 못했습니다.", Toast.LENGTH_LONG).show();
        }

        //<XML parsing>
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    progressBar.setVisibility(View.VISIBLE);
                    hospitalArray = new ArrayList<HospitalInfo>();
                    getHospitalXmlData();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //화면에 띄우기
                        Collections.sort(hospitalArray);

                        recyclerView = (RecyclerView) findViewById(R.id.recycler_searchHospital);
                        hospitalItemAdapter = new HospitalItemAdapter();
                        hospitalItemAdapter.setHospitalList(hospitalArray);

                        hospitalItemAdapter.setOnItemClickListener(new HospitalItemAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                                        && checkSelfPermission(Manifest.permission.CALL_PHONE)
                                        != PackageManager.PERMISSION_GRANTED) {

                                    requestPermissions(
                                            new String[]{Manifest.permission.CALL_PHONE},
                                            PERMISSIONS_CALL_PHONE);
                                } else {
                                    String name = hospitalArray.get(position).hosName;
                                    String tel = "tel:" + hospitalArray.get(position).hosPhoneNumber;
                                    alertDialog = createDialog(name, tel);
                                    alertDialog.show();
                                }
                            }
                        });

                        progressBar.setVisibility(View.GONE);

                        recyclerView.setAdapter(hospitalItemAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(SearchHospitalActivity.this));
                    }
                });
            }
        }).start();
    }

    void getHospitalXmlData() throws IOException {
        //공공데이터 url
        //의원급, 산부인과, 전국 3712개
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + key);
        urlBuilder.append("&" + URLEncoder.encode("QZ", "UTF-8") + "=" + URLEncoder.encode("C", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("QD", "UTF-8") + "=" + URLEncoder.encode("D011", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("3172", "UTF-8"));

        try {
            Log.d(TAG, "parsing start");
            URL url = new URL(urlBuilder.toString());
            InputStream inputStream = url.openStream();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new InputStreamReader(inputStream, "UTF-8"));

            String tagName;
            xmlPullParser.next();
            int eventType = xmlPullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        tagName = xmlPullParser.getName();

                        //병원 정보 parsing
                        if (tagName.equals("item")) {
                            hospitalInfo = new HospitalInfo();
                        } else if (tagName.equals("dutyAddr")) { //병원주소
                            xmlPullParser.next();
                            hospitalInfo.setHosAddress(xmlPullParser.getText());
                        } else if (tagName.equals("dutyName")) { //병원명
                            xmlPullParser.next();
                            hospitalInfo.setHosName(xmlPullParser.getText());
                        } else if (tagName.equals("dutyTel1")) { //병원 전화 번호
                            xmlPullParser.next();
                            hospitalInfo.setHosPhoneNumber(xmlPullParser.getText());
                        } else if (tagName.equals("wgs84Lat")) { //병원 위도
                            xmlPullParser.next();
                            hospitalInfo.setHosLatitude(xmlPullParser.getText());
                        } else if (tagName.equals("wgs84Lon")) { //병원 경도
                            xmlPullParser.next();
                            hospitalInfo.setHosLongitude(xmlPullParser.getText());
                        }
                        break;
                    case XmlPullParser.TEXT:
                        break;
                    case XmlPullParser.END_TAG:
                        tagName = xmlPullParser.getName();
                        //원하는 정보 parsing 종료
                        if (tagName.equals("item")) {

                            if (hospitalInfo.hosLatitude == null || hospitalInfo.hosLatitude == null)
                                break;
                            //경도, 위도 이용해 curLocation과 거리 계산
                            Location destinationHospital = new Location("hospital Loc");

                            double hosLatitude_double = Double.parseDouble(hospitalInfo.hosLatitude);
                            double hosLongitude_double = Double.parseDouble(hospitalInfo.hosLongitude);

                            destinationHospital.setLatitude(hosLatitude_double);
                            destinationHospital.setLongitude(hosLongitude_double);

                            float distance = curLocation.distanceTo(destinationHospital) / 1000;

                            //5km 이내라면 배열에 넣는다.
                            if (distance <= 2.0) {
                                if (hospitalInfo.hosName.contains("여성") || hospitalInfo.hosName.contains("산부인과")) {
                                    String distance_string = trimming(distance); //소수점 조정 및 단위(m, km) 변환

                                    //Log.d(TAG, hospitalInfo.hosName);
                                    hospitalInfo.setDistance(distance);
                                    hospitalInfo.setHosDistance_string(distance_string);
                                    hospitalArray.add(hospitalInfo);
                                } else break;
                            }
                        }
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

    }

    private String trimming(float distance) {
        String output;
        float tmp = (float) ((float) Math.ceil(distance * 100) / 100.0);
        if (tmp < 1.0) {
            output = ((int) (tmp * 1000)) + "m";
        } else {
            output = tmp + " km";
        }
        return output;
    }

    public AlertDialog createDialog(String name, String tel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("전화걸기");
        builder.setMessage(name + " 병원으로 전화를 걸까요?");
        builder.setCancelable(true);

        builder.setPositiveButton("전화걸기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(tel));
                startActivity(intent);

                setDismiss(alertDialog);
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setDismiss(alertDialog);
            }
        });

        return builder.create();
    }

    private void setDismiss(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) dialog.dismiss();
    }
}