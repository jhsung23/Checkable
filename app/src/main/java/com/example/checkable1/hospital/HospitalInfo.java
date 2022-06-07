package com.example.checkable1.hospital;

public class HospitalInfo implements Comparable<HospitalInfo> {
    public String hosName;
    public String hosPhoneNumber;
    public String hosAddress;
    public String hosLatitude;
    public String hosLongitude;
    public String hosDistance_string;
    public float distance;

    public HospitalInfo() {
        super();
    }

    public HospitalInfo(String hosName, String hosPhoneNumber, String hosAddress) {
        this.hosName = hosName;
        this.hosPhoneNumber = hosPhoneNumber;
        this.hosAddress = hosAddress;
    }

    public void setHosName(String hosName) {
        this.hosName = hosName;
    }

    public void setHosPhoneNumber(String hosPhoneNumber) {
        this.hosPhoneNumber = hosPhoneNumber;
    }

    public void setHosAddress(String hosAddress) {
        this.hosAddress = hosAddress;
    }

    public void setHosLatitude(String hosLatitude) {
        this.hosLatitude = hosLatitude;
    }

    public void setHosLongitude(String hosLongitude) {
        this.hosLongitude = hosLongitude;
    }

    public void setHosDistance_string(String hosDistance_string) {
        this.hosDistance_string = hosDistance_string;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(HospitalInfo hospitalInfo) {
        if (hospitalInfo.distance < distance) {
            return 1;
        } else if (hospitalInfo.distance > distance) {
            return -1;
        }
        return 0;
    }
}
