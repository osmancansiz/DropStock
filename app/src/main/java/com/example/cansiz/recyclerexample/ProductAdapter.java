package com.example.cansiz.recyclerexample;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private Context context;
    List<Product> mProductList;
    private ListItemClickListener listItemClickListener;
    LayoutInflater inflater;

    public ProductAdapter(Context context, List<Product> products,ListItemClickListener listItemClickListener) {
        inflater = LayoutInflater.from(context);
        this.listItemClickListener=listItemClickListener;
        this.mProductList = products;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_product, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        try{
            final Product product = mProductList.get(position);

            holder.productName.setText(product.getProductName());
            holder.productNumbers.setText(product.getProductNumbers());
            holder.productPrice.setText(product.getProductPrice());

            // Güncelleme ve Silmeyi burada yapacam.Unutma!
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listItemClickListener.clickPosition(product.getId(), product.getProductName(),product.getProductNumbers(),product.getProductPrice());
                    //Toast.makeText(view.getContext(),position + "",Toast.LENGTH_SHORT).show();
                }
            });

            Product selectedProduct = mProductList.get(position);
            holder.setData(selectedProduct, position);
        }
        catch (Exception ex){
            Toast.makeText(context,"Değerleri Sadece Admin Değiştirebilir",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public int getItemCount() {
        return mProductList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView productName, productNumbers,productPrice;
        ImageView productImage, deleteproduct;

        public MyViewHolder(View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.productName);
            productNumbers = (TextView) itemView.findViewById(R.id.productNumbers);
            productImage = (ImageView) itemView.findViewById(R.id.productImage);
            productPrice = (TextView) itemView.findViewById(R.id.productPrice);

        }

        public void setData(Product selectedProduct, int position) {

            this.productName.setText(selectedProduct.getProductName());
            this.productNumbers.setText(selectedProduct.getProductNumbers());
            this.productPrice.setText(selectedProduct.getProductPrice());
            this.productImage.setImageResource(selectedProduct.getImageID());
        }


        @Override
        public void onClick(View v) {

        }

    }
}