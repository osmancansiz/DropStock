package com.example.cansiz.recyclerexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements ListItemClickListener{
    private List<Product> listProduct = new ArrayList<Product>();
    final Database db = new Database(MainActivity.this);
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        listProduct.addAll(db.productItems());
        ProductAdapter productAdapter = new ProductAdapter(this, listProduct,this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(productAdapter);
    }

    @Override
    public void clickPosition(int id, String name,String numbers, String price) {
        Intent intent = new Intent(MainActivity.this,UpdateAndDeleteStock.class);
        intent.putExtra("index",id);
        intent.putExtra("urun_Adi",name);
        intent.putExtra("urun_Adedi",numbers);
        intent.putExtra("urun_Fiyati",price);
        startActivity(intent);
        finish();
        //Toast.makeText(getApplicationContext(), position + " Full View ID" + name, Toast.LENGTH_SHORT).show();
    }
}
