package com.example.mariia.withoutdoctor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StreamDownloadTask;

import java.util.ArrayList;

public class PackagesInCompany extends AppCompatActivity {

    ListView mViewList;
    DatabaseReference db;
    ArrayAdapter mAdapter;
    ArrayList<InsurancePackage> insurancePackages;
    final String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        insurancePackages = new ArrayList<>();
        final String message = intent.getStringExtra("company_name");
        db= FirebaseDatabase.getInstance().getReference("packages");
        setContentView(R.layout.activity_packages_in_company);
        mViewList = (ListView)findViewById(R.id.list_view);
        FirebaseListAdapter<InsurancePackage> fireAdapter = new FirebaseListAdapter<InsurancePackage>
                (this,
                        InsurancePackage.class,
                        android.R.layout.simple_list_item_1,
                        db) {
            @Override
            protected void populateView(View v, InsurancePackage model, int position) {
                Log.wtf(TAG, model.getCompanyName());
                Log.wtf(TAG, message);
                if(model.getCompanyName().equals(message)) {
                    ((TextView) v.findViewById(android.R.id.text1)).setText(model.getName());
                    insurancePackages.add(model);
                }
                Log.wtf(TAG ,model.getName());
            }
        };
        mViewList.setAdapter(fireAdapter);
        mViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                Intent intent = new Intent(PackagesInCompany.this , BuyPackage.class);
                InsurancePackage message = insurancePackages.get(position);
                intent.putExtra("package_name", message.getName());
                intent.putExtra("package_cost", String.valueOf(message.getPrice()));
                intent.putExtra("package_description", message.getDescription());
                intent.putExtra("company_id", message.getInsuranceCompanyID());
                intent.putExtra("company_name", message.getCompanyName());
                startActivity(intent);
            }
        });
    }



}
