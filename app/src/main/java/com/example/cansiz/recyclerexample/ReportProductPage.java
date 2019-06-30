package com.example.cansiz.recyclerexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReportProductPage extends Activity {
    private List<Product> listProduct = new ArrayList<Product>();
    final Database db = new Database(ReportProductPage.this);
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_product_page);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewReport);

        listProduct.addAll(db.productItems());
        ProductAdapter productAdapter = new ProductAdapter(this, listProduct,null);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(productAdapter);
    }
}
