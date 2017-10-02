package com.example.a5280081.firebasecloudmessagingapp.model;

import java.util.ArrayList;
import java.util.List;

public class Package {

    private int packageId;
    private String packageName;
    private String shipperName;

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public Package(int packageId, String packageName, String shipperName){
        this.packageId = packageId;
        this.packageName = packageName;
        this.shipperName = shipperName;
    }

    public static List<Package> createPackageList(){
        List<Package> packageList = new ArrayList<>();
        for (int i=0; i< 10; i++){
            packageList.add(new Package(i, "package"+i,"shipperName"+i));
        }
        return packageList;
    }
}
