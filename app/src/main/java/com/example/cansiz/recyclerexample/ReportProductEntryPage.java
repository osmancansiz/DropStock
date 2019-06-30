package com.example.cansiz.recyclerexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReportProductEntryPage extends Activity {

    private List<ReportModel> reportList = new ArrayList<ReportModel>();
    final Database db = new Database(ReportProductEntryPage.this);
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_product_entry_page);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewEntryReport);

        reportList.addAll(db.reportEntryListItems());
        ReportAdapter reportAdapter = new ReportAdapter(this,reportList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(reportAdapter);
    }
}
