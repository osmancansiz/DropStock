package com.example.cansiz.recyclerexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {
    private Context context;
    List<ReportModel> mReportList;
    LayoutInflater inflater;

    public ReportAdapter(Context context, List<ReportModel> reportList){
        inflater=LayoutInflater.from(context);
        this.mReportList=reportList;
    }


    @NonNull
    @Override

    public ReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.report_product, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.ViewHolder viewHolder, int i) {
        try{
            final ReportModel reportModel = mReportList.get(i);

            viewHolder.urunAdi.setText(reportModel.geturunAdi());
            viewHolder.urunAdedi.setText(reportModel.geturunAdedi());

            ReportModel selectedProduct = mReportList.get(i);
            viewHolder.setData(selectedProduct, i);
        }
        catch (Exception ex){
            Toast.makeText(context,"Hata!",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return  mReportList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView urunAdi, urunAdedi;

        public ViewHolder(View itemView) {
            super(itemView);
            urunAdi = (TextView) itemView.findViewById(R.id.urunAdi);
            urunAdedi = (TextView) itemView.findViewById(R.id.urunAdedi);

        }

        public void setData(ReportModel selectedProduct, int position) {

            this.urunAdi.setText(selectedProduct.geturunAdi());
            this.urunAdedi.setText(selectedProduct.geturunAdedi());
        }

        @Override
        public void onClick(View view) {

        }
    }
}
