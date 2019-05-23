package com.example.cansiz.recyclerexample;

import java.util.ArrayList;

public class Product {

    private String productName;
    private String productNumbers;
    private String productPrice;
    private int imageID;

    public Product() {
    }

    public Product(int imageID, String productName, String productNumbers,String productPrice) {
        this.imageID = imageID;
        this.productName = productName;
        this.productNumbers = productNumbers;
        this.productPrice = productPrice;
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

    public String getProductNumbers() {
        return productNumbers;
    }

    public void setProductNumbers(String productNumbers) {
        this.productNumbers = productNumbers;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public static ArrayList<Product> getData() {
        ArrayList<Product> productList = new ArrayList<Product>();
        int productImages[] = {R.drawable.list,R.drawable.list,R.drawable.list,R.drawable.list,R.drawable.list};
        String[] productNames = {"Ürün 1", "Ürün 2", "Ürün 3", "Ürün 4", "Ürün 5"};
        String[] productNumbers = {"10 Adet", "150 Adet", "60 Adet", "30 Adet", "5 Adet"};
        String[] productPrice = {"20 TL", "25 TL", "30 TL", "45 TL", "55 TL"};

        for (int i = 0; i < productImages.length; i++) {
            Product temp = new Product();
            temp.setImageID(productImages[i]);
            temp.setProductName(productNames[i]);
            temp.setProductNumbers(productNumbers[i]);
            temp.setProductPrice(productPrice[i]);
            productList.add(temp);

        }
        return productList;
    }
}