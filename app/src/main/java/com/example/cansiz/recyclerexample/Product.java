package com.example.cansiz.recyclerexample;

import java.util.ArrayList;

public class Product {

    private String productName;
    private String productDescription;
    private int imageID;

    public Product() {
    }

    public Product(int imageID, String productName, String productDescription) {
        this.imageID = imageID;
        this.productName = productName;
        this.productDescription = productDescription;
    }


    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public static ArrayList<Product> getData() {
        ArrayList<Product> productList = new ArrayList<Product>();
        int productImages[] = {R.drawable.xxx,R.drawable.xxx,R.drawable.xxx,R.drawable.xxx,R.drawable.xxx};
        String[] productNames = {"Geleceği Yazanlar", "Paycell", "Tv+", "Dergilik", "Bip"};

        for (int i = 0; i < productImages.length; i++) {
            Product temp = new Product();
            temp.setImageID(productImages[i]);
            temp.setProductName(productNames[i]);
            temp.setProductDescription("Turkcell");

            productList.add(temp);

        }


        return productList;


    }
}