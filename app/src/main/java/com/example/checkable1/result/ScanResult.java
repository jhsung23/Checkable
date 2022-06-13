package com.example.checkable1.result;

public class ScanResult implements Comparable<ScanResult> {

    private Float srConfidence;
    private String srClassname;

    public ScanResult(Float srConfidence, String srClassname) {
        this.srConfidence = srConfidence;
        this.srClassname = srClassname;
    }

    public Float getSrConfidence() {
        return srConfidence;
    }

    public String getSrClassname() {
        return srClassname;
    }

    @Override
    public int compareTo(ScanResult scanResult) {
        if (this.srConfidence < scanResult.getSrConfidence()) return 1;
        else if (this.srConfidence > scanResult.getSrConfidence()) return -1;
        return 0;
    }
}
