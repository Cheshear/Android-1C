package com.example.mariia.withoutdoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Policy;
import java.util.ArrayList;

public class UserPolicy extends AppCompatActivity {

    ListView mViewList;
    DatabaseReference db;
    ArrayAdapter mAdapter;
    ArrayList<InsurancePackage> insurancePackages;
    private String userID;
    private FirebaseUser currentUser;
    final String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);
        Intent intent = getIntent();
        insurancePackages = new ArrayList<>();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            userID = currentUser.getUid();
        } else {
            Log.e("error", "we haven't registred");
        }
        db= FirebaseDatabase.getInstance().getReference("policy/"+userID);
        mViewList = (ListView)findViewById(R.id.policy_list_view);
        FirebaseListAdapter<InsurancePackage> fireAdapter = new FirebaseListAdapter<InsurancePackage>
                (this,
                        InsurancePackage.class,
                        android.R.layout.simple_list_item_1,
                        db) {
            @Override
            protected void populateView(View v, InsurancePackage model, int position) {
                Log.wtf(TAG, model.getCompanyName());
                ((TextView) v.findViewById(android.R.id.text1)).setText(model.getName());
                insurancePackages.add(model);
            }
        };
        mViewList.setAdapter(fireAdapter);
        mViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                Intent intent = new Intent(UserPolicy.this , UserPolicyInformation.class);
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
