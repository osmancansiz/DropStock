package com.example.cansiz.recyclerexample;

import java.util.ArrayList;

public class ReportModel{

    private int id;
    private String urunAdi;
    private String urunAdedi;

    public ReportModel() {
    }


    public ReportModel(int id , String urunAdi, String urunAdedi) {
        this.id=id;
        this.urunAdi = urunAdi;
        this.urunAdedi = urunAdedi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String geturunAdi() {
        return urunAdi;
    }

    public void seturunAdi(String urunAdi) {
        this.urunAdi = urunAdi;
    }

    public String geturunAdedi() {
        return urunAdedi;
    }

    public void seturunAdedi(String urunAdedi) {
        this.urunAdedi = urunAdedi;
    }
}